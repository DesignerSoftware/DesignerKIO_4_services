package co.com.designer.kiosko.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.*;


/**
 *
 * @author Felipe Trivi�o
 */
@Entity
@Table(name = "PREGUNTASKIOSKOS")
@NamedQueries({
    @NamedQuery(name = "PreguntasKioskos.findAll", query = "SELECT p FROM PreguntasKioskos p")})
public class PreguntasKioskos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "SECUENCIA")
    private BigDecimal secuencia;
    @Column(name = "CODIGO")
    private BigInteger codigo;
    @Column(name = "PREGUNTA")
    private String pregunta;

    public PreguntasKioskos() {
    }

    public PreguntasKioskos(BigDecimal secuencia) {
        this.secuencia = secuencia;
    }

    public BigDecimal getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(BigDecimal secuencia) {
        this.secuencia = secuencia;
    }

    public BigInteger getCodigo() {
        return codigo;
    }

    public void setCodigo(BigInteger codigo) {
        this.codigo = codigo;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secuencia != null ? secuencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntasKioskos)) {
            return false;
        }
        PreguntasKioskos other = (PreguntasKioskos) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.kiosko.administrar.entidades.PreguntasKioskos[ secuencia=" + secuencia + " ]";
    }
    
}
