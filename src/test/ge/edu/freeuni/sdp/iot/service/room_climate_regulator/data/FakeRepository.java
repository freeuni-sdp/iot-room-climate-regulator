package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.model.Task;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FakeRepository extends InMemoryRepository {

    private static FakeRepository instance;

    protected FakeRepository(Set<Task> memo) {
        super(memo);
    }

    public static FakeRepository instance() {
        if (instance == null)
            instance = new FakeRepository(new HashSet<Task>());
        return instance;
    }

    public boolean contains(Task task) {
        boolean ret = false;
        for (Task t : memo)
            if (t.equals(task))
                ret = true;
        return ret;
    }

    public void clear() {
        memo.clear();
    }
}
