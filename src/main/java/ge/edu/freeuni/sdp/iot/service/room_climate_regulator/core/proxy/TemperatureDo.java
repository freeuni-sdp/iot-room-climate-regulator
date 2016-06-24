package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemperatureDo {

    @JsonProperty(value = "house_id", required = false)
    private String houseId;

    @JsonProperty(value = "floor_id", required = false)
    private String floorId;

    private Double temperature;

    public TemperatureDo(String houseId, String floorId, Double temperature) {
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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
