package com.sergax.crudhibernate.repository.hibernateRepoImpl;

import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.repository.TagRepository;
import com.sergax.crudhibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TagRepoImpl implements TagRepository {
    private final String TAG_BY_ID = "FROM Tag WHERE id =: id";
    private final String TAG_ALL = "FROM Tag";

    @Override
    public Tag getById(Long id) {
        Tag tag = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
    public List getAll() {
        List<Tag> tagList = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tagList = session.createQuery(TAG_ALL).getResultList();
        } catch (Exception e) {
            return null;
        }
        return tagList;
    }

    @Override
    public boolean deleteById(Long id) {
        Tag tag = new Tag();
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            tag.setTag_id(id);
            session.delete(tag);
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
    public Tag create(Tag tag) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(tag);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
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
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return tag;
    }
}
