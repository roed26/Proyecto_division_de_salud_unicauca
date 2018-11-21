/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acer_acer
 */
@Entity
@Table(name = "antecedentes_personales_sexual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntecedentesPersonalesSexual.findAll", query = "SELECT a FROM AntecedentesPersonalesSexual a")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findById", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.id = :id")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByFumador", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.fumador = :fumador")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByCigarrillosPorDia", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.cigarrillosPorDia = :cigarrillosPorDia")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByAgoG", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.agoG = :agoG")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByAgoP", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.agoP = :agoP")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByAgoA", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.agoA = :agoA")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByAgoC", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.agoC = :agoC")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByAgoV", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.agoV = :agoV")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByAgoM", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.agoM = :agoM")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByAgoEspecificar", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.agoEspecificar = :agoEspecificar")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByAgoFechaUltimoAborto", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.agoFechaUltimoAborto = :agoFechaUltimoAborto")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByEdadMenarca", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.edadMenarca = :edadMenarca")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByCiclosRegulares", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.ciclosRegulares = :ciclosRegulares")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByEdadInicioVidaSexual", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.edadInicioVidaSexual = :edadInicioVidaSexual")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByNumParejasSexuales", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.numParejasSexuales = :numParejasSexuales")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByNumParejasUltimoAnio", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.numParejasUltimoAnio = :numParejasUltimoAnio")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByTranstornosMenstruales", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.transtornosMenstruales = :transtornosMenstruales")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByFechaUltParto", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.fechaUltParto = :fechaUltParto")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByEstaLactando", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.estaLactando = :estaLactando")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByExclusiva", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.exclusiva = :exclusiva")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByFechaUltMenstruacion", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.fechaUltMenstruacion = :fechaUltMenstruacion")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByCitologiaPrevia", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.citologiaPrevia = :citologiaPrevia")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByAnioCitPrev", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.anioCitPrev = :anioCitPrev")
    , @NamedQuery(name = "AntecedentesPersonalesSexual.findByResultCitPrev", query = "SELECT a FROM AntecedentesPersonalesSexual a WHERE a.resultCitPrev = :resultCitPrev")})
public class AntecedentesPersonalesSexual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 2)
    @Column(name = "FUMADOR")
    private String fumador;
    @Column(name = "CIGARRILLOS_POR_DIA")
    private Short cigarrillosPorDia;
    @Column(name = "AGO_G")
    private Short agoG;
    @Column(name = "AGO_P")
    private Short agoP;
    @Column(name = "AGO_A")
    private Short agoA;
    @Column(name = "AGO_C")
    private Short agoC;
    @Column(name = "AGO_V")
    private Short agoV;
    @Column(name = "AGO_M")
    private Short agoM;
    @Size(max = 15)
    @Column(name = "AGO_ESPECIFICAR")
    private String agoEspecificar;
    @Column(name = "AGO_FECHA_ULTIMO_ABORTO")
    @Temporal(TemporalType.DATE)
    private Date agoFechaUltimoAborto;
    @Column(name = "EDAD_MENARCA")
    private Short edadMenarca;
    @Column(name = "CICLOS_PERIODO")
    private Short ciclosPeriodo;
    @Column(name = "CICLOS_DURACION")
    private Short ciclosDuracion;
    @Size(max = 2)
    @Column(name = "CICLOS_REGULARES")
    private String ciclosRegulares;
    @Column(name = "EDAD_INICIO_VIDA_SEXUAL")
    private Short edadInicioVidaSexual;
    @Column(name = "NUM_PAREJAS_SEXUALES")
    private Short numParejasSexuales;
    @Column(name = "NUM_PAREJAS_ULTIMO_ANIO")
    private Short numParejasUltimoAnio;
    @Size(max = 2)
    @Column(name = "TRANSTORNOS_MENSTRUALES")
    private String transtornosMenstruales;
    @Column(name = "FECHA_ULT_PARTO")
    @Temporal(TemporalType.DATE)
    private Date fechaUltParto;
    @Size(max = 2)
    @Column(name = "ESTA_LACTANDO")
    private String estaLactando;
    @Size(max = 2)
    @Column(name = "EXCLUSIVA")
    private String exclusiva;
    @Column(name = "FECHA_ULT_MENSTRUACION")
    @Temporal(TemporalType.DATE)
    private Date fechaUltMenstruacion;
    @Size(max = 2)
    @Column(name = "CITOLOGIA_PREVIA")
    private String citologiaPrevia;
    @Column(name = "ANIO_CIT_PREV")
    private Short anioCitPrev;
    @Size(max = 10)
    @Column(name = "RESULT_CIT_PREV")
    private String resultCitPrev;
    @Lob
    @Size(max = 65535)
    @Column(name = "OBSERVACIONES_ADICIONALES")
    private String observacionesAdicionales;
    @JoinTable(name = "transtorno_menstrual_antecedente", joinColumns = {
        @JoinColumn(name = "ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_TRANS_MENS", referencedColumnName = "ID_TRANS_MENS")})
    @ManyToMany
    private List<TipoTranstornoMentrual> tipoTranstornoMentrualList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "antecedentesPersonalesSexual")
    private List<InformacionAntecedentePacienteSexual> informacionAntecedentePacienteSexualList;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private HistoriaModuloSexualidad historiaModuloSexualidad;

    public AntecedentesPersonalesSexual() {
    }

    public AntecedentesPersonalesSexual(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFumador() {
        return fumador;
    }

    public void setFumador(String fumador) {
        this.fumador = fumador;
    }

    public Short getCigarrillosPorDia() {
        return cigarrillosPorDia;
    }

    public void setCigarrillosPorDia(Short cigarrillosPorDia) {
        this.cigarrillosPorDia = cigarrillosPorDia;
    }

    public Short getAgoG() {
        return agoG;
    }

    public void setAgoG(Short agoG) {
        this.agoG = agoG;
    }

    public Short getAgoP() {
        return agoP;
    }

    public void setAgoP(Short agoP) {
        this.agoP = agoP;
    }

    public Short getAgoA() {
        return agoA;
    }

    public void setAgoA(Short agoA) {
        this.agoA = agoA;
    }

    public Short getAgoC() {
        return agoC;
    }

    public void setAgoC(Short agoC) {
        this.agoC = agoC;
    }

    public Short getAgoV() {
        return agoV;
    }

    public void setAgoV(Short agoV) {
        this.agoV = agoV;
    }

    public Short getAgoM() {
        return agoM;
    }

    public void setAgoM(Short agoM) {
        this.agoM = agoM;
    }

    public String getAgoEspecificar() {
        return agoEspecificar;
    }

    public void setAgoEspecificar(String agoEspecificar) {
        this.agoEspecificar = agoEspecificar;
    }

    public Date getAgoFechaUltimoAborto() {
        return agoFechaUltimoAborto;
    }

    public void setAgoFechaUltimoAborto(Date agoFechaUltimoAborto) {
        this.agoFechaUltimoAborto = agoFechaUltimoAborto;
    }

    public Short getEdadMenarca() {
        return edadMenarca;
    }

    public void setEdadMenarca(Short edadMenarca) {
        this.edadMenarca = edadMenarca;
    }

    public Short getCiclosPeriodo() {
        return ciclosPeriodo;
    }

    public void setCiclosPeriodo(Short ciclosPeriodo) {
        this.ciclosPeriodo = ciclosPeriodo;
    }

    public Short getCiclosDuracion() {
        return ciclosDuracion;
    }

    public void setCiclosDuracion(Short ciclosDuracion) {
        this.ciclosDuracion = ciclosDuracion;
    }
    
    public String getCiclosRegulares() {
        return ciclosRegulares;
    }

    public void setCiclosRegulares(String ciclosRegulares) {
        this.ciclosRegulares = ciclosRegulares;
    }

    public Short getEdadInicioVidaSexual() {
        return edadInicioVidaSexual;
    }

    public void setEdadInicioVidaSexual(Short edadInicioVidaSexual) {
        this.edadInicioVidaSexual = edadInicioVidaSexual;
    }

    public Short getNumParejasSexuales() {
        return numParejasSexuales;
    }

    public void setNumParejasSexuales(Short numParejasSexuales) {
        this.numParejasSexuales = numParejasSexuales;
    }

    public Short getNumParejasUltimoAnio() {
        return numParejasUltimoAnio;
    }

    public void setNumParejasUltimoAnio(Short numParejasUltimoAnio) {
        this.numParejasUltimoAnio = numParejasUltimoAnio;
    }

    public String getTranstornosMenstruales() {
        return transtornosMenstruales;
    }

    public void setTranstornosMenstruales(String transtornosMenstruales) {
        this.transtornosMenstruales = transtornosMenstruales;
    }

    public Date getFechaUltParto() {
        return fechaUltParto;
    }

    public void setFechaUltParto(Date fechaUltParto) {
        this.fechaUltParto = fechaUltParto;
    }

    public String getEstaLactando() {
        return estaLactando;
    }

    public void setEstaLactando(String estaLactando) {
        this.estaLactando = estaLactando;
    }

    public String getExclusiva() {
        return exclusiva;
    }

    public void setExclusiva(String exclusiva) {
        this.exclusiva = exclusiva;
    }

    public Date getFechaUltMenstruacion() {
        return fechaUltMenstruacion;
    }

    public void setFechaUltMenstruacion(Date fechaUltMenstruacion) {
        this.fechaUltMenstruacion = fechaUltMenstruacion;
    }

    public String getCitologiaPrevia() {
        return citologiaPrevia;
    }

    public void setCitologiaPrevia(String citologiaPrevia) {
        this.citologiaPrevia = citologiaPrevia;
    }

    public Short getAnioCitPrev() {
        return anioCitPrev;
    }

    public void setAnioCitPrev(Short anioCitPrev) {
        this.anioCitPrev = anioCitPrev;
    }

    public String getResultCitPrev() {
        return resultCitPrev;
    }

    public void setResultCitPrev(String resultCitPrev) {
        this.resultCitPrev = resultCitPrev;
    }

    public String getObservacionesAdicionales() {
        return observacionesAdicionales;
    }

    public void setObservacionesAdicionales(String observacionesAdicionales) {
        this.observacionesAdicionales = observacionesAdicionales;
    }

    @XmlTransient
    public List<TipoTranstornoMentrual> getTipoTranstornoMentrualList() {
        return tipoTranstornoMentrualList;
    }

    public void setTipoTranstornoMentrualList(List<TipoTranstornoMentrual> tipoTranstornoMentrualList) {
        this.tipoTranstornoMentrualList = tipoTranstornoMentrualList;
    }

    @XmlTransient
    public List<InformacionAntecedentePacienteSexual> getInformacionAntecedentePacienteSexualList() {
        return informacionAntecedentePacienteSexualList;
    }

    public void setInformacionAntecedentePacienteSexualList(List<InformacionAntecedentePacienteSexual> informacionAntecedentePacienteSexualList) {
        this.informacionAntecedentePacienteSexualList = informacionAntecedentePacienteSexualList;
    }

    public HistoriaModuloSexualidad getHistoriaModuloSexualidad() {
        return historiaModuloSexualidad;
    }

    public void setHistoriaModuloSexualidad(HistoriaModuloSexualidad historiaModuloSexualidad) {
        this.historiaModuloSexualidad = historiaModuloSexualidad;
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
        if (!(object instanceof AntecedentesPersonalesSexual)) {
            return false;
        }
        AntecedentesPersonalesSexual other = (AntecedentesPersonalesSexual) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.AntecedentesPersonalesSexual[ id=" + id + " ]";
    }
    
}
