package server;

import data.GenreDataObject;
import data.TrackDataObject;

import java.io.*;
import java.util.List;

public class SaveLoadService {

    public SaveLoadService() {
    }

    public void saveTrack(File file, List<TrackDataObject> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException ex) {

        }
    }

    public void saveGenre(File file, List<GenreDataObject> obj) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException ex) {

        }
    }

    public List<TrackDataObject> loadTracks(File file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<TrackDataObject>) in.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<GenreDataObject> loadGenres(File file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<GenreDataObject>) in.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
