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
import javax.persistence.Lob;
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
@Table(name = "quirurgico_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuirurgicoMed.findAll", query = "SELECT q FROM QuirurgicoMed q"),
    @NamedQuery(name = "QuirurgicoMed.findByIdx", query = "SELECT q FROM QuirurgicoMed q WHERE q.idx = :idx")})
public class QuirurgicoMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "CONSULTA_MEDICA_MED_IDX", referencedColumnName = "IDX")
    @ManyToOne(optional = false)
    private ConsultaMedicaMed consultaMedicaMedIdx;
    @JoinColumn(name = "procedimientos_cups_med_codigo", referencedColumnName = "CODIGO")
    @ManyToOne
    private ProcedimientosCupsMed procedimientosCupsMedCodigo;

    public QuirurgicoMed() {
    }

    public QuirurgicoMed(Integer idx) {
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
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

    public ProcedimientosCupsMed getProcedimientosCupsMedCodigo() {
        return procedimientosCupsMedCodigo;
    }

    public void setProcedimientosCupsMedCodigo(ProcedimientosCupsMed procedimientosCupsMedCodigo) {
        this.procedimientosCupsMedCodigo = procedimientosCupsMedCodigo;
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
        if (!(object instanceof QuirurgicoMed)) {
            return false;
        }
        QuirurgicoMed other = (QuirurgicoMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.QuirurgicoMed[ idx=" + idx + " ]";
    }
    
}
