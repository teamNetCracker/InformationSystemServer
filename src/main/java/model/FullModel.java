package main.java.model;

import main.java.data.GenreDataObject;
import main.java.data.TrackDataObject;

import java.io.Serializable;
import java.util.List;

public class FullModel implements Serializable {
    List<TrackDataObject> tackListArr;
    List<GenreDataObject> genreListArr;

    public FullModel(List<TrackDataObject> tackListArr, List<GenreDataObject> genreListArr) {
        this.tackListArr = tackListArr;
        this.genreListArr = genreListArr;
    }

    public List<TrackDataObject> getTackListArr() {
        return tackListArr;
    }

    public void setTackListArr(List<TrackDataObject> tackListArr) {
        this.tackListArr = tackListArr;
    }

    public List<GenreDataObject> getGenreListArr() {
        return genreListArr;
    }

    public void setGenreListArr(List<GenreDataObject> genreListArr) {
        this.genreListArr = genreListArr;
    }
}
