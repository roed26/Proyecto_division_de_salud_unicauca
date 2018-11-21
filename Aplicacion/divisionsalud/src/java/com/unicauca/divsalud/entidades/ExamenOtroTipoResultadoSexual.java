/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "examen_otro_tipo_resultado_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamenOtroTipoResultadoSexual.findAll", query = "SELECT e FROM ExamenOtroTipoResultadoSexual e")
    , @NamedQuery(name = "ExamenOtroTipoResultadoSexual.findByIdExamenFisicoSexual", query = "SELECT e FROM ExamenOtroTipoResultadoSexual e WHERE e.examenOtroTipoResultadoSexualPK.idExamenFisicoSexual = :idExamenFisicoSexual")
    , @NamedQuery(name = "ExamenOtroTipoResultadoSexual.findByIdOtroExamen", query = "SELECT e FROM ExamenOtroTipoResultadoSexual e WHERE e.examenOtroTipoResultadoSexualPK.idOtroExamen = :idOtroExamen")
    , @NamedQuery(name = "ExamenOtroTipoResultadoSexual.findByEspecificacionExamenFisico", query = "SELECT e FROM ExamenOtroTipoResultadoSexual e WHERE e.especificacionExamenFisico = :especificacionExamenFisico")
    , @NamedQuery(name = "ExamenOtroTipoResultadoSexual.findByResultadoExamenFisico", query = "SELECT e FROM ExamenOtroTipoResultadoSexual e WHERE e.resultadoExamenFisico = :resultadoExamenFisico")
    , @NamedQuery(name = "ExamenOtroTipoResultadoSexual.findByFechaExamenFisico", query = "SELECT e FROM ExamenOtroTipoResultadoSexual e WHERE e.fechaExamenFisico = :fechaExamenFisico")})
public class ExamenOtroTipoResultadoSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExamenOtroTipoResultadoSexualPK examenOtroTipoResultadoSexualPK;
    @Size(max = 300)
    @Column(name = "ESPECIFICACION_EXAMEN_FISICO")
    private String especificacionExamenFisico;
    @Size(max = 2)
    @Column(name = "RESULTADO_EXAMEN_FISICO")
    private String resultadoExamenFisico;
    @Column(name = "FECHA_EXAMEN_FISICO")
    @Temporal(TemporalType.DATE)
    private Date fechaExamenFisico;
    @JoinColumn(name = "ID_OTRO_EXAMEN", referencedColumnName = "ID_OTRO_EXAMEN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OtroTipoExamenFisicoSexual otroTipoExamenFisicoSexual;
    @JoinColumn(name = "ID_EXAMEN_FISICO_SEXUAL", referencedColumnName = "ID_EXAMEN_FISICO_SEXUAL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ExamenFisicoSexual examenFisicoSexual;

    public ExamenOtroTipoResultadoSexual() {
    }

    public ExamenOtroTipoResultadoSexual(ExamenOtroTipoResultadoSexualPK examenOtroTipoResultadoSexualPK) {
        this.examenOtroTipoResultadoSexualPK = examenOtroTipoResultadoSexualPK;
    }

    public ExamenOtroTipoResultadoSexual(long idExamenFisicoSexual, long idOtroExamen) {
        this.examenOtroTipoResultadoSexualPK = new ExamenOtroTipoResultadoSexualPK(idExamenFisicoSexual, idOtroExamen);
    }

    public ExamenOtroTipoResultadoSexualPK getExamenOtroTipoResultadoSexualPK() {
        return examenOtroTipoResultadoSexualPK;
    }

    public void setExamenOtroTipoResultadoSexualPK(ExamenOtroTipoResultadoSexualPK examenOtroTipoResultadoSexualPK) {
        this.examenOtroTipoResultadoSexualPK = examenOtroTipoResultadoSexualPK;
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

    public Date getFechaExamenFisico() {
        return fechaExamenFisico;
    }

    public void setFechaExamenFisico(Date fechaExamenFisico) {
        this.fechaExamenFisico = fechaExamenFisico;
    }

    public OtroTipoExamenFisicoSexual getOtroTipoExamenFisicoSexual() {
        return otroTipoExamenFisicoSexual;
    }

    public void setOtroTipoExamenFisicoSexual(OtroTipoExamenFisicoSexual otroTipoExamenFisicoSexual) {
        this.otroTipoExamenFisicoSexual = otroTipoExamenFisicoSexual;
    }

    public ExamenFisicoSexual getExamenFisicoSexual() {
        return examenFisicoSexual;
    }

    public void setExamenFisicoSexual(ExamenFisicoSexual examenFisicoSexual) {
        this.examenFisicoSexual = examenFisicoSexual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examenOtroTipoResultadoSexualPK != null ? examenOtroTipoResultadoSexualPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenOtroTipoResultadoSexual)) {
            return false;
        }
        ExamenOtroTipoResultadoSexual other = (ExamenOtroTipoResultadoSexual) object;
        if ((this.examenOtroTipoResultadoSexualPK == null && other.examenOtroTipoResultadoSexualPK != null) || (this.examenOtroTipoResultadoSexualPK != null && !this.examenOtroTipoResultadoSexualPK.equals(other.examenOtroTipoResultadoSexualPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ExamenOtroTipoResultadoSexual[ examenOtroTipoResultadoSexualPK=" + examenOtroTipoResultadoSexualPK + " ]";
    }
    
}
