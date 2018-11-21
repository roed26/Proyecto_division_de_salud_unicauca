/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Albert Muñoz
 */
@Entity
@Table(name = "procedimientos_cups_med")
@NamedQueries({
    @NamedQuery(name = "ProcedimientosCupsMed.findAll", query = "SELECT p FROM ProcedimientosCupsMed p"),
    @NamedQuery(name = "ProcedimientosCupsMed.findByCodigo", query = "SELECT p FROM ProcedimientosCupsMed p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ProcedimientosCupsMed.findByDescripcion", query = "SELECT p FROM ProcedimientosCupsMed p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ProcedimientosCupsMed.findByEstado", query = "SELECT p FROM ProcedimientosCupsMed p WHERE p.estado = :estado"),
    @NamedQuery(name = "ProcedimientosCupsMed.findByProcedimientos", query = "SELECT p FROM ProcedimientosCupsMed p WHERE LOWER(CONCAT(CONCAT(CONCAT(p.codigo,' '), p.descripcion),' ')) LIKE :busqueda")
})
public class ProcedimientosCupsMed implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedimientosCupsMedCodigo")
    private Collection<QuirurgicoMed> quirurgicoMedCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO")
    private String codigo;
    @Size(max = 1000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private boolean estado;

    public ProcedimientosCupsMed() {
    }

    public ProcedimientosCupsMed(String codigo) {
        this.codigo = codigo;
    }

    public ProcedimientosCupsMed(String codigo, boolean estado) {
        this.codigo = codigo;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcedimientosCupsMed)) {
            return false;
        }
        ProcedimientosCupsMed other = (ProcedimientosCupsMed) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ProcedimientosCupsMed[ codigo=" + codigo + " ]";
    }

    @XmlTransient
    public Collection<QuirurgicoMed> getQuirurgicoMedCollection() {
        return quirurgicoMedCollection;
    }

    public void setQuirurgicoMedCollection(Collection<QuirurgicoMed> quirurgicoMedCollection) {
        this.quirurgicoMedCollection = quirurgicoMedCollection;
    }
    
}
