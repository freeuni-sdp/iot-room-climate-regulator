package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.model.ServiceState;

public class HumanAtHomeServiceProxy {

    private ServiceState state;

    public HumanAtHomeServiceProxy(ServiceState state) {
        this.state = state;
    }

    public boolean get(String houseId) {
        return false;
    }
}
