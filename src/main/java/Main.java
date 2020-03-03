
import data.GenreEntity;
import data.TracksEntity;
import model.GenreDAO;
import model.TrackDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import utils.HibernateSessionFactory;

import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        GenreDAO Dao = new GenreDAO();
        TrackDAO DaoT = new TrackDAO();
        List test =  DaoT.getTracksByGenre(2);

       /* TracksEntity tracksEntity1 = new TracksEntity();
        tracksEntity1.setGenreByIdGenre(genreEntity);
        tracksEntity1.setDuration(55);
        tracksEntity1.setPerformer("Lala lent");
        tracksEntity1.setTitle("bomba");
        tracksEntity1.setAlbum("Polka");
        DaoT.addTrack(tracksEntity1);

        */

       //  GenreEntity ss = (GenreEntity) Dao.searchGenre("Rock");
        int i = 5;


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
