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
@Table(name = "informacion_antecedente_paciente_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformacionAntecedentePacienteSexual.findAll", query = "SELECT i FROM InformacionAntecedentePacienteSexual i")
    , @NamedQuery(name = "InformacionAntecedentePacienteSexual.findById", query = "SELECT i FROM InformacionAntecedentePacienteSexual i WHERE i.informacionAntecedentePacienteSexualPK.id = :id")
    , @NamedQuery(name = "InformacionAntecedentePacienteSexual.findByIdAntecedente", query = "SELECT i FROM InformacionAntecedentePacienteSexual i WHERE i.informacionAntecedentePacienteSexualPK.idAntecedente = :idAntecedente")
    , @NamedQuery(name = "InformacionAntecedentePacienteSexual.findByResultadoAntecedente", query = "SELECT i FROM InformacionAntecedentePacienteSexual i WHERE i.resultadoAntecedente = :resultadoAntecedente")})
public class InformacionAntecedentePacienteSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InformacionAntecedentePacienteSexualPK informacionAntecedentePacienteSexualPK;
    @Size(max = 2)
    @Column(name = "RESULTADO_ANTECEDENTE")
    private String resultadoAntecedente;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AntecedentesPersonalesSexual antecedentesPersonalesSexual;
    @JoinColumn(name = "ID_ANTECEDENTE", referencedColumnName = "ID_ANTECEDENTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoAntecedentePersonalSexual tipoAntecedentePersonalSexual;

    public InformacionAntecedentePacienteSexual() {
    }

    public InformacionAntecedentePacienteSexual(InformacionAntecedentePacienteSexualPK informacionAntecedentePacienteSexualPK) {
        this.informacionAntecedentePacienteSexualPK = informacionAntecedentePacienteSexualPK;
    }

    public InformacionAntecedentePacienteSexual(int id, long idAntecedente) {
        this.informacionAntecedentePacienteSexualPK = new InformacionAntecedentePacienteSexualPK(id, idAntecedente);
    }

    public InformacionAntecedentePacienteSexualPK getInformacionAntecedentePacienteSexualPK() {
        return informacionAntecedentePacienteSexualPK;
    }

    public void setInformacionAntecedentePacienteSexualPK(InformacionAntecedentePacienteSexualPK informacionAntecedentePacienteSexualPK) {
        this.informacionAntecedentePacienteSexualPK = informacionAntecedentePacienteSexualPK;
    }

    public String getResultadoAntecedente() {
        return resultadoAntecedente;
    }

    public void setResultadoAntecedente(String resultadoAntecedente) {
        this.resultadoAntecedente = resultadoAntecedente;
    }

    public AntecedentesPersonalesSexual getAntecedentesPersonalesSexual() {
        return antecedentesPersonalesSexual;
    }

    public void setAntecedentesPersonalesSexual(AntecedentesPersonalesSexual antecedentesPersonalesSexual) {
        this.antecedentesPersonalesSexual = antecedentesPersonalesSexual;
    }

    public TipoAntecedentePersonalSexual getTipoAntecedentePersonalSexual() {
        return tipoAntecedentePersonalSexual;
    }

    public void setTipoAntecedentePersonalSexual(TipoAntecedentePersonalSexual tipoAntecedentePersonalSexual) {
        this.tipoAntecedentePersonalSexual = tipoAntecedentePersonalSexual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (informacionAntecedentePacienteSexualPK != null ? informacionAntecedentePacienteSexualPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformacionAntecedentePacienteSexual)) {
            return false;
        }
        InformacionAntecedentePacienteSexual other = (InformacionAntecedentePacienteSexual) object;
        if ((this.informacionAntecedentePacienteSexualPK == null && other.informacionAntecedentePacienteSexualPK != null) || (this.informacionAntecedentePacienteSexualPK != null && !this.informacionAntecedentePacienteSexualPK.equals(other.informacionAntecedentePacienteSexualPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.InformacionAntecedentePacienteSexual[ informacionAntecedentePacienteSexualPK=" + informacionAntecedentePacienteSexualPK + " ]";
    }
    
}
