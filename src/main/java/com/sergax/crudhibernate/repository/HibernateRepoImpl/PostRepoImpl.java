package com.sergax.crudhibernate.repository.HibernateRepoImpl;

import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.model.TagPost;
import com.sergax.crudhibernate.model.Writer;
import com.sergax.crudhibernate.repository.PostRepository;
import com.sergax.crudhibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PostRepoImpl implements PostRepository {
    private static final String POST_BY_ID = "FROM Post WHERE id =:id";
    private static final String POST_ALL = "SELECT * FROM post " +
            "LEFT JOIN tag_post USING(post_id)" +
            "LEFT JOIN tag USING(tag_id)" +
            "ORDER BY post_id";

    @Override
    public Post getById(Long id) {
        Post post = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(POST_BY_ID);
            query.setParameter("id", id);
            List postList = query.getResultList();
            post = (Post) postList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> postList = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            postList = session.createSQLQuery(POST_ALL).addEntity(Post.class).getResultList();
        } catch (Exception e) {
            return null;
        }
        return postList;
    }

    @Override
    public void deleteById(Post post) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(post);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Post create(Post post) {
        Transaction transaction = null;
        TagPost tagPost = new TagPost();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(post);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(post);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return post;
    }
}
