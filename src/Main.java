import data.DataSaver;
import model.GenreModel;
import model.TrackModel;
import server.ServerEP;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try {
            //DataSaver d = new DataSaver();

            TrackModel trackModel = new TrackModel(args[0]);
            GenreModel genreModel = new GenreModel(args[1]);

            ServerEP serverEP = new ServerEP(genreModel, trackModel);
            serverEP.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
