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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Albert Muñoz
 */
@Entity
@Table(name = "presentacion_medicamento_med")
@NamedQueries({
    @NamedQuery(name = "PresentacionMedicamentoMed.findAll", query = "SELECT p FROM PresentacionMedicamentoMed p"),
    @NamedQuery(name = "PresentacionMedicamentoMed.findByIdx", query = "SELECT p FROM PresentacionMedicamentoMed p WHERE p.idx = :idx"),
    @NamedQuery(name = "PresentacionMedicamentoMed.findByNombrePresen", query = "SELECT p FROM PresentacionMedicamentoMed p WHERE p.nombrePresen = :nombrePresen")})
public class PresentacionMedicamentoMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Size(max = 45)
    @Column(name = "NOMBRE_PRESEN")
    private String nombrePresen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preIdx")
    private Collection<MedicamentoMed> medicamentoMedCollection;

    public PresentacionMedicamentoMed() {
    }

    public PresentacionMedicamentoMed(Integer idx) {
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getNombrePresen() {
        return nombrePresen;
    }

    public void setNombrePresen(String nombrePresen) {
        this.nombrePresen = nombrePresen;
    }

    public Collection<MedicamentoMed> getMedicamentoMedCollection() {
        return medicamentoMedCollection;
    }

    public void setMedicamentoMedCollection(Collection<MedicamentoMed> medicamentoMedCollection) {
        this.medicamentoMedCollection = medicamentoMedCollection;
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
        if (!(object instanceof PresentacionMedicamentoMed)) {
            return false;
        }
        PresentacionMedicamentoMed other = (PresentacionMedicamentoMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + nombrePresen + "";
        //return "com.unicauca.divsalud.entidades.PresentacionMedicamentoMed[ idx=" + idx + " ]";
    }
    
}
