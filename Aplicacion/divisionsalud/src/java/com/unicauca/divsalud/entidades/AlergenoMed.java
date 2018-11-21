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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Albert Muñoz
 */
@Entity
@Table(name = "alergeno_med")
@NamedQueries({
    @NamedQuery(name = "AlergenoMed.findAll", query = "SELECT a FROM AlergenoMed a"),
    @NamedQuery(name = "AlergenoMed.findByIdx", query = "SELECT a FROM AlergenoMed a WHERE a.idx = :idx"),
    @NamedQuery(name = "AlergenoMed.findByNombre", query = "SELECT a FROM AlergenoMed a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AlergenoMed.findByTipo", query = "SELECT a FROM AlergenoMed a WHERE a.idxTipoAlergeno = :idxTipoAlergeno"),
    @NamedQuery(name = "AlergenoMed.findByEstado", query = "SELECT a FROM AlergenoMed a WHERE a.estado = :estado"),
    @NamedQuery(name = "AlergenoMed.findByAlergeno", query = "SELECT a FROM AlergenoMed a WHERE LOWER(CONCAT(CONCAT(CONCAT(CONCAT(a.idx,' '), a.nombre),' '),a.idxTipoAlergeno.nombre,' ')) LIKE :busqueda")  
})
public class AlergenoMed implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alergenoMed")
    private Collection<ConsultaAlergenoMed> consultaAlergenoMedCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private boolean estado;
    @JoinColumn(name = "IDX_TIPO_ALERGENO", referencedColumnName = "IDX")
    @ManyToOne
    private TipoAlergenoMed idxTipoAlergeno;

    public AlergenoMed() {
    }

    public AlergenoMed(Integer idx) {
        this.idx = idx;
    }

    public AlergenoMed(Integer idx, boolean estado) {
        this.idx = idx;
        this.estado = estado;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public TipoAlergenoMed getIdxTipoAlergeno() {
        return idxTipoAlergeno;
    }

    public void setIdxTipoAlergeno(TipoAlergenoMed idxTipoAlergeno) {
        this.idxTipoAlergeno = idxTipoAlergeno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idx != null ? idx.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlergenoMed)) {
            return false;
        }
        AlergenoMed other = (AlergenoMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + nombre + "";
        //return "com.unicauca.divsalud.entidades.AlergenoMed[ idx=" + idx + " ]";
    }

    @XmlTransient
    public Collection<ConsultaAlergenoMed> getConsultaAlergenoMedCollection() {
        return consultaAlergenoMedCollection;
    }

    public void setConsultaAlergenoMedCollection(Collection<ConsultaAlergenoMed> consultaAlergenoMedCollection) {
        this.consultaAlergenoMedCollection = consultaAlergenoMedCollection;
    }
    
}
