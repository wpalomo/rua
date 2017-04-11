/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "gen_accion_motivo_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenAccionMotivoEmpleado.findAll", query = "SELECT g FROM GenAccionMotivoEmpleado g"),
    @NamedQuery(name = "GenAccionMotivoEmpleado.findByIdeGeame", query = "SELECT g FROM GenAccionMotivoEmpleado g WHERE g.ideGeame = :ideGeame"),
    @NamedQuery(name = "GenAccionMotivoEmpleado.findByCampoVisibleGeame", query = "SELECT g FROM GenAccionMotivoEmpleado g WHERE g.campoVisibleGeame = :campoVisibleGeame"),
    @NamedQuery(name = "GenAccionMotivoEmpleado.findByCampoDatoGeame", query = "SELECT g FROM GenAccionMotivoEmpleado g WHERE g.campoDatoGeame = :campoDatoGeame"),
    @NamedQuery(name = "GenAccionMotivoEmpleado.findByActivoGeame", query = "SELECT g FROM GenAccionMotivoEmpleado g WHERE g.activoGeame = :activoGeame"),
    @NamedQuery(name = "GenAccionMotivoEmpleado.findByUsuarioIngre", query = "SELECT g FROM GenAccionMotivoEmpleado g WHERE g.usuarioIngre = :usuarioIngre"),
    @NamedQuery(name = "GenAccionMotivoEmpleado.findByFechaIngre", query = "SELECT g FROM GenAccionMotivoEmpleado g WHERE g.fechaIngre = :fechaIngre"),
    @NamedQuery(name = "GenAccionMotivoEmpleado.findByUsuarioActua", query = "SELECT g FROM GenAccionMotivoEmpleado g WHERE g.usuarioActua = :usuarioActua"),
    @NamedQuery(name = "GenAccionMotivoEmpleado.findByFechaActua", query = "SELECT g FROM GenAccionMotivoEmpleado g WHERE g.fechaActua = :fechaActua"),
    @NamedQuery(name = "GenAccionMotivoEmpleado.findByHoraIngre", query = "SELECT g FROM GenAccionMotivoEmpleado g WHERE g.horaIngre = :horaIngre"),
    @NamedQuery(name = "GenAccionMotivoEmpleado.findByHoraActua", query = "SELECT g FROM GenAccionMotivoEmpleado g WHERE g.horaActua = :horaActua"),
    @NamedQuery(name = "GenAccionMotivoEmpleado.findByAnteriorGeame", query = "SELECT g FROM GenAccionMotivoEmpleado g WHERE g.anteriorGeame = :anteriorGeame")})
public class GenAccionMotivoEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ide_geame")
    private Integer ideGeame;
    @Column(name = "campo_visible_geame")
    private String campoVisibleGeame;
    @Column(name = "campo_dato_geame")
    private String campoDatoGeame;
    @Basic(optional = false)
    @Column(name = "activo_geame")
    private boolean activoGeame;
    @Column(name = "usuario_ingre")
    private String usuarioIngre;
    @Column(name = "fecha_ingre")
    @Temporal(TemporalType.DATE)
    private Date fechaIngre;
    @Column(name = "usuario_actua")
    private String usuarioActua;
    @Column(name = "fecha_actua")
    @Temporal(TemporalType.DATE)
    private Date fechaActua;
    @Column(name = "hora_ingre")
    @Temporal(TemporalType.TIME)
    private Date horaIngre;
    @Column(name = "hora_actua")
    @Temporal(TemporalType.TIME)
    private Date horaActua;
    @Column(name = "anterior_geame")
    private Boolean anteriorGeame;
    @OneToMany(mappedBy = "ideGeame")
    private Collection<GenDetalleEmpleadoDepartame> genDetalleEmpleadoDepartameCollection;
    @JoinColumn(name = "ide_gemed", referencedColumnName = "ide_gemed")
    @ManyToOne
    private GenMotivoEmpleadoDepa ideGemed;
    @JoinColumn(name = "ide_geaed", referencedColumnName = "ide_geaed")
    @ManyToOne
    private GenAccionEmpleadoDepa ideGeaed;

    public GenAccionMotivoEmpleado() {
    }

    public GenAccionMotivoEmpleado(Integer ideGeame) {
        this.ideGeame = ideGeame;
    }

    public GenAccionMotivoEmpleado(Integer ideGeame, boolean activoGeame) {
        this.ideGeame = ideGeame;
        this.activoGeame = activoGeame;
    }

    public Integer getIdeGeame() {
        return ideGeame;
    }

    public void setIdeGeame(Integer ideGeame) {
        this.ideGeame = ideGeame;
    }

    public String getCampoVisibleGeame() {
        return campoVisibleGeame;
    }

    public void setCampoVisibleGeame(String campoVisibleGeame) {
        this.campoVisibleGeame = campoVisibleGeame;
    }

    public String getCampoDatoGeame() {
        return campoDatoGeame;
    }

    public void setCampoDatoGeame(String campoDatoGeame) {
        this.campoDatoGeame = campoDatoGeame;
    }

    public boolean getActivoGeame() {
        return activoGeame;
    }

    public void setActivoGeame(boolean activoGeame) {
        this.activoGeame = activoGeame;
    }

    public String getUsuarioIngre() {
        return usuarioIngre;
    }

    public void setUsuarioIngre(String usuarioIngre) {
        this.usuarioIngre = usuarioIngre;
    }

    public Date getFechaIngre() {
        return fechaIngre;
    }

    public void setFechaIngre(Date fechaIngre) {
        this.fechaIngre = fechaIngre;
    }

    public String getUsuarioActua() {
        return usuarioActua;
    }

    public void setUsuarioActua(String usuarioActua) {
        this.usuarioActua = usuarioActua;
    }

    public Date getFechaActua() {
        return fechaActua;
    }

    public void setFechaActua(Date fechaActua) {
        this.fechaActua = fechaActua;
    }

    public Date getHoraIngre() {
        return horaIngre;
    }

    public void setHoraIngre(Date horaIngre) {
        this.horaIngre = horaIngre;
    }

    public Date getHoraActua() {
        return horaActua;
    }

    public void setHoraActua(Date horaActua) {
        this.horaActua = horaActua;
    }

    public Boolean getAnteriorGeame() {
        return anteriorGeame;
    }

    public void setAnteriorGeame(Boolean anteriorGeame) {
        this.anteriorGeame = anteriorGeame;
    }

    @XmlTransient
    public Collection<GenDetalleEmpleadoDepartame> getGenDetalleEmpleadoDepartameCollection() {
        return genDetalleEmpleadoDepartameCollection;
    }

    public void setGenDetalleEmpleadoDepartameCollection(Collection<GenDetalleEmpleadoDepartame> genDetalleEmpleadoDepartameCollection) {
        this.genDetalleEmpleadoDepartameCollection = genDetalleEmpleadoDepartameCollection;
    }

    public GenMotivoEmpleadoDepa getIdeGemed() {
        return ideGemed;
    }

    public void setIdeGemed(GenMotivoEmpleadoDepa ideGemed) {
        this.ideGemed = ideGemed;
    }

    public GenAccionEmpleadoDepa getIdeGeaed() {
        return ideGeaed;
    }

    public void setIdeGeaed(GenAccionEmpleadoDepa ideGeaed) {
        this.ideGeaed = ideGeaed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideGeame != null ? ideGeame.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GenAccionMotivoEmpleado)) {
            return false;
        }
        GenAccionMotivoEmpleado other = (GenAccionMotivoEmpleado) object;
        if ((this.ideGeame == null && other.ideGeame != null) || (this.ideGeame != null && !this.ideGeame.equals(other.ideGeame))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "portal.entidades.GenAccionMotivoEmpleado[ ideGeame=" + ideGeame + " ]";
    }
    
}