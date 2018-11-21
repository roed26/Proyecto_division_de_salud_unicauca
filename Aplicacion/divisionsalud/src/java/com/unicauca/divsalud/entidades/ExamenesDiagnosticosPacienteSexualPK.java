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
public class ExamenesDiagnosticosPacienteSexualPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EXAM_DIAG")
    private long idExamDiag;

    public ExamenesDiagnosticosPacienteSexualPK() {
    }

    public ExamenesDiagnosticosPacienteSexualPK(int id, long idExamDiag) {
        this.id = id;
        this.idExamDiag = idExamDiag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdExamDiag() {
        return idExamDiag;
    }

    public void setIdExamDiag(long idExamDiag) {
        this.idExamDiag = idExamDiag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idExamDiag;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenesDiagnosticosPacienteSexualPK)) {
            return false;
        }
        ExamenesDiagnosticosPacienteSexualPK other = (ExamenesDiagnosticosPacienteSexualPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idExamDiag != other.idExamDiag) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ExamenesDiagnosticosPacienteSexualPK[ id=" + id + ", idExamDiag=" + idExamDiag + " ]";
    }
    
}
