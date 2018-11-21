/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sahydo
 */
@Entity
@Table(name = "cita_medica_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CitaMedicaMed.findAll", query = "SELECT c FROM CitaMedicaMed c"),
    @NamedQuery(name = "CitaMedicaMed.findById", query = "SELECT c FROM CitaMedicaMed c WHERE c.id = :id"),
    @NamedQuery(name = "CitaMedicaMed.findByFechaProgramada", query = "SELECT c FROM CitaMedicaMed c WHERE c.fechaProgramada = :fechaProgramada"),
    @NamedQuery(name = "CitaMedicaMed.findByHoraInicio", query = "SELECT c FROM CitaMedicaMed c WHERE c.horaInicio = :horaInicio"),
    @NamedQuery(name = "CitaMedicaMed.findByHoraFin", query = "SELECT c FROM CitaMedicaMed c WHERE c.horaFin = :horaFin"),
    @NamedQuery(name = "CitaMedicaMed.findByEstado", query = "SELECT c FROM CitaMedicaMed c WHERE c.estado = :estado")})
public class CitaMedicaMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA_PROGRAMADA")
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    private Date fechaProgramada;
    @Column(name = "HORA_INICIO")
    @Temporal(TemporalType.TIME)
    @Basic(optional = false)
    private Date horaInicio;
    @Column(name = "HORA_FIN")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Size(max = 10)
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "paciente_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Paciente pacienteID;
    @JoinColumn(name = "tipo_cita_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoCitaMed tipocitaID;
    @JoinColumn(name = "usuarios_sistema_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UsuariosSistema usuariossistemaID;

    public CitaMedicaMed() {
    }

    public CitaMedicaMed(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPacienteID() {
        return pacienteID;
    }

    public void setPacienteID(Paciente pacienteID) {
        this.pacienteID = pacienteID;
    }

    public TipoCitaMed getTipocitaID() {
        return tipocitaID;
    }

    public void setTipocitaID(TipoCitaMed tipocitaID) {
        this.tipocitaID = tipocitaID;
    }

    public UsuariosSistema getUsuariossistemaID() {
        return usuariossistemaID;
    }

    public void setUsuariossistemaID(UsuariosSistema usuariossistemaID) {
        this.usuariossistemaID = usuariossistemaID;
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
        if (!(object instanceof CitaMedicaMed)) {
            return false;
        }
        CitaMedicaMed other = (CitaMedicaMed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CitaMedicaMed[ id=" + id + " ]";
    }
    
}
