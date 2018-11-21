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
 * @author acer_acer
 */
@Embeddable
public class InformacionAntecedentePacienteSexualPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ANTECEDENTE")
    private long idAntecedente;

    public InformacionAntecedentePacienteSexualPK() {
    }

    public InformacionAntecedentePacienteSexualPK(int id, long idAntecedente) {
        this.id = id;
        this.idAntecedente = idAntecedente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdAntecedente() {
        return idAntecedente;
    }

    public void setIdAntecedente(long idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idAntecedente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformacionAntecedentePacienteSexualPK)) {
            return false;
        }
        InformacionAntecedentePacienteSexualPK other = (InformacionAntecedentePacienteSexualPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idAntecedente != other.idAntecedente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.InformacionAntecedentePacienteSexualPK[ id=" + id + ", idAntecedente=" + idAntecedente + " ]";
    }
    
}
