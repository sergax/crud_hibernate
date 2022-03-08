package com.sergax.crudhibernate.repository.hibernateRepoImpl;

import com.sergax.crudhibernate.model.Writer;
import com.sergax.crudhibernate.repository.WriterRepository;
import com.sergax.crudhibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class WriterRepoImpl implements WriterRepository {
    private static final String WRITER_BY_ID = "FROM Writer WHERE id =: id";
    private static final String WRITER_ALL = "SELECT * FROM writer w " +
            "LEFT JOIN post p ON p.post_writer_id = w.id " +
            "ORDER BY id";

    @Override
    public Writer getById(Long id) {
        Writer writer = new Writer();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(WRITER_BY_ID);
            query.setParameter("id", id);
            List writerList = query.getResultList();
            writer = (Writer) writerList.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return writer;
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writerList = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            writerList = session.createSQLQuery(WRITER_ALL).addEntity(Writer.class).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return writerList;
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = null;
        Writer writer = new Writer();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            writer.setId(id);
            session.delete(writer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public Writer create(Writer writer) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(writer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(writer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return writer;
    }
}
