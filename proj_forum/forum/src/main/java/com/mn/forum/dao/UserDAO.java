package com.mn.forum.dao;

import com.mn.forum.entities.Role;
import com.mn.forum.entities.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserDAO {

    @PersistenceContext(unitName = "forumPU")
    private EntityManager em;

    public User findByUsername(String username) {
        try {
            return em.createNamedQuery("User.findByUsername", User.class)
                     .setParameter("username", username)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public void create(User user) {
    em.persist(user);
}

public boolean existsByUsername(String username) {
    Long count = em.createQuery(
            "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
            .setParameter("username", username)
            .getSingleResult();
    return count > 0;
}
public Role findRoleByName(String name) {
    return em.createQuery(
            "SELECT r FROM Role r WHERE r.name = :name", Role.class)
            .setParameter("name", name)
            .getSingleResult();
}

}
