package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.model.Task;

import java.util.*;

public class FakeRepository extends InMemoryRepository {

    private static FakeRepository instance;

    protected FakeRepository(Map<Integer, Task> memo) {
        super(memo);
    }

    public static FakeRepository instance() {
        if (instance == null)
            instance = new FakeRepository(new HashMap<Integer, Task>());
        return instance;
    }

    public boolean contains(Task task) {
        return memo.containsKey(task.hashCode()) && memo.get(task.hashCode()).equals(task);
    }

    public void clear() {
        memo.clear();
    }
}
