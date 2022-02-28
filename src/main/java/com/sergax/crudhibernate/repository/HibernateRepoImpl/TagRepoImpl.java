package com.sergax.crudhibernate.repository.HibernateRepoImpl;

import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.model.Writer;
import com.sergax.crudhibernate.repository.TagRepository;
import com.sergax.crudhibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TagRepoImpl implements TagRepository {
    private final String TAG_BY_ID = "FROM tag WHERE id =: id";
    private final String TAG_ALL = "SELECT * FROM tag";

    @Override
    public Tag getById(Long id) {
        Tag tag = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // try to use ...= session.byId("tag")
            Query query = session.createQuery(TAG_BY_ID);
            query.setParameter("id", id);
            List tagList = query.getResultList();
            tag = (Tag) tagList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    public List<Tag> getAll() {
        List<Tag> tagList = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tagList = session.createSQLQuery(TAG_ALL).addEntity(Tag.class).getResultList();
        } catch (Exception e) {
            return null;
        }
        return tagList;
    }

    @Override
    public void deleteById(Tag tag) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(tag);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tag create(Tag tag) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(tag);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    public Tag update(Tag tag) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(tag);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return tag;
    }
}
