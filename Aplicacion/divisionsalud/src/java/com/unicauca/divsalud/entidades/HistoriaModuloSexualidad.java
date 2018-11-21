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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "historia_modulo_sexualidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoriaModuloSexualidad.findAll", query = "SELECT h FROM HistoriaModuloSexualidad h")
    , @NamedQuery(name = "HistoriaModuloSexualidad.findById", query = "SELECT h FROM HistoriaModuloSexualidad h WHERE h.id = :id")
    , @NamedQuery(name = "HistoriaModuloSexualidad.findByFechaCreacion", query = "SELECT h FROM HistoriaModuloSexualidad h WHERE h.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "HistoriaModuloSexualidad.findByYearMes", query = "SELECT h FROM HistoriaModuloSexualidad h WHERE FUNCTION('YEAR', h.fechaCreacion) = :year AND FUNCTION('MONTH', h.fechaCreacion) = :month")
    , @NamedQuery(name = "HistoriaModuloSexualidad.findByYear", query = "SELECT h FROM HistoriaModuloSexualidad h WHERE FUNCTION('YEAR', h.fechaCreacion) = :year")})
public class HistoriaModuloSexualidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historiaModuloSexualidad")
    private List<MetodoAdoptadoSexual> metodoAdoptadoSexualList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historiaModuloSexualidad")
    private List<HistorialMetodosUsadosSexual> historialMetodosUsadosSexualList;
    @OneToMany(mappedBy = "id")
    private List<ControlPacienteSexual> controlPacienteSexualList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historiaModuloSexualidad")
    private List<OtrosExamenesDiagnosticosPacienteSexual> otrosExamenesDiagnosticosPacienteSexualList;
    @OneToMany(mappedBy = "historiaModuloSexualidad")
    private List<ExamenFisicoSexual> examenFisicoSexualList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historiaModuloSexualidad")
    private List<ExamenesDiagnosticosPacienteSexual> examenesDiagnosticosPacienteSexualList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "historiaModuloSexualidad")
    private AntecedentesPersonalesSexual antecedentesPersonalesSexual;

    public HistoriaModuloSexualidad() {
        id=0;
        fechaCreacion=null;
    }

    public HistoriaModuloSexualidad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @XmlTransient
    public List<MetodoAdoptadoSexual> getMetodoAdoptadoSexualList() {
        return metodoAdoptadoSexualList;
    }

    public void setMetodoAdoptadoSexualList(List<MetodoAdoptadoSexual> metodoAdoptadoSexualList) {
        this.metodoAdoptadoSexualList = metodoAdoptadoSexualList;
    }

    @XmlTransient
    public List<HistorialMetodosUsadosSexual> getHistorialMetodosUsadosSexualList() {
        return historialMetodosUsadosSexualList;
    }

    public void setHistorialMetodosUsadosSexualList(List<HistorialMetodosUsadosSexual> historialMetodosUsadosSexualList) {
        this.historialMetodosUsadosSexualList = historialMetodosUsadosSexualList;
    }

    @XmlTransient
    public List<ControlPacienteSexual> getControlPacienteSexualList() {
        return controlPacienteSexualList;
    }

    public void setControlPacienteSexualList(List<ControlPacienteSexual> controlPacienteSexualList) {
        this.controlPacienteSexualList = controlPacienteSexualList;
    }

    @XmlTransient
    public List<OtrosExamenesDiagnosticosPacienteSexual> getOtrosExamenesDiagnosticosPacienteSexualList() {
        return otrosExamenesDiagnosticosPacienteSexualList;
    }

    public void setOtrosExamenesDiagnosticosPacienteSexualList(List<OtrosExamenesDiagnosticosPacienteSexual> otrosExamenesDiagnosticosPacienteSexualList) {
        this.otrosExamenesDiagnosticosPacienteSexualList = otrosExamenesDiagnosticosPacienteSexualList;
    }

    @XmlTransient
    public List<ExamenFisicoSexual> getExamenFisicoSexualList() {
        return examenFisicoSexualList;
    }

    public void setExamenFisicoSexualList(List<ExamenFisicoSexual> examenFisicoSexualList) {
        this.examenFisicoSexualList = examenFisicoSexualList;
    }

    @XmlTransient
    public List<ExamenesDiagnosticosPacienteSexual> getExamenesDiagnosticosPacienteSexualList() {
        return examenesDiagnosticosPacienteSexualList;
    }

    public void setExamenesDiagnosticosPacienteSexualList(List<ExamenesDiagnosticosPacienteSexual> examenesDiagnosticosPacienteSexualList) {
        this.examenesDiagnosticosPacienteSexualList = examenesDiagnosticosPacienteSexualList;
    }

    public AntecedentesPersonalesSexual getAntecedentesPersonalesSexual() {
        return antecedentesPersonalesSexual;
    }

    public void setAntecedentesPersonalesSexual(AntecedentesPersonalesSexual antecedentesPersonalesSexual) {
        this.antecedentesPersonalesSexual = antecedentesPersonalesSexual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriaModuloSexualidad)) {
            return false;
        }
        HistoriaModuloSexualidad other = (HistoriaModuloSexualidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.HistoriaModuloSexualidad[ id=" + id + " ]";
    }
    
}
