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
@Table(name = "categoria_afiliado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaAfiliado.findAll", query = "SELECT c FROM CategoriaAfiliado c"),
    @NamedQuery(name = "CategoriaAfiliado.findById", query = "SELECT c FROM CategoriaAfiliado c WHERE c.id = :id"),
    @NamedQuery(name = "CategoriaAfiliado.findByNombre", query = "SELECT c FROM CategoriaAfiliado c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CategoriaAfiliado.findByCuotaCot", query = "SELECT c FROM CategoriaAfiliado c WHERE c.cuotaCot = :cuotaCot"),
    @NamedQuery(name = "CategoriaAfiliado.findByCopagoCot", query = "SELECT c FROM CategoriaAfiliado c WHERE c.copagoCot = :copagoCot"),
    @NamedQuery(name = "CategoriaAfiliado.findByCuotaBen", query = "SELECT c FROM CategoriaAfiliado c WHERE c.cuotaBen = :cuotaBen"),
    @NamedQuery(name = "CategoriaAfiliado.findByCopagoBen", query = "SELECT c FROM CategoriaAfiliado c WHERE c.copagoBen = :copagoBen"),
    @NamedQuery(name = "CategoriaAfiliado.findByMaxCopago", query = "SELECT c FROM CategoriaAfiliado c WHERE c.maxCopago = :maxCopago")})
public class CategoriaAfiliado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CUOTA_COT")
    private Integer cuotaCot;
    @Size(max = 4)
    @Column(name = "COPAGO_COT")
    private String copagoCot;
    @Column(name = "CUOTA_BEN")
    private Integer cuotaBen;
    @Size(max = 4)
    @Column(name = "COPAGO_BEN")
    private String copagoBen;
    @Column(name = "MAX_COPAGO")
    private Integer maxCopago;
    @JoinColumn(name = "ID_REGIMEN", referencedColumnName = "ID")
    @ManyToOne
    private TipoRegimen idRegimen;
    @OneToMany(mappedBy = "categoriaAfiliado")
    private Collection<Paciente> pacienteCollection;

    public CategoriaAfiliado() {
    }

    public CategoriaAfiliado(Integer id) {
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

    public Integer getCuotaCot() {
        return cuotaCot;
    }

    public void setCuotaCot(Integer cuotaCot) {
        this.cuotaCot = cuotaCot;
    }

    public String getCopagoCot() {
        return copagoCot;
    }

    public void setCopagoCot(String copagoCot) {
        this.copagoCot = copagoCot;
    }

    public Integer getCuotaBen() {
        return cuotaBen;
    }

    public void setCuotaBen(Integer cuotaBen) {
        this.cuotaBen = cuotaBen;
    }

    public String getCopagoBen() {
        return copagoBen;
    }

    public void setCopagoBen(String copagoBen) {
        this.copagoBen = copagoBen;
    }

    public Integer getMaxCopago() {
        return maxCopago;
    }

    public void setMaxCopago(Integer maxCopago) {
        this.maxCopago = maxCopago;
    }

    public TipoRegimen getIdRegimen() {
        return idRegimen;
    }

    public void setIdRegimen(TipoRegimen idRegimen) {
        this.idRegimen = idRegimen;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
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
        if (!(object instanceof CategoriaAfiliado)) {
            return false;
        }
        CategoriaAfiliado other = (CategoriaAfiliado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.CategoriaAfiliado[ id=" + id + " ]";
    }
    
}
