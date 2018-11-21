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
@Table(name = "medicamento_consulta_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentoConsultaMed.findAll", query = "SELECT m FROM MedicamentoConsultaMed m"),
    @NamedQuery(name = "MedicamentoConsultaMed.findByIdx", query = "SELECT m FROM MedicamentoConsultaMed m WHERE m.idx = :idx"),
    @NamedQuery(name = "MedicamentoConsultaMed.findByCantidad", query = "SELECT m FROM MedicamentoConsultaMed m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MedicamentoConsultaMed.findByDosis", query = "SELECT m FROM MedicamentoConsultaMed m WHERE m.dosis = :dosis")})
public class MedicamentoConsultaMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @Size(max = 200)
    @Column(name = "DOSIS")
    private String dosis;
    @JoinColumn(name = "CONSULTA_MEDICA_MED_IDX", referencedColumnName = "IDX")
    @ManyToOne(optional = false)
    private ConsultaMedicaMed consultaMedicaMedIdx;
    @JoinColumn(name = "MEDICAMENTO_MED_IDX", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private MedicamentoMed medicamentoMedIdx;

    public MedicamentoConsultaMed() {
    }

    public MedicamentoConsultaMed(Integer idx) {
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public ConsultaMedicaMed getConsultaMedicaMedIdx() {
        return consultaMedicaMedIdx;
    }

    public void setConsultaMedicaMedIdx(ConsultaMedicaMed consultaMedicaMedIdx) {
        this.consultaMedicaMedIdx = consultaMedicaMedIdx;
    }

    public MedicamentoMed getMedicamentoMedIdx() {
        return medicamentoMedIdx;
    }

    public void setMedicamentoMedIdx(MedicamentoMed medicamentoMedIdx) {
        this.medicamentoMedIdx = medicamentoMedIdx;
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
        if (!(object instanceof MedicamentoConsultaMed)) {
            return false;
        }
        MedicamentoConsultaMed other = (MedicamentoConsultaMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.MedicamentoConsultaMed[ idx=" + idx + " ]";
    }
    
}
