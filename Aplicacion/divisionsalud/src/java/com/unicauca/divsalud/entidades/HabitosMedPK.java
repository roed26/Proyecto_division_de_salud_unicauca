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
public class HabitosMedPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_HABITO")
    private int idTipoHabito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDX_CONSULTA")
    private int idxConsulta;

    public HabitosMedPK() {
    }

    public HabitosMedPK(int idTipoHabito, int idxConsulta) {
        this.idTipoHabito = idTipoHabito;
        this.idxConsulta = idxConsulta;
    }

    public int getIdTipoHabito() {
        return idTipoHabito;
    }

    public void setIdTipoHabito(int idTipoHabito) {
        this.idTipoHabito = idTipoHabito;
    }

    public int getIdxConsulta() {
        return idxConsulta;
    }

    public void setIdxConsulta(int idxConsulta) {
        this.idxConsulta = idxConsulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTipoHabito;
        hash += (int) idxConsulta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HabitosMedPK)) {
            return false;
        }
        HabitosMedPK other = (HabitosMedPK) object;
        if (this.idTipoHabito != other.idTipoHabito) {
            return false;
        }
        if (this.idxConsulta != other.idxConsulta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.HabitosMedPK[ idTipoHabito=" + idTipoHabito + ", idxConsulta=" + idxConsulta + " ]";
    }
    
}
