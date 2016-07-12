package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.model.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository implements Repository {

    private static InMemoryRepository instance;

    protected Map<Integer, Task> memo;

    protected InMemoryRepository(Map<Integer, Task> memo) {
        this.memo = memo;
    }

    public synchronized static InMemoryRepository instance() {
        if (instance == null)
            instance = new InMemoryRepository(Collections.synchronizedMap(new HashMap<Integer, Task>()));
        return instance;
    }

    @Override
    public void insertOrUpdate(Task task) {
        memo.put(task.hashCode(), task);
    }

    @Override
    public Iterable<Task> getAll() {
        return memo.values();
    }

    @Override
    public synchronized void removeAll(Collection<Task> tasks) {
        for (Task task : tasks)
            memo.remove(task.hashCode());
    }
}
