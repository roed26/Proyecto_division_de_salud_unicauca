/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "tipo_antecedente_personal_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAntecedentePersonalSexual.findAll", query = "SELECT t FROM TipoAntecedentePersonalSexual t")
    , @NamedQuery(name = "TipoAntecedentePersonalSexual.findByIdAntecedente", query = "SELECT t FROM TipoAntecedentePersonalSexual t WHERE t.idAntecedente = :idAntecedente")
    , @NamedQuery(name = "TipoAntecedentePersonalSexual.findByNombreAntecedente", query = "SELECT t FROM TipoAntecedentePersonalSexual t WHERE t.nombreAntecedente = :nombreAntecedente")})
public class TipoAntecedentePersonalSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ANTECEDENTE")
    private Long idAntecedente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE_ANTECEDENTE")
    private String nombreAntecedente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAntecedentePersonalSexual")
    private List<InformacionAntecedentePacienteSexual> informacionAntecedentePacienteSexualList;

    public TipoAntecedentePersonalSexual() {
    }

    public TipoAntecedentePersonalSexual(Long idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public TipoAntecedentePersonalSexual(Long idAntecedente, String nombreAntecedente) {
        this.idAntecedente = idAntecedente;
        this.nombreAntecedente = nombreAntecedente;
    }

    public Long getIdAntecedente() {
        return idAntecedente;
    }

    public void setIdAntecedente(Long idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public String getNombreAntecedente() {
        return nombreAntecedente;
    }

    public void setNombreAntecedente(String nombreAntecedente) {
        this.nombreAntecedente = nombreAntecedente;
    }

    @XmlTransient
    public List<InformacionAntecedentePacienteSexual> getInformacionAntecedentePacienteSexualList() {
        return informacionAntecedentePacienteSexualList;
    }

    public void setInformacionAntecedentePacienteSexualList(List<InformacionAntecedentePacienteSexual> informacionAntecedentePacienteSexualList) {
        this.informacionAntecedentePacienteSexualList = informacionAntecedentePacienteSexualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAntecedente != null ? idAntecedente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAntecedentePersonalSexual)) {
            return false;
        }
        TipoAntecedentePersonalSexual other = (TipoAntecedentePersonalSexual) object;
        if ((this.idAntecedente == null && other.idAntecedente != null) || (this.idAntecedente != null && !this.idAntecedente.equals(other.idAntecedente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.TipoAntecedentePersonalSexual[ idAntecedente=" + idAntecedente + " ]";
    }
    
}
