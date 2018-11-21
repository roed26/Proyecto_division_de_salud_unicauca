/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findById", query = "SELECT p FROM Paciente p WHERE p.id = :id"),
    @NamedQuery(name = "Paciente.findByFechaApertura", query = "SELECT p FROM Paciente p WHERE p.fechaApertura = :fechaApertura"),
    @NamedQuery(name = "Paciente.findByNombre1", query = "SELECT p FROM Paciente p WHERE p.nombre1 = :nombre1"),
    @NamedQuery(name = "Paciente.findByNombre2", query = "SELECT p FROM Paciente p WHERE p.nombre2 = :nombre2"),
    @NamedQuery(name = "Paciente.findByApellido1", query = "SELECT p FROM Paciente p WHERE p.apellido1 = :apellido1"),
    @NamedQuery(name = "Paciente.findByApellido2", query = "SELECT p FROM Paciente p WHERE p.apellido2 = :apellido2"),
    @NamedQuery(name = "Paciente.findByIdentificacion", query = "SELECT p FROM Paciente p WHERE p.identificacion = :identificacion"),
    @NamedQuery(name = "Paciente.findBySexo", query = "SELECT p FROM Paciente p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Paciente.findByFechaNacimiento", query = "SELECT p FROM Paciente p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Paciente.findByDireccion", query = "SELECT p FROM Paciente p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Paciente.findByTelefono", query = "SELECT p FROM Paciente p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Paciente.findByZonaResidencia", query = "SELECT p FROM Paciente p WHERE p.zonaResidencia = :zonaResidencia"),
    @NamedQuery(name = "Paciente.findByPersonaResponsable", query = "SELECT p FROM Paciente p WHERE p.personaResponsable = :personaResponsable"),
    @NamedQuery(name = "Paciente.findByDireccionPresponsable", query = "SELECT p FROM Paciente p WHERE p.direccionPresponsable = :direccionPresponsable"),
    @NamedQuery(name = "Paciente.findByTelefonoPresponsable", query = "SELECT p FROM Paciente p WHERE p.telefonoPresponsable = :telefonoPresponsable"),
    @NamedQuery(name = "Paciente.findByOcupacion", query = "SELECT p FROM Paciente p WHERE p.ocupacion = :ocupacion"),
    @NamedQuery(name = "Paciente.findByEstado", query = "SELECT p FROM Paciente p WHERE p.estado = :estado"),
    @NamedQuery(name = "Paciente.findByCarpeta", query = "SELECT p FROM Paciente p WHERE p.carpeta = :carpeta"),
    @NamedQuery(name = "Paciente.findByPacientesActivos", query = "SELECT p FROM Paciente p WHERE LOWER(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(p.identificacion,' '), p.nombre1),' ') ,p.nombre2),' '),p.apellido1),' '),p.apellido2)) LIKE :busqueda"),
    @NamedQuery(name = "Paciente.findByPacientesConHistoria", query = "SELECT p FROM Paciente p JOIN HistoriaModuloSexualidad h WHERE p.id=h.id"),
    @NamedQuery(name = "Paciente.findByPacientesConHistoriaBusqueda", query = "SELECT p FROM Paciente p JOIN HistoriaModuloSexualidad h WHERE p.id=h.id AND LOWER(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(p.identificacion,' '), p.nombre1),' ') ,p.nombre2),' '),p.apellido1),' '),p.apellido2)) LIKE :busqueda"),
    @NamedQuery(name = "Paciente.findByPacientesSinHistoria", query = "SELECT p FROM Paciente p where not exists (SELECT 1 from HistoriaModuloSexualidad h WHERE h.id=p.id)"),
    @NamedQuery(name = "Paciente.findByPacientesSinHistoriaBusqueda", query = "SELECT p FROM Paciente p where not exists (SELECT 1 from HistoriaModuloSexualidad h WHERE h.id=p.id) AND LOWER(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(p.identificacion,' '), p.nombre1),' ') ,p.nombre2),' '),p.apellido1),' '),p.apellido2)) LIKE :busqueda"),
    @NamedQuery(name = "Paciente.findByIdentificacionTipoIdentificacion", query = "SELECT p FROM Paciente p WHERE p.identificacion = :identificacion AND p.tipoIdentificacion.id =:tipoIdentificacion")
})
public class Paciente implements Serializable {

    @OneToMany(mappedBy = "idPaciente")
    private Collection<ActualizacionOdo> actualizacionOdoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)    
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA_APERTURA")
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
    @Size(max = 20)
    @Column(name = "NOMBRE1")
    private String nombre1;
    @Size(max = 20)
    @Column(name = "NOMBRE2")
    private String nombre2;
    @Size(max = 30)
    @Column(name = "APELLIDO1")
    private String apellido1;
    @Size(max = 30)
    @Column(name = "APELLIDO2")
    private String apellido2;
    @Size(max = 20)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Column(name = "SEXO")
    private Character sexo;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 60)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 30)
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "ZONA_RESIDENCIA")
    private Character zonaResidencia;
    @Size(max = 60)
    @Column(name = "PERSONA_RESPONSABLE")
    private String personaResponsable;
    @Size(max = 60)
    @Column(name = "DIRECCION_PRESPONSABLE")
    private String direccionPresponsable;
    @Size(max = 30)
    @Column(name = "TELEFONO_PRESPONSABLE")
    private String telefonoPresponsable;
    @Size(max = 30)
    @Column(name = "OCUPACION")
    private String ocupacion;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 20)
    @Column(name = "CARPETA")
    private String carpeta;
    @JoinColumn(name = "CATEGORIA_AFILIADO", referencedColumnName = "ID")
    @ManyToOne
    private CategoriaAfiliado categoriaAfiliado;
    @JoinColumn(name = "DEPTO_NACIMIENTO", referencedColumnName = "ID")
    @ManyToOne
    private Depto deptoNacimiento;
    @JoinColumn(name = "DEPTO_RESIDENCIA", referencedColumnName = "ID")
    @ManyToOne
    private Depto deptoResidencia;
    @JoinColumn(name = "EPS", referencedColumnName = "ID")
    @ManyToOne
    private Eps eps;
    @JoinColumn(name = "ESCOLARIDAD", referencedColumnName = "ID")
    @ManyToOne
    private Escolaridad escolaridad;
    @JoinColumn(name = "ESTADO_CIVIL", referencedColumnName = "ID")
    @ManyToOne
    private EstadoCivil estadoCivil;
    @JoinColumn(name = "ESTRATO", referencedColumnName = "ID")
    @ManyToOne
    private Estrato estrato;
    @JoinColumn(name = "FACULTAD", referencedColumnName = "ID")
    @ManyToOne
    private Facultad facultad;
    @JoinColumn(name = "PROGRAMA", referencedColumnName = "ID")
    @ManyToOne
    private Programas programa;
    @JoinColumn(name = "TIPO_AFILIADO", referencedColumnName = "ID")
    @ManyToOne
    private TipoAfiliado tipoAfiliado;
    @JoinColumn(name = "MUNICIPIO_NACIMIENTO", referencedColumnName = "ID")
    @ManyToOne
    private Municipio municipioNacimiento;
    @JoinColumn(name = "MUNICIPIO_RESIDENCIA", referencedColumnName = "ID")
    @ManyToOne
    private Municipio municipioResidencia;
    @JoinColumn(name = "TIPO_REGIMEN", referencedColumnName = "ID")
    @ManyToOne
    private TipoRegimen tipoRegimen;
    @JoinColumn(name = "PARENTESCO", referencedColumnName = "ID")
    @ManyToOne
    private Parentesco parentesco;
    @JoinColumn(name = "RAZA", referencedColumnName = "ID")
    @ManyToOne
    private Raza raza;
    @JoinColumn(name = "TIPO_IDENTIFICACION", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoIdentificacion tipoIdentificacion;

    public Paciente() {
        this.raza=new Raza();
    }

    public Paciente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Character getZonaResidencia() {
        return zonaResidencia;
    }

    public void setZonaResidencia(Character zonaResidencia) {
        this.zonaResidencia = zonaResidencia;
    }

    public String getPersonaResponsable() {
        return personaResponsable;
    }

    public void setPersonaResponsable(String personaResponsable) {
        this.personaResponsable = personaResponsable;
    }

    public String getDireccionPresponsable() {
        return direccionPresponsable;
    }

    public void setDireccionPresponsable(String direccionPresponsable) {
        this.direccionPresponsable = direccionPresponsable;
    }

    public String getTelefonoPresponsable() {
        return telefonoPresponsable;
    }

    public void setTelefonoPresponsable(String telefonoPresponsable) {
        this.telefonoPresponsable = telefonoPresponsable;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    public CategoriaAfiliado getCategoriaAfiliado() {
        return categoriaAfiliado;
    }

    public void setCategoriaAfiliado(CategoriaAfiliado categoriaAfiliado) {
        this.categoriaAfiliado = categoriaAfiliado;
    }

    public Depto getDeptoNacimiento() {
        return deptoNacimiento;
    }

    public void setDeptoNacimiento(Depto deptoNacimiento) {
        this.deptoNacimiento = deptoNacimiento;
    }

    public Depto getDeptoResidencia() {
        return deptoResidencia;
    }

    public void setDeptoResidencia(Depto deptoResidencia) {
        this.deptoResidencia = deptoResidencia;
    }

    public Eps getEps() {
        return eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    public Escolaridad getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(Escolaridad escolaridad) {
        this.escolaridad = escolaridad;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Estrato getEstrato() {
        return estrato;
    }

    public void setEstrato(Estrato estrato) {
        this.estrato = estrato;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Programas getPrograma() {
        return programa;
    }

    public void setPrograma(Programas programa) {
        this.programa = programa;
    }

    public TipoAfiliado getTipoAfiliado() {
        return tipoAfiliado;
    }

    public void setTipoAfiliado(TipoAfiliado tipoAfiliado) {
        this.tipoAfiliado = tipoAfiliado;
    }

    public Municipio getMunicipioNacimiento() {
        return municipioNacimiento;
    }

    public void setMunicipioNacimiento(Municipio municipioNacimiento) {
        this.municipioNacimiento = municipioNacimiento;
    }

    public Municipio getMunicipioResidencia() {
        return municipioResidencia;
    }

    public void setMunicipioResidencia(Municipio municipioResidencia) {
        this.municipioResidencia = municipioResidencia;
    }

    public TipoRegimen getTipoRegimen() {
        return tipoRegimen;
    }

    public void setTipoRegimen(TipoRegimen tipoRegimen) {
        this.tipoRegimen = tipoRegimen;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.Paciente[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<ActualizacionOdo> getActualizacionOdoCollection() {
        return actualizacionOdoCollection;
    }

    public void setActualizacionOdoCollection(Collection<ActualizacionOdo> actualizacionOdoCollection) {
        this.actualizacionOdoCollection = actualizacionOdoCollection;
    }
    
}
