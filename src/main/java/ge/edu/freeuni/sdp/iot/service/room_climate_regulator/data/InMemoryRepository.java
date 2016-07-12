package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.model.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class InMemoryRepository implements Repository {

    private static InMemoryRepository instance;

    protected Set<Task> memo;

    protected InMemoryRepository(Set<Task> memo) {
        this.memo = memo;
    }

    public synchronized static InMemoryRepository instance() {
        if (instance == null)
            instance = new InMemoryRepository(Collections.synchronizedSet(new HashSet<Task>()));
        return instance;
    }

    @Override
    public void insertOrUpdate(Task task) {
        memo.add(task);
    }

    @Override
    public Iterable<Task> getAll() {
        return memo;
    }

    @Override
    public void removeAll(Collection<Task> tasks) {
        memo.removeAll(tasks);
    }
}
