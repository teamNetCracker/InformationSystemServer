
import data.GenreDataObject;
import data.GenreEntity;
import data.TrackDataObject;
import model.DataBase;
import model.GenreDAO;
import model.TrackDAO;
import requests.TrackRequest;

public class Main {
    public static void main(String[] args)
    {
        GenreDAO Dao = new GenreDAO();
        TrackDAO DaoT = new TrackDAO();
        DataBase dataBase = new DataBase();
        TrackDataObject trackDataObject = new TrackDataObject("20","lola","kot","sos",new GenreDataObject("11","Rock"),44);
        dataBase.addTrack(trackDataObject);
        TrackRequest trackRequest = new TrackRequest();
        trackRequest.setDataBase(dataBase);




       /* TracksEntity tracksEntity1 = new TracksEntity();
        tracksEntity1.setGenreByIdGenre(genreEntity);
        tracksEntity1.setDuration(55);
        tracksEntity1.setPerformer("Lala lent");
        tracksEntity1.setTitle("bomba");
        tracksEntity1.setAlbum("Polka");
        DaoT.addTrack(tracksEntity1);

        */

       //  GenreEntity ss = (GenreEntity) Dao.searchGenre("Rock");



       /* try {
            DataReset d = new DataReset();

            TrackModel trackModel = new TrackModel(args[0]);
            GenreModel genreModel = new GenreModel(args[1]);

            ServerEP serverEP = new ServerEP(genreModel, trackModel);
             genreModel.subscribe(serverEP);
            trackModel.subscribe(serverEP);
            serverEP.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        */

    }
}
