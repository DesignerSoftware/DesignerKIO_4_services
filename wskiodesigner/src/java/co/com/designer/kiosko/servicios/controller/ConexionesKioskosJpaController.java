/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.designer.kiosko.servicios.controller;

import co.com.designer.kiosko.entidades.ConexionesKioskos;
import co.com.designer.kiosko.persistencia.implementacion.PersistenciaConexionInicial;
import co.com.designer.kiosko.persistencia.interfaz.IPersistenciaConexionInicial;
import co.com.designer.kiosko.servicios.controller.exceptions.NonexistentEntityException;
import co.com.designer.kiosko.servicios.controller.exceptions.PreexistingEntityException;
import co.com.designer.kiosko.servicios.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lenovo
 */
public class ConexionesKioskosJpaController implements Serializable {

//    @EJB
    IPersistenciaConexionInicial persistenciaConexionInicial;

    public ConexionesKioskosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
        persistenciaConexionInicial = new PersistenciaConexionInicial();
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private void setearRol(EntityManager em) {
        try {
            persistenciaConexionInicial.setearKiosko(em, "KRYPTO");
        } catch (Exception ex) {
            Logger.getLogger(CiudadesJpaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(ConexionesKioskos conexionesKioskos) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(conexionesKioskos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findConexionesKioskos(conexionesKioskos.getSecuencia()) != null) {
                throw new PreexistingEntityException("ConexionesKioskos " + conexionesKioskos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConexionesKioskos conexionesKioskos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            conexionesKioskos = em.merge(conexionesKioskos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = conexionesKioskos.getSecuencia();
                if (findConexionesKioskos(id) == null) {
                    throw new NonexistentEntityException("The conexionesKioskos with id " + id + " no longer exists.");
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
            ConexionesKioskos conexionesKioskos;
            try {
                conexionesKioskos = em.getReference(ConexionesKioskos.class, id);
                conexionesKioskos.getSecuencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conexionesKioskos with id " + id + " no longer exists.", enfe);
            }
            em.remove(conexionesKioskos);
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

    public List<ConexionesKioskos> findConexionesKioskosEntities() {
        return findConexionesKioskosEntities(true, -1, -1);
    }

    public List<ConexionesKioskos> findConexionesKioskosEntities(int maxResults, int firstResult) {
        return findConexionesKioskosEntities(false, maxResults, firstResult);
    }

    private List<ConexionesKioskos> findConexionesKioskosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        List resultado = null;
        try {
            this.utx.begin();
            em.joinTransaction();
            setearRol(em);
            Query q = em.createQuery("select object(o) from ConexionesKioskos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
//            return q.getResultList();
            resultado = q.getResultList();
            this.utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(ConexionesKioskosJpaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
        return resultado;
    }

    public ConexionesKioskos findConexionesKioskos(BigDecimal id) {
        EntityManager em = getEntityManager();
        ConexionesKioskos resultado = null;
        try {
            this.utx.begin();
            em.joinTransaction();
            setearRol(em);
//            return em.find(ConexionesKioskos.class, id);
            resultado = em.find(ConexionesKioskos.class, id);
            this.utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(ConexionesKioskosJpaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
        return resultado;
    }

    public int getConexionesKioskosCount() {
        EntityManager em = getEntityManager();
        Long conteo = null;
        try {
            this.utx.begin();
            em.joinTransaction();
            setearRol(em);
            Query q = em.createQuery("select count(o) from ConexionesKioskos as o");
//            return ((Long) q.getSingleResult()).intValue();
            conteo = (Long) q.getSingleResult();
            this.utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(ConexionesKioskosJpaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
        return conteo.intValue();
    }

}
