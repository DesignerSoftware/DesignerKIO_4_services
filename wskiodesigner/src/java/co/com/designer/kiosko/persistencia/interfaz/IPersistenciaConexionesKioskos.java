package co.com.designer.kiosko.persistencia.interfaz;

import co.com.designer.kiosko.entidades.ConexionesKioskos;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe Trivi�o
 */
public interface IPersistenciaConexionesKioskos {

    public boolean registrarConexion(EntityManager eManager, co.com.designer.kiosko.entidades.ConexionesKioskos cnk);

    public ConexionesKioskos consultarConexionEmpleado(EntityManager eManager, java.lang.String codigoEmpleado, long nitEmpresa);

    public ConexionesKioskos consultarConexionEmpleado(EntityManager eManager, String numerodocumento);
    
    public ConexionesKioskos consultarConexionPersona(EntityManager eManager, String numeroDocumento, long nitEmpresa) ;
}
