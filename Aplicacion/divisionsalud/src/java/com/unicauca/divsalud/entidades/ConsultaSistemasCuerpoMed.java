/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Danilo
 */
@Entity
@Table(name = "consulta_sistemas_cuerpo_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultaSistemasCuerpoMed.findAll", query = "SELECT c FROM ConsultaSistemasCuerpoMed c"),
    @NamedQuery(name = "ConsultaSistemasCuerpoMed.findByIdx", query = "SELECT c FROM ConsultaSistemasCuerpoMed c WHERE c.idx = :idx"),
    @NamedQuery(name = "ConsultaSistemasCuerpoMed.findByEstado", query = "SELECT c FROM ConsultaSistemasCuerpoMed c WHERE c.estado = :estado"),
    @NamedQuery(name = "ConsultaSistemasCuerpoMed.findByObservaciones", query = "SELECT c FROM ConsultaSistemasCuerpoMed c WHERE c.observaciones = :observaciones")})
public class ConsultaSistemasCuerpoMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Column(name = "ESTADO_")
    private Integer estado;
    @Size(max = 2000)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "CONSULTA_MEDICA_MED_IDX", referencedColumnName = "IDX")
    @ManyToOne(optional = false)
    private ConsultaMedicaMed consultaMedicaMedIdx;
    @JoinColumn(name = "SISTEMA_CUERPO_MED_IDX", referencedColumnName = "IDX")
    @ManyToOne(optional = false)
    private SistemaCuerpoMed sistemaCuerpoMedIdx;

    public ConsultaSistemasCuerpoMed() {
    }

    public ConsultaSistemasCuerpoMed(Integer idx) {
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ConsultaMedicaMed getConsultaMedicaMedIdx() {
        return consultaMedicaMedIdx;
    }

    public void setConsultaMedicaMedIdx(ConsultaMedicaMed consultaMedicaMedIdx) {
        this.consultaMedicaMedIdx = consultaMedicaMedIdx;
    }

    public SistemaCuerpoMed getSistemaCuerpoMedIdx() {
        return sistemaCuerpoMedIdx;
    }

    public void setSistemaCuerpoMedIdx(SistemaCuerpoMed sistemaCuerpoMedIdx) {
        this.sistemaCuerpoMedIdx = sistemaCuerpoMedIdx;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idx != null ? idx.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultaSistemasCuerpoMed)) {
            return false;
        }
        ConsultaSistemasCuerpoMed other = (ConsultaSistemasCuerpoMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ConsultaSistemasCuerpoMed[ idx=" + idx + " ]";
    }
    
}
