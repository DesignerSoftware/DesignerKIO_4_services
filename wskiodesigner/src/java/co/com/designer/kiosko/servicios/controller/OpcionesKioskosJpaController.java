/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.designer.kiosko.servicios.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.com.designer.kiosko.entidades.OpcionesKioskos;
import co.com.designer.kiosko.servicios.controller.exceptions.NonexistentEntityException;
import co.com.designer.kiosko.servicios.controller.exceptions.PreexistingEntityException;
import co.com.designer.kiosko.servicios.controller.exceptions.RollbackFailureException;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lenovo
 */
public class OpcionesKioskosJpaController implements Serializable {

    public OpcionesKioskosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpcionesKioskos opcionesKioskos) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OpcionesKioskos opcionkioskopadre = opcionesKioskos.getOpcionkioskopadre();
            if (opcionkioskopadre != null) {
                opcionkioskopadre = em.getReference(opcionkioskopadre.getClass(), opcionkioskopadre.getSecuencia());
                opcionesKioskos.setOpcionkioskopadre(opcionkioskopadre);
            }
            em.persist(opcionesKioskos);
            if (opcionkioskopadre != null) {
                OpcionesKioskos oldOpcionkioskopadreOfOpcionkioskopadre = opcionkioskopadre.getOpcionkioskopadre();
                if (oldOpcionkioskopadreOfOpcionkioskopadre != null) {
                    oldOpcionkioskopadreOfOpcionkioskopadre.setOpcionkioskopadre(null);
                    oldOpcionkioskopadreOfOpcionkioskopadre = em.merge(oldOpcionkioskopadreOfOpcionkioskopadre);
                }
                opcionkioskopadre.setOpcionkioskopadre(opcionesKioskos);
                opcionkioskopadre = em.merge(opcionkioskopadre);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findOpcionesKioskos(opcionesKioskos.getSecuencia()) != null) {
                throw new PreexistingEntityException("OpcionesKioskos " + opcionesKioskos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpcionesKioskos opcionesKioskos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OpcionesKioskos persistentOpcionesKioskos = em.find(OpcionesKioskos.class, opcionesKioskos.getSecuencia());
            OpcionesKioskos opcionkioskopadreOld = persistentOpcionesKioskos.getOpcionkioskopadre();
            OpcionesKioskos opcionkioskopadreNew = opcionesKioskos.getOpcionkioskopadre();
            if (opcionkioskopadreNew != null) {
                opcionkioskopadreNew = em.getReference(opcionkioskopadreNew.getClass(), opcionkioskopadreNew.getSecuencia());
                opcionesKioskos.setOpcionkioskopadre(opcionkioskopadreNew);
            }
            opcionesKioskos = em.merge(opcionesKioskos);
            if (opcionkioskopadreOld != null && !opcionkioskopadreOld.equals(opcionkioskopadreNew)) {
                opcionkioskopadreOld.setOpcionkioskopadre(null);
                opcionkioskopadreOld = em.merge(opcionkioskopadreOld);
            }
            if (opcionkioskopadreNew != null && !opcionkioskopadreNew.equals(opcionkioskopadreOld)) {
                OpcionesKioskos oldOpcionkioskopadreOfOpcionkioskopadre = opcionkioskopadreNew.getOpcionkioskopadre();
                if (oldOpcionkioskopadreOfOpcionkioskopadre != null) {
                    oldOpcionkioskopadreOfOpcionkioskopadre.setOpcionkioskopadre(null);
                    oldOpcionkioskopadreOfOpcionkioskopadre = em.merge(oldOpcionkioskopadreOfOpcionkioskopadre);
                }
                opcionkioskopadreNew.setOpcionkioskopadre(opcionesKioskos);
                opcionkioskopadreNew = em.merge(opcionkioskopadreNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigInteger id = opcionesKioskos.getSecuencia();
                if (findOpcionesKioskos(id) == null) {
                    throw new NonexistentEntityException("The opcionesKioskos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigInteger id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OpcionesKioskos opcionesKioskos;
            try {
                opcionesKioskos = em.getReference(OpcionesKioskos.class, id);
                opcionesKioskos.getSecuencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opcionesKioskos with id " + id + " no longer exists.", enfe);
            }
            OpcionesKioskos opcionkioskopadre = opcionesKioskos.getOpcionkioskopadre();
            if (opcionkioskopadre != null) {
                opcionkioskopadre.setOpcionkioskopadre(null);
                opcionkioskopadre = em.merge(opcionkioskopadre);
            }
            em.remove(opcionesKioskos);
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

    public List<OpcionesKioskos> findOpcionesKioskosEntities() {
        return findOpcionesKioskosEntities(true, -1, -1);
    }

    public List<OpcionesKioskos> findOpcionesKioskosEntities(int maxResults, int firstResult) {
        return findOpcionesKioskosEntities(false, maxResults, firstResult);
    }

    private List<OpcionesKioskos> findOpcionesKioskosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpcionesKioskos.class));
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

    public OpcionesKioskos findOpcionesKioskos(BigInteger id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpcionesKioskos.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpcionesKioskosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpcionesKioskos> rt = cq.from(OpcionesKioskos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
