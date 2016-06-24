package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.model;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.service.RoomClimateRegulatorTaskDo;

public class Task {

    private String houseId;

    private String floorId;

    private double temperature;

    private int period;

    private long startTime;

    public Task() {
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public static Task fromDo(RoomClimateRegulatorTaskDo taskDo) {
        Task ret = new Task();
        ret.setPeriod(taskDo.getPeriod());
        ret.setTemperature(taskDo.getTemperature());

        return ret;
    }
}
