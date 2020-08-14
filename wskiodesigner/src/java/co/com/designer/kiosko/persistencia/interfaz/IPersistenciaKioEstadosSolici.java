package co.com.designer.kiosko.persistencia.interfaz;

import co.com.designer.kiosko.entidades.KioEstadosSolici;
import co.com.designer.kiosko.entidades.KioSoliciVacas;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Edwin
 */

public interface IPersistenciaKioEstadosSolici {
    /**
     * M�todo que consulta los estados de las solicitudes (KioEstadosSolici) por empleado.
     * @param em EntityManager ya inicializado.
     * @param secEmpleado Secuencia del empleado
     * @return Lista de KioEstadosSolici asociados al empleado.s
     * @throws Exception
     */
    public List<KioEstadosSolici> consultarEstadosXEmpl(EntityManager em, BigDecimal secEmpleado) throws Exception ;
    /**
     * 
     * @param em EntityManager
     * @param solicitud
     * @return Estado de la solicitud
     */
    public KioEstadosSolici consultarEstadosXSolici(EntityManager em, KioSoliciVacas solicitud);
    /**
     * M�todo que crea el estado de la solicitud. Se utiliza al momento de guardar la solicitud.
     * @param em EntityManager
     * @param solicitud
     * @param secEmplEjecuta Secuencia del empleado que ejecuta la creaci�n de la solicitud.
     * @param estado Puede ser: GUARDADO, ENVIADO, AUTORIZADO, LIQUIDADO, RECHAZADO, CANCELADO
     * @param motivo Raz�n por la cual rechaza la solicitud
     * @throws EntityExistsException Si la entidad ya existe en la base de datos.
     * @throws TransactionRolledbackLocalException Si la transacci�n fue abortada.
     * @throws Exception Si hay error en general.s
     */
    //public void crearEstadoSolicitud(EntityManager em, KioSoliciVacas solicitud, BigDecimal secEmplEjecuta, String estado, String motivo) throws EntityExistsException, TransactionRolledbackLocalException, Exception ;
    public void crearEstadoSolicitud(EntityManager em, KioSoliciVacas solicitud, BigDecimal secEmplEjecuta, String estado, String motivo) throws EntityExistsException,  Exception ;
    /**
     * M�todo que consulta los estados determinados de las solicitudes que tiene asociadas un empleado.
     * @param em EntityManager
     * @param secEmpleado Secuencia del empleado objetivo
     * @param estado Puede ser: GUARDADO, ENVIADO, AUTORIZADO, LIQUIDADO, RECHAZADO, CANCELADO
     * @return 
     */
    public List<KioEstadosSolici> consultarEstadosXEmplEsta(EntityManager em, BigDecimal secEmpleado, String estado);
    
    public KioEstadosSolici recargarEstadoSolicitud(EntityManager em, KioEstadosSolici estado) throws NoResultException, NonUniqueResultException, IllegalStateException, Exception;
    /**
     * 
     * @param em entityManager
     * @param secEmpresa secuencia de la empresa
     * @return
     * @throws Exception 
     */
    public List<KioEstadosSolici> consultarEstadosXEmpre(EntityManager em, BigInteger secEmpresa ) throws Exception;
    /**
     * M�todo que realiza la consulta de los estados de solicitud con determinado estado para determinada empresa.
     * @param em
     * @param secEmpresa
     * @param estado
     * @return
     * @throws Exception 
     */
    public List<KioEstadosSolici> consultarEstadosXEmpre(EntityManager em, BigInteger secEmpresa, String estado) throws Exception ;
    /**
     * M�todo que realiza la consulta de los estados de solicitud con determinado estado para determinada empresa 
     * y para determinado jefe.
     * @param em
     * @param secEmpresa
     * @param estado
     * @param secJefe
     * @return
     * @throws Exception 
     */
    public List<KioEstadosSolici> consultarEstadosXEmpre(EntityManager em, BigInteger secEmpresa, String estado, BigDecimal secJefe) throws Exception;
    /**
     * M�todo para crear la novedad de vacaciones en el sistema de n�mina Designer.NOM
     * Recibe la solicitud a utilizar para crear la novedad.
     * @param em
     * @param solicitud
     * @return respuesta del paquete
     * @throws EntityExistsException
     * @throws TransactionRolledbackLocalException
     * @throws Exception 
     */
    //public String registrarNovedad(EntityManager em, KioSoliciVacas solicitud) throws EntityExistsException, TransactionRolledbackLocalException, Exception;
    public String registrarNovedad(EntityManager em, KioSoliciVacas solicitud) throws EntityExistsException,  Exception;
    public List<KioEstadosSolici> consultarEstadosXEmpre(EntityManager em, String estado, BigInteger secPersona) throws Exception;
    public void crearEstadoSolicitud(EntityManager em, KioSoliciVacas solicitud,
            BigDecimal secEmplEjecuta, String estado,
            //String motivo, BigInteger secPersona) throws EntityExistsException, TransactionRolledbackLocalException, Exception;
            String motivo, BigInteger secPersona) throws EntityExistsException,  Exception;
}
