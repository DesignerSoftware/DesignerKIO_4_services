package co.com.designer.kiosko.persistencia.interfaz;

import co.com.designer.kiosko.entidades.KioSolicisLocaliza;
/*import javax.ejb.Local;
import javax.ejb.TransactionRolledbackLocalException;*/
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Edwin Hastamorir
 */
//@Local
public interface IPersistenciaKioSolicisLocaliza {
    //public void crearSolicitud(EntityManager em, KioSolicisLocaliza solicitud) throws EntityExistsException, TransactionRolledbackLocalException, Exception;
    public void crearSolicitud(EntityManager em, KioSolicisLocaliza solicitud) throws EntityExistsException, Exception;
    public KioSolicisLocaliza recargarSolicitud(EntityManager em, KioSolicisLocaliza solicitud) throws NoResultException, NonUniqueResultException, IllegalStateException ;
}
