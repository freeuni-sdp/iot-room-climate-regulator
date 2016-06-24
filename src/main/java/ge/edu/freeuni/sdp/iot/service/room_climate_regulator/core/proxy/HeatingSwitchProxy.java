package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.model.ServiceState;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HeatingSwitchProxy {

    private static final String REAL_HEATING_SWITCH_API =
            "https://iot-heating-switch.herokuapp.com";
    private static final String DEV_HEATING_SWITCH_API =
            "http://private-e534c-iotheatingswitch.apiary-mock.com";

    private final Client client;
    private final ServiceState state;

    public HeatingSwitchProxy(ServiceState state) {
        this.state = state;
        ClientConfig config = new ClientConfig().register(JacksonFeature.class);
        this.client = ClientBuilder.newClient(config);
    }

    private String getApiUrl() {
        String apiUrl= null;

        if (state == ServiceState.DEV)
            apiUrl = DEV_HEATING_SWITCH_API;
        else if (state == ServiceState.REAL)
            apiUrl = REAL_HEATING_SWITCH_API;

        return apiUrl;
    }

    public boolean update(String houseId, String floorId, HeatingSwitchTaskDo task) {
        String requestUrl = String.format("%s/house/%s/floor/%s", getApiUrl(), houseId, floorId);
        Response response = client
                .target(requestUrl)
                .request()
                .put(Entity.entity(task, MediaType.APPLICATION_JSON_TYPE));

        return ResponseUtils.is200(response);
    }

    public boolean delete(String houseId, String floorId) {
        String requestUrl = String.format("%s/house/%s/floor/%s", getApiUrl(), houseId, floorId);
        Response response = client
                .target(requestUrl)
                .request()
                .delete();

        return ResponseUtils.is200(response);
    }

}
