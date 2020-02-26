
import data.GenreEntity;
import data.TracksEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import utils.HibernateSessionFactory;

public class Main {
    public static void main(String[] args)
    {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        GenreEntity genre1 = new GenreEntity();
        genre1.setName("Damp");
        session.save(genre1);
        session.getTransaction().commit();
        session.close();

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
