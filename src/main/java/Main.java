
import data.GenreEntity;
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
        dataBase.setTrackDAO(DaoT);
        dataBase.setGenreDAO(Dao);
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setName("Rock");
        dataBase.addGenre(genreEntity);
        TrackRequest trackRequest = new TrackRequest(dataBase);



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
