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
@Table(name = "historico_ginecostetricos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoGinecostetricos.findAll", query = "SELECT h FROM HistoricoGinecostetricos h"),
    @NamedQuery(name = "HistoricoGinecostetricos.findByIdxConsulta", query = "SELECT h FROM HistoricoGinecostetricos h WHERE h.historicoGinecostetricosPK.idxConsulta = :idxConsulta"),
    @NamedQuery(name = "HistoricoGinecostetricos.findByIdxGinecostetricos", query = "SELECT h FROM HistoricoGinecostetricos h WHERE h.historicoGinecostetricosPK.idxGinecostetricos = :idxGinecostetricos")})
public class HistoricoGinecostetricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoricoGinecostetricosPK historicoGinecostetricosPK;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "IDX_GINECOSTETRICOS", referencedColumnName = "IDX", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GinecostetricosMed ginecostetricosMed;
    @JoinColumn(name = "IDX_CONSULTA", referencedColumnName = "IDX", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ConsultaMedicaMed consultaMedicaMed;

    public HistoricoGinecostetricos() {
    }

    public HistoricoGinecostetricos(HistoricoGinecostetricosPK historicoGinecostetricosPK) {
        this.historicoGinecostetricosPK = historicoGinecostetricosPK;
    }

    public HistoricoGinecostetricos(int idxConsulta, int idxGinecostetricos) {
        this.historicoGinecostetricosPK = new HistoricoGinecostetricosPK(idxConsulta, idxGinecostetricos);
    }

    public HistoricoGinecostetricosPK getHistoricoGinecostetricosPK() {
        return historicoGinecostetricosPK;
    }

    public void setHistoricoGinecostetricosPK(HistoricoGinecostetricosPK historicoGinecostetricosPK) {
        this.historicoGinecostetricosPK = historicoGinecostetricosPK;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public GinecostetricosMed getGinecostetricosMed() {
        return ginecostetricosMed;
    }

    public void setGinecostetricosMed(GinecostetricosMed ginecostetricosMed) {
        this.ginecostetricosMed = ginecostetricosMed;
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
        hash += (historicoGinecostetricosPK != null ? historicoGinecostetricosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoGinecostetricos)) {
            return false;
        }
        HistoricoGinecostetricos other = (HistoricoGinecostetricos) object;
        if ((this.historicoGinecostetricosPK == null && other.historicoGinecostetricosPK != null) || (this.historicoGinecostetricosPK != null && !this.historicoGinecostetricosPK.equals(other.historicoGinecostetricosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.HistoricoGinecostetricos[ historicoGinecostetricosPK=" + historicoGinecostetricosPK + " ]";
    }
    
}
