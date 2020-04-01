package model;

import data.GenreDataObject;
import data.TrackDataObject;

import java.util.List;

public interface DataBaseInterface {
    void addTrack(TrackDataObject track);

    void addGenre(GenreDataObject genre);

    void deleteTrack(int id);

    void deleteGenre(int id);

    TrackDataObject getTrack(int id);

    GenreDataObject getGenre(int id);

    void updateTrack(TrackDataObject track);

    void updateGenre(GenreDataObject genre);

    List<TrackDataObject> searchTrack(String title);

    GenreDataObject searchGenre(String title);

    List<GenreDataObject> getAllGenres();

    List<TrackDataObject> getAllTracks();

    List<TrackDataObject> searchTrackByGenre(String title);

    List<TrackDataObject> searchTrackByGenre(int id);
}
