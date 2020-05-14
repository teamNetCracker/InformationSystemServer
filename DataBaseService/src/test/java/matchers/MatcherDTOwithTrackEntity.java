package matchers;

import data.TrackDataObject;

import entity.TrackEntity;
import org.hamcrest.*;

public class MatcherDTOwithTrackEntity extends TypeSafeDiagnosingMatcher<TrackDataObject>
{
    TrackEntity trackEntity;
    public MatcherDTOwithTrackEntity(TrackEntity trackEntity)
    {
        this.trackEntity = trackEntity;
    }

    @Override
    protected boolean matchesSafely(TrackDataObject trackDataObject, Description description ) {
        description.appendText("[title=" + trackDataObject.getTitle() + "', performer=" + trackDataObject.getPerformer() +
                "', album=" + trackDataObject.getAlbum() + "', genre=" + trackDataObject.getGenre().getTitle() + "', duration=" + trackDataObject.getDuration()+ "]");
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
    public void describeTo(Description description) {
        description.appendText("[title=" + trackEntity.getTitle() + "', performer=" + trackEntity.getPerformer() + "'," +
                " album=" + trackEntity.getAlbum() + "', genre=" + trackEntity.getGenreByIdGenre().getName() + "', duration=" + trackEntity.getDuration()+ "]");
    }
    public static Matcher<TrackDataObject> matcher(TrackEntity trackEntity)
    {
        return new MatcherDTOwithTrackEntity(trackEntity);
    }

}

