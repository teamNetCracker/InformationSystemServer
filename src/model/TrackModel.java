package model;

import data.GenreDataObject;
import data.TrackDataObject;


import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TrackModel  {
    private List<TrackDataObject> arrTrack;

    public TrackModel() {
        arrTrack = new LinkedList<>();
    }

    public List<TrackDataObject> getAllTracks() {
        return arrTrack;
    }

    public void addTrack(String id, String title, String performer, String album, GenreDataObject genre, Integer duration) {
        id = UUID.randomUUID().toString();
        TrackDataObject newTrack = new TrackDataObject(id, title, performer, album, genre, duration);
        for (TrackDataObject track : arrTrack) {
            if (track.equals(newTrack))
                throw new IllegalArgumentException("This track already exists");
        }
        arrTrack.add(newTrack);

    }

    public void addToArrTrack(List<TrackDataObject> addedArrTrack) {
        if (!arrTrack.isEmpty()) {
            addedArrTrack.removeAll(arrTrack);
        }
        arrTrack.addAll(addedArrTrack);
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

    public TrackDataObject getTrack(String id) {
        for (TrackDataObject track : arrTrack) {
            if (track.getId().equals(id)) {
                return track;
            }
        }
        return null;
    }


    public void removeTrack(String id) {
        for (TrackDataObject track : arrTrack) {
            if (track.getId().equals(id)) {
                this.arrTrack.remove(track);
                break;
            }
        }
    }

    public void changeTrack(String id, String title, String performer, String album, GenreDataObject genre, Integer duration) {
        for (TrackDataObject trackDataObject : arrTrack) {
            if (trackDataObject.getId().equals(id)) {
                trackDataObject.setTitle(title);
                trackDataObject.setPerformer(performer);
                trackDataObject.setAlbum(album);
                trackDataObject.setGenre(genre);
                trackDataObject.setDuration(duration);
            }
        }

    }

    public void setTitleTrack(String id, String newTitleTrack) {
        getTrack(id).setTitle(newTitleTrack);
        System.out.println(getTrack(id).getTitle());

    }

    public void setPerformerTrack(String titleTrack, String newPerformerTrack) {
        for (TrackDataObject track : arrTrack) {
            if (track.getTitle().equals(titleTrack)) {
                track.setPerformer(newPerformerTrack);
                break;
            }
        }
    }

    public void setAlbumTrack(String titleTrack, String newAlbumTrack) {
        for (TrackDataObject track : arrTrack) {
            if (track.getTitle().equals(titleTrack)) {
                track.setAlbum(newAlbumTrack);
                break;
            }
        }
    }

    public void setDurationTrack(String titleTrack, Integer newDurationTrack) {
        for (TrackDataObject track : arrTrack) {
            if (track.getTitle().equals(titleTrack)) {
                track.setDuration(newDurationTrack);
                break;
            }
        }
    }


    public void removeTrackByGenreId(String id) {
        for (int i = 0; i < arrTrack.size(); i++) {
            if (arrTrack.get(i).getGenre().getId().equals(id)) {
                arrTrack.remove(i);
            }
        }
        /*
        for (TrackDataObject trackDataObject : arrTrack) {
            if(trackDataObject.getGenre().getId().equals(id)){

            }
        }

         */

    }
}
