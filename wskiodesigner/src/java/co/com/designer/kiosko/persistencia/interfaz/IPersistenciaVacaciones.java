package co.com.designer.kiosko.persistencia.interfaz;

import co.com.designer.kiosko.entidades.Vacaciones;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

/**
 *
 * @author Edwin
 */

public interface IPersistenciaVacaciones {
    public List<Vacaciones> consultarVacasDispEmpleado(EntityManager em, BigDecimal secEmpleado);
}
