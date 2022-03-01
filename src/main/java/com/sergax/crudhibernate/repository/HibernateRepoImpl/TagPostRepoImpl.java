package com.sergax.crudhibernate.repository.HibernateRepoImpl;

import com.sergax.crudhibernate.model.TagPost;
import com.sergax.crudhibernate.repository.TagPostRepository;
import com.sergax.crudhibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TagPostRepoImpl implements TagPostRepository {
//    private static final String INSERT_INTO_TAG_POST = "INSERT INTO tag_post " +
//            "(tag_id, post_id) " +
//            "" +
//            "JOIN FETCH Post p";
    private static final String TAG_POST_ALL = "SELECT * FROM tag_post";

    @Override
    public TagPost create(TagPost tagPost) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(tagPost);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tagPost;
    }

    @Override
    public List<Long> getAll() {
        List<Long> tagPostList = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tagPostList = session.createSQLQuery(TAG_POST_ALL).addEntity(TagPost.class).getResultList();
//            tagPostList = session.createQuery(TAG_POST_ALL).getResultList();
        } catch (Exception e) {
            return null;
        }
        return tagPostList;
    }
}
