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
public class OtrosExamenesDiagnosticosPacienteSexualPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_OTRO_EXAM_DIAG")
    private long idOtroExamDiag;

    public OtrosExamenesDiagnosticosPacienteSexualPK() {
    }

    public OtrosExamenesDiagnosticosPacienteSexualPK(int id, long idOtroExamDiag) {
        this.id = id;
        this.idOtroExamDiag = idOtroExamDiag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdOtroExamDiag() {
        return idOtroExamDiag;
    }

    public void setIdOtroExamDiag(long idOtroExamDiag) {
        this.idOtroExamDiag = idOtroExamDiag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idOtroExamDiag;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtrosExamenesDiagnosticosPacienteSexualPK)) {
            return false;
        }
        OtrosExamenesDiagnosticosPacienteSexualPK other = (OtrosExamenesDiagnosticosPacienteSexualPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idOtroExamDiag != other.idOtroExamDiag) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.OtrosExamenesDiagnosticosPacienteSexualPK[ id=" + id + ", idOtroExamDiag=" + idOtroExamDiag + " ]";
    }
    
}
