package co.com.designer.kiosko.persistencia.interfaz;

import javax.persistence.EntityManager;

/**
 *
 * @author Felipe Trivi�o
 */
public interface IPersistenciaUtilidadesBD {

    public byte[] encriptar(javax.persistence.EntityManager eManager, java.lang.String valor);

    public String desencriptar(javax.persistence.EntityManager eManager, byte[] valor);

    public String consultaUsuario(EntityManager eManager);
    
}
