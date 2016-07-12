package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.service;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data.Repository;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.data.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.model.Task;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.proxy.ProxyFactory;

import javax.servlet.ServletContext;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/houses/{house_id}/floors/{floor_id}/task")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoomClimateRegulatorService {

    @PUT
    public Response update(@PathParam("house_id") String houseId, @PathParam("floor_id") String floorId,
                           @NotNull RoomClimateRegulatorTaskDo taskDo, @Context ServletContext sc) {
        if (!getProxyFactory().getHouseRegistryService().get(houseId))
            throw new NotFoundException();

        Task task = getTask(houseId, floorId, taskDo);

        getRepository().insertOrUpdate(task);

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
        return ProxyFactory.getProxyFactory();
    }

    protected Repository getRepository() {
        return RepositoryFactory.create();
    }

}
