package model;

import data.GenreEntity;
import data.TrackEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import utils.HibernateSessionFactory;

import java.util.LinkedList;
import java.util.List;
public class TrackDAO {
    public void addTrack(TrackEntity newTrack) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(newTrack);
            session.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateTrack(TrackEntity track) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(track);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<TrackEntity> searchTrack(String title) {
        Session session = null;
        List tracks = new LinkedList();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from TrackEntity where Title = :title")
                    .setString("title", title);
            tracks = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tracks;
    }

    public List<TrackEntity> searchTracksByGenre(String titleGenre) {
        Session session = null;
        List<TrackEntity> tracks = new LinkedList();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria genreCriteria = session.createCriteria(GenreEntity.class);
            genreCriteria.add(Restrictions.eq("name", titleGenre));
            GenreEntity genre = (GenreEntity) genreCriteria.uniqueResult();
            Criteria tracksCriteria = session.createCriteria(TrackEntity.class);
            tracksCriteria.add(Restrictions.eq("genreByIdGenre", genre));
            tracks = tracksCriteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tracks;
    }

    public TrackEntity getTrack(int id) {

        Session session = null;
        TrackEntity track = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from TrackEntity where idTracks = :id")
                    .setInteger("id", id);
            track = (TrackEntity) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return track;
    }

    public void deleteTrack(int id) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("DELETE  TrackEntity WHERE idTracks = :id")
                    .setInteger("id", id);
            int rows = query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<TrackEntity> getAllTracks() {
        Session session = null;
        List<TrackEntity> tracks = new LinkedList<>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            tracks = session.createCriteria(TrackEntity.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tracks;
    }

    public List<TrackEntity> searchTracksByGenre(int idGenre) {
        Session session = null;
        List<TrackEntity> tracks = new LinkedList<>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            GenreEntity genre = session.load(GenreEntity.class, idGenre);
            Criteria tracksCriteria = session.createCriteria(TrackEntity.class);
            tracksCriteria.add(Restrictions.eq("genreByIdGenre", genre));
            tracks = tracksCriteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tracks;
    }
}