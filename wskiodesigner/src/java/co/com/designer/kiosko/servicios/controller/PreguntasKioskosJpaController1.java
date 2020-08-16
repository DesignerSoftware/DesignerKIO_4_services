/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.designer.kiosko.servicios.controller;

import co.com.designer.kiosko.entidades.PreguntasKioskos;
import co.com.designer.kiosko.servicios.controller.exceptions.NonexistentEntityException;
import co.com.designer.kiosko.servicios.controller.exceptions.PreexistingEntityException;
import co.com.designer.kiosko.servicios.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lenovo
 */
public class PreguntasKioskosJpaController1 implements Serializable {

    public PreguntasKioskosJpaController1(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PreguntasKioskos preguntasKioskos) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(preguntasKioskos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPreguntasKioskos(preguntasKioskos.getSecuencia()) != null) {
                throw new PreexistingEntityException("PreguntasKioskos " + preguntasKioskos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PreguntasKioskos preguntasKioskos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            preguntasKioskos = em.merge(preguntasKioskos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = preguntasKioskos.getSecuencia();
                if (findPreguntasKioskos(id) == null) {
                    throw new NonexistentEntityException("The preguntasKioskos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PreguntasKioskos preguntasKioskos;
            try {
                preguntasKioskos = em.getReference(PreguntasKioskos.class, id);
                preguntasKioskos.getSecuencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preguntasKioskos with id " + id + " no longer exists.", enfe);
            }
            em.remove(preguntasKioskos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PreguntasKioskos> findPreguntasKioskosEntities() {
        return findPreguntasKioskosEntities(true, -1, -1);
    }

    public List<PreguntasKioskos> findPreguntasKioskosEntities(int maxResults, int firstResult) {
        return findPreguntasKioskosEntities(false, maxResults, firstResult);
    }

    private List<PreguntasKioskos> findPreguntasKioskosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PreguntasKioskos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PreguntasKioskos findPreguntasKioskos(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PreguntasKioskos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreguntasKioskosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PreguntasKioskos> rt = cq.from(PreguntasKioskos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
