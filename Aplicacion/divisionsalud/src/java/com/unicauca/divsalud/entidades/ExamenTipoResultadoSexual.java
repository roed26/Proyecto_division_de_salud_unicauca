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
@Table(name = "examen_tipo_resultado_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamenTipoResultadoSexual.findAll", query = "SELECT e FROM ExamenTipoResultadoSexual e")
    , @NamedQuery(name = "ExamenTipoResultadoSexual.findByIdExamen", query = "SELECT e FROM ExamenTipoResultadoSexual e WHERE e.examenTipoResultadoSexualPK.idExamen = :idExamen")
    , @NamedQuery(name = "ExamenTipoResultadoSexual.findByIdExamenFisicoSexual", query = "SELECT e FROM ExamenTipoResultadoSexual e WHERE e.examenTipoResultadoSexualPK.idExamenFisicoSexual = :idExamenFisicoSexual")
    , @NamedQuery(name = "ExamenTipoResultadoSexual.findByEspecificacionExamenFisico", query = "SELECT e FROM ExamenTipoResultadoSexual e WHERE e.especificacionExamenFisico = :especificacionExamenFisico")
    , @NamedQuery(name = "ExamenTipoResultadoSexual.findByResultadoExamenFisico", query = "SELECT e FROM ExamenTipoResultadoSexual e WHERE e.resultadoExamenFisico = :resultadoExamenFisico")
    , @NamedQuery(name = "ExamenTipoResultadoSexual.findByFechaExamenFisico", query = "SELECT e FROM ExamenTipoResultadoSexual e WHERE e.fechaExamenFisico = :fechaExamenFisico")})
public class ExamenTipoResultadoSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExamenTipoResultadoSexualPK examenTipoResultadoSexualPK;
    @Size(max = 300)
    @Column(name = "ESPECIFICACION_EXAMEN_FISICO")
    private String especificacionExamenFisico;
    @Size(max = 7)
    @Column(name = "RESULTADO_EXAMEN_FISICO")
    private String resultadoExamenFisico;
    @Column(name = "FECHA_EXAMEN_FISICO")
    @Temporal(TemporalType.DATE)
    private Date fechaExamenFisico;
    @JoinColumn(name = "ID_EXAMEN_FISICO_SEXUAL", referencedColumnName = "ID_EXAMEN_FISICO_SEXUAL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ExamenFisicoSexual examenFisicoSexual;
    @JoinColumn(name = "ID_EXAMEN", referencedColumnName = "ID_EXAMEN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoExamenFisicoSexual tipoExamenFisicoSexual;

    public ExamenTipoResultadoSexual() {
    }

    public ExamenTipoResultadoSexual(ExamenTipoResultadoSexualPK examenTipoResultadoSexualPK) {
        this.examenTipoResultadoSexualPK = examenTipoResultadoSexualPK;
    }

    public ExamenTipoResultadoSexual(long idExamen, long idExamenFisicoSexual) {
        this.examenTipoResultadoSexualPK = new ExamenTipoResultadoSexualPK(idExamen, idExamenFisicoSexual);
    }

    public ExamenTipoResultadoSexualPK getExamenTipoResultadoSexualPK() {
        return examenTipoResultadoSexualPK;
    }

    public void setExamenTipoResultadoSexualPK(ExamenTipoResultadoSexualPK examenTipoResultadoSexualPK) {
        this.examenTipoResultadoSexualPK = examenTipoResultadoSexualPK;
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

    public ExamenFisicoSexual getExamenFisicoSexual() {
        return examenFisicoSexual;
    }

    public void setExamenFisicoSexual(ExamenFisicoSexual examenFisicoSexual) {
        this.examenFisicoSexual = examenFisicoSexual;
    }

    public TipoExamenFisicoSexual getTipoExamenFisicoSexual() {
        return tipoExamenFisicoSexual;
    }

    public void setTipoExamenFisicoSexual(TipoExamenFisicoSexual tipoExamenFisicoSexual) {
        this.tipoExamenFisicoSexual = tipoExamenFisicoSexual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examenTipoResultadoSexualPK != null ? examenTipoResultadoSexualPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenTipoResultadoSexual)) {
            return false;
        }
        ExamenTipoResultadoSexual other = (ExamenTipoResultadoSexual) object;
        if ((this.examenTipoResultadoSexualPK == null && other.examenTipoResultadoSexualPK != null) || (this.examenTipoResultadoSexualPK != null && !this.examenTipoResultadoSexualPK.equals(other.examenTipoResultadoSexualPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ExamenTipoResultadoSexual[ examenTipoResultadoSexualPK=" + examenTipoResultadoSexualPK + " ]";
    }
    
}
