package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.ServiceState;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class RoomThermometerProxy {

    private static final String REAL_ROOM_THERMOMETER_API =
            "https://iot-room-thermometer.herokuapp.com/webapi";
    private static final String DEV_ROOM_THERMOMETER_API =
            "http://private-87c1d9-iotroomthermometer.apiary-mock.com/webapi";

    private final Client client;
    private final ServiceState state;

    public RoomThermometerProxy(ServiceState state) {
        this.state = state;
        ClientConfig config = new ClientConfig().register(JacksonFeature.class);
        this.client = ClientBuilder.newClient(config);
    }

    private String getApiUrl() {
        String apiUrl= null;

        if (state == ServiceState.DEV)
            apiUrl = DEV_ROOM_THERMOMETER_API;
        else if (state == ServiceState.REAL)
            apiUrl = REAL_ROOM_THERMOMETER_API;

        return apiUrl;
    }

    public TemperatureDo get(String houseId, String floorId) {
        String requestUrl = String.format("%s/houses/%s/floors/%s", getApiUrl(), houseId, floorId);
        Response response = client.target(requestUrl).request().get();

        TemperatureDo ret = null;
        if (ResponseUtils.is200(response))
            ret = response.readEntity(TemperatureDo.class);

        return ret;
    }
}
