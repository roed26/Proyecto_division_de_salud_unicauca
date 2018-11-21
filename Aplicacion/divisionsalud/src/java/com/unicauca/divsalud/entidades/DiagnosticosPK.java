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
import javax.validation.constraints.Size;

/**
 *
 * @author Danilo
 */
@Embeddable
public class DiagnosticosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO_CIE10")
    private String codigoCie10;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDX_CONSULTA")
    private int idxConsulta;

    public DiagnosticosPK() {
    }

    public DiagnosticosPK(String codigoCie10, int idxConsulta) {
        this.codigoCie10 = codigoCie10;
        this.idxConsulta = idxConsulta;
    }

    public String getCodigoCie10() {
        return codigoCie10;
    }

    public void setCodigoCie10(String codigoCie10) {
        this.codigoCie10 = codigoCie10;
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
        hash += (codigoCie10 != null ? codigoCie10.hashCode() : 0);
        hash += (int) idxConsulta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticosPK)) {
            return false;
        }
        DiagnosticosPK other = (DiagnosticosPK) object;
        if ((this.codigoCie10 == null && other.codigoCie10 != null) || (this.codigoCie10 != null && !this.codigoCie10.equals(other.codigoCie10))) {
            return false;
        }
        if (this.idxConsulta != other.idxConsulta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.DiagnosticosPK[ codigoCie10=" + codigoCie10 + ", idxConsulta=" + idxConsulta + " ]";
    }
    
}
