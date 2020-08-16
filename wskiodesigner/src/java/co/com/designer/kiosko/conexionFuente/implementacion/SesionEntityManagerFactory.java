package co.com.designer.kiosko.conexionFuente.implementacion;

import co.com.designer.kiosko.conexionFuente.interfaz.ISesionEntityManagerFactory;
import java.io.Serializable;
//import javax.ejb.Stateful;
//import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Felipe Trivi�o
 * @author Edwin Hastamorir
 */
//@Stateless
//@Stateful
public class SesionEntityManagerFactory implements ISesionEntityManagerFactory, Serializable {
//public class SesionEntityManagerFactory implements Serializable {

    @Override
    public EntityManagerFactory crearConexionUsuario(String unidadPersistencia) {
        try {
            return Persistence.createEntityManagerFactory(unidadPersistencia);
        } catch (Exception e) {
            System.out.println("Error SesionEntityManagerFactory.crearConexionUsuario: " + e);
            return null;
        }
    }

}
