package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.worker;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.model.Task;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy.ProxyFactory;

import java.util.Set;

public class RoomClimateRegulator implements Runnable {

    private Set<Task> tasks;
    private ProxyFactory proxyFactory;

    public RoomClimateRegulator(Set<Task> tasks, ProxyFactory proxyFactory) {
        this.tasks = tasks;
        this.proxyFactory = proxyFactory;
    }

    @Override
    public void run() {
        for (Task task : tasks)
            processTask(task);
    }

    private void processTask(Task task) {
        // TODO add task proccessing
    }
}
