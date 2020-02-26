package data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genre", schema = "database", catalog = "")
public class GenreEntity {
    private int idGenre;
    private String name;

    @Id
    @Column(name = "idGenre", nullable = false)
    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity that = (GenreEntity) o;
        return idGenre == that.idGenre &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenre, name);
    }
}
