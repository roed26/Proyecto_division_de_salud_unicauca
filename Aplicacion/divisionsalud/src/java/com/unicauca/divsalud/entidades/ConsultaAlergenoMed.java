/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "consulta_alergeno_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultaAlergenoMed.findAll", query = "SELECT c FROM ConsultaAlergenoMed c"),
    @NamedQuery(name = "ConsultaAlergenoMed.findByConIdx", query = "SELECT c FROM ConsultaAlergenoMed c WHERE c.consultaAlergenoMedPK.conIdx = :conIdx"),
    @NamedQuery(name = "ConsultaAlergenoMed.findByIdx", query = "SELECT c FROM ConsultaAlergenoMed c WHERE c.consultaAlergenoMedPK.idx = :idx")})
public class ConsultaAlergenoMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsultaAlergenoMedPK consultaAlergenoMedPK;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "IDX", referencedColumnName = "IDX", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AlergenoMed alergenoMed;
    @JoinColumn(name = "CON_IDX", referencedColumnName = "IDX", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ConsultaMedicaMed consultaMedicaMed;

    public ConsultaAlergenoMed() {
    }

    public ConsultaAlergenoMed(ConsultaAlergenoMedPK consultaAlergenoMedPK) {
        this.consultaAlergenoMedPK = consultaAlergenoMedPK;
    }

    public ConsultaAlergenoMed(int conIdx, int idx) {
        this.consultaAlergenoMedPK = new ConsultaAlergenoMedPK(conIdx, idx);
    }

    public ConsultaAlergenoMedPK getConsultaAlergenoMedPK() {
        return consultaAlergenoMedPK;
    }

    public void setConsultaAlergenoMedPK(ConsultaAlergenoMedPK consultaAlergenoMedPK) {
        this.consultaAlergenoMedPK = consultaAlergenoMedPK;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public AlergenoMed getAlergenoMed() {
        return alergenoMed;
    }

    public void setAlergenoMed(AlergenoMed alergenoMed) {
        this.alergenoMed = alergenoMed;
    }

    public ConsultaMedicaMed getConsultaMedicaMed() {
        return consultaMedicaMed;
    }

    public void setConsultaMedicaMed(ConsultaMedicaMed consultaMedicaMed) {
        this.consultaMedicaMed = consultaMedicaMed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consultaAlergenoMedPK != null ? consultaAlergenoMedPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultaAlergenoMed)) {
            return false;
        }
        ConsultaAlergenoMed other = (ConsultaAlergenoMed) object;
        if ((this.consultaAlergenoMedPK == null && other.consultaAlergenoMedPK != null) || (this.consultaAlergenoMedPK != null && !this.consultaAlergenoMedPK.equals(other.consultaAlergenoMedPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ConsultaAlergenoMed[ consultaAlergenoMedPK=" + consultaAlergenoMedPK + " ]";
    }
    
}
