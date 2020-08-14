package co.com.designer.kiosko.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author Felipe Trivi�o
 */
@Entity
@Table(name = "CONFICORREOKIOSKO")
@NamedQueries({
    @NamedQuery(name = "ConfiguracionCorreo.findAll", query = "SELECT c FROM ConfiguracionCorreo c")})
public class ConfiguracionCorreo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    
    @Column(name = "SECUENCIA")
    private BigDecimal secuencia;
    @JoinColumn(name = "EMPRESA", referencedColumnName = "SECUENCIA")
    @ManyToOne(optional = false)
    private Empresas empresa;
    @Basic(optional = false)
    
    
    @Column(name = "SERVIDORSMTP")
    private String servidorSmtp;
    @Basic(optional = false)
    
    
    @Column(name = "PUERTO")
    private String puerto;
    
    @Column(name = "STARTTLS")
    private String starttls;
    
    @Column(name = "USARSSL")
    private String usarssl;
    @Basic(optional = false)
    
    @Column(name = "AUTENTICADO")
    private String autenticado;
    @Basic(optional = false)
    
    
    @Column(name = "REMITENTE")
    private String remitente;
    
    @Column(name = "CLAVE")
    private String clave;

    public ConfiguracionCorreo() {
    }

    public ConfiguracionCorreo(BigDecimal secuencia) {
        this.secuencia = secuencia;
    }

    public ConfiguracionCorreo(BigDecimal secuencia, String servidorSmtp, String puerto, String autenticado, String remitente) {
        this.secuencia = secuencia;
        this.servidorSmtp = servidorSmtp;
        this.puerto = puerto;
        this.autenticado = autenticado;
        this.remitente = remitente;
    }

    public BigDecimal getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(BigDecimal secuencia) {
        this.secuencia = secuencia;
    }

    public Empresas getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    public String getServidorSmtp() {
        return servidorSmtp;
    }

    public void setServidorSmtp(String servidorSmtp) {
        this.servidorSmtp = servidorSmtp;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getStarttls() {
        return starttls;
    }

    public void setStarttls(String starttls) {
        this.starttls = starttls;
    }

    public String getUsarssl() {
        return usarssl;
    }

    public void setUsarssl(String usarssl) {
        this.usarssl = usarssl;
    }

    public String getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(String autenticado) {
        this.autenticado = autenticado;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
        if (!(object instanceof ConfiguracionCorreo)) {
            return false;
        }
        ConfiguracionCorreo other = (ConfiguracionCorreo) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.kiosko.administrar.entidades.ConfiguracionCorreo[ secuencia=" + secuencia + " ]";
    }
}
