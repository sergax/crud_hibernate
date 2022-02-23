package com.sergax.crudhibernate.repository.HibernateRepoImpl;

import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.repository.TagRepository;
import com.sergax.crudhibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
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
        List tagList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tagList = session.createQuery(TAG_ALL).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tagList;
    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(getById(id));
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
            e.printStackTrace();
        }
        return tag;
    }
}
