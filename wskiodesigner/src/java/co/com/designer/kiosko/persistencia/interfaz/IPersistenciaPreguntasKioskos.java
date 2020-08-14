package co.com.designer.kiosko.persistencia.interfaz;

/**
 *
 * @author Felipe Trivi�o
 */
public interface IPersistenciaPreguntasKioskos {

    public java.util.List<co.com.designer.kiosko.entidades.PreguntasKioskos> obtenerPreguntasSeguridad(javax.persistence.EntityManager eManager);

    public co.com.designer.kiosko.entidades.PreguntasKioskos consultarPreguntaSeguridad(javax.persistence.EntityManager eManager, java.math.BigDecimal secuencia);
    
}
