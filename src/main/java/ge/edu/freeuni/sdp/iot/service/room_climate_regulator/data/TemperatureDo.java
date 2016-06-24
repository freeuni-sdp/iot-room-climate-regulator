package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemperatureDo {

    @JsonProperty(value = "house_id", required = false)
    private String houseId;

    @JsonProperty(value = "floor_id", required = false)
    private String floorId;

    private Integer temperature;

    public TemperatureDo(String houseId, String floorId, Integer temperature) {
        this.houseId = houseId;
        this.floorId = floorId;
        this.temperature = temperature;
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

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }
}
