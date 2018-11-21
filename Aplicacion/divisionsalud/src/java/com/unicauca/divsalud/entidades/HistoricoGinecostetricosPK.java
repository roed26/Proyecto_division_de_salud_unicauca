/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Danilo
 */
@Embeddable
public class HistoricoGinecostetricosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDX_CONSULTA")
    private int idxConsulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDX_GINECOSTETRICOS")
    private int idxGinecostetricos;

    public HistoricoGinecostetricosPK() {
    }

    public HistoricoGinecostetricosPK(int idxConsulta, int idxGinecostetricos) {
        this.idxConsulta = idxConsulta;
        this.idxGinecostetricos = idxGinecostetricos;
    }

    public int getIdxConsulta() {
        return idxConsulta;
    }

    public void setIdxConsulta(int idxConsulta) {
        this.idxConsulta = idxConsulta;
    }

    public int getIdxGinecostetricos() {
        return idxGinecostetricos;
    }

    public void setIdxGinecostetricos(int idxGinecostetricos) {
        this.idxGinecostetricos = idxGinecostetricos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idxConsulta;
        hash += (int) idxGinecostetricos;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoGinecostetricosPK)) {
            return false;
        }
        HistoricoGinecostetricosPK other = (HistoricoGinecostetricosPK) object;
        if (this.idxConsulta != other.idxConsulta) {
            return false;
        }
        if (this.idxGinecostetricos != other.idxGinecostetricos) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.HistoricoGinecostetricosPK[ idxConsulta=" + idxConsulta + ", idxGinecostetricos=" + idxGinecostetricos + " ]";
    }
    
}
