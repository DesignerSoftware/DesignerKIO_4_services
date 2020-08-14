package co.com.designer.kiosko.persistencia.interfaz;

import co.com.designer.kiosko.entidades.VwVacaPendientesEmpleados;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author Edwin
 */

public interface IPersistenciaVwVacaPendientesEmpleados {
    public List<VwVacaPendientesEmpleados> consultarPeriodosPendientesEmpleado(EntityManager em, BigDecimal secEmpleado) throws Exception;
    public VwVacaPendientesEmpleados consultarPeriodoMasAntiguo(EntityManager em, BigDecimal secEmpleado, Date fechaContrato) throws Exception;
    public BigDecimal consultarCodigoJornada(EntityManager em, BigDecimal secEmpleado, Date fechaDisfrute) throws Exception;
    public boolean verificarFestivo(EntityManager em, Date fechaDisfrute) throws Exception;
    public boolean verificarDiaLaboral(EntityManager em, Date fechaDisfrute, BigDecimal codigoJornada) throws Exception;
    public BigDecimal consultaDiasPendientes(EntityManager em, BigDecimal secEmpleado) throws Exception;
    /**
     * M�todo que calcula la fecha de regreso a laborar de la novedad de vacaciones
     * que se esta creando.
     * @param em
     * @param secEmpleado
     * @param fechaIniVaca
     * @param dias
     * @return
     * @throws PersistenceException
     * @throws NullPointerException
     * @throws Exception 
     */
    public Date calculaFechaRegreso(EntityManager em, BigDecimal secEmpleado, Date fechaIniVaca, BigInteger dias) throws PersistenceException, NullPointerException, Exception;
    /**
     * M�todo que calcula la fecha de finalizaci�n de la novedad de vacaciones 
     * que se esta creando.
     * @param em
     * @param secEmpleado
     * @param fechaIniVaca
     * @param fechaRegreso
     * @return
     * @throws PersistenceException
     * @throws NullPointerException
     * @throws Exception 
     */
    public Date calculaFechaFinVaca(EntityManager em, BigDecimal secEmpleado, Date fechaIniVaca, Date fechaRegreso) throws PersistenceException, NullPointerException, Exception;
    /**
     * Calcula la fecha de pago de la novedad de vacaciones a crear, seg�n los 
     * criterios de la base de datos.
     * @param em
     * @param secEmpleado
     * @param fechaIniVaca
     * @param fechaRegreso
     * @param procDifNom
     * @return
     * @throws PersistenceException
     * @throws NullPointerException
     * @throws Exception 
     */
    public Date calculaFechaPago(EntityManager em, BigDecimal secEmpleado, Date fechaIniVaca, Date fechaRegreso, String procDifNom) throws PersistenceException, NullPointerException, Exception;
    /**
     * M�todo que consulta la fecha de �ltimo pago de la n�mina o de las vacaciones.
     * @param em
     * @param secEmpleado
     * @return 
     */
    public Date consultaFechaUltimoPago(EntityManager em, BigDecimal secEmpleado) throws Exception;
    public BigDecimal consultarDiasNoContinuos(EntityManager em, BigDecimal secEmpleado, Date fechaIngreso, Date fechaUltPago) throws Exception;
    /**
     * M�todo que consulta la fecha de pago la �ltima vacaci�n.
     * @param em
     * @param secEmpleado
     * @return 
     */
    public Date consultarVacaMaxPago(EntityManager em, BigDecimal secEmpleado) throws Exception ;
    /**
     * M�todo que consulta la fecha siguiente al final de las vacaciones, 
     * es decir, la fecha de regreso a laborar.
     * @param em
     * @param secEmpleado
     * @return 
     */
    public Date consultarVacaSigFinVaca(EntityManager em, BigDecimal secEmpleado) throws Exception;
    /**
     * M�todo para consultar los d�as pendientes reales del periodo, el cual se calcula teniendo en cuenta 
     * las solicitudes que est�n en estado GUARDADO, ENVIADO, AUTORIZADO y LIQUIDADO
     * @param em
     * @param rfVacacion secuencia de la vacacion sobre la cual se calculan los datos.
     * @return
     * @throws Exception 
     */
    public BigDecimal consultarDiasRealPendPeriodo(EntityManager em, BigDecimal rfVacacion) throws Exception;

    public BigDecimal consultarDiasPenGanDerecho(EntityManager em, BigDecimal rfEmpleado) throws Exception;

    public BigDecimal consultarDiasProvisionados(EntityManager em, BigDecimal rfEmpleado) throws Exception;

    public BigDecimal consultarDiasProvisiMenosDisfruta(EntityManager em, BigDecimal rfEmpleado) throws Exception;

    public BigDecimal consultarDiasSolicitados(EntityManager em, BigDecimal rfEmpleado) throws Exception;
}
