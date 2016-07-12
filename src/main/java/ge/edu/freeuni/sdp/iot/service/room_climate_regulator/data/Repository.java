package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.model.Task;

import java.util.Collection;

public interface Repository {

    void insertOrUpdate(Task task);

    Iterable<Task> getAll();

    void removeAll(Collection<Task> tasks);

}
