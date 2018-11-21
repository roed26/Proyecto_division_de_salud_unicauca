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
@Table(name = "otro_tipo_examen_fisico_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OtroTipoExamenFisicoSexual.findAll", query = "SELECT o FROM OtroTipoExamenFisicoSexual o")
    , @NamedQuery(name = "OtroTipoExamenFisicoSexual.findByIdOtroExamen", query = "SELECT o FROM OtroTipoExamenFisicoSexual o WHERE o.idOtroExamen = :idOtroExamen")
    , @NamedQuery(name = "OtroTipoExamenFisicoSexual.findByNombreExamen", query = "SELECT o FROM OtroTipoExamenFisicoSexual o WHERE o.nombreExamen = :nombreExamen")})
public class OtroTipoExamenFisicoSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OTRO_EXAMEN")
    private Long idOtroExamen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE_EXAMEN")
    private String nombreExamen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "otroTipoExamenFisicoSexual")
    private List<ExamenOtroTipoResultadoSexual> examenOtroTipoResultadoSexualList;

    public OtroTipoExamenFisicoSexual() {
    }

    public OtroTipoExamenFisicoSexual(Long idOtroExamen) {
        this.idOtroExamen = idOtroExamen;
    }

    public OtroTipoExamenFisicoSexual(Long idOtroExamen, String nombreExamen) {
        this.idOtroExamen = idOtroExamen;
        this.nombreExamen = nombreExamen;
    }

    public Long getIdOtroExamen() {
        return idOtroExamen;
    }

    public void setIdOtroExamen(Long idOtroExamen) {
        this.idOtroExamen = idOtroExamen;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    @XmlTransient
    public List<ExamenOtroTipoResultadoSexual> getExamenOtroTipoResultadoSexualList() {
        return examenOtroTipoResultadoSexualList;
    }

    public void setExamenOtroTipoResultadoSexualList(List<ExamenOtroTipoResultadoSexual> examenOtroTipoResultadoSexualList) {
        this.examenOtroTipoResultadoSexualList = examenOtroTipoResultadoSexualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOtroExamen != null ? idOtroExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtroTipoExamenFisicoSexual)) {
            return false;
        }
        OtroTipoExamenFisicoSexual other = (OtroTipoExamenFisicoSexual) object;
        if ((this.idOtroExamen == null && other.idOtroExamen != null) || (this.idOtroExamen != null && !this.idOtroExamen.equals(other.idOtroExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.OtroTipoExamenFisicoSexual[ idOtroExamen=" + idOtroExamen + " ]";
    }
    
}
