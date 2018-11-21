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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author ROED26
 */
@Entity
@Table(name = "usuarios_sistema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosSistema.findAll", query = "SELECT u FROM UsuariosSistema u"),
    @NamedQuery(name = "UsuariosSistema.findById", query = "SELECT u FROM UsuariosSistema u WHERE u.id = :id"),
    @NamedQuery(name = "UsuariosSistema.findByIdentificacion", query = "SELECT u FROM UsuariosSistema u WHERE u.identificacion = :identificacion"),
    @NamedQuery(name = "UsuariosSistema.findByNombres", query = "SELECT u FROM UsuariosSistema u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "UsuariosSistema.findByApellidos", query = "SELECT u FROM UsuariosSistema u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "UsuariosSistema.findByLogin", query = "SELECT u FROM UsuariosSistema u WHERE u.login = :login"),
    @NamedQuery(name = "UsuariosSistema.findByContrasena", query = "SELECT u FROM UsuariosSistema u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "UsuariosSistema.findByRegistro", query = "SELECT u FROM UsuariosSistema u WHERE u.registro = :registro"),
    @NamedQuery(name = "UsuariosSistema.findByActivo", query = "SELECT u FROM UsuariosSistema u WHERE u.activo = :activo"),
    @NamedQuery(name = "UsuariosSistema.findByCargo", query = "SELECT u FROM UsuariosSistema u WHERE u.cargo = :cargo"),
    @NamedQuery(name = "UsuariosSistema.findByCelular", query = "SELECT u FROM UsuariosSistema u WHERE u.celular = :celular"),
    @NamedQuery(name = "UsuariosSistema.findByTelefono", query = "SELECT u FROM UsuariosSistema u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "UsuariosSistema.findByExtension", query = "SELECT u FROM UsuariosSistema u WHERE u.extension = :extension"),
    @NamedQuery(name = "UsuariosSistema.findByUsuarios", query = "SELECT u FROM UsuariosSistema u WHERE LOWER(CONCAT(CONCAT(CONCAT(CONCAT(u.identificacion,' '), u.nombres),' ') ,u.apellidos)) LIKE :busqueda")
})
public class UsuariosSistema implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosSistema")
    private Collection<GrupoUsuarioTipo> grupoUsuarioTipoCollection;

    @OneToMany(mappedBy = "idUsuario")
    private Collection<ActualizacionOdo> actualizacionOdoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LOGIN")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(max = 250)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Size(max = 30)
    @Column(name = "REGISTRO")
    private String registro;
    @Column(name = "ACTIVO")
    private Integer activo;
    @Size(max = 50)
    @Column(name = "CARGO")
    private String cargo;
    @Size(max = 10)
    @Column(name = "CELULAR")
    private String celular;
    @Size(max = 8)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 5)
    @Column(name = "EXTENSION")
    private String extension;

    public UsuariosSistema() {

    }

    public UsuariosSistema(Integer id) {
        this.id = id;
    }

    public UsuariosSistema(Integer id, String identificacion, String nombres, String apellidos, String login, String contrasena) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.login = login;
        this.contrasena = contrasena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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
        if (!(object instanceof UsuariosSistema)) {
            return false;
        }
        UsuariosSistema other = (UsuariosSistema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.divsalud.entidades.UsuariosSistema[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<ActualizacionOdo> getActualizacionOdoCollection() {
        return actualizacionOdoCollection;
    }

    public void setActualizacionOdoCollection(Collection<ActualizacionOdo> actualizacionOdoCollection) {
        this.actualizacionOdoCollection = actualizacionOdoCollection;
    }

    @XmlTransient
    public Collection<GrupoUsuarioTipo> getGrupoUsuarioTipoCollection() {
        return grupoUsuarioTipoCollection;
    }

    public void setGrupoUsuarioTipoCollection(Collection<GrupoUsuarioTipo> grupoUsuarioTipoCollection) {
        this.grupoUsuarioTipoCollection = grupoUsuarioTipoCollection;
    }

}
