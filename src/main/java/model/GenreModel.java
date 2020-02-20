package main.java.model;

import main.java.data.GenreDataObject;
import main.java.server.EventListener;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class GenreModel implements Observable {
    private List<GenreDataObject> arrGenre;
    private List<EventListener> listeners = new LinkedList<>();
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    public GenreModel(String dataBase) throws IOException, ClassNotFoundException {
        try {
            //arrTrack = new LinkedList<TrackDataObject>();
            this.inputStream = new ObjectInputStream(new FileInputStream(dataBase));
            arrGenre = (List<GenreDataObject>) inputStream.readObject();
            inputStream.close();
            this.outputStream = new ObjectOutputStream(new FileOutputStream(dataBase));
            System.out.println(arrGenre.size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveData()  {
        try {
            outputStream.writeObject(arrGenre);
        } catch (IOException e) {
            System.out.println("Can't write to File");
            e.printStackTrace();
        }
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
        saveData();
        for (EventListener listener : listeners) {
            listener.update();
        }
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
                saveData();
                for (EventListener listener : listeners) {
                    listener.update();
                }
                break;
            }
        }
    }

    public void changeGenre(String id, String newGenreTitle) {
        //newGenreTitle.setId(UUID.randomUUID().toString());
        for (GenreDataObject genreDataObject : arrGenre) {
            if (genreDataObject.getId().equals(id)) {
                genreDataObject.setTitle(newGenreTitle);
                saveData();
            }
        }
        for (EventListener listener : listeners) {
            listener.update();
        }
    }

    public void setGenre(String oldGenre, String newGenre) {
        for (GenreDataObject genres : arrGenre) {
            if (genres.getTitle().equals(oldGenre)) {
                genres.setTitle(newGenre);
                saveData();
                break;
            }
        }
    }

    public void updateGenreArr(List<GenreDataObject> addedArrGenres) {

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
        saveData();
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
