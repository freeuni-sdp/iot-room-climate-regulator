package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.service;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data.FakeRepository;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data.Repository;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.proxy.FakeProxyFactory;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.proxy.ProxyFactory;

public class FakeRoomClimateRegulatorService extends RoomClimateRegulatorService {

    @Override
    protected ProxyFactory getProxyFactory() {
        return FakeProxyFactory.instance();
    }

    @Override
    protected Repository getRepository() {
        return FakeRepository.instance();
    }

}
