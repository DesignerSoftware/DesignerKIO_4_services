package co.com.designer.kiosko.persistencia.implementacion;

import co.com.designer.kiosko.entidades.ConexionesKioskos;
import co.com.designer.kiosko.persistencia.interfaz.IPersistenciaConexionesKioskos;
//import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
/*import javax.ejb.Stateless;*/
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Felipe Trivi�o
 */

public class PersistenciaConexionesKioskos implements IPersistenciaConexionesKioskos {

    @Override
    public boolean registrarConexion(EntityManager eManager, ConexionesKioskos cnk) {
        boolean resp;
        System.out.println(this.getClass().getName()+".registrarConexion()");
        System.out.println("Se cre� entityManager.");
        System.out.println("eManager: "+eManager.toString());
        Map<String,Object> propiedades = eManager.getProperties();
        cnk.setUltimaconexion(new Date());
        try {
            eManager.merge(cnk);
            resp = true;
        } catch (PersistenceException re){
            System.out.println("PersistenciaConexionesKioskos.registrarConexion");
            System.out.println("Error PersistenceException: " + re);
            resp = false;
        } catch (Exception e) {
            System.out.println("PersistenciaConexionesKioskos.registrarConexion");
            System.out.println("Error generico: " + e);
            resp = false;
        }
        return resp;
    }

    @Override
    public ConexionesKioskos consultarConexionEmpleado(EntityManager eManager, String codigoEmpleado, long nitEmpresa) {
        System.out.println(this.getClass().getName()+".consultarConexionEmpleado()-2");
        System.out.println("eManager: " + eManager);
        System.out.println("codigoEmpleado: " + codigoEmpleado);
        System.out.println("nitEmpresa: " + nitEmpresa);
        System.out.println("Codigoempleado:: "+codigoEmpleado); //agregado por Tm 20190828
            System.out.println("nitEmpresa:: "+nitEmpresa); // agregado por Tm 20190828
        try {
            String sqlQuery = "SELECT ck FROM ConexionesKioskos ck WHERE ck.empleado.codigoempleado = :codigoEmpleado and ck.empleado.empresa.nit = :nitEmpresa";
            Query query = eManager.createQuery(sqlQuery);
            query.setParameter("codigoEmpleado", new BigInteger(codigoEmpleado));
            query.setParameter("nitEmpresa", nitEmpresa);
            return (ConexionesKioskos) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error PersistenciaConexionesKioskos.consultarConexionEmpleado(eManager, codigoempleado, nitEmpresa): " + e);
            try {
            } catch (NullPointerException npe) {
                System.out.println("La transacci�n es nula.");
            }
            return null;
        }
    }
    
    @Override
    public ConexionesKioskos consultarConexionEmpleado(EntityManager eManager, String numerodocumento) {
        System.out.println(this.getClass().getName()+".consultarConexionEmpleado()");
        System.out.println("eManager: " + eManager);
        System.out.println("codigoEmpleado: " + numerodocumento);
        try {
            String sqlQuery = "SELECT ck FROM ConexionesKioskos ck WHERE ck.persona.numerodocumento = :numeroDocumento ";
            Query query = eManager.createQuery(sqlQuery);
            query.setParameter("numeroDocumento", new BigInteger(numerodocumento));
            return (ConexionesKioskos) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error PersistenciaConexionesKioskos.consultarConexionEmpleado(eManager, numerodocumento): " + e);
            try {
            } catch (NullPointerException npe) {
                System.out.println("La transacci�n es nula.");
            }
            return null;
        }
    }
    
     @Override
    public ConexionesKioskos consultarConexionPersona(EntityManager eManager, String numeroDocumento, long nitEmpresa) {
        System.out.println(this.getClass().getName()+".consultarConexionPersona()");
        System.out.println("eManager: " + eManager);
        System.out.println("codigoEmpleado: " + numeroDocumento);
        System.out.println("nitEmpresa: " + nitEmpresa);
        try {
            String sqlQuery = "SELECT ck FROM ConexionesKioskos ck WHERE ck.persona.numerodocumento = :numeroDocumento and ck.nitEmpresa = :nitEmpresa";
            Query query = eManager.createQuery(sqlQuery);
            query.setParameter("numeroDocumento", new BigInteger(numeroDocumento));
            query.setParameter("nitEmpresa", nitEmpresa);
            return (ConexionesKioskos) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error PersistenciaConexionesKioskos.consultarConexionPersona(eManager, numeroDocumento, nitEmpresa): " + e);
            try {
            } catch (NullPointerException npe) {
                System.out.println("La transacci�n es nula.");
            }
            return null;
        }
    }
}
