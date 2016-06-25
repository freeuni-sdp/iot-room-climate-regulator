package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.proxy;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.model.ServiceState;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class AirConditioningSwitchProxy {

    private static final String REAL_AIR_CONDITIONING_SWITCH_API =
            "https://iot-air-conditioning-switch.herokuapp.com/webapi";
    private static final String DEV_AIR_CONDITIONING_SWITCH_API =
            "http://private-23d81-airconditioningswitch.apiary-mock.com/webapi";

    private final Client client;
    private final ServiceState state;

    public AirConditioningSwitchProxy(ServiceState state) {
        this.state = state;
        ClientConfig config = new ClientConfig().register(JacksonFeature.class);
        this.client = ClientBuilder.newClient(config);
    }

    private String getApiUrl() {
        String apiUrl= null;

        if (state == ServiceState.DEV)
            apiUrl = DEV_AIR_CONDITIONING_SWITCH_API;
        else if (state == ServiceState.REAL)
            apiUrl = REAL_AIR_CONDITIONING_SWITCH_API;

        return apiUrl;
    }

    public void create(String houseId, AirConditioningSwitchTaskDo task) {
        String requestUrl = String.format("%s/houses/%s", getApiUrl(), houseId);
        client.target(requestUrl)
                .request()
                .post(Entity.entity(task, MediaType.APPLICATION_JSON_TYPE));
    }

}
