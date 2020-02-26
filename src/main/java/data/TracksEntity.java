package data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tracks", schema = "database", catalog = "")
public class TracksEntity {
    private int idTracks;
    private String title;
    private String performer;
    private String album;
    private int duration;
    private GenreEntity genreByIdGenre;

    @Id
    @Column(name = "idTracks", nullable = false)
    public int getIdTracks() {
        return idTracks;
    }

    public void setIdTracks(int idTracks) {
        this.idTracks = idTracks;
    }

    @Basic
    @Column(name = "Title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Performer", nullable = false, length = 100)
    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    @Basic
    @Column(name = "Album", nullable = false, length = 100)
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Basic
    @Column(name = "Duration", nullable = false)
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TracksEntity that = (TracksEntity) o;
        return idTracks == that.idTracks &&
                duration == that.duration &&
                Objects.equals(title, that.title) &&
                Objects.equals(performer, that.performer) &&
                Objects.equals(album, that.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTracks, title, performer, album, duration);
    }

    @ManyToOne
    @JoinColumn(name = "idGenre", referencedColumnName = "idGenre", nullable = false)
    public GenreEntity getGenreByIdGenre() {
        return genreByIdGenre;
    }

    public void setGenreByIdGenre(GenreEntity genreByIdGenre) {
        this.genreByIdGenre = genreByIdGenre;
    }
}
