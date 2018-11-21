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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "examenes_diagnosticos_paciente_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamenesDiagnosticosPacienteSexual.findAll", query = "SELECT e FROM ExamenesDiagnosticosPacienteSexual e")
    , @NamedQuery(name = "ExamenesDiagnosticosPacienteSexual.findById", query = "SELECT e FROM ExamenesDiagnosticosPacienteSexual e WHERE e.examenesDiagnosticosPacienteSexualPK.id = :id")
    , @NamedQuery(name = "ExamenesDiagnosticosPacienteSexual.findByIdExamDiag", query = "SELECT e FROM ExamenesDiagnosticosPacienteSexual e WHERE e.examenesDiagnosticosPacienteSexualPK.idExamDiag = :idExamDiag")
    , @NamedQuery(name = "ExamenesDiagnosticosPacienteSexual.findByEspecificacionExamenFisico", query = "SELECT e FROM ExamenesDiagnosticosPacienteSexual e WHERE e.especificacionExamenFisico = :especificacionExamenFisico")
    , @NamedQuery(name = "ExamenesDiagnosticosPacienteSexual.findByResultadoExamenFisico", query = "SELECT e FROM ExamenesDiagnosticosPacienteSexual e WHERE e.resultadoExamenFisico = :resultadoExamenFisico")})
public class ExamenesDiagnosticosPacienteSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExamenesDiagnosticosPacienteSexualPK examenesDiagnosticosPacienteSexualPK;
    @Size(max = 300)
    @Column(name = "ESPECIFICACION_EXAMEN_FISICO")
    private String especificacionExamenFisico;
    @Size(max = 2)
    @Column(name = "RESULTADO_EXAMEN_FISICO")
    private String resultadoExamenFisico;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private HistoriaModuloSexualidad historiaModuloSexualidad;
    @JoinColumn(name = "ID_EXAM_DIAG", referencedColumnName = "ID_EXAM_DIAG", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoExamDiagnosticoSexual tipoExamDiagnosticoSexual;

    public ExamenesDiagnosticosPacienteSexual() {
    }

    public ExamenesDiagnosticosPacienteSexual(ExamenesDiagnosticosPacienteSexualPK examenesDiagnosticosPacienteSexualPK) {
        this.examenesDiagnosticosPacienteSexualPK = examenesDiagnosticosPacienteSexualPK;
    }

    public ExamenesDiagnosticosPacienteSexual(int id, long idExamDiag) {
        this.examenesDiagnosticosPacienteSexualPK = new ExamenesDiagnosticosPacienteSexualPK(id, idExamDiag);
    }

    public ExamenesDiagnosticosPacienteSexualPK getExamenesDiagnosticosPacienteSexualPK() {
        return examenesDiagnosticosPacienteSexualPK;
    }

    public void setExamenesDiagnosticosPacienteSexualPK(ExamenesDiagnosticosPacienteSexualPK examenesDiagnosticosPacienteSexualPK) {
        this.examenesDiagnosticosPacienteSexualPK = examenesDiagnosticosPacienteSexualPK;
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

    public HistoriaModuloSexualidad getHistoriaModuloSexualidad() {
        return historiaModuloSexualidad;
    }

    public void setHistoriaModuloSexualidad(HistoriaModuloSexualidad historiaModuloSexualidad) {
        this.historiaModuloSexualidad = historiaModuloSexualidad;
    }

    public TipoExamDiagnosticoSexual getTipoExamDiagnosticoSexual() {
        return tipoExamDiagnosticoSexual;
    }

    public void setTipoExamDiagnosticoSexual(TipoExamDiagnosticoSexual tipoExamDiagnosticoSexual) {
        this.tipoExamDiagnosticoSexual = tipoExamDiagnosticoSexual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examenesDiagnosticosPacienteSexualPK != null ? examenesDiagnosticosPacienteSexualPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenesDiagnosticosPacienteSexual)) {
            return false;
        }
        ExamenesDiagnosticosPacienteSexual other = (ExamenesDiagnosticosPacienteSexual) object;
        if ((this.examenesDiagnosticosPacienteSexualPK == null && other.examenesDiagnosticosPacienteSexualPK != null) || (this.examenesDiagnosticosPacienteSexualPK != null && !this.examenesDiagnosticosPacienteSexualPK.equals(other.examenesDiagnosticosPacienteSexualPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ExamenesDiagnosticosPacienteSexual[ examenesDiagnosticosPacienteSexualPK=" + examenesDiagnosticosPacienteSexualPK + " ]";
    }
    
}
