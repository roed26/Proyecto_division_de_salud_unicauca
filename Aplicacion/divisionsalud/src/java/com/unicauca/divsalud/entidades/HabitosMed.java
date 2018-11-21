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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Danilo
 */
@Entity
@Table(name = "habitos_med")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HabitosMed.findAll", query = "SELECT h FROM HabitosMed h"),
    @NamedQuery(name = "HabitosMed.findByIdTipoHabito", query = "SELECT h FROM HabitosMed h WHERE h.habitosMedPK.idTipoHabito = :idTipoHabito"),
    @NamedQuery(name = "HabitosMed.findByIdxConsulta", query = "SELECT h FROM HabitosMed h WHERE h.habitosMedPK.idxConsulta = :idxConsulta"),
    @NamedQuery(name = "HabitosMed.findByConsumeSiNoEx", query = "SELECT h FROM HabitosMed h WHERE h.consumeSiNoEx = :consumeSiNoEx")})
public class HabitosMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HabitosMedPK habitosMedPK;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CONSUME_SI_NO_EX")
    private Integer consumeSiNoEx;
    @JoinColumn(name = "IDX_CONSULTA", referencedColumnName = "IDX", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ConsultaMedicaMed consultaMedicaMed;
    @JoinColumn(name = "ID_TIPO_HABITO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoHabito tipoHabito;

    public HabitosMed() {
    }

    public HabitosMed(HabitosMedPK habitosMedPK) {
        this.habitosMedPK = habitosMedPK;
    }

    public HabitosMed(int idTipoHabito, int idxConsulta) {
        this.habitosMedPK = new HabitosMedPK(idTipoHabito, idxConsulta);
    }

    public HabitosMedPK getHabitosMedPK() {
        return habitosMedPK;
    }

    public void setHabitosMedPK(HabitosMedPK habitosMedPK) {
        this.habitosMedPK = habitosMedPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getConsumeSiNoEx() {
        return consumeSiNoEx;
    }

    public void setConsumeSiNoEx(Integer consumeSiNoEx) {
        this.consumeSiNoEx = consumeSiNoEx;
    }

    public ConsultaMedicaMed getConsultaMedicaMed() {
        return consultaMedicaMed;
    }

    public void setConsultaMedicaMed(ConsultaMedicaMed consultaMedicaMed) {
        this.consultaMedicaMed = consultaMedicaMed;
    }

    public TipoHabito getTipoHabito() {
        return tipoHabito;
    }

    public void setTipoHabito(TipoHabito tipoHabito) {
        this.tipoHabito = tipoHabito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (habitosMedPK != null ? habitosMedPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HabitosMed)) {
            return false;
        }
        HabitosMed other = (HabitosMed) object;
        if ((this.habitosMedPK == null && other.habitosMedPK != null) || (this.habitosMedPK != null && !this.habitosMedPK.equals(other.habitosMedPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.HabitosMed[ habitosMedPK=" + habitosMedPK + " ]";
    }
    
}
