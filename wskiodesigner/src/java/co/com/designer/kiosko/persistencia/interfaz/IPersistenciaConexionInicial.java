package co.com.designer.kiosko.persistencia.interfaz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public interface IPersistenciaConexionInicial {

    /**
     * Método que setea el kiosko usando el esquema que se proporcione para
     * complementar el nombre del rol con que se har�n las consultas.
     *
     * @param eManager
     * @param esquema
     * @throws java.lang.Exception
     */
    public void setearKiosko(EntityManager eManager, String esquema) throws Exception;

    public boolean validarUsuarioyEmpresa(EntityManager eManager, String usuario, String nitEmpresa) throws Exception;

    public boolean validarAutorizador(EntityManager eManager, String usuario) throws Exception;

    public boolean validarUsuarioRegistrado(EntityManager eManager, String usuario, String nitEmpresa) throws Exception ;

    public boolean validarIngresoUsuarioRegistrado(EntityManager eManager, String usuario, String clave, String nitEmpresa) throws Exception ;

    public EntityManager validarConexionUsuario(EntityManagerFactory emf) throws Exception ;

    public boolean validarEstadoUsuario(EntityManager eManager, String usuario, String nitEmpresa) throws Exception;

    public boolean validarEstadoUsuario(EntityManager eManager, String usuario) throws Exception;

}
