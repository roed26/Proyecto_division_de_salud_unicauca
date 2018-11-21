/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author ROED26
 */
@Entity
@Table(name = "programas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programas.findAll", query = "SELECT p FROM Programas p"),
    @NamedQuery(name = "Programas.findById", query = "SELECT p FROM Programas p WHERE p.id = :id"),
    @NamedQuery(name = "Programas.findByNombre", query = "SELECT p FROM Programas p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Programas.findByIdPrograma", query = "SELECT p FROM Programas p WHERE p.facultad.id = :idProgram"),
    @NamedQuery(name = "Programas.findByProgramas", query = "SELECT p FROM Programas p WHERE LOWER(CONCAT(CONCAT(CONCAT(CONCAT(p.id,' '), p.nombre),' ') ,p.facultad.nombre,' ')) LIKE :busqueda")    
})
public class Programas implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private boolean estado;

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ID")
    private Integer id;        
    
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;    
    
    /*
    @Size(min = 1, max = 2)
    @Column(name = "estado")
    private Integer estado;
    */        
    
    @OneToMany(mappedBy = "programa")    
    private Collection<Paciente> pacienteCollection;
    
    @JoinColumn(name = "FACULTAD", referencedColumnName = "ID")
    @ManyToOne
    private Facultad facultad;

    public Programas() {
    }

    public Programas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    public Facultad getFacultad() {
        //String aux=facultad.getNombre();
        //Facultad aux2=(aux);
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programas)) {
            return false;
        }
        Programas other = (Programas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + nombre + "";
        //return "com.unicauca.divsalud.entidades.Programas[ id=" + id + " ]";
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
