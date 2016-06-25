package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.proxy;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirConditioningSwitchTaskDo {

    private String status;

    public AirConditioningSwitchTaskDo() {
    }

    public AirConditioningSwitchTaskDo(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static AirConditioningSwitchTaskDo stop() {
        return new AirConditioningSwitchTaskDo("0");
    }

    public static AirConditioningSwitchTaskDo volumeOne() {
        return new AirConditioningSwitchTaskDo("*");
    }

    public static AirConditioningSwitchTaskDo volumeTwo() {
        return new AirConditioningSwitchTaskDo("**");
    }

    public static AirConditioningSwitchTaskDo ventilate() {
        return new AirConditioningSwitchTaskDo("#");
    }
}
