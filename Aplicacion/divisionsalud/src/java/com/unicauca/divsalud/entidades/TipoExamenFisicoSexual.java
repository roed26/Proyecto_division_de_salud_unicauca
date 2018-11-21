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
@Table(name = "tipo_examen_fisico_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoExamenFisicoSexual.findAll", query = "SELECT t FROM TipoExamenFisicoSexual t")
    , @NamedQuery(name = "TipoExamenFisicoSexual.findByIdExamen", query = "SELECT t FROM TipoExamenFisicoSexual t WHERE t.idExamen = :idExamen")
    , @NamedQuery(name = "TipoExamenFisicoSexual.findByNombreExamen", query = "SELECT t FROM TipoExamenFisicoSexual t WHERE t.nombreExamen = :nombreExamen")})
public class TipoExamenFisicoSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EXAMEN")
    private Long idExamen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE_EXAMEN")
    private String nombreExamen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoExamenFisicoSexual")
    private List<ExamenTipoResultadoSexual> examenTipoResultadoSexualList;

    public TipoExamenFisicoSexual() {
    }

    public TipoExamenFisicoSexual(Long idExamen) {
        this.idExamen = idExamen;
    }

    public TipoExamenFisicoSexual(Long idExamen, String nombreExamen) {
        this.idExamen = idExamen;
        this.nombreExamen = nombreExamen;
    }

    public Long getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Long idExamen) {
        this.idExamen = idExamen;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    @XmlTransient
    public List<ExamenTipoResultadoSexual> getExamenTipoResultadoSexualList() {
        return examenTipoResultadoSexualList;
    }

    public void setExamenTipoResultadoSexualList(List<ExamenTipoResultadoSexual> examenTipoResultadoSexualList) {
        this.examenTipoResultadoSexualList = examenTipoResultadoSexualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamen != null ? idExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoExamenFisicoSexual)) {
            return false;
        }
        TipoExamenFisicoSexual other = (TipoExamenFisicoSexual) object;
        if ((this.idExamen == null && other.idExamen != null) || (this.idExamen != null && !this.idExamen.equals(other.idExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.TipoExamenFisicoSexual[ idExamen=" + idExamen + " ]";
    }
    
}
