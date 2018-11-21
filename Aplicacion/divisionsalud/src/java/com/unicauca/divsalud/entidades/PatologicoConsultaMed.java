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
@Table(name = "patologico_consulta_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatologicoConsultaMed.findAll", query = "SELECT p FROM PatologicoConsultaMed p"),
    @NamedQuery(name = "PatologicoConsultaMed.findByIdx", query = "SELECT p FROM PatologicoConsultaMed p WHERE p.idx = :idx"),
    @NamedQuery(name = "PatologicoConsultaMed.findByEstadoSiNo", query = "SELECT p FROM PatologicoConsultaMed p WHERE p.estadoSiNo = :estadoSiNo"),
    @NamedQuery(name = "PatologicoConsultaMed.findByDescripcion", query = "SELECT p FROM PatologicoConsultaMed p WHERE p.descripcion = :descripcion")})
public class PatologicoConsultaMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Column(name = "ESTADO_SI_NO")
    private Integer estadoSiNo;
    @Size(max = 1000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "CONSULTA_MEDICA_MED_IDX", referencedColumnName = "IDX")
    @ManyToOne(optional = false)
    private ConsultaMedicaMed consultaMedicaMedIdx;
    @JoinColumn(name = "PATOLOGICOS_MED_IDX", referencedColumnName = "IDX")
    @ManyToOne(optional = false)
    private PatologicosMed patologicosMedIdx;

    public PatologicoConsultaMed() {
    }

    public PatologicoConsultaMed(Integer idx) {
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Integer getEstadoSiNo() {
        return estadoSiNo;
    }

    public void setEstadoSiNo(Integer estadoSiNo) {
        this.estadoSiNo = estadoSiNo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ConsultaMedicaMed getConsultaMedicaMedIdx() {
        return consultaMedicaMedIdx;
    }

    public void setConsultaMedicaMedIdx(ConsultaMedicaMed consultaMedicaMedIdx) {
        this.consultaMedicaMedIdx = consultaMedicaMedIdx;
    }

    public PatologicosMed getPatologicosMedIdx() {
        return patologicosMedIdx;
    }

    public void setPatologicosMedIdx(PatologicosMed patologicosMedIdx) {
        this.patologicosMedIdx = patologicosMedIdx;
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
        if (!(object instanceof PatologicoConsultaMed)) {
            return false;
        }
        PatologicoConsultaMed other = (PatologicoConsultaMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.PatologicoConsultaMed[ idx=" + idx + " ]";
    }
    
}
