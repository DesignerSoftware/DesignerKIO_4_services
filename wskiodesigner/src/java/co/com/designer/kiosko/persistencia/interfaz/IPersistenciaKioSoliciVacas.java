package co.com.designer.kiosko.persistencia.interfaz;

import co.com.designer.kiosko.entidades.KioSoliciVacas;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/*import javax.ejb.Local;
import javax.ejb.TransactionRolledbackLocalException;*/
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Edwin
 */

public interface IPersistenciaKioSoliciVacas {
    //public void crearSolicitud(EntityManager em, KioSoliciVacas solicitud) throws EntityExistsException, TransactionRolledbackLocalException, Exception;
    public void crearSolicitud(EntityManager em, KioSoliciVacas solicitud) throws EntityExistsException,  Exception;
    public KioSoliciVacas recargarSolicitud(EntityManager em, KioSoliciVacas solicitud) throws NoResultException, NonUniqueResultException, IllegalStateException;
    public void modificarSolicitud(EntityManager em, KioSoliciVacas solicitud) throws Exception;
    /**
     * M�todo para consultar las solicitudes que tienen estado ENVIADO
     * @param em EntityManager
     * @return Lista de la las solicitudes obtenidas.
     * @throws Exception 
     */
    public List consultaSolicitudesEnviadas(EntityManager em) throws Exception;
    public BigDecimal verificaExistenciaSolicitud(EntityManager em, BigDecimal secEmpleado, Date fechaIniVaca) throws Exception;
}
