package com.sergax.crudhibernate.repository.HibernateRepoImpl;

import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.repository.PostRepository;
import com.sergax.crudhibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class PostRepoImpl implements PostRepository {
    @Override
    public Post getById(Long id) {
        Post post = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            Query query = session.createQuery("FROM post where id =:id");
            query.setParameter("id", id);
            List postList = query.getResultList();
            post = (Post) postList.get(0);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Post create(Post post) {
        return null;
    }

    @Override
    public Post update(Post post) {
        return null;
    }
}
