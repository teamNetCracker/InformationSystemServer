package requests;


import data.GenreDataObject;
import data.TrackDataObject;
import data.TrackEntity;
import model.DataBase;
import model.TrackDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/track")
public class TrackRequest {


    /*private DataBase dataBase;

    public TrackRequest(DataBase dataBase) {
        this.dataBase = dataBase;
    }*/

    @GET
    @Path("/getTrack/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TrackDataObject getTrack(@PathParam("id") String id) {

        String output = "Get track request " + id;
        //TrackEntity trackEntity = dataBase.getTrack(Integer.parseInt(id));
        return new TrackDataObject("3","234","23","5555",new GenreDataObject("rock"),23);
    }

    @POST
    @Path("/addTrack")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(TrackDataObject track) {

        String result = "Track saved : " + track;
        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("/deleteTrack/{id}")
    public void deleteTrack(@PathParam("id") String id) {

    }



}

