package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.model;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.service.RoomClimateRegulatorTaskDo;

import java.util.Objects;

public class Task {

    private String houseId;

    private String floorId;

    private double temperature;

    private int period;

    private long startTime;

    private boolean started;

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

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public static Task fromDo(RoomClimateRegulatorTaskDo taskDo) {
        Task ret = new Task();
        ret.setPeriod(taskDo.getPeriod());
        ret.setTemperature(taskDo.getTemperature());

        return ret;
    }

    public boolean isAlive() {
        return System.currentTimeMillis() - startTime <= (long) period;
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseId, floorId);
    }

    @Override
    public String toString() {
        return "Task{" +
                "houseId='" + houseId + '\'' +
                ", floorId='" + floorId + '\'' +
                ", temperature=" + temperature +
                ", period=" + period +
                ", startTime=" + startTime +
                '}';
    }
}
