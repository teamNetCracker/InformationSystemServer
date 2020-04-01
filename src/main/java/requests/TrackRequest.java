package requests;


import com.google.inject.Inject;
import data.GenreDataObject;
import data.TrackDataObject;
import data.TrackEntity;
import model.DataBase;
import model.DataBaseInterface;
import model.TrackDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/track")
public class TrackRequest {

    @Inject
    private DataBaseInterface dataBase;

    @GET
    @Path("/getTrack/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TrackDataObject getTrack(@PathParam("id") String id) {
        return dataBase.getTrack(Integer.parseInt(id));
    }

    @POST
    @Path("/addTrack")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createTrackInJSON(TrackDataObject track) {
        dataBase.addTrack(track);
    }

    @DELETE
    @Path("/deleteTrack/{id}")
    public String deleteTrack(@PathParam("id") String id) {
        return "Delete " + id;
    }


    public void setDataBase(DataBaseInterface dataBase) {
        this.dataBase = dataBase;
    }
}

