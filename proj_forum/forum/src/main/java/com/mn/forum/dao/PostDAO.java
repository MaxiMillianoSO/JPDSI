package com.mn.forum.dao;

import com.mn.forum.entities.Post;
import com.mn.forum.entities.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PostDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Post> findAll() {
        return em.createQuery(
                "SELECT p FROM Post p ORDER BY p.createdAt DESC",
                Post.class
        ).getResultList();
    }

    public List<Post> findByAuthor(User author) {
        return em.createQuery(
                "SELECT p FROM Post p WHERE p.author = :author ORDER BY p.createdAt DESC",
                Post.class)
                .setParameter("author", author)
                .getResultList();
    }

    public Post findById(Integer id) {
        return em.find(Post.class, id);
    }

    public void create(Post post) {
        em.persist(post);
    }

    public void update(Post post) {
        em.merge(post);
    }

    public void deleteById(Integer id) {
        Post post = em.find(Post.class, id);
        if (post != null) {
            em.remove(post);
        }
    }

    public List<Post> findFiltered(String title) {
        if (title == null || title.isBlank()) {
            return em.createQuery(
                    "SELECT p FROM Post p ORDER BY p.createdAt DESC",
                    Post.class
            ).getResultList();
        }

        return em.createQuery(
                "SELECT p FROM Post p WHERE LOWER(p.title) LIKE :title ORDER BY p.createdAt DESC",
                Post.class
        )
                .setParameter("title", "%" + title.toLowerCase() + "%")
                .getResultList();
    }
}
