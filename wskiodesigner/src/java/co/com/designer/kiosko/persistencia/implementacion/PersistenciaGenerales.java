package co.com.designer.kiosko.persistencia.implementacion;

import co.com.designer.kiosko.entidades.Generales;
import co.com.designer.kiosko.persistencia.interfaz.IPersistenciaGenerales;
/*import javax.ejb.Stateless;*/
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Felipe Trivi�o
 */

public class PersistenciaGenerales implements IPersistenciaGenerales {

    @Override
    public Generales consultarRutasGenerales(EntityManager eManager) {
        try {
            String sqlQuery = "SELECT g FROM Generales g";
            Query query = eManager.createQuery(sqlQuery);
            Generales g = (Generales) query.getResultList().get(0);
            return g;
        } catch (Exception e) {
            System.out.println("Error PersistenciaGenerales.consultarRutasGenerales: " + e);
            return null;
        }
    }
}
