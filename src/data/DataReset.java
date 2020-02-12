package data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class DataReset {
    public DataReset(){
        LinkedList<TrackDataObject> arrTrack = new LinkedList<>();
        LinkedList<GenreDataObject> arrGenre = new LinkedList<>();
        arrTrack.add(new TrackDataObject("1", "dsf", "df", "sd", new GenreDataObject("fds"), 1));
        arrGenre.add(new GenreDataObject("ROCK"));
        try {
            ObjectOutputStream tracksOut = new ObjectOutputStream(new FileOutputStream("tracks.txt"));
            tracksOut.writeObject(arrTrack);
            tracksOut.close();
            ObjectOutputStream genresOut = new ObjectOutputStream(new FileOutputStream("genres.txt"));
            genresOut.writeObject(arrGenre);
            genresOut.close();
            //tracksOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
