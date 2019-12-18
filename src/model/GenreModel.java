package model;

import data.GenreDataObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class GenreModel {
    private List<GenreDataObject> arrGenre;

    public void setArrGenre(List<GenreDataObject> arrGenre) {
        this.arrGenre = arrGenre;
    }

    public GenreModel() {
        arrGenre = new ArrayList<>();
    }

    public List<GenreDataObject> getAllGenres() {
        return arrGenre;
    }

    public GenreDataObject addGenre(String genreTitle) {
        for (GenreDataObject storageGenre : arrGenre) {
            if (storageGenre.getTitle().equals(genreTitle)) {
                return storageGenre;
            }
        }
        GenreDataObject newGenre = new GenreDataObject(UUID.randomUUID().toString(), genreTitle);
        arrGenre.add(newGenre);
        return newGenre;
    }

    public GenreDataObject getGenre(String id) {
        for (GenreDataObject genre : arrGenre) {
            if (genre.getId().equals(id)) {
                return genre;
            }
        }
        return null;
    }

    public void removeGenre(String id) {
        for (GenreDataObject genre : arrGenre) {
            if (genre.getId().equals(id)) {
                arrGenre.remove(genre);
                break;
            }
        }
    }

    public void changeGenre(String id, String newGenreTitle) {
        //newGenreTitle.setId(UUID.randomUUID().toString());
        for (GenreDataObject genreDataObject : arrGenre) {
            if (genreDataObject.getId().equals(id)) {
                genreDataObject.setTitle(newGenreTitle);
            }
        }

    }

    public void setGenre(String oldGenre, String newGenre) {
        for (GenreDataObject genres : arrGenre) {
            if (genres.getTitle().equals(oldGenre)) {
                genres.setTitle(newGenre);
                break;
            }
        }
    }

    public void addToArrGenre(List<GenreDataObject> addedArrGenres) {

        if (!arrGenre.isEmpty()) {
            addedArrGenres.removeAll(arrGenre);
        }
        arrGenre.addAll(addedArrGenres);
        /*for (TrackDataObject addedTrack : addedArrTrack) {
            boolean isDuplicate = false;
            for (TrackDataObject trackInStorage : storageTracks) {
                if (addedTrack.getId().equals(trackInStorage.getId())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                arrTrack.add(addedTrack);
            }

        }

         */

    }


}
