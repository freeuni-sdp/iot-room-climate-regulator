package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.worker;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.model.InvalidTaskException;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.model.Task;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy.AirConditioningSwitchTaskDo;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy.HeatingSwitchTaskDo;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy.ProxyFactory;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy.TemperatureDo;

import java.util.HashSet;
import java.util.Set;

public class RoomClimateRegulator implements Runnable {

    private static final double TEMP_EPS = 1;
    private static final int HEATING_PERIOD = 90;
    private static final double TEMP_THRESHOLD = 18;
    private static final double AIR_COND_TEMP_THRESHOLD = 5;

    private Set<Task> tasks;
    private ProxyFactory proxyFactory;

    public RoomClimateRegulator(Set<Task> tasks, ProxyFactory proxyFactory) {
        this.tasks = tasks;
        this.proxyFactory = proxyFactory;
    }

    @Override
    public void run() {
        Set<Task> tasksToRemove = new HashSet<>();

        for (Task task : tasks) {
            try {
                processTask(task);
            } catch (InvalidTaskException ex) {
                tasksToRemove.add(task);
            }
        }

        tasks.removeAll(tasksToRemove);
    }

    private void processTask(Task task) throws InvalidTaskException {
        if (!task.isAlive() && task.isStarted())
            throw new InvalidTaskException();

        double currentTemperature = getCurrentTemperature(task.getHouseId(), task.getFloorId());

        if (Math.abs(currentTemperature - task.getTemperature()) <= TEMP_EPS)
            throw new InvalidTaskException();
        else if (currentTemperature < task.getTemperature()) {
            stopAirConditioning(task.getHouseId());
            startHeating(task.getHouseId(), task.getFloorId());
        } else {
            if (currentTemperature <= TEMP_THRESHOLD)
                throw new InvalidTaskException();
            stopHeating(task.getHouseId(), task.getFloorId());
            startAirConditioning(task.getHouseId(), task.getTemperature(), currentTemperature);
        }

        task.setStarted(true);
    }

    private void startAirConditioning(String houseId, double desiredTemperature, double currentTemperature) {
        AirConditioningSwitchTaskDo taskDo = AirConditioningSwitchTaskDo.ventilate();
        if (proxyFactory.getHumanAtHomeService().get(houseId))
            taskDo = Math.abs(currentTemperature - desiredTemperature) < AIR_COND_TEMP_THRESHOLD ?
                    AirConditioningSwitchTaskDo.volumeOne() : AirConditioningSwitchTaskDo.volumeTwo();
        proxyFactory.getAirConditioningSwitch().create(houseId, taskDo);
    }

    private void stopAirConditioning(String houseId) {
        proxyFactory.getAirConditioningSwitch().create(houseId, AirConditioningSwitchTaskDo.stop());
    }

    private void startHeating(String houseId, String floorId) throws InvalidTaskException {
        if (!proxyFactory.getHeatingSwitch().update(houseId, floorId, new HeatingSwitchTaskDo(HEATING_PERIOD)))
            throw new InvalidTaskException();
    }

    private void stopHeating(String houseId, String floorId) throws InvalidTaskException {
        if (!proxyFactory.getHeatingSwitch().delete(houseId, floorId))
            throw new InvalidTaskException();
    }


    private double getCurrentTemperature(String houseId, String floorId) throws InvalidTaskException {
        TemperatureDo temperatureDo = proxyFactory.getRoomThermometer().get(houseId, floorId);

        if (temperatureDo == null)
            throw new InvalidTaskException();

        return temperatureDo.getTemperature();
    }
}
