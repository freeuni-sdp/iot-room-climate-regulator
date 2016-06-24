package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.service;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomClimateRegulatorTaskDo {

    private Double temperature;

    private Integer period;

    public RoomClimateRegulatorTaskDo() {

    }

    public RoomClimateRegulatorTaskDo(Double temperature, Integer period) {
        this.temperature = temperature;
        this.period = period;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
