package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data;

public class RepositoryFactory {

    public static Repository create() {
        return InMemoryRepository.instance();
    }

}
