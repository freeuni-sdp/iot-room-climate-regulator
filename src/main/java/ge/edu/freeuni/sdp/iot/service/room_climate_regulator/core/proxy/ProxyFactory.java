package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.model.ServiceState;

public class ProxyFactory {

    protected ServiceState getServiceState() {
        return ServiceState.DEV;
    }

    public AirConditioningSwitchProxy getAirConditioningSwitch() {
        return new AirConditioningSwitchProxy(getServiceState());
    }

    public HeatingSwitchProxy getHeatingSwitch() {
        return new HeatingSwitchProxy(getServiceState());
    }

    public HouseRegistryServiceProxy getHouseRegistryService() {
        return new HouseRegistryServiceProxy(getServiceState());
    }

    public HumanAtHomeServiceProxy getHumanAtHomeService() {
        return new HumanAtHomeServiceProxy(getServiceState());
    }

    public RoomThermometerProxy getRoomThermometer() {
        return new RoomThermometerProxy(getServiceState());
    }
}
