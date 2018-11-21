/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "listado_ant_odon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListadoAntOdon.findAll", query = "SELECT l FROM ListadoAntOdon l"),
    @NamedQuery(name = "ListadoAntOdon.findById", query = "SELECT l FROM ListadoAntOdon l WHERE l.id = :id"),
    @NamedQuery(name = "ListadoAntOdon.findByNombre", query = "SELECT l FROM ListadoAntOdon l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "ListadoAntOdon.findByFamiliar", query = "SELECT l FROM ListadoAntOdon l WHERE l.familiar = :familiar"),
    @NamedQuery(name = "ListadoAntOdon.findByPersonal", query = "SELECT l FROM ListadoAntOdon l WHERE l.personal = :personal")})
public class ListadoAntOdon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 1)
    @Column(name = "FAMILIAR")
    private String familiar;
    @Size(max = 1)
    @Column(name = "PERSONAL")
    private String personal;

    public ListadoAntOdon() {
    }

    public ListadoAntOdon(Integer id) {
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

    public String getFamiliar() {
        return familiar;
    }

    public void setFamiliar(String familiar) {
        this.familiar = familiar;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
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
        if (!(object instanceof ListadoAntOdon)) {
            return false;
        }
        ListadoAntOdon other = (ListadoAntOdon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ListadoAntOdon[ id=" + id + " ]";
    }
    
}
