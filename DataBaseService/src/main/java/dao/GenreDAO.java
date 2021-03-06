package dao;


import entity.GenreEntity;
import sessionFactory.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;


import java.util.LinkedList;
import java.util.List;

public class GenreDAO
{
    public void addGenre(GenreEntity newGenre)
    {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(newGenre);
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
    public void updateGenre(GenreEntity Genre)
    {
        Session session = null;
        try
        {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(Genre);
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
    public void deleteGenre(GenreEntity genreEntity)
    {
        Session session = null;
        try
        {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(genreEntity);
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
    public GenreEntity getGenre(int id)
    {

            Session session = null;
            GenreEntity genre = null;
            try
            {
                session = HibernateSessionFactory.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("from GenreEntity where idGenre = :id").setInteger("id", id);
                genre = (GenreEntity) query.uniqueResult();
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
    public void deleteGenre(int id)
    {
        Session session = null;
        try
        {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("DELETE  GenreEntity WHERE idGenre = :id").setInteger("id", new Integer(id));
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
    public List<GenreEntity> getAllGenres()
    {
        Session session = null;
        List genres = new LinkedList<GenreEntity>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            genres = session.createCriteria(GenreEntity.class).list();
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
        return genres;
    }
    public GenreEntity searchGenre(String title)
    {
        Session session = null;
        GenreEntity genre = null;
        try
        {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from GenreEntity where name = :title").setString("title", title);
            genre = (GenreEntity) query.uniqueResult();
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


}
