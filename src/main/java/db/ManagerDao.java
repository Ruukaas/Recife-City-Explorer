/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

import modelo.Usuario;
import modelo.concreteTouristSpot;

/**
 *
 * @author euluc
 */
public class ManagerDao {

    private static ManagerDao myself = null;

    public static ManagerDao getCurrentInstance() {
        if (myself == null) {
            myself = new ManagerDao();
        }
        return myself;
    }

    private EntityManagerFactory emf = null;

    private ManagerDao() {
        this.emf = Persistence.createEntityManagerFactory("RCEPU");
    }

    public void insert(Object o) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(o);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    public void update(Object o) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.merge(o);
        em.getTransaction().commit();
        em.close();
    }

    public Usuario readUsuario(int id) {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT c FROM Usuario c WHERE c.id = :id");
        query.setParameter("id", id);
        Usuario retorno = (Usuario) query.getSingleResult();

        em.close();

        return retorno;
    }

    public void delete(Object o) {
        EntityManager em = emf.createEntityManager();

        Object oDelete = o;

        if (!em.contains(o)) {
            oDelete = em.merge(o);
        }
        em.getTransaction().begin();

        em.remove(oDelete);
        em.getTransaction().commit();
        em.close();
    }
}
