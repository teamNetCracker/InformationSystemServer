package requests;


import com.google.inject.Inject;
import data.GenreDataObject;
import data.TrackDataObject;
import db.DataBase;
import db.DataBaseInterface;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/track")
public class TrackRequest {

    @Inject
    private DataBaseInterface dataBase;

    public TrackRequest()
    {
        this.dataBase = new DataBase();
    }

    @GET
    @Path("/getTrack/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TrackDataObject getTrack(@PathParam("id") String id) {
        return dataBase.getTrack(Integer.parseInt(id));
    }


    @POST
    @Path("/addTrack")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createTrackInJSON(TrackDataObject track) {
        dataBase.addTrack(track);
        return true;
    }

    @DELETE
    @Path("/deleteTrack/{id}")
    public boolean deleteTrack(@PathParam("id") String id) {
        dataBase.deleteTrack(Integer.parseInt(id));
        return true;
    }

    @PUT
    @Path("/updateTrack")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateTrack(TrackDataObject trackDataObject) {
        dataBase.updateTrack(trackDataObject);
        return true;
    }

    @GET
    @Path("/searchTrack/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrackDataObject> searchTrack(@PathParam("title") String title) {
        return dataBase.searchTrack(title);
    }

    @GET
    @Path("/getAllTracks")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrackDataObject> getAllTracks() {
        return dataBase.getAllTracks();
    }

    @GET
    @Path("/searchTrackByGenreTitle/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrackDataObject> searchTrackByGenreTitle(@PathParam("title") String title) {
        return dataBase.searchTrackByGenre(title);
    }

    @GET
    @Path("/searchTrackByGenreId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrackDataObject> searchTrackByGenreId(@PathParam("id") String id) {
        return dataBase.searchTrackByGenre(Integer.parseInt(id));
    }


    public void setDataBase(DataBaseInterface dataBase) {
        this.dataBase = dataBase;
    }
}

