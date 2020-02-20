package main.java;

import main.java.data.DataReset;
import main.java.model.GenreModel;
import main.java.model.TrackModel;
import main.java.server.ServerEP;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try {
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
    }
}
