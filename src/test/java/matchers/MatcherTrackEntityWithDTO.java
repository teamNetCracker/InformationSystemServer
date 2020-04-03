package matchers;

import data.TrackDataObject;
import data.TrackEntity;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class MatcherTrackEntityWithDTO extends TypeSafeDiagnosingMatcher<TrackEntity>
{
    private TrackDataObject trackDataObject;
    public MatcherTrackEntityWithDTO(TrackDataObject trackDataObject)
    {
        this.trackDataObject = trackDataObject;
    }
    @Override
    protected boolean matchesSafely(TrackEntity trackEntity, Description description) {
        description.appendText("[title=" + trackEntity.getTitle() + "', performer=" + trackEntity.getPerformer() + "'," +
                " album=" + trackEntity.getAlbum() + "', genre=" + trackEntity.getGenreByIdGenre().getName() + "', duration=" + trackEntity.getDuration()+ "]");
        Boolean idMatch = trackDataObject.getId()
                .equals(Integer.toString(trackEntity.getIdTracks()));
        Boolean titleGenreMatch = trackDataObject.getGenre().getTitle()
                .equals(trackEntity.getGenreByIdGenre().getName());
        Boolean idGenreMatch = trackDataObject.getGenre().getId()
                .equals(Integer.toString(trackEntity.getGenreByIdGenre().getIdGenre()));
        Boolean albumMatch = trackDataObject.getAlbum().equals(trackEntity.getAlbum());
        Boolean titleMatch = trackDataObject.getTitle().equals(trackEntity.getTitle());
        Boolean performerMatch = trackDataObject.getPerformer()
                .equals(trackEntity.getPerformer());
        Boolean durationMatch = trackDataObject.getDuration().equals(trackEntity.getDuration());
        return idMatch && titleGenreMatch && idGenreMatch && albumMatch
                && titleMatch && performerMatch && durationMatch;
    }

    @Override
    public void describeTo(Description description)
    {
        description.appendText("[title=" + trackDataObject.getTitle() + "', performer=" + trackDataObject.getPerformer() +
                "', album=" + trackDataObject.getAlbum() + "', genre=" + trackDataObject.getGenre().getTitle() + "', duration=" + trackDataObject.getDuration()+ "]");
    }
    public static Matcher<TrackEntity> matcher(TrackDataObject trackDataObject)
    {
        return new MatcherTrackEntityWithDTO(trackDataObject);
    }
}
