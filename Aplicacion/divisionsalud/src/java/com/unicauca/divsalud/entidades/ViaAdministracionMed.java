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
@Table(name = "via_administracion_med")
@NamedQueries({
    @NamedQuery(name = "ViaAdministracionMed.findAll", query = "SELECT v FROM ViaAdministracionMed v"),
    @NamedQuery(name = "ViaAdministracionMed.findByIdx", query = "SELECT v FROM ViaAdministracionMed v WHERE v.idx = :idx"),
    @NamedQuery(name = "ViaAdministracionMed.findByNombreAdminis", query = "SELECT v FROM ViaAdministracionMed v WHERE v.nombreAdminis = :nombreAdminis")})
public class ViaAdministracionMed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDX")
    private Integer idx;
    @Size(max = 45)
    @Column(name = "NOMBRE_ADMINIS")
    private String nombreAdminis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viaIdx")
    private Collection<MedicamentoMed> medicamentoMedCollection;

    public ViaAdministracionMed() {
    }

    public ViaAdministracionMed(Integer idx) {
        this.idx = idx;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getNombreAdminis() {
        return nombreAdminis;
    }

    public void setNombreAdminis(String nombreAdminis) {
        this.nombreAdminis = nombreAdminis;
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
        if (!(object instanceof ViaAdministracionMed)) {
            return false;
        }
        ViaAdministracionMed other = (ViaAdministracionMed) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + nombreAdminis + "";
        //return "com.unicauca.divsalud.entidades.ViaAdministracionMed[ idx=" + idx + " ]";
    }
    
}
