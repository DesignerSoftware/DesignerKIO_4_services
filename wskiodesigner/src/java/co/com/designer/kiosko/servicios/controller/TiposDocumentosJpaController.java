/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.designer.kiosko.servicios.controller;

import co.com.designer.kiosko.entidades.TiposDocumentos;
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
import javax.transaction.UserTransaction;

/**
 *
 * @author Lenovo
 */
public class TiposDocumentosJpaController implements Serializable {

    public TiposDocumentosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiposDocumentos tiposDocumentos) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(tiposDocumentos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTiposDocumentos(tiposDocumentos.getSecuencia()) != null) {
                throw new PreexistingEntityException("TiposDocumentos " + tiposDocumentos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiposDocumentos tiposDocumentos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            tiposDocumentos = em.merge(tiposDocumentos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = tiposDocumentos.getSecuencia();
                if (findTiposDocumentos(id) == null) {
                    throw new NonexistentEntityException("The tiposDocumentos with id " + id + " no longer exists.");
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
            TiposDocumentos tiposDocumentos;
            try {
                tiposDocumentos = em.getReference(TiposDocumentos.class, id);
                tiposDocumentos.getSecuencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposDocumentos with id " + id + " no longer exists.", enfe);
            }
            em.remove(tiposDocumentos);
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

    public List<TiposDocumentos> findTiposDocumentosEntities() {
        return findTiposDocumentosEntities(true, -1, -1);
    }

    public List<TiposDocumentos> findTiposDocumentosEntities(int maxResults, int firstResult) {
        return findTiposDocumentosEntities(false, maxResults, firstResult);
    }

    private List<TiposDocumentos> findTiposDocumentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TiposDocumentos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TiposDocumentos findTiposDocumentos(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiposDocumentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposDocumentosCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TiposDocumentos as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
