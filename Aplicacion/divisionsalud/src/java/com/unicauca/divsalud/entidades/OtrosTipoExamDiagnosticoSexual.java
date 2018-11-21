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
@Table(name = "otros_tipo_exam_diagnostico_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OtrosTipoExamDiagnosticoSexual.findAll", query = "SELECT o FROM OtrosTipoExamDiagnosticoSexual o")
    , @NamedQuery(name = "OtrosTipoExamDiagnosticoSexual.findByIdOtroExamDiag", query = "SELECT o FROM OtrosTipoExamDiagnosticoSexual o WHERE o.idOtroExamDiag = :idOtroExamDiag")
    , @NamedQuery(name = "OtrosTipoExamDiagnosticoSexual.findByNombre", query = "SELECT o FROM OtrosTipoExamDiagnosticoSexual o WHERE o.nombre = :nombre")})
public class OtrosTipoExamDiagnosticoSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OTRO_EXAM_DIAG")
    private Long idOtroExamDiag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "otrosTipoExamDiagnosticoSexual")
    private List<OtrosExamenesDiagnosticosPacienteSexual> otrosExamenesDiagnosticosPacienteSexualList;

    public OtrosTipoExamDiagnosticoSexual(String nombre) {
        this.nombre=nombre;
    }

    public OtrosTipoExamDiagnosticoSexual(Long idOtroExamDiag) {
        this.idOtroExamDiag = idOtroExamDiag;
    }

    public OtrosTipoExamDiagnosticoSexual(Long idOtroExamDiag, String nombre) {
        this.idOtroExamDiag = idOtroExamDiag;
        this.nombre = nombre;
    }

    public OtrosTipoExamDiagnosticoSexual() {        
    }

    public Long getIdOtroExamDiag() {
        return idOtroExamDiag;
    }

    public void setIdOtroExamDiag(Long idOtroExamDiag) {
        this.idOtroExamDiag = idOtroExamDiag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<OtrosExamenesDiagnosticosPacienteSexual> getOtrosExamenesDiagnosticosPacienteSexualList() {
        return otrosExamenesDiagnosticosPacienteSexualList;
    }

    public void setOtrosExamenesDiagnosticosPacienteSexualList(List<OtrosExamenesDiagnosticosPacienteSexual> otrosExamenesDiagnosticosPacienteSexualList) {
        this.otrosExamenesDiagnosticosPacienteSexualList = otrosExamenesDiagnosticosPacienteSexualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOtroExamDiag != null ? idOtroExamDiag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtrosTipoExamDiagnosticoSexual)) {
            return false;
        }
        OtrosTipoExamDiagnosticoSexual other = (OtrosTipoExamDiagnosticoSexual) object;
        if ((this.idOtroExamDiag == null && other.idOtroExamDiag != null) || (this.idOtroExamDiag != null && !this.idOtroExamDiag.equals(other.idOtroExamDiag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.OtrosTipoExamDiagnosticoSexual[ idOtroExamDiag=" + idOtroExamDiag + " ]";
    }
    
}
