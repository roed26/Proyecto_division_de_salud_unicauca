/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "metodo_adoptado_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MetodoAdoptadoSexual.findAll", query = "SELECT m FROM MetodoAdoptadoSexual m")
    , @NamedQuery(name = "MetodoAdoptadoSexual.findById", query = "SELECT m FROM MetodoAdoptadoSexual m WHERE m.metodoAdoptadoSexualPK.id = :id")
    , @NamedQuery(name = "MetodoAdoptadoSexual.findByIdMetodoPlanificacion", query = "SELECT m FROM MetodoAdoptadoSexual m WHERE m.metodoAdoptadoSexualPK.idMetodoPlanificacion = :idMetodoPlanificacion")
    , @NamedQuery(name = "MetodoAdoptadoSexual.findByFechaInscripcion", query = "SELECT m FROM MetodoAdoptadoSexual m WHERE m.fechaInscripcion = :fechaInscripcion")
    , @NamedQuery(name = "MetodoAdoptadoSexual.findByFechaProximaCita", query = "SELECT m FROM MetodoAdoptadoSexual m WHERE m.fechaProximaCita = :fechaProximaCita")
    , @NamedQuery(name = "MetodoAdoptadoSexual.findByConQuien", query = "SELECT m FROM MetodoAdoptadoSexual m WHERE m.conQuien = :conQuien")
    , @NamedQuery(name = "MetodoAdoptadoSexual.findByCumpleOms", query = "SELECT m FROM MetodoAdoptadoSexual m WHERE m.cumpleOms = :cumpleOms")
    , @NamedQuery(name = "MetodoAdoptadoSexual.findByMetodoAdoptado", query = "SELECT m FROM MetodoAdoptadoSexual m WHERE m.metodoAdoptado = :metodoAdoptado")})
public class MetodoAdoptadoSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MetodoAdoptadoSexualPK metodoAdoptadoSexualPK;
    @Column(name = "FECHA_INSCRIPCION")
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;
    @Column(name = "FECHA_PROXIMA_CITA")
    @Temporal(TemporalType.DATE)
    private Date fechaProximaCita;
    @Size(max = 60)
    @Column(name = "CON_QUIEN")
    private String conQuien;
    @Size(max = 2)
    @Column(name = "CUMPLE_OMS")
    private String cumpleOms;
    @Size(max = 60)
    @Column(name = "METODO_ADOPTADO")
    private String metodoAdoptado;
    @Size(max = 300)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private HistoriaModuloSexualidad historiaModuloSexualidad;
    @JoinColumn(name = "ID_METODO_PLANIFICACION", referencedColumnName = "ID_METODO_PLANIFICACION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MetodoPlanificacion metodoPlanificacion;

    public MetodoAdoptadoSexual() {
        this.cumpleOms = new String();
    }

    public MetodoAdoptadoSexual(MetodoAdoptadoSexualPK metodoAdoptadoSexualPK) {
        this.metodoAdoptadoSexualPK = metodoAdoptadoSexualPK;
    }

    public MetodoAdoptadoSexual(int id, short idMetodoPlanificacion) {
        this.metodoAdoptadoSexualPK = new MetodoAdoptadoSexualPK(id, idMetodoPlanificacion);
    }

    public MetodoAdoptadoSexualPK getMetodoAdoptadoSexualPK() {
        return metodoAdoptadoSexualPK;
    }

    public void setMetodoAdoptadoSexualPK(MetodoAdoptadoSexualPK metodoAdoptadoSexualPK) {
        this.metodoAdoptadoSexualPK = metodoAdoptadoSexualPK;
    }

    public Date getFechaInscripcion() {
        if (fechaInscripcion==null) fechaInscripcion = new Date();
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Date getFechaProximaCita() {
        return fechaProximaCita;
    }

    public void setFechaProximaCita(Date fechaProximaCita) {
        this.fechaProximaCita = fechaProximaCita;
    }

    public String getConQuien() {
        return conQuien;
    }

    public void setConQuien(String conQuien) {
        this.conQuien = conQuien;
    }

    public String getCumpleOms() {
        if (cumpleOms==null) cumpleOms = new String();
        return cumpleOms;
    }

    public void setCumpleOms(String cumpleOms) {
        this.cumpleOms = cumpleOms;
    }

    public String getMetodoAdoptado() {
        return metodoAdoptado;
    }

    public void setMetodoAdoptado(String metodoAdoptado) {
        this.metodoAdoptado = metodoAdoptado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public HistoriaModuloSexualidad getHistoriaModuloSexualidad() {
        return historiaModuloSexualidad;
    }

    public void setHistoriaModuloSexualidad(HistoriaModuloSexualidad historiaModuloSexualidad) {
        this.historiaModuloSexualidad = historiaModuloSexualidad;
    }

    public MetodoPlanificacion getMetodoPlanificacion() {
        if(metodoPlanificacion==null)
            metodoPlanificacion = new MetodoPlanificacion();
        return metodoPlanificacion;
    }

    public void setMetodoPlanificacion(MetodoPlanificacion metodoPlanificacion) {
        this.metodoPlanificacion = metodoPlanificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (metodoAdoptadoSexualPK != null ? metodoAdoptadoSexualPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetodoAdoptadoSexual)) {
            return false;
        }
        MetodoAdoptadoSexual other = (MetodoAdoptadoSexual) object;
        if ((this.metodoAdoptadoSexualPK == null && other.metodoAdoptadoSexualPK != null) || (this.metodoAdoptadoSexualPK != null && !this.metodoAdoptadoSexualPK.equals(other.metodoAdoptadoSexualPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.MetodoAdoptadoSexual[ metodoAdoptadoSexualPK=" + metodoAdoptadoSexualPK + " ]";
    }
    
}
