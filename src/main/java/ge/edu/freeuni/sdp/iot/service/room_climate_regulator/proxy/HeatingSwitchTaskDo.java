package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.proxy;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeatingSwitchTaskDo {

    private Integer period;

    public HeatingSwitchTaskDo() {
    }

    public HeatingSwitchTaskDo(Integer period) {
        this.period = period;
    }


    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
