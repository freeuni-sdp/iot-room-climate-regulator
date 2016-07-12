package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.proxy;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.worker.RoomClimateRegulator;

public class FakeProxyFactory extends ProxyFactory {

    private static FakeProxyFactory instance;

    private AirConditioningSwitchProxy airConditioningSwitchProxy;
    private HeatingSwitchProxy heatingSwitchProxy;
    private HouseRegistryServiceProxy houseRegistryServiceProxy;
    private HumanAtHomeServiceProxy humanAtHomeServiceProxy;
    private RoomThermometerProxy roomThermometerProxy;

    public static FakeProxyFactory instance() {
        if (instance == null)
            instance = new FakeProxyFactory();
        return instance;
    }

    @Override
    public AirConditioningSwitchProxy getAirConditioningSwitch() {
        return airConditioningSwitchProxy;
    }

    @Override
    public HeatingSwitchProxy getHeatingSwitch() {
        return heatingSwitchProxy;
    }

    @Override
    public HouseRegistryServiceProxy getHouseRegistryService() {
        return houseRegistryServiceProxy;
    }

    @Override
    public HumanAtHomeServiceProxy getHumanAtHomeService() {
        return humanAtHomeServiceProxy;
    }

    @Override
    public RoomThermometerProxy getRoomThermometer() {
        return roomThermometerProxy;
    }

    public void setAirConditioningSwitchProxy(AirConditioningSwitchProxy airConditioningSwitchProxy) {
        this.airConditioningSwitchProxy = airConditioningSwitchProxy;
    }

    public void setHeatingSwitchProxy(HeatingSwitchProxy heatingSwitchProxy) {
        this.heatingSwitchProxy = heatingSwitchProxy;
    }

    public void setHouseRegistryServiceProxy(HouseRegistryServiceProxy houseRegistryServiceProxy) {
        this.houseRegistryServiceProxy = houseRegistryServiceProxy;
    }

    public void setHumanAtHomeServiceProxy(HumanAtHomeServiceProxy humanAtHomeServiceProxy) {
        this.humanAtHomeServiceProxy = humanAtHomeServiceProxy;
    }

    public void setRoomThermometerProxy(RoomThermometerProxy roomThermometerProxy) {
        this.roomThermometerProxy = roomThermometerProxy;
    }
}
