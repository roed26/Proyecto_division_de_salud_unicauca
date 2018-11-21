/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "tipo_exam_diagnostico_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoExamDiagnosticoSexual.findAll", query = "SELECT t FROM TipoExamDiagnosticoSexual t")
    , @NamedQuery(name = "TipoExamDiagnosticoSexual.findByIdExamDiag", query = "SELECT t FROM TipoExamDiagnosticoSexual t WHERE t.idExamDiag = :idExamDiag")
    , @NamedQuery(name = "TipoExamDiagnosticoSexual.findByNombre", query = "SELECT t FROM TipoExamDiagnosticoSexual t WHERE t.nombre = :nombre")})
public class TipoExamDiagnosticoSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EXAM_DIAG")
    private Long idExamDiag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoExamDiagnosticoSexual")
    private List<ExamenesDiagnosticosPacienteSexual> examenesDiagnosticosPacienteSexualList;

    public TipoExamDiagnosticoSexual() {
    }

    public TipoExamDiagnosticoSexual(Long idExamDiag) {
        this.idExamDiag = idExamDiag;
    }

    public TipoExamDiagnosticoSexual(Long idExamDiag, String nombre) {
        this.idExamDiag = idExamDiag;
        this.nombre = nombre;
    }

    public Long getIdExamDiag() {
        return idExamDiag;
    }

    public void setIdExamDiag(Long idExamDiag) {
        this.idExamDiag = idExamDiag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ExamenesDiagnosticosPacienteSexual> getExamenesDiagnosticosPacienteSexualList() {
        return examenesDiagnosticosPacienteSexualList;
    }

    public void setExamenesDiagnosticosPacienteSexualList(List<ExamenesDiagnosticosPacienteSexual> examenesDiagnosticosPacienteSexualList) {
        this.examenesDiagnosticosPacienteSexualList = examenesDiagnosticosPacienteSexualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamDiag != null ? idExamDiag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoExamDiagnosticoSexual)) {
            return false;
        }
        TipoExamDiagnosticoSexual other = (TipoExamDiagnosticoSexual) object;
        if ((this.idExamDiag == null && other.idExamDiag != null) || (this.idExamDiag != null && !this.idExamDiag.equals(other.idExamDiag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.TipoExamDiagnosticoSexual[ idExamDiag=" + idExamDiag + " ]";
    }
    
}
