package requests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path("/Track")
public class TrackRequest {

    @GET
    @Path("/addTrack")
    public Response getMsg(@PathParam("param") String msg) {

        return Response.status(200).build();
    }
}

