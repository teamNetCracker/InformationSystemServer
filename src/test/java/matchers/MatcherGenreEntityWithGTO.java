package matchers;

import data.GenreDataObject;
import data.GenreEntity;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class MatcherGenreEntityWithGTO extends TypeSafeDiagnosingMatcher<GenreEntity>
{
    private  GenreDataObject genreDataObject;

    public MatcherGenreEntityWithGTO(GenreDataObject genreDataObject)
    {
        this.genreDataObject = genreDataObject;
    }
    @Override
    protected boolean matchesSafely(GenreEntity genreEntity, Description description) {
        description.appendText("[ Title=" + genreEntity.getName() + ", id = " + genreEntity.getIdGenre());
        boolean titleMatch = genreEntity.getName().equals(genreDataObject.getTitle());
        boolean idMatch = genreEntity.getIdGenre() == Integer.parseInt(genreDataObject.getId());
        return titleMatch && idMatch;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("[ Title="+ genreDataObject.getTitle() + ", id = " + genreDataObject.getId());
    }
    public static Matcher<GenreEntity> matcher(GenreDataObject genreDataObject)
    {
        return new MatcherGenreEntityWithGTO(genreDataObject);
    }
}
