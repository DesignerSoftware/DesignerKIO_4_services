package co.com.designer.kiosko.persistencia.interfaz;

/**
 *
 * @author Felipe Trivi�o
 */
public interface IPersistenciaConfiguracionCorreo {

    public co.com.designer.kiosko.entidades.ConfiguracionCorreo consultarConfiguracionServidorCorreo(javax.persistence.EntityManager eManager, java.math.BigInteger secuenciaEmpresa);
    
}
