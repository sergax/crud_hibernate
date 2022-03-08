package com.sergax.crudhibernate.repository.hibernateRepoImpl;

import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.repository.PostRepository;
import com.sergax.crudhibernate.util.HibernateUtil;
import com.sergax.crudhibernate.util.JdbcUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostRepoImpl implements PostRepository {
    private static final String POST_BY_ID = "FROM Post WHERE id=:id";
    private static final String POST_ALL = "SELECT * FROM post " +
            "LEFT JOIN tag_post USING(post_id)" +
            "LEFT JOIN tag USING(tag_id)" +
            "ORDER BY post_id";
    private static final String UPDATE_WRITER = "UPDATE post " +
            "SET post_writer_id = ? " +
            "WHERE post_id = ?";
    private final String SQL_ADD_TAGS_POSTS = "insert into tag_post " +
            "(tag_id, post_id) " +
            "values " +
            "(?, ?)";

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
    public List getAll() {
        List<Post> postList = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            postList = session.createSQLQuery(POST_ALL).addEntity(Post.class).getResultList();
        } catch (Exception e) {
            return null;
        }
        return postList;
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = null;
        Post post = new Post();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            post.setPost_id(id);
            session.delete(post);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    public void createPostList(Long postWriterId, Long postID) {
        try (PreparedStatement preparedStatementAddTags = JdbcUtil.getConnection().prepareStatement(UPDATE_WRITER)) {
            preparedStatementAddTags.setLong(1, postWriterId);
            preparedStatementAddTags.setLong(2, postID);
            preparedStatementAddTags.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTagPost(Long postId, Long tagId) {
        try (PreparedStatement preparedStatementAddTags = JdbcUtil.getConnection().prepareStatement(SQL_ADD_TAGS_POSTS)) {
            preparedStatementAddTags.setLong(1, tagId);
            preparedStatementAddTags.setLong(2, postId);
            preparedStatementAddTags.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Post update(Post post) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(post);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return post;
    }

    @Override
    public Post create(Post post) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(post);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return post;
    }
}
