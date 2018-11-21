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
@Table(name = "ant_familiar_consulta_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntFamiliarConsultaMed.findAll", query = "SELECT a FROM AntFamiliarConsultaMed a"),
    @NamedQuery(name = "AntFamiliarConsultaMed.findByIdx", query = "SELECT a FROM AntFamiliarConsultaMed a WHERE a.idx = :idx"),
    @NamedQuery(name = "AntFamiliarConsultaMed.findByObservaciones", query = "SELECT a FROM AntFamiliarConsultaMed a WHERE a.observaciones = :observaciones"),
    @NamedQuery(name = "AntFamiliarConsultaMed.findByPadre", query = "SELECT a FROM AntFamiliarConsultaMed a WHERE a.padre = :padre"),
    @NamedQuery(name = "AntFamiliarConsultaMed.findByAbueloP", query = "SELECT a FROM AntFamiliarConsultaMed a WHERE a.abueloP = :abueloP"),
    @NamedQuery(name = "AntFamiliarConsultaMed.findByAbuelaP", query = "SELECT a FROM AntFamiliarConsultaMed a WHERE a.abuelaP = :abuelaP"),
    @NamedQuery(name = "AntFamiliarConsultaMed.findByMadre", query = "SELECT a FROM AntFamiliarConsultaMed a WHERE a.madre = :madre"),
    @NamedQuery(name = "AntFamiliarConsultaMed.findByAbueloM", query = "SELECT a FROM AntFamiliarConsultaMed a WHERE a.abueloM = :abueloM"),
    @NamedQuery(name = "AntFamiliarConsultaMed.findByAbuelaM", query = "SELECT a FROM AntFamiliarConsultaMed a WHERE a.abuelaM = :abuelaM"),
    @NamedQuery(name = "AntFamiliarConsultaMed.findByHermanos", query = "SELECT a FROM AntFamiliarConsultaMed a WHERE a.hermanos = :hermanos")})
public class AntFamiliarConsultaMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Size(max = 2000)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "PADRE")
    private Integer padre;
    @Column(name = "ABUELO_P")
    private Integer abueloP;
    @Column(name = "ABUELA_P")
    private Integer abuelaP;
    @Column(name = "MADRE")
    private Integer madre;
    @Column(name = "ABUELO_M")
    private Integer abueloM;
    @Column(name = "ABUELA_M")
    private Integer abuelaM;
    @Column(name = "HERMANOS")
    private Integer hermanos;
    @JoinColumn(name = "ANT_FAMILIARES_MED_IDX", referencedColumnName = "IDX")
    @ManyToOne(optional = false)
    private AntFamiliaresMed antFamiliaresMedIdx;
    @JoinColumn(name = "CONSULTA_MEDICA_MED_IDX", referencedColumnName = "IDX")
    @ManyToOne(optional = false)
    private ConsultaMedicaMed consultaMedicaMedIdx;

    public AntFamiliarConsultaMed() {
    }

    public AntFamiliarConsultaMed(Integer idx) {
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

    public Integer getPadre() {
        return padre;
    }

    public void setPadre(Integer padre) {
        this.padre = padre;
    }

    public Integer getAbueloP() {
        return abueloP;
    }

    public void setAbueloP(Integer abueloP) {
        this.abueloP = abueloP;
    }

    public Integer getAbuelaP() {
        return abuelaP;
    }

    public void setAbuelaP(Integer abuelaP) {
        this.abuelaP = abuelaP;
    }

    public Integer getMadre() {
        return madre;
    }

    public void setMadre(Integer madre) {
        this.madre = madre;
    }

    public Integer getAbueloM() {
        return abueloM;
    }

    public void setAbueloM(Integer abueloM) {
        this.abueloM = abueloM;
    }

    public Integer getAbuelaM() {
        return abuelaM;
    }

    public void setAbuelaM(Integer abuelaM) {
        this.abuelaM = abuelaM;
    }

    public Integer getHermanos() {
        return hermanos;
    }

    public void setHermanos(Integer hermanos) {
        this.hermanos = hermanos;
    }

    public AntFamiliaresMed getAntFamiliaresMedIdx() {
        return antFamiliaresMedIdx;
    }

    public void setAntFamiliaresMedIdx(AntFamiliaresMed antFamiliaresMedIdx) {
        this.antFamiliaresMedIdx = antFamiliaresMedIdx;
    }

    public ConsultaMedicaMed getConsultaMedicaMedIdx() {
        return consultaMedicaMedIdx;
    }

    public void setConsultaMedicaMedIdx(ConsultaMedicaMed consultaMedicaMedIdx) {
        this.consultaMedicaMedIdx = consultaMedicaMedIdx;
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
        if (!(object instanceof AntFamiliarConsultaMed)) {
            return false;
        }
        AntFamiliarConsultaMed other = (AntFamiliarConsultaMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.AntFamiliarConsultaMed[ idx=" + idx + " ]";
    }
    
}
