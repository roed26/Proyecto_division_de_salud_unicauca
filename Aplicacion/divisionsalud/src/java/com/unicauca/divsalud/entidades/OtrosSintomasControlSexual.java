/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "otros_sintomas_control_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OtrosSintomasControlSexual.findAll", query = "SELECT o FROM OtrosSintomasControlSexual o")
    , @NamedQuery(name = "OtrosSintomasControlSexual.findByIdOtroSintomaControl", query = "SELECT o FROM OtrosSintomasControlSexual o WHERE o.idOtroSintomaControl = :idOtroSintomaControl")
    , @NamedQuery(name = "OtrosSintomasControlSexual.findByNombre", query = "SELECT o FROM OtrosSintomasControlSexual o WHERE o.nombre = :nombre")})
public class OtrosSintomasControlSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OTRO_SINTOMA_CONTROL")
    private Long idOtroSintomaControl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinTable(name = "otros_contol_sintomas_sexual", joinColumns = {
        @JoinColumn(name = "ID_OTRO_SINTOMA_CONTROL", referencedColumnName = "ID_OTRO_SINTOMA_CONTROL")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_CONTROL_SEXUAL", referencedColumnName = "ID_CONTROL_SEXUAL")})
    @ManyToMany
    private List<ControlPacienteSexual> controlPacienteSexualList;

    public OtrosSintomasControlSexual() {
    }

    public OtrosSintomasControlSexual(Long idOtroSintomaControl) {
        this.idOtroSintomaControl = idOtroSintomaControl;
    }

    public OtrosSintomasControlSexual(Long idOtroSintomaControl, String nombre) {
        this.idOtroSintomaControl = idOtroSintomaControl;
        this.nombre = nombre;
    }

    public Long getIdOtroSintomaControl() {
        return idOtroSintomaControl;
    }

    public void setIdOtroSintomaControl(Long idOtroSintomaControl) {
        this.idOtroSintomaControl = idOtroSintomaControl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ControlPacienteSexual> getControlPacienteSexualList() {
        return controlPacienteSexualList;
    }

    public void setControlPacienteSexualList(List<ControlPacienteSexual> controlPacienteSexualList) {
        this.controlPacienteSexualList = controlPacienteSexualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOtroSintomaControl != null ? idOtroSintomaControl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtrosSintomasControlSexual)) {
            return false;
        }
        OtrosSintomasControlSexual other = (OtrosSintomasControlSexual) object;
        if ((this.idOtroSintomaControl == null && other.idOtroSintomaControl != null) || (this.idOtroSintomaControl != null && !this.idOtroSintomaControl.equals(other.idOtroSintomaControl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.OtrosSintomasControlSexual[ idOtroSintomaControl=" + idOtroSintomaControl + " ]";
    }
    
}
