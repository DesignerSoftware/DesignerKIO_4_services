/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.designer.kiosko.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;


/**
 *
 * @author 908036
 */
@Entity
@Table(name = "TIPOSDOCUMENTOS")
@NamedQueries({
    @NamedQuery(name = "TiposDocumentos.findAll", query = "SELECT t FROM TiposDocumentos t")})
public class TiposDocumentos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "SECUENCIA")
    private BigDecimal secuencia;
    @Basic(optional = false)
    @Column(name = "NOMBRELARGO")
    private String nombrelargo;
    @Column(name = "NOMBRECORTO")
    private String nombrecorto;

    public TiposDocumentos() {
    }

    public TiposDocumentos(BigDecimal secuencia) {
        this.secuencia = secuencia;
    }

    public TiposDocumentos(BigDecimal secuencia, String nombrelargo) {
        this.secuencia = secuencia;
        this.nombrelargo = nombrelargo;
    }

    public BigDecimal getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(BigDecimal secuencia) {
        this.secuencia = secuencia;
    }

    public String getNombrelargo() {
        return nombrelargo;
    }

    public void setNombrelargo(String nombrelargo) {
        this.nombrelargo = nombrelargo;
    }

    public String getNombrecorto() {
        return nombrecorto;
    }

    public void setNombrecorto(String nombrecorto) {
        this.nombrecorto = nombrecorto;
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
        if (!(object instanceof TiposDocumentos)) {
            return false;
        }
        TiposDocumentos other = (TiposDocumentos) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.kiosko.administrar.entidades.TiposDocumentos[ secuencia=" + secuencia + " ]";
    }
    
}
