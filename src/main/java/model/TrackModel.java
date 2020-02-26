package model;

import data.GenreDataObject;
import data.TrackDataObject;
import main.java.server.EventListener;


import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TrackModel implements Observable {
    private List<TrackDataObject> arrTrack;
    private List<EventListener> listeners = new LinkedList<>();
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public TrackModel(String dataSourcePath) {
        try {
            File dataSource = new File(dataSourcePath);
            this.inputStream = new ObjectInputStream(new FileInputStream(dataSourcePath));
            arrTrack = (List<TrackDataObject>) inputStream.readObject();
            inputStream.close();
            this.outputStream = new ObjectOutputStream(new FileOutputStream(dataSourcePath));
            System.out.println(arrTrack.size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void saveData()  {
        try {
            outputStream.writeObject(arrTrack);
            System.out.println("Save Done");
        } catch (IOException e) {
            System.out.println("Can't write to File");
            e.printStackTrace();
        }
    }

    public List<TrackDataObject> getAllTracks() {
        return arrTrack;
    }

    public void addTrack(String id, String title, String performer, String album, GenreDataObject genre, Integer duration) {
        id = UUID.randomUUID().toString();
        TrackDataObject newTrack = new TrackDataObject(id, title, performer, album, genre, duration);
        arrTrack.add(newTrack);
        System.out.println(arrTrack.size());
        saveData();
        for (EventListener listener : listeners) {
            listener.update();
        }
    }

    public void updateTrackArr(List<TrackDataObject> addedArrTrack) {
        if (!arrTrack.isEmpty()) {
            arrTrack.removeAll(arrTrack);
        }
        arrTrack.addAll(addedArrTrack);
        saveData();
        for (EventListener listener : listeners) {
            listener.update();
        }
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
                saveData();
                for (EventListener listener : listeners) {
                    listener.update();
                }
                break;
            }
        }
    }

    public void changeTrack(String id, String title, String performer, String album, GenreDataObject genre, Integer duration) {
        for (TrackDataObject trackDataObject : arrTrack) {
            if(trackDataObject.getId().equals(id)){
                trackDataObject.setTitle(title);
                trackDataObject.setPerformer(performer);
                trackDataObject.setAlbum(album);
                trackDataObject.setGenre(genre);
                trackDataObject.setDuration(duration);
            }
        }
        saveData();
        for (EventListener listener : listeners) {
            listener.update();
        }
    }

    public void setTitleTrack(String id, String newTitleTrack) {
        getTrack(id).setTitle(newTitleTrack);
        System.out.println(getTrack(id).getTitle());
        saveData();
        for (EventListener listener : listeners) {
            listener.update();
        }
    }


    public void removeTrackByGenreId(String id) {
        for (int i = 0; i < arrTrack.size(); i++) {
            if (arrTrack.get(i).getGenre().getId().equals(id)) {
                arrTrack.remove(i);
                saveData();
            }
        }
        /*
        for (TrackDataObject trackDataObject : arrTrack) {
            if(trackDataObject.getGenre().getId().equals(id)){

            }
        }

         */
        for (EventListener listener : listeners) {
            listener.update();
        }
    }

    @Override
    public void subscribe(EventListener eventListener) {
        listeners.add(eventListener);
    }

    @Override
    public void unsubscribe(EventListener eventListener) {
        listeners.remove(eventListener);
    }
}
