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
 * @author Danilo
 */
@Entity
@Table(name = "diagnosticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagnosticos.findAll", query = "SELECT d FROM Diagnosticos d"),
    @NamedQuery(name = "Diagnosticos.findByCodigoCie10", query = "SELECT d FROM Diagnosticos d WHERE d.diagnosticosPK.codigoCie10 = :codigoCie10"),
    @NamedQuery(name = "Diagnosticos.findByIdxConsulta", query = "SELECT d FROM Diagnosticos d WHERE d.diagnosticosPK.idxConsulta = :idxConsulta"),
    @NamedQuery(name = "Diagnosticos.findByImpresion", query = "SELECT d FROM Diagnosticos d WHERE d.impresion = :impresion"),
    @NamedQuery(name = "Diagnosticos.findByConsultaFecha", query = "SELECT d FROM Diagnosticos d WHERE d.diagnosticosPK.codigoCie10 = :codigoCie10 AND(d.consultaMedicaMed.fecha BETWEEN :fechaInicio AND :fechaFin)")})
public class Diagnosticos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiagnosticosPK diagnosticosPK;
    @Size(max = 20)
    @Column(name = "IMPRESION")
    private String impresion;
    @JoinColumn(name = "IDX_CONSULTA", referencedColumnName = "IDX", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ConsultaMedicaMed consultaMedicaMed;
    @JoinColumn(name = "CODIGO_CIE10", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EnfermedadesCie10Med enfermedadesCie10Med;

    public Diagnosticos() {
    }

    public Diagnosticos(DiagnosticosPK diagnosticosPK) {
        this.diagnosticosPK = diagnosticosPK;
    }

    public Diagnosticos(String codigoCie10, int idxConsulta) {
        this.diagnosticosPK = new DiagnosticosPK(codigoCie10, idxConsulta);
    }

    public DiagnosticosPK getDiagnosticosPK() {
        return diagnosticosPK;
    }

    public void setDiagnosticosPK(DiagnosticosPK diagnosticosPK) {
        this.diagnosticosPK = diagnosticosPK;
    }

    public String getImpresion() {
        return impresion;
    }

    public void setImpresion(String impresion) {
        this.impresion = impresion;
    }

    public ConsultaMedicaMed getConsultaMedicaMed() {
        return consultaMedicaMed;
    }

    public void setConsultaMedicaMed(ConsultaMedicaMed consultaMedicaMed) {
        this.consultaMedicaMed = consultaMedicaMed;
    }

    public EnfermedadesCie10Med getEnfermedadesCie10Med() {
        return enfermedadesCie10Med;
    }

    public void setEnfermedadesCie10Med(EnfermedadesCie10Med enfermedadesCie10Med) {
        this.enfermedadesCie10Med = enfermedadesCie10Med;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diagnosticosPK != null ? diagnosticosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diagnosticos)) {
            return false;
        }
        Diagnosticos other = (Diagnosticos) object;
        if ((this.diagnosticosPK == null && other.diagnosticosPK != null) || (this.diagnosticosPK != null && !this.diagnosticosPK.equals(other.diagnosticosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.Diagnosticos[ diagnosticosPK=" + diagnosticosPK + " ]";
    }

}
