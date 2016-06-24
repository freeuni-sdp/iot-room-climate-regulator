package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirConditioningSwitchTaskDo {

    private String status;

    public AirConditioningSwitchTaskDo(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
