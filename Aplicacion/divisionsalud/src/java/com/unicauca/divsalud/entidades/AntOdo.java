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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "ant_odo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntOdo.findAll", query = "SELECT a FROM AntOdo a"),
    @NamedQuery(name = "AntOdo.findById", query = "SELECT a FROM AntOdo a WHERE a.id = :id"),
    @NamedQuery(name = "AntOdo.findByNombre", query = "SELECT a FROM AntOdo a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AntOdo.findByResultado", query = "SELECT a FROM AntOdo a WHERE a.resultado = :resultado"),
    @NamedQuery(name = "AntOdo.findByTipo", query = "SELECT a FROM AntOdo a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AntOdo.findByIdAnt", query = "SELECT a FROM AntOdo a WHERE a.idAnt = :idAnt"),
    @NamedQuery(name = "AntOdo.findByActualizacion", query = "SELECT a FROM AntOdo a WHERE a.idActualizacion = :actualizacion"),
    @NamedQuery(name = "AntOdo.findByActualizacionAlergia", query = "SELECT a FROM AntOdo a WHERE a.idActualizacion = :actualizacion AND a.nombre = :alergia AND a.tipo = :tipo")

})
public class AntOdo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 2)
    @Column(name = "RESULTADO")
    private String resultado;
    @Size(max = 2)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 2)
    @Column(name = "ID_ANT")
    private String idAnt;
    @JoinColumn(name = "ID_ACTUALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private ActualizacionOdo idActualizacion;

    public AntOdo() {
    }

    public AntOdo(Integer id) {
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

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdAnt() {
        return idAnt;
    }

    public void setIdAnt(String idAnt) {
        this.idAnt = idAnt;
    }

    public ActualizacionOdo getIdActualizacion() {
        return idActualizacion;
    }

    public void setIdActualizacion(ActualizacionOdo idActualizacion) {
        this.idActualizacion = idActualizacion;
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
        if (!(object instanceof AntOdo)) {
            return false;
        }
        AntOdo other = (AntOdo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.AntOdo[ id=" + id + " ]";
    }
    
}
