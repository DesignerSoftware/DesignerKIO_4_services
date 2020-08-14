package co.com.designer.kiosko.persistencia.interfaz;

import co.com.designer.kiosko.entidades.Empleados;
import co.com.designer.kiosko.entidades.Empresas;
import co.com.designer.kiosko.entidades.Personas;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Felipe Trivi�o
 */
public interface IPersistenciaEmpleados {

    public co.com.designer.kiosko.entidades.Empleados consultarEmpleado(EntityManager eManager, BigInteger codigoEmpleado, long nit);

    public Date fechaContratoEmpl(EntityManager em, BigDecimal secEmpleado);

    public boolean esJefe(EntityManager em, BigDecimal secEmpleado, BigInteger secEmpresa);

    /**
     * M�todo para consultar el empleado jefe de un empleado.
     *
     * @param em
     * @param secEmpleado
     * @param secEmpresa
     * @return
     * @throws Exception
     */
    public Empleados consutarJefe(EntityManager em, BigDecimal secEmpleado, BigInteger secEmpresa) throws Exception;

    /**
     * M�todo para consultar a todos los empleados de una empresa.
     *
     * @param em
     * @param nit
     * @return
     * @throws Exception
     */
    public List consultarEmpleadosEmpresa(EntityManager em, long nit) throws Exception;
    /**
     * Consulta la lista de empleados asociados a una empresa y a un empleado jefe.
     * Realiza la consulta directamente sobre la tabla vigenciasCargos, ignorando la relaci�n 
     * con la solicitud.
     * @param em
     * @param nit
     * @param secJefe
     * @return Lista de empleados a cargo del usuario que ingresa.
     * @throws Exception PersistenceException, IllegalStateException
     */
    public List consultarEmpleadosXJefe(EntityManager em, long nit, BigDecimal secJefe) throws Exception;

    public Empleados consultaEmpleadoxSec(EntityManager em, BigDecimal secEmpleado) throws Exception;
    public Personas consultarPersona(EntityManager eManager, BigInteger numeroDocumento);
    public Empleados consultarEmpleadoXPersoEmpre(EntityManager em, BigInteger numeroDocumento, long nit) throws Exception;
    public boolean esAutorizador(EntityManager em, BigDecimal rfPersona );
    public BigInteger consultarEmpresaXNit(EntityManager em, long nit);
    public Empresas consultarEmpresa(EntityManager em, long nit);
    public List consultarEmpleadosXAutorizador(EntityManager em, long nit, BigInteger secPersona) throws Exception;
    public Personas consultaPersonaxSec(EntityManager em, BigInteger secPersona) throws Exception;
    public Personas consutarAutorizador(EntityManager em, BigDecimal secEmpleado, BigInteger secEmpresa) throws Exception;
}
