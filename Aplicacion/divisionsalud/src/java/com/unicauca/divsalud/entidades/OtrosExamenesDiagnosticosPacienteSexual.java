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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "otros_examenes_diagnosticos_paciente_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OtrosExamenesDiagnosticosPacienteSexual.findAll", query = "SELECT o FROM OtrosExamenesDiagnosticosPacienteSexual o")
    , @NamedQuery(name = "OtrosExamenesDiagnosticosPacienteSexual.findById", query = "SELECT o FROM OtrosExamenesDiagnosticosPacienteSexual o WHERE o.otrosExamenesDiagnosticosPacienteSexualPK.id = :id")
    , @NamedQuery(name = "OtrosExamenesDiagnosticosPacienteSexual.findByIdOtroExamDiag", query = "SELECT o FROM OtrosExamenesDiagnosticosPacienteSexual o WHERE o.otrosExamenesDiagnosticosPacienteSexualPK.idOtroExamDiag = :idOtroExamDiag")
    , @NamedQuery(name = "OtrosExamenesDiagnosticosPacienteSexual.findByEspecificacionExamenFisico", query = "SELECT o FROM OtrosExamenesDiagnosticosPacienteSexual o WHERE o.especificacionExamenFisico = :especificacionExamenFisico")
    , @NamedQuery(name = "OtrosExamenesDiagnosticosPacienteSexual.findByResultadoExamenFisico", query = "SELECT o FROM OtrosExamenesDiagnosticosPacienteSexual o WHERE o.resultadoExamenFisico = :resultadoExamenFisico")})
public class OtrosExamenesDiagnosticosPacienteSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OtrosExamenesDiagnosticosPacienteSexualPK otrosExamenesDiagnosticosPacienteSexualPK;
    @Size(max = 300)
    @Column(name = "ESPECIFICACION_EXAMEN_FISICO")
    private String especificacionExamenFisico;
    @Size(max = 2)
    @Column(name = "RESULTADO_EXAMEN_FISICO")
    private String resultadoExamenFisico;
    @JoinColumn(name = "ID_OTRO_EXAM_DIAG", referencedColumnName = "ID_OTRO_EXAM_DIAG", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OtrosTipoExamDiagnosticoSexual otrosTipoExamDiagnosticoSexual;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private HistoriaModuloSexualidad historiaModuloSexualidad;
    @Transient
    private String nombre="otro";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public OtrosExamenesDiagnosticosPacienteSexual() {
    }

    public OtrosExamenesDiagnosticosPacienteSexual(OtrosExamenesDiagnosticosPacienteSexualPK otrosExamenesDiagnosticosPacienteSexualPK) {
        this.otrosExamenesDiagnosticosPacienteSexualPK = otrosExamenesDiagnosticosPacienteSexualPK;
    }

    public OtrosExamenesDiagnosticosPacienteSexual(int id, long idOtroExamDiag) {
        this.otrosExamenesDiagnosticosPacienteSexualPK = new OtrosExamenesDiagnosticosPacienteSexualPK(id, idOtroExamDiag);
    }

    public OtrosExamenesDiagnosticosPacienteSexualPK getOtrosExamenesDiagnosticosPacienteSexualPK() {
        return otrosExamenesDiagnosticosPacienteSexualPK;
    }

    public void setOtrosExamenesDiagnosticosPacienteSexualPK(OtrosExamenesDiagnosticosPacienteSexualPK otrosExamenesDiagnosticosPacienteSexualPK) {
        this.otrosExamenesDiagnosticosPacienteSexualPK = otrosExamenesDiagnosticosPacienteSexualPK;
    }

    public String getEspecificacionExamenFisico() {
        return especificacionExamenFisico;
    }

    public void setEspecificacionExamenFisico(String especificacionExamenFisico) {
        this.especificacionExamenFisico = especificacionExamenFisico;
    }

    public String getResultadoExamenFisico() {
        return resultadoExamenFisico;
    }

    public void setResultadoExamenFisico(String resultadoExamenFisico) {
        this.resultadoExamenFisico = resultadoExamenFisico;
    }

    public OtrosTipoExamDiagnosticoSexual getOtrosTipoExamDiagnosticoSexual() {
        return otrosTipoExamDiagnosticoSexual;
    }

    public void setOtrosTipoExamDiagnosticoSexual(OtrosTipoExamDiagnosticoSexual otrosTipoExamDiagnosticoSexual) {
        this.otrosTipoExamDiagnosticoSexual = otrosTipoExamDiagnosticoSexual;
    }

    public HistoriaModuloSexualidad getHistoriaModuloSexualidad() {
        return historiaModuloSexualidad;
    }

    public void setHistoriaModuloSexualidad(HistoriaModuloSexualidad historiaModuloSexualidad) {
        this.historiaModuloSexualidad = historiaModuloSexualidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (otrosExamenesDiagnosticosPacienteSexualPK != null ? otrosExamenesDiagnosticosPacienteSexualPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtrosExamenesDiagnosticosPacienteSexual)) {
            return false;
        }
        OtrosExamenesDiagnosticosPacienteSexual other = (OtrosExamenesDiagnosticosPacienteSexual) object;
        if ((this.otrosExamenesDiagnosticosPacienteSexualPK == null && other.otrosExamenesDiagnosticosPacienteSexualPK != null) || (this.otrosExamenesDiagnosticosPacienteSexualPK != null && !this.otrosExamenesDiagnosticosPacienteSexualPK.equals(other.otrosExamenesDiagnosticosPacienteSexualPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.OtrosExamenesDiagnosticosPacienteSexual[ otrosExamenesDiagnosticosPacienteSexualPK=" + otrosExamenesDiagnosticosPacienteSexualPK + " ]";
    }
    
}
