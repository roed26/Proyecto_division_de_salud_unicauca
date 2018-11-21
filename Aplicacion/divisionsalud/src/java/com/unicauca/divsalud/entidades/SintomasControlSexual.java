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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "sintomas_control_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SintomasControlSexual.findAll", query = "SELECT s FROM SintomasControlSexual s")
    , @NamedQuery(name = "SintomasControlSexual.findByIdSintomaControl", query = "SELECT s FROM SintomasControlSexual s WHERE s.idSintomaControl = :idSintomaControl")
    , @NamedQuery(name = "SintomasControlSexual.findByNombre", query = "SELECT s FROM SintomasControlSexual s WHERE s.nombre = :nombre")})
public class SintomasControlSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SINTOMA_CONTROL")
    private Long idSintomaControl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinTable(name = "control_sintomas_sexual", joinColumns = {
        @JoinColumn(name = "ID_SINTOMA_CONTROL", referencedColumnName = "ID_SINTOMA_CONTROL")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_CONTROL_SEXUAL", referencedColumnName = "ID_CONTROL_SEXUAL")})
    @ManyToMany
    private List<ControlPacienteSexual> controlPacienteSexualList;
    
    @Transient
    private boolean seleccionado;
    
    public SintomasControlSexual() {
        seleccionado = false;
    }

    public boolean isSeleccionado() {
        
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
        
    }
    
    public SintomasControlSexual(Long idSintomaControl) {
        this.idSintomaControl = idSintomaControl;
    }

    public SintomasControlSexual(Long idSintomaControl, String nombre) {
        this.idSintomaControl = idSintomaControl;
        this.nombre = nombre;
    }

    public Long getIdSintomaControl() {
        return idSintomaControl;
    }

    public void setIdSintomaControl(Long idSintomaControl) {
        this.idSintomaControl = idSintomaControl;
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
        hash += (idSintomaControl != null ? idSintomaControl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SintomasControlSexual)) {
            return false;
        }
        SintomasControlSexual other = (SintomasControlSexual) object;
        if ((this.idSintomaControl == null && other.idSintomaControl != null) || (this.idSintomaControl != null && !this.idSintomaControl.equals(other.idSintomaControl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.SintomasControlSexual[ idSintomaControl=" + idSintomaControl + " ]";
    }
    
}
