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
public class ConsultaAlergenoMedPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CON_IDX")
    private int conIdx;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDX")
    private int idx;

    public ConsultaAlergenoMedPK() {
    }

    public ConsultaAlergenoMedPK(int conIdx, int idx) {
        this.conIdx = conIdx;
        this.idx = idx;
    }

    public int getConIdx() {
        return conIdx;
    }

    public void setConIdx(int conIdx) {
        this.conIdx = conIdx;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) conIdx;
        hash += (int) idx;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultaAlergenoMedPK)) {
            return false;
        }
        ConsultaAlergenoMedPK other = (ConsultaAlergenoMedPK) object;
        if (this.conIdx != other.conIdx) {
            return false;
        }
        if (this.idx != other.idx) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ConsultaAlergenoMedPK[ conIdx=" + conIdx + ", idx=" + idx + " ]";
    }
    
}
