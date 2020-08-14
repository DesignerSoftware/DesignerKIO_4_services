package co.com.designer.kiosko.persistencia.interfaz;

/**
 *
 * @author Felipe Trivi�o
 */
public interface IPersistenciaParametrizaClave {

    public co.com.designer.kiosko.entidades.ParametrizaClave obtenerFormatoClave(javax.persistence.EntityManager eManager, long nitEmpresa);
    
}
