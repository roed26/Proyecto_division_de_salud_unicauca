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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Danilo
 */
@Entity
@Table(name = "enfermedades_cie10_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnfermedadesCie10Med.findAll", query = "SELECT e FROM EnfermedadesCie10Med e"),
    @NamedQuery(name = "EnfermedadesCie10Med.findByCodigo", query = "SELECT e FROM EnfermedadesCie10Med e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EnfermedadesCie10Med.findByDescripcion", query = "SELECT e FROM EnfermedadesCie10Med e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "EnfermedadesCie10Med.findByEstado", query = "SELECT e FROM EnfermedadesCie10Med e WHERE e.estado = :estado"),
    @NamedQuery(name = "EnfermedadesCie10Med.findByDiagnostico", query = "SELECT e FROM EnfermedadesCie10Med e WHERE LOWER(CONCAT(CONCAT(CONCAT(e.codigo,' '), e.descripcion),' ')) LIKE :busqueda")  
})
public class EnfermedadesCie10Med implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO")
    private String codigo;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enfermedadesCie10Med")
    private Collection<Diagnosticos> diagnosticosCollection;

    public EnfermedadesCie10Med() {
    }

    public EnfermedadesCie10Med(String codigo) {
        this.codigo = codigo;
    }

    public EnfermedadesCie10Med(String codigo, boolean estado) {
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

    @XmlTransient
    public Collection<Diagnosticos> getDiagnosticosCollection() {
        return diagnosticosCollection;
    }

    public void setDiagnosticosCollection(Collection<Diagnosticos> diagnosticosCollection) {
        this.diagnosticosCollection = diagnosticosCollection;
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
        if (!(object instanceof EnfermedadesCie10Med)) {
            return false;
        }
        EnfermedadesCie10Med other = (EnfermedadesCie10Med) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.EnfermedadesCie10Med[ codigo=" + codigo + " ]";
    }
    
}
