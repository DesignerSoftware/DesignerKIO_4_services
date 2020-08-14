package co.com.designer.kiosko.persistencia.interfaz;



/**
 *
 * @author Felipe Trivi�o
 */

public interface IPersistenciaOpcionesKioskos {

    public java.util.List<co.com.designer.kiosko.entidades.OpcionesKioskos> consultarOpcionesPorPadre(javax.persistence.EntityManager eManager, java.math.BigInteger secuenciaPadre, java.math.BigInteger secuenciaEmpresa);
    
}
