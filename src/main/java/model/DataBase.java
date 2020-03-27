package model;

import data.GenreEntity;
import data.TrackEntity;

import java.util.List;

public class DataBase {
    private GenreDAO genreDAO;
    private TrackDAO trackDAO;

    public void setGenreDAO(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public void addTrack(TrackEntity tracks) {
        trackDAO.addTrack(tracks);
    }

    public void addGenre(GenreEntity genre) {
        genreDAO.addGenre(genre);
    }

    public void deleteTrack(int id) {
        trackDAO.deleteTrack(id);
    }

    public void deleteGenre(int id) {
        genreDAO.deleteGenre(id);
    }

    public TrackEntity getTrack(int id) {
        return trackDAO.getTrack(id);
    }

    public GenreEntity getGenre(int id) {
        return genreDAO.getGenre(id);
    }

    public void updateTrack(TrackEntity track) {
        trackDAO.updateTrack(track);
    }

    public void updateGenre(GenreEntity genre) {
        genreDAO.updateGenre(genre);
    }

    public List<TrackEntity> searchTrack(String title) {
        return trackDAO.searchTrack(title);
    }

    public GenreEntity searchGenre(String title) {
        return genreDAO.searchGenre(title);
    }

    public List<GenreEntity> getAllGenres() {
        return genreDAO.getAllGenres();
    }

    public List<TrackEntity> getAllTracks() {
        return trackDAO.getAllTracks();
    }

    public List<TrackEntity> searchTrackByGenre(String title) {
        return trackDAO.searchTracksByGenre(title);
    }

    public List<TrackEntity> searchTrackByGenre(int id) {
        return trackDAO.searchTracksByGenre(id);
    }
}
