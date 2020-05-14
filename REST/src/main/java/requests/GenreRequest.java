package requests;

import com.google.inject.Inject;
import data.GenreDataObject;
import db.DataBase;
import db.DataBaseInterface;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/genre")
public class GenreRequest {

    @Inject
    private DataBaseInterface dataBase;

    public GenreRequest() {
        this.dataBase = new DataBase();
    }

    @GET
    @Path("/getGenre/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GenreDataObject getGenre(@PathParam("id") String id) {
        return dataBase.getGenre(Integer.parseInt(id));
    }

    @POST
    @Path("/addGenre")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createGenreInJSON(GenreDataObject genreDataObject) {
        dataBase.addGenre(genreDataObject);
        return true;
    }

    @DELETE
    @Path("/deleteGenre/{id}")
    public boolean deleteGenre(@PathParam("id") String id) {
        dataBase.deleteGenre(Integer.parseInt(id));
        return true;
    }

    @PUT
    @Path("/updateGenre")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateGenre(GenreDataObject genreDataObject) {
        dataBase.updateGenre(genreDataObject);
        return true;
    }

    @GET
    @Path("/getAllGenres")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GenreDataObject> getAllGenres() {
        return dataBase.getAllGenres();
    }

    @GET
    @Path("/searchGenre/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public GenreDataObject searchGenre(@PathParam("title") String title) {
        return dataBase.searchGenre(title);
    }

    public void setDataBase(DataBaseInterface dataBase) {
        this.dataBase = dataBase;
    }
}
