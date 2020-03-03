package model;

import data.GenreEntity;
import data.TracksEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateSessionFactory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TrackDAO
{
    public void addTrack(TracksEntity newTrack)
    {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(newTrack);
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public void updateTrack(TracksEntity track)
    {
        Session session = null;
        try
        {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(track);
            session.getTransaction().commit();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public List searchTrack(String title)
    {
        Session session = null;
        List tracks = new LinkedList();
        try
        {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from TracksEntity where Title = :title")
                    .setString("title", title);
            tracks = query.list();
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tracks;
    }
    public Object getTrack(int id)
    {

        Session session = null;
        Object genre = null;
        try
        {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from TracksEntity where idTracks = :id")
                    .setInteger("id", id);
            genre = query.uniqueResult();
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return genre;
    }
    public void deleteTrack(int id)
    {
        Session session = null;
        try
        {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("DELETE  TracksEntity WHERE idTracks = :id")
                    .setInteger("id", new Integer(id));
            int rows = query.executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public List getAllTracks()
    {
        Session session = null;
        List tracks = new LinkedList<>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            tracks = session.createCriteria(TracksEntity.class).list();
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tracks;
    }
    public List getTracksByGenre(int idGenre) {
        Session session = null;
        List listGenres = new LinkedList<>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from TracksEntity where idGenre = :idGenre")
                    .setInteger("idGenre", idGenre);
            listGenres = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return listGenres;
    }
}
