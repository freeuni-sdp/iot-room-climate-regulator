package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.proxy;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.model.ServiceState;

public class ProxyFactory {

    public static ProxyFactory getProxyFactory() {
        return new ProxyFactory();
    }

    protected ServiceState getServiceState() {
        return ServiceState.REAL;
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
