package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirConditioningSwitchTaskDo {

    @JsonProperty(value = "set_status")
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
