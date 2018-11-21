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
@Table(name = "servicios_odo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiciosOdo.findAll", query = "SELECT s FROM ServiciosOdo s"),
    @NamedQuery(name = "ServiciosOdo.findById", query = "SELECT s FROM ServiciosOdo s WHERE s.id = :id"),
    @NamedQuery(name = "ServiciosOdo.findByNombre", query = "SELECT s FROM ServiciosOdo s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "ServiciosOdo.findByCodigoPre", query = "SELECT s FROM ServiciosOdo s WHERE s.codigoPre = :codigoPre"),
    @NamedQuery(name = "ServiciosOdo.findByValorPre", query = "SELECT s FROM ServiciosOdo s WHERE s.valorPre = :valorPre"),
    @NamedQuery(name = "ServiciosOdo.findByCodigoPos", query = "SELECT s FROM ServiciosOdo s WHERE s.codigoPos = :codigoPos"),
    @NamedQuery(name = "ServiciosOdo.findByValorPos", query = "SELECT s FROM ServiciosOdo s WHERE s.valorPos = :valorPos")})
public class ServiciosOdo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 80)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 10)
    @Column(name = "CODIGO_PRE")
    private String codigoPre;
    @Size(max = 10)
    @Column(name = "VALOR_PRE")
    private String valorPre;
    @Size(max = 10)
    @Column(name = "CODIGO_POS")
    private String codigoPos;
    @Size(max = 10)
    @Column(name = "VALOR_POS")
    private String valorPos;

    public ServiciosOdo() {
    }

    public ServiciosOdo(Integer id) {
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

    public String getCodigoPre() {
        return codigoPre;
    }

    public void setCodigoPre(String codigoPre) {
        this.codigoPre = codigoPre;
    }

    public String getValorPre() {
        return valorPre;
    }

    public void setValorPre(String valorPre) {
        this.valorPre = valorPre;
    }

    public String getCodigoPos() {
        return codigoPos;
    }

    public void setCodigoPos(String codigoPos) {
        this.codigoPos = codigoPos;
    }

    public String getValorPos() {
        return valorPos;
    }

    public void setValorPos(String valorPos) {
        this.valorPos = valorPos;
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
        if (!(object instanceof ServiciosOdo)) {
            return false;
        }
        ServiciosOdo other = (ServiciosOdo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.ServiciosOdo[ id=" + id + " ]";
    }
    
}
