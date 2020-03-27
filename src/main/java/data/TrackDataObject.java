package data;

import java.io.Serializable;

public class TrackDataObject implements Serializable {
    private String id;
    private String title;
    private String performer;
    private String album;
    private GenreDataObject genre;
    private Integer duration;

    public TrackDataObject(String id, String title, String performer, String album, GenreDataObject genre, Integer duration) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.album = album;
        this.genre = genre;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public GenreDataObject getGenre() {
        return genre;
    }

    public String getAlbum() {
        return album;
    }

    public String getPerformer() {
        return performer;
    }

    public String getTitle() {
        return title;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setGenre(GenreDataObject genre) {
        this.genre = genre;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setId(String id) {
        this.id = id;
    }
/*
    public boolean equals(Object object) {
        if (object == null) return false;
        if (!(object instanceof TrackDataObject)) return false;
        return (getTitle().equals( ((TrackDataObject) object).getTitle())
                && getPerformer().equals(((TrackDataObject) object).getPerformer()))
                && getAlbum().equals(((TrackDataObject) object).getAlbum())
                && getDuration().equals(((TrackDataObject) object).getDuration());
    }

 */
public boolean equals(Object object) {
    if (object == null) return false;
    if (!(object instanceof TrackDataObject)) return false;
    return (((TrackDataObject) object).getId().equals(this.id));
}


    public Integer getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "TrackDataObject[" +
                "title=" + title + '\'' +
                ", performer=" + performer + '\'' +
                ", album=" + album + '\'' +
                ", genre=" + genre +
                ", duration=" + duration +
                ']';
    }


}
