package model;

import data.GenreEntity;
import data.TrackDataObject;
import data.TrackEntity;

import java.util.List;

public class DataBase {
    private GenreDAO genreDAO;
    private TrackDAO trackDAO;

    public DataBase()
    {
        this.trackDAO = new TrackDAO();
        this.genreDAO = new GenreDAO();
    }
    private TrackDataObject transformToDTO(TrackEntity track)
    {
        GenreEntity genreEntity = track.getGenreByIdGenre();
        GenreDataObject genre = new GenreDataObject(Integer.toString(genreEntity.getIdGenre()), genreEntity.getName());
        return new TrackDataObject(Integer.toString(track.getIdTracks()), track.getTitle(), track.getPerformer(),
                track.getAlbum(), genre, track.getDuration());
    }
    private TrackEntity transformToTrackEntity(TrackDataObject track)
    {
        GenreDataObject genreDO = track.getGenre();
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setName(genreDO.getTitle());
        genreEntity.setIdGenre(Integer.parseInt(genreDO.getId()));
        TrackEntity trackEntity = new TrackEntity();
        if (track.getId() != null) {
            trackEntity.setIdTracks(Integer.parseInt(track.getId()));
        }
        trackEntity.setTitle(track.getTitle());
        trackEntity.setPerformer(track.getPerformer());
        trackEntity.setAlbum(track.getAlbum());
        trackEntity.setDuration(track.getDuration());
        trackEntity.setGenreByIdGenre(genreEntity);
        return trackEntity;
    }
    private GenreDataObject transformToGTO(GenreEntity genre)
    {
        return new GenreDataObject(Integer.toString(genre.getIdGenre()), genre.getName());
    }
    private GenreEntity transformToGenreEntity(GenreDataObject genre)
    {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setIdGenre(Integer.parseInt(genre.getId()));
        genreEntity.setName(genre.getTitle());
        return genreEntity;
    }
    public void addTrack(TrackDataObject track)
    {
       TrackEntity trackEntity =  this.transformToTrackEntity(track);
        trackDAO.addTrack(trackEntity);
    }

    public void addGenre(GenreDataObject genre)
    {
        GenreEntity genreEntity = this.transformToGenreEntity(genre);
        genreDAO.addGenre(genreEntity);
    }

    public void deleteTrack(int id) { trackDAO.deleteTrack(id); }

    public void deleteGenre(int id) {
        genreDAO.deleteGenre(id);
    }

    public TrackDataObject getTrack(int id) {
        TrackEntity trackEntity = trackDAO.getTrack(id);
        return this.transformToDTO(trackEntity);
    }

    public GenreDataObject getGenre(int id) {
        GenreEntity genreEntity = genreDAO.getGenre(id);
        return this.transformToGTO(genreEntity);
    }

    public void updateTrack(TrackDataObject track)
    {
        TrackEntity trackEntity = this.transformToTrackEntity(track);
        trackDAO.updateTrack(trackEntity);
    }

    public void updateGenre(GenreDataObject genre) {
        GenreEntity genreEntity = this.transformToGenreEntity(genre);
        genreDAO.updateGenre(genreEntity);
    }

    public List<TrackDataObject> searchTrack(String title) {

        List<TrackEntity> entityList =  trackDAO.searchTrack(title);
        List<TrackDataObject> trackList = new LinkedList<>();
        entityList.forEach(trackEntity ->
        {
            trackList.add(this.transformToDTO(trackEntity));
        });
        return trackList;
    }

    public GenreDataObject searchGenre(String title)
    {
        GenreEntity genreEntity = genreDAO.searchGenre(title);
        return this.transformToGTO(genreEntity);
    }

    public List<GenreDataObject> getAllGenres() {

        List<GenreEntity> entityList = genreDAO.getAllGenres();
        List<GenreDataObject> genreList = new LinkedList<>();
        entityList.forEach(genreEntity ->
        {
            genreList.add(this.transformToGTO(genreEntity));
        });
        return genreList;
    }

    public List<TrackDataObject> getAllTracks() {
        List<TrackEntity> entityList = trackDAO.getAllTracks();
        List<TrackDataObject> trackList = new LinkedList<>();
        entityList.forEach(track ->
        {
            trackList.add(this.transformToDTO(track));
        });
        return trackList;
    }

    public List<TrackDataObject> searchTrackByGenre(String title)
    {
        List<TrackEntity> entityList = trackDAO.searchTracksByGenre(title);
        List<TrackDataObject> trackList = new LinkedList<>();
        entityList.forEach(track ->
        {
            trackList.add(this.transformToDTO(track));
        });
        return trackList;
    }

    public List<TrackDataObject> searchTrackByGenre(int id) {
        List<TrackEntity> entityList = trackDAO.searchTracksByGenre(id);
        List<TrackDataObject> trackList = new LinkedList<>();
        entityList.forEach(track ->
        {
            trackList.add(this.transformToDTO(track));
        });
        return trackList;
    }
}
