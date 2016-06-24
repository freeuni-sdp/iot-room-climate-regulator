package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.service;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.model.Task;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy.ProxyFactory;

import javax.servlet.ServletContext;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;


@Path("/houses/{house_id}/floors/{floor_id}/task")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoomClimateRegulatorService {

    private static final String TASK_POOL = "TaskPool";

    @PUT
    public Response update(@PathParam("house_id") String houseId, @PathParam("floor_id") String floorId,
                           @NotNull RoomClimateRegulatorTaskDo taskDo, @Context ServletContext sc) {
        if (!getProxyFactory().getHouseRegistryService().get(houseId))
            throw new WebApplicationException(404);

        Set<Task> tasks = (Set<Task>) sc.getAttribute(TASK_POOL);
        if (tasks == null)
            throw new WebApplicationException(503);

        Task task = getTask(houseId, floorId, taskDo);

        tasks.add(task);

        return Response.ok().build();
    }

    private Task getTask(String houseId, String floorId, RoomClimateRegulatorTaskDo taskDo) {
        Task task = Task.fromDo(taskDo);
        task.setHouseId(houseId);
        task.setFloorId(floorId);
        task.setStartTime(System.currentTimeMillis());
        task.setStarted(false);
        return task;
    }

    protected ProxyFactory getProxyFactory() {
        return new ProxyFactory();
    }

}
