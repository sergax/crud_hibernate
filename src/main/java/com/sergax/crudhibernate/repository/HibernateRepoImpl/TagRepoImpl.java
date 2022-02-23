package com.sergax.crudhibernate.repository.HibernateRepoImpl;

import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.repository.TagRepository;
import com.sergax.crudhibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TagRepoImpl implements TagRepository {
    private final String TAG_BY_ID = "FROM tag WHERE id =: id";

    @Override
    public Tag getById(Long id) {
        Tag tag = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            /** try to use session.byId("tag") */
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
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Tag create(Tag tag) {
        return null;
    }

    @Override
    public Tag update(Tag tag) {
        return null;
    }
}
