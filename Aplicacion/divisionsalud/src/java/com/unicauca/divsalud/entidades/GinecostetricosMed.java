/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Danilo
 */
@Entity
@Table(name = "ginecostetricos_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GinecostetricosMed.findAll", query = "SELECT g FROM GinecostetricosMed g"),
    @NamedQuery(name = "GinecostetricosMed.findByIdx", query = "SELECT g FROM GinecostetricosMed g WHERE g.idx = :idx"),
    @NamedQuery(name = "GinecostetricosMed.findByFechaUltimaMestruacion", query = "SELECT g FROM GinecostetricosMed g WHERE g.fechaUltimaMestruacion = :fechaUltimaMestruacion"),
    @NamedQuery(name = "GinecostetricosMed.findByG", query = "SELECT g FROM GinecostetricosMed g WHERE g.g = :g"),
    @NamedQuery(name = "GinecostetricosMed.findByP", query = "SELECT g FROM GinecostetricosMed g WHERE g.p = :p"),
    @NamedQuery(name = "GinecostetricosMed.findByA", query = "SELECT g FROM GinecostetricosMed g WHERE g.a = :a"),
    @NamedQuery(name = "GinecostetricosMed.findByC", query = "SELECT g FROM GinecostetricosMed g WHERE g.c = :c"),
    @NamedQuery(name = "GinecostetricosMed.findByM", query = "SELECT g FROM GinecostetricosMed g WHERE g.m = :m"),
    @NamedQuery(name = "GinecostetricosMed.findByFechaUltimoParto", query = "SELECT g FROM GinecostetricosMed g WHERE g.fechaUltimoParto = :fechaUltimoParto"),
    @NamedQuery(name = "GinecostetricosMed.findByMetodoPlanificacion", query = "SELECT g FROM GinecostetricosMed g WHERE g.metodoPlanificacion = :metodoPlanificacion"),
    @NamedQuery(name = "GinecostetricosMed.findByCitologia", query = "SELECT g FROM GinecostetricosMed g WHERE g.citologia = :citologia")})
public class GinecostetricosMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Column(name = "FECHA_ULTIMA_MESTRUACION")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimaMestruacion;
    @Column(name = "G")
    private Integer g;
    @Column(name = "P")
    private Integer p;
    @Column(name = "A")
    private Integer a;
    @Column(name = "C")
    private Integer c;
    @Column(name = "M")
    private Integer m;
    @Column(name = "FECHA_ULTIMO_PARTO")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimoParto;
    @Size(max = 2000)
    @Column(name = "METODO_PLANIFICACION")
    private String metodoPlanificacion;
    @Size(max = 2000)
    @Column(name = "CITOLOGIA")
    private String citologia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ginecostetricosMed")
    private Collection<HistoricoGinecostetricos> historicoGinecostetricosCollection;

    public GinecostetricosMed() {
    }

    public GinecostetricosMed(Integer idx) {
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Date getFechaUltimaMestruacion() {
        return fechaUltimaMestruacion;
    }

    public void setFechaUltimaMestruacion(Date fechaUltimaMestruacion) {
        this.fechaUltimaMestruacion = fechaUltimaMestruacion;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getM() {
        return m;
    }

    public void setM(Integer m) {
        this.m = m;
    }

    public Date getFechaUltimoParto() {
        return fechaUltimoParto;
    }

    public void setFechaUltimoParto(Date fechaUltimoParto) {
        this.fechaUltimoParto = fechaUltimoParto;
    }

    public String getMetodoPlanificacion() {
        return metodoPlanificacion;
    }

    public void setMetodoPlanificacion(String metodoPlanificacion) {
        this.metodoPlanificacion = metodoPlanificacion;
    }

    public String getCitologia() {
        return citologia;
    }

    public void setCitologia(String citologia) {
        this.citologia = citologia;
    }

    @XmlTransient
    public Collection<HistoricoGinecostetricos> getHistoricoGinecostetricosCollection() {
        return historicoGinecostetricosCollection;
    }

    public void setHistoricoGinecostetricosCollection(Collection<HistoricoGinecostetricos> historicoGinecostetricosCollection) {
        this.historicoGinecostetricosCollection = historicoGinecostetricosCollection;
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
        if (!(object instanceof GinecostetricosMed)) {
            return false;
        }
        GinecostetricosMed other = (GinecostetricosMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.GinecostetricosMed[ idx=" + idx + " ]";
    }
    
}
