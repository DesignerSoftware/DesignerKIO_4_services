package co.com.designer.kiosko.persistencia.interfaz;

import co.com.designer.kiosko.entidades.KioEstadosLocalizaSolici;
import co.com.designer.kiosko.entidades.KioLocalizacionesEmpl;
import co.com.designer.kiosko.entidades.KioSolicisLocaliza;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
/*import javax.ejb.Local;
import javax.ejb.TransactionRolledbackLocalException;*/
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

/**
 *
 * @author Edwin Hastamorir
 */

public interface IPersistenciaKioEstadosLocalizaSolici {

    public void crearEstadoSolicitud(EntityManager em, KioSolicisLocaliza solicitud, BigDecimal secEmplEjecuta, String estado, String motivo, BigInteger secPersona) throws EntityExistsException, Exception;

    public List<KioEstadosLocalizaSolici> consultarEstadosLocalizaXJefe(EntityManager em, BigDecimal secEmpleado, String estado) throws Exception;
    public List<KioEstadosLocalizaSolici> consultarEstadosLocalizaXEmpleado(EntityManager em, BigDecimal secEmpleado, String estado) throws Exception;

    public List<KioLocalizacionesEmpl> consultarLocalizacionesXSolicitud(EntityManager em, BigDecimal secSolicitud) throws Exception;
    
}
