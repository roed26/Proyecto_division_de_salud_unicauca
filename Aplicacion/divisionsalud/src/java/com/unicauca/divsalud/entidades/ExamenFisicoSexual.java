/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "examen_fisico_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamenFisicoSexual.findAll", query = "SELECT e FROM ExamenFisicoSexual e")
    , @NamedQuery(name = "ExamenFisicoSexual.findByPeso", query = "SELECT e FROM ExamenFisicoSexual e WHERE e.peso = :peso")
    , @NamedQuery(name = "ExamenFisicoSexual.findByTalla", query = "SELECT e FROM ExamenFisicoSexual e WHERE e.talla = :talla")
    , @NamedQuery(name = "ExamenFisicoSexual.findByImc", query = "SELECT e FROM ExamenFisicoSexual e WHERE e.imc = :imc")
    , @NamedQuery(name = "ExamenFisicoSexual.findByPresionArterialSistolica", query = "SELECT e FROM ExamenFisicoSexual e WHERE e.presionArterialSistolica = :presionArterialSistolica")
    , @NamedQuery(name = "ExamenFisicoSexual.findByPresionArterialDistolica", query = "SELECT e FROM ExamenFisicoSexual e WHERE e.presionArterialDistolica = :presionArterialDistolica")
    , @NamedQuery(name = "ExamenFisicoSexual.findByPulso", query = "SELECT e FROM ExamenFisicoSexual e WHERE e.pulso = :pulso")
    , @NamedQuery(name = "ExamenFisicoSexual.findByOtrosHallazgos", query = "SELECT e FROM ExamenFisicoSexual e WHERE e.otrosHallazgos = :otrosHallazgos")
    , @NamedQuery(name = "ExamenFisicoSexual.findByIdExamenFisicoSexual", query = "SELECT e FROM ExamenFisicoSexual e WHERE e.idExamenFisicoSexual = :idExamenFisicoSexual")
        , @NamedQuery(name = "ExamenFisicoSexual.findById", query = "SELECT e FROM ExamenFisicoSexual e WHERE e.historiaModuloSexualidad.id = :id ") })
public class ExamenFisicoSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESO")
    private BigDecimal peso;
    @Column(name = "TALLA")
    private BigDecimal talla;
    @Column(name = "IMC")
    private BigDecimal imc;
    @Column(name = "PRESION_ARTERIAL_SISTOLICA")
    private Short presionArterialSistolica;
    @Column(name = "PRESION_ARTERIAL_DISTOLICA")
    private Short presionArterialDistolica;
    @Column(name = "PULSO")
    private Short pulso;
    @Size(max = 300)
    @Column(name = "OTROS_HALLAZGOS")
    private String otrosHallazgos;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EXAMEN_FISICO_SEXUAL")
    private Long idExamenFisicoSexual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examenFisicoSexual")
    private List<ExamenTipoResultadoSexual> examenTipoResultadoSexualList;
    @JoinColumn(name = "ID_CONTROL_SEXUAL", referencedColumnName = "ID_CONTROL_SEXUAL")
    @ManyToOne
    private ControlPacienteSexual idControlSexual;
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    @ManyToOne
    private HistoriaModuloSexualidad historiaModuloSexualidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examenFisicoSexual")
    private List<ExamenOtroTipoResultadoSexual> examenOtroTipoResultadoSexualList;

    public ExamenFisicoSexual() {
    }

    public ExamenFisicoSexual(Long idExamenFisicoSexual) {
        this.idExamenFisicoSexual = idExamenFisicoSexual;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getTalla() {
        return talla;
    }

    public void setTalla(BigDecimal talla) {
        this.talla = talla;
    }

    public BigDecimal getImc() {
        return imc;
    }

    public void setImc(BigDecimal imc) {
        this.imc = imc;
    }

    public Short getPresionArterialSistolica() {
        return presionArterialSistolica;
    }

    public void setPresionArterialSistolica(Short presionArterialSistolica) {
        this.presionArterialSistolica = presionArterialSistolica;
    }

    public Short getPresionArterialDistolica() {
        return presionArterialDistolica;
    }

    public void setPresionArterialDistolica(Short presionArterialDistolica) {
        this.presionArterialDistolica = presionArterialDistolica;
    }

    public Short getPulso() {
        return pulso;
    }

    public void setPulso(Short pulso) {
        this.pulso = pulso;
    }

    public String getOtrosHallazgos() {
        return otrosHallazgos;
    }

    public void setOtrosHallazgos(String otrosHallazgos) {
        this.otrosHallazgos = otrosHallazgos;
    }

    public Long getIdExamenFisicoSexual() {
        return idExamenFisicoSexual;
    }

    public void setIdExamenFisicoSexual(Long idExamenFisicoSexual) {
        this.idExamenFisicoSexual = idExamenFisicoSexual;
    }

    @XmlTransient
    public List<ExamenTipoResultadoSexual> getExamenTipoResultadoSexualList() {
        return examenTipoResultadoSexualList;
    }

    public void setExamenTipoResultadoSexualList(List<ExamenTipoResultadoSexual> examenTipoResultadoSexualList) {
        this.examenTipoResultadoSexualList = examenTipoResultadoSexualList;
    }

    public ControlPacienteSexual getIdControlSexual() {
        return idControlSexual;
    }

    public void setIdControlSexual(ControlPacienteSexual idControlSexual) {
        this.idControlSexual = idControlSexual;
    }

    public HistoriaModuloSexualidad getId() {
        return historiaModuloSexualidad;
    }

    public void setId(HistoriaModuloSexualidad id) {
        this.historiaModuloSexualidad = id;
    }

    @XmlTransient
    public List<ExamenOtroTipoResultadoSexual> getExamenOtroTipoResultadoSexualList() {
        return examenOtroTipoResultadoSexualList;
    }

    public void setExamenOtroTipoResultadoSexualList(List<ExamenOtroTipoResultadoSexual> examenOtroTipoResultadoSexualList) {
        this.examenOtroTipoResultadoSexualList = examenOtroTipoResultadoSexualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenFisicoSexual != null ? idExamenFisicoSexual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenFisicoSexual)) {
            return false;
        }
        ExamenFisicoSexual other = (ExamenFisicoSexual) object;
        if ((this.idExamenFisicoSexual == null && other.idExamenFisicoSexual != null) || (this.idExamenFisicoSexual != null && !this.idExamenFisicoSexual.equals(other.idExamenFisicoSexual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ExamenFisicoSexual[ idExamenFisicoSexual=" + idExamenFisicoSexual + " ]";
    }
    
}
