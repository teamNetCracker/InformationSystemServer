
import dao.GenreDAO;
import dao.TrackDAO;
import db.DataBase;
import entity.GenreEntity;
import entity.TrackEntity;
import data.GenreDataObject;
import data.TrackDataObject;
import matchers.MatcherDTOwithTrackEntity;
import matchers.MatcherGTOwithGenreEntity;
import matchers.MatcherGenreEntityWithGTO;
import matchers.MatcherTrackEntityWithDTO;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.BDDMockito.given;


public class DataBaseTest
{
    @Mock
    private TrackDAO trackDAO;
    @Mock
    private GenreDAO genreDAO;

    private DataBase dataBase;

    public DataBaseTest()
    {
        MockitoAnnotations.initMocks(this);
        this.dataBase = new DataBase(trackDAO, genreDAO);
    }

    @Test
    public void checkGetGenre()
    {
        GenreEntity genre = new GenreEntity(1, "Rock");
        given(genreDAO.getGenre(1)).willReturn(genre);
        GenreDataObject genreDataObject = dataBase.getGenre(1);
        MatcherAssert.assertThat(genreDataObject, MatcherGTOwithGenreEntity.matcher(genre));
    }
    @Test
    public void checkGetTrack()
    {
        GenreEntity genreEntity = new GenreEntity(1, "Rock");
        TrackEntity trackEntity = new TrackEntity(1, "Dream", "Metallica",
                "Earth Dream", 132, genreEntity);
        given(trackDAO.getTrack(1)).willReturn(trackEntity);
        TrackDataObject trackDataObject = dataBase.getTrack(1);
        MatcherAssert.assertThat(trackDataObject, MatcherDTOwithTrackEntity.matcher(trackEntity));

    }
    @Test
    public void checkSearchTrack()
    {
        GenreEntity genreEntity = new GenreEntity(7, "Pop");
        TrackEntity trackEntity1 = new TrackEntity(8, "Uno", "Little Big",
                "Euro", 144, genreEntity);
        TrackEntity trackEntity2 = new TrackEntity(9, "Uno", "Snup Dog",
                "My Drass", 144, genreEntity);
        List<TrackEntity> list = new LinkedList<>();
        list.add(trackEntity1);
        list.add(trackEntity2);
        given(trackDAO.searchTrack("Uno")).willReturn(list);
        List<TrackDataObject> trackDataObjectList = dataBase.searchTrack("Uno");
        MatcherAssert.assertThat(trackDataObjectList.get(0), MatcherDTOwithTrackEntity.matcher(trackEntity1));
        MatcherAssert.assertThat(trackDataObjectList.get(1), MatcherDTOwithTrackEntity.matcher(trackEntity2));
    }
    @Test
    public void checkSearchGenre()
    {
        GenreEntity genreEntity = new GenreEntity(6, "Rap");
        given(genreDAO.searchGenre("Rap")).willReturn(genreEntity);
        GenreDataObject genreDataObject = dataBase.searchGenre("Rap");
        MatcherAssert.assertThat(genreDataObject, MatcherGTOwithGenreEntity.matcher(genreEntity));
    }
    @Test
    public void checkSearchTrackByGenre()
    {
        GenreEntity genre1 = new GenreEntity(6, "Rap");
        TrackEntity track1 = new TrackEntity(8, "Uno", "Little Big",
                "Euro", 144, genre1);
        List<TrackEntity> trackEntities = new LinkedList<TrackEntity>();
        trackEntities.add(track1);
        given(trackDAO.searchTracksByGenre("Rap")).willReturn(trackEntities);
        List<TrackDataObject> trackDataObjectList = dataBase.searchTrackByGenre("Rap");
        MatcherAssert.assertThat(trackDataObjectList.get(0), MatcherDTOwithTrackEntity.matcher(track1));
    }
    @Test
    public void checkAddTrack()
    {
        GenreDataObject genreDataObject = new GenreDataObject("2", "Rap");
        TrackDataObject trackDataObject = new TrackDataObject("1", "Uno", "Little Big",
                "Euro", genreDataObject, 124);
        dataBase.addTrack(trackDataObject);
        ArgumentCaptor<TrackEntity> trackCaptor = ArgumentCaptor.forClass(TrackEntity.class);
        Mockito.verify(trackDAO).addTrack(trackCaptor.capture());
       TrackEntity trackEntity = trackCaptor.getValue();
       MatcherAssert.assertThat(trackEntity, MatcherTrackEntityWithDTO.matcher(trackDataObject));
    }
    @Test
    public void checkAddGenre()
    {
        GenreDataObject genreDataObject = new GenreDataObject("2", "Rap");
        ArgumentCaptor<GenreEntity> genreCaptor = ArgumentCaptor.forClass(GenreEntity.class);
        dataBase.addGenre(genreDataObject);
        Mockito.verify(genreDAO).addGenre(genreCaptor.capture());
        GenreEntity genreEntity = genreCaptor.getValue();
        MatcherAssert.assertThat(genreEntity, MatcherGenreEntityWithGTO.matcher(genreDataObject));
    }
    @Test
    public void checkGetAllTracks()
    {
        GenreEntity genreEntity1 = new GenreEntity(7, "Pop");
        GenreEntity genreEntity2 = new GenreEntity(8, "Rock");
        TrackEntity trackEntity1 = new TrackEntity(8, "Uno", "Little Big",
                "Euro", 144, genreEntity1);
        TrackEntity trackEntity2 = new TrackEntity(9, "Uno", "Snup Dog",
                "My Drass", 144, genreEntity2);
        List<TrackEntity> trackEntityList = new LinkedList<>();
        trackEntityList.add(trackEntity1);
        trackEntityList.add(trackEntity2);
        given(trackDAO.getAllTracks()).willReturn(trackEntityList);
        List<TrackDataObject> trackDataObjectList = dataBase.getAllTracks();
        MatcherAssert.assertThat(trackDataObjectList.get(0), MatcherDTOwithTrackEntity.matcher(trackEntity1));
        MatcherAssert.assertThat(trackDataObjectList.get(1), MatcherDTOwithTrackEntity.matcher(trackEntity2));
    }
    @Test
    public void checkGetAllGenres()
    {
        GenreEntity genreEntity1 = new GenreEntity(7, "Pop");
        GenreEntity genreEntity2 = new GenreEntity(8, "Rock");
        List<GenreEntity> entityList = new LinkedList<>();
        entityList.add(genreEntity1);
        entityList.add(genreEntity2);
        given(genreDAO.getAllGenres()).willReturn(entityList);
        List<GenreDataObject> genreDataObjects = dataBase.getAllGenres();
        MatcherAssert.assertThat(genreDataObjects.get(0), MatcherGTOwithGenreEntity.matcher(genreEntity1));
        MatcherAssert.assertThat(genreDataObjects.get(1), MatcherGTOwithGenreEntity.matcher(genreEntity2));
    }
    @Test
    public void checkUpdateTrack()
    {
        GenreDataObject genreDataObject = new GenreDataObject("2", "Rap");
        TrackDataObject trackDataObject = new TrackDataObject("1", "Uno", "Little Big",
                "Euro", genreDataObject, 124);
        dataBase.updateTrack(trackDataObject);
        ArgumentCaptor<TrackEntity> argumentCaptor = ArgumentCaptor.forClass(TrackEntity.class);
        Mockito.verify(trackDAO).updateTrack(argumentCaptor.capture());
        TrackEntity trackEntity = argumentCaptor.getValue();
        MatcherAssert.assertThat(trackEntity, MatcherTrackEntityWithDTO.matcher(trackDataObject));
    }
    @Test
    public void checkUpdateGenre()
    {
        GenreDataObject genreDataObject = new GenreDataObject("2", "Rap");
        dataBase.updateGenre(genreDataObject);
        ArgumentCaptor<GenreEntity> argumentCaptor = ArgumentCaptor.forClass(GenreEntity.class);
        Mockito.verify(genreDAO).updateGenre(argumentCaptor.capture());
        GenreEntity genreEntity = argumentCaptor.getValue();
        MatcherAssert.assertThat(genreEntity, MatcherGenreEntityWithGTO.matcher(genreDataObject));
    }

}
