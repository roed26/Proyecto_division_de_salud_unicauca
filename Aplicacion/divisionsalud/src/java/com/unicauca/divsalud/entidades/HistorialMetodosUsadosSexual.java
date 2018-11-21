/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "historial_metodos_usados_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialMetodosUsadosSexual.findAll", query = "SELECT h FROM HistorialMetodosUsadosSexual h")
    , @NamedQuery(name = "HistorialMetodosUsadosSexual.findById", query = "SELECT h FROM HistorialMetodosUsadosSexual h WHERE h.historialMetodosUsadosSexualPK.id = :id")
    , @NamedQuery(name = "HistorialMetodosUsadosSexual.findByIdMetodoPlanificacion", query = "SELECT h FROM HistorialMetodosUsadosSexual h WHERE h.historialMetodosUsadosSexualPK.idMetodoPlanificacion = :idMetodoPlanificacion")
    , @NamedQuery(name = "HistorialMetodosUsadosSexual.findByTiempoUtilizacion", query = "SELECT h FROM HistorialMetodosUsadosSexual h WHERE h.tiempoUtilizacion = :tiempoUtilizacion")
    , @NamedQuery(name = "HistorialMetodosUsadosSexual.findByRazonSuspension", query = "SELECT h FROM HistorialMetodosUsadosSexual h WHERE h.razonSuspension = :razonSuspension")
    , @NamedQuery(name = "HistorialMetodosUsadosSexual.findByProblemasMetodo", query = "SELECT h FROM HistorialMetodosUsadosSexual h WHERE h.problemasMetodo = :problemasMetodo")
        
})
public class HistorialMetodosUsadosSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistorialMetodosUsadosSexualPK historialMetodosUsadosSexualPK;
    @Column(name = "TIEMPO_UTILIZACION")
    private Integer tiempoUtilizacion;
    @Size(max = 70)
    @Column(name = "RAZON_SUSPENSION")
    private String razonSuspension;
    @Size(max = 70)
    @Column(name = "PROBLEMAS_METODO")
    private String problemasMetodo;
    @Size(max = 60)
    @Column(name = "NOMBRE_COMERCIAL")
    private String nombreComercial;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private HistoriaModuloSexualidad historiaModuloSexualidad;
    @JoinColumn(name = "ID_METODO_PLANIFICACION", referencedColumnName = "ID_METODO_PLANIFICACION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MetodoPlanificacion metodoPlanificacion;
     
    public HistorialMetodosUsadosSexual() {
    }
    

    public HistorialMetodosUsadosSexual(HistorialMetodosUsadosSexualPK historialMetodosUsadosSexualPK) {
        this.historialMetodosUsadosSexualPK = historialMetodosUsadosSexualPK;
    }

    public HistorialMetodosUsadosSexual(int id, short idMetodoPlanificacion) {
        this.historialMetodosUsadosSexualPK = new HistorialMetodosUsadosSexualPK(id, idMetodoPlanificacion);
    }

    public HistorialMetodosUsadosSexualPK getHistorialMetodosUsadosSexualPK() {
        return historialMetodosUsadosSexualPK;
    }

    public void setHistorialMetodosUsadosSexualPK(HistorialMetodosUsadosSexualPK historialMetodosUsadosSexualPK) {
        this.historialMetodosUsadosSexualPK = historialMetodosUsadosSexualPK;
    }

    public Integer getTiempoUtilizacion() {
        return tiempoUtilizacion;
    }

    public void setTiempoUtilizacion(Integer tiempoUtilizacion) {
        this.tiempoUtilizacion = tiempoUtilizacion;
    }

    public String getRazonSuspension() {
        return razonSuspension;
    }

    public void setRazonSuspension(String razonSuspension) {
        this.razonSuspension = razonSuspension;
    }

    public String getProblemasMetodo() {
        return problemasMetodo;
    }

    public void setProblemasMetodo(String problemasMetodo) {
        this.problemasMetodo = problemasMetodo;
    }

    public HistoriaModuloSexualidad getHistoriaModuloSexualidad() {
        return historiaModuloSexualidad;
    }

    public void setHistoriaModuloSexualidad(HistoriaModuloSexualidad historiaModuloSexualidad) {
        this.historiaModuloSexualidad = historiaModuloSexualidad;
    }

    public MetodoPlanificacion getMetodoPlanificacion() {
        if(metodoPlanificacion==null)
        {
            this.metodoPlanificacion= new MetodoPlanificacion();
        }
        
        return metodoPlanificacion;
    }

    public void setMetodoPlanificacion(MetodoPlanificacion metodoPlanificacion) {
        System.out.println("Se invoco el metodo set metodoPlanificacion" + metodoPlanificacion);
        this.metodoPlanificacion = metodoPlanificacion;
    }
    
    public String getNombreComercial() {
        if (this.nombreComercial==null) nombreComercial = "";
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historialMetodosUsadosSexualPK != null ? historialMetodosUsadosSexualPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialMetodosUsadosSexual)) {
            return false;
        }
        HistorialMetodosUsadosSexual other = (HistorialMetodosUsadosSexual) object;
        if ((this.historialMetodosUsadosSexualPK == null && other.historialMetodosUsadosSexualPK != null) || (this.historialMetodosUsadosSexualPK != null && !this.historialMetodosUsadosSexualPK.equals(other.historialMetodosUsadosSexualPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.HistorialMetodosUsadosSexual[ historialMetodosUsadosSexualPK=" + historialMetodosUsadosSexualPK + " ]";
    }
    
    
    
    
}
