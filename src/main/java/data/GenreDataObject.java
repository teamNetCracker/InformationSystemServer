package main.java.data;

import java.io.Serializable;

public class GenreDataObject implements Serializable{
    private String title;
    private String id;
    public GenreDataObject(String title)
    {
        this.title = title;
    }

    public GenreDataObject(String id,String title)
    {
        this.id = id;
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean equals(Object object) {
        return title.equals(((GenreDataObject)object).title);
    }



    @Override
    public String toString() {
        return title;
    }


}
