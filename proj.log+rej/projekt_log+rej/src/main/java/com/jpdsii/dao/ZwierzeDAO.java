package com.jpdsii.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jpdsii.entities.Zwierze;

@Stateless
public class ZwierzeDAO {

    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    // CREATE
    public void create(Zwierze zw) {
        em.persist(zw);
    }

    // UPDATE
    public Zwierze merge(Zwierze zw) {
        return em.merge(zw);
    }

    // DELETE
    public void remove(Zwierze zw) {
        em.remove(em.merge(zw));
    }

    // FIND BY ID
    public Zwierze find(Object id) {
        return em.find(Zwierze.class, id);
    }

    // GET FULL LIST
    public List<Zwierze> getFullList() {
        List<Zwierze> list = null;

        Query query = em.createQuery("SELECT z FROM Zwierze z");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // GET LIST WITH FILTERS
    public List<Zwierze> getList(Map<String, Object> searchParams) {
        List<Zwierze> list = null;

        String select = "SELECT z ";
        String from = "FROM Zwierze z ";
        String where = "";
        String orderby = "ORDER BY z.imie ASC, z.gatunek";

        // Filter by name
        String imie = (String) searchParams.get("imie");
        if (imie != null && !imie.isEmpty()) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "z.imie LIKE :imie ";
        }

        // Filter by species (gatunek)
        String gatunek = (String) searchParams.get("gatunek");
        if (gatunek != null && !gatunek.isEmpty()) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "z.gatunek LIKE :gatunek ";
        }

        Query query = em.createQuery(select + from + where + orderby);

        // set parameters
        if (imie != null && !imie.isEmpty()) {
            query.setParameter("imie", imie + "%");
        }

        if (gatunek != null && !gatunek.isEmpty()) {
            query.setParameter("gatunek", gatunek + "%");
        }

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

