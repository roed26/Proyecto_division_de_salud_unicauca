/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "control_paciente_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlPacienteSexual.findAll", query = "SELECT c FROM ControlPacienteSexual c")
    , @NamedQuery(name = "ControlPacienteSexual.findByFechaControl", query = "SELECT c FROM ControlPacienteSexual c WHERE c.fechaControl = :fechaControl")
    , @NamedQuery(name = "ControlPacienteSexual.findByDescripcionControl", query = "SELECT c FROM ControlPacienteSexual c WHERE c.descripcionControl = :descripcionControl")
    , @NamedQuery(name = "ControlPacienteSexual.findByRemision", query = "SELECT c FROM ControlPacienteSexual c WHERE c.remision = :remision")
    , @NamedQuery(name = "ControlPacienteSexual.findByFechaProximaCita", query = "SELECT c FROM ControlPacienteSexual c WHERE c.fechaProximaCita = :fechaProximaCita")
    , @NamedQuery(name = "ControlPacienteSexual.findByProfesionalQueAtiende", query = "SELECT c FROM ControlPacienteSexual c WHERE c.profesionalQueAtiende = :profesionalQueAtiende")
    , @NamedQuery(name = "ControlPacienteSexual.findBySatisfaccionMetodo", query = "SELECT c FROM ControlPacienteSexual c WHERE c.satisfaccionMetodo = :satisfaccionMetodo")
    , @NamedQuery(name = "ControlPacienteSexual.findByCambioMetodo", query = "SELECT c FROM ControlPacienteSexual c WHERE c.cambioMetodo = :cambioMetodo")
    , @NamedQuery(name = "ControlPacienteSexual.findByMetodoCambiado", query = "SELECT c FROM ControlPacienteSexual c WHERE c.metodoCambiado = :metodoCambiado")
    , @NamedQuery(name = "ControlPacienteSexual.findByIdControlSexual", query = "SELECT c FROM ControlPacienteSexual c WHERE c.idControlSexual = :idControlSexual")
    , @NamedQuery(name = "ControlPacienteSexual.findByIdHistoria", query = "SELECT c FROM ControlPacienteSexual c WHERE c.id = :id")
    , @NamedQuery(name = "ControlPacienteSexual.findByYearMes", query = "SELECT c FROM ControlPacienteSexual c WHERE FUNCTION('YEAR', c.fechaControl) = :year AND FUNCTION('MONTH', c.fechaControl) = :month")
     , @NamedQuery(name = "ControlPacienteSexual.findByYear", query = "SELECT c FROM ControlPacienteSexual c WHERE FUNCTION('YEAR', c.fechaControl) = :year")})

public class ControlPacienteSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "FECHA_CONTROL")
    @Temporal(TemporalType.DATE)
    private Date fechaControl;
    @Size(max = 700)
    @Column(name = "DESCRIPCION_CONTROL")
    private String descripcionControl;
    @Size(max = 40)
    @Column(name = "REMISION")
    private String remision;
    @Column(name = "FECHA_PROXIMA_CITA")
    @Temporal(TemporalType.DATE)
    private Date fechaProximaCita;
    @Size(max = 40)
    @Column(name = "PROFESIONAL_QUE_ATIENDE")
    private String profesionalQueAtiende;
    @Size(max = 2)
    @Column(name = "SATISFACCION_METODO")
    private String satisfaccionMetodo;
    @Size(max = 2)
    @Column(name = "CAMBIO_METODO")
    private String cambioMetodo;
    @Size(max = 50)
    @Column(name = "METODO_CAMBIADO")
    private String metodoCambiado;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CONTROL_SEXUAL")
    private Long idControlSexual;
    @ManyToMany(mappedBy = "controlPacienteSexualList")
    private List<OtrosSintomasControlSexual> otrosSintomasControlSexualList;
    @JoinTable(name = "control_sintomas_sexual", joinColumns = {
        @JoinColumn(name = "ID_CONTROL_SEXUAL", referencedColumnName = "ID_CONTROL_SEXUAL")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_SINTOMA_CONTROL", referencedColumnName = "ID_SINTOMA_CONTROL")})
    @ManyToMany
    private List<SintomasControlSexual> sintomasControlSexualList;
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    @ManyToOne
    private HistoriaModuloSexualidad id;
    @OneToMany(mappedBy = "idControlSexual")
    private List<ExamenFisicoSexual> examenFisicoSexualList;

    public ControlPacienteSexual() {
    }

    public ControlPacienteSexual(Long idControlSexual) {
        this.idControlSexual = idControlSexual;
    }

    public Date getFechaControl() {
        return fechaControl;
    }

    public void setFechaControl(Date fechaControl) {
        this.fechaControl = fechaControl;
    }

    public String getDescripcionControl() {
        return descripcionControl;
    }

    public void setDescripcionControl(String descripcionControl) {
        this.descripcionControl = descripcionControl;
    }

    public String getRemision() {
        return remision;
    }

    public void setRemision(String remision) {
        this.remision = remision;
    }

    public Date getFechaProximaCita() {
        return fechaProximaCita;
    }

    public void setFechaProximaCita(Date fechaProximaCita) {
        this.fechaProximaCita = fechaProximaCita;
    }

    public String getProfesionalQueAtiende() {
        return profesionalQueAtiende;
    }

    public void setProfesionalQueAtiende(String profesionalQueAtiende) {
        this.profesionalQueAtiende = profesionalQueAtiende;
    }

    public String getSatisfaccionMetodo() {
        return satisfaccionMetodo;
    }

    public void setSatisfaccionMetodo(String satisfaccionMetodo) {
        this.satisfaccionMetodo = satisfaccionMetodo;
    }

    public String getCambioMetodo() {
        return cambioMetodo;
    }

    public void setCambioMetodo(String cambioMetodo) {
        this.cambioMetodo = cambioMetodo;
    }

    public String getMetodoCambiado() {
        return metodoCambiado;
    }

    public void setMetodoCambiado(String metodoCambiado) {
        this.metodoCambiado = metodoCambiado;
    }

    public Long getIdControlSexual() {
        return idControlSexual;
    }

    public void setIdControlSexual(Long idControlSexual) {
        this.idControlSexual = idControlSexual;
    }

    @XmlTransient
    public List<OtrosSintomasControlSexual> getOtrosSintomasControlSexualList() {
        return otrosSintomasControlSexualList;
    }

    public void setOtrosSintomasControlSexualList(List<OtrosSintomasControlSexual> otrosSintomasControlSexualList) {
        this.otrosSintomasControlSexualList = otrosSintomasControlSexualList;
    }

    @XmlTransient
    public List<SintomasControlSexual> getSintomasControlSexualList() {
        return sintomasControlSexualList;
    }

    public void setSintomasControlSexualList(List<SintomasControlSexual> sintomasControlSexualList) {
        this.sintomasControlSexualList = sintomasControlSexualList;
    }

    public HistoriaModuloSexualidad getId() {
        return id;
    }

    public void setId(HistoriaModuloSexualidad id) {
        this.id = id;
    }

    public List<ExamenFisicoSexual> getExamenFisicoSexualList() {
        return examenFisicoSexualList;
    }

    public void setExamenFisicoSexualList(List<ExamenFisicoSexual> examenFisicoSexualList) {
        this.examenFisicoSexualList = examenFisicoSexualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControlSexual != null ? idControlSexual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlPacienteSexual)) {
            return false;
        }
        ControlPacienteSexual other = (ControlPacienteSexual) object;
        if ((this.idControlSexual == null && other.idControlSexual != null) || (this.idControlSexual != null && !this.idControlSexual.equals(other.idControlSexual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ControlPacienteSexual[ idControlSexual=" + idControlSexual + " ]";
    }

}
