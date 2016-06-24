package ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.service;

import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.ServiceState;
import ge.edu.freeuni.sdp.iot.service.room_climate_regulator.core.proxy.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/houses/{house_id}/floors/{floor_id}/task")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoomClimateRegulatorService {

    @PUT
    public Response update(@PathParam("house_id") String houseId, @PathParam("floor_id") String floorId,
                           @NotNull RoomClimateRegulatorTaskDo task) {
        // TODO add real implementation.
        return Response.ok().build();
    }

    protected ProxyFactory getProxyFactory() {
        return new ProxyFactory();
    }

}
