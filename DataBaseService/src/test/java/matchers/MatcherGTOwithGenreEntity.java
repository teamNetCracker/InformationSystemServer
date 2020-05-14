package matchers;

import data.GenreDataObject;
import entity.GenreEntity;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class MatcherGTOwithGenreEntity extends TypeSafeDiagnosingMatcher<GenreDataObject>
{
private GenreEntity genreEntity;
public MatcherGTOwithGenreEntity(GenreEntity genreEntity)
{
    this.genreEntity = genreEntity;
}
    @Override
    protected boolean matchesSafely(GenreDataObject genreDataObject, Description description) {
        description.appendText("[ Title="+ genreDataObject.getTitle() + ", id = " + genreDataObject.getId() + "]");
        boolean idMatch = genreDataObject.getId().equals(Integer.toString(genreEntity.getIdGenre()));
        boolean titleMatch = genreDataObject.getTitle().equals(genreEntity.getName());
        return idMatch && titleMatch;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("[ Title="+ genreEntity.getName() + ", id = " + genreEntity.getIdGenre() + "]");
    }

    public static Matcher<GenreDataObject> matcher(GenreEntity genreEntity)
    {
        return new MatcherGTOwithGenreEntity(genreEntity);
    }
}
