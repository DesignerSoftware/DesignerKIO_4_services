/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.designer.kiosko.servicios.controller;

import co.com.designer.kiosko.entidades.Ciudades;
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
//import javax.ejb.EJB;
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
public class CiudadesJpaController implements Serializable {

//    @EJB
    IPersistenciaConexionInicial persistenciaConexionInicial;

    public CiudadesJpaController(UserTransaction utx, EntityManagerFactory emf) {
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

    public void create(Ciudades ciudades) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            setearRol(em);
            em.persist(ciudades);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCiudades(ciudades.getSecuencia()) != null) {
                throw new PreexistingEntityException("Ciudades " + ciudades + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ciudades ciudades) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            setearRol(em);
            ciudades = em.merge(ciudades);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ciudades.getSecuencia();
                if (findCiudades(id) == null) {
                    throw new NonexistentEntityException("The ciudades with id " + id + " no longer exists.");
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
            setearRol(em);
            Ciudades ciudades;
            try {
                ciudades = em.getReference(Ciudades.class, id);
                ciudades.getSecuencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudades with id " + id + " no longer exists.", enfe);
            }
            em.remove(ciudades);
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

    public List<Ciudades> findCiudadesEntities() {
        return findCiudadesEntities(true, -1, -1);
    }

    public List<Ciudades> findCiudadesEntities(int maxResults, int firstResult) {
        return findCiudadesEntities(false, maxResults, firstResult);
    }

    private List<Ciudades> findCiudadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        List resultado = null;
        try {
            utx.begin();
            em.joinTransaction();
            setearRol(em);
            Query q = em.createQuery("select object(o) from Ciudades as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            utx.commit();
//            return q.getResultList();
            resultado = q.getResultList();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(CiudadesJpaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
        return resultado;
    }

    public Ciudades findCiudades(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            setearRol(em);
            return em.find(Ciudades.class, id);
        } finally {
            em.close();
        }
    }

    public int getCiudadesCount() {
        EntityManager em = getEntityManager();
        Long conteo = null;
        try {
            utx.begin();
            em.joinTransaction();
            setearRol(em);
            Query q = em.createQuery("select count(o) from Ciudades as o");
//            return ((Long) q.getSingleResult()).intValue();
            conteo =  ((Long) q.getSingleResult());
            utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(CiudadesJpaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
        return conteo.intValue();
    }

}
