/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.primefaces.context.RequestContext;


@Named(value = "cargarVistaSecretariaController")
@SessionScoped
public class CargarVistaSeretariaController implements Serializable {
   
   private String ruta;
   public String getRuta() {
      return ruta;
   }
   
   public CargarVistaSeretariaController() {
      this.ruta = "/usuariodelsistema/secretaria/inicio/opciones.xhtml";
   }
   public void cargarInicio(){
       this.ruta = "/usuariodelsistema/secretaria/inicio/opciones.xhtml";
   }
   public void cargarPerfilUsuario() {
        this.ruta = "/usuariodelsistema/perfilUsuario.xhtml";
    }
   /*Ruta para la gestión de usuarios*/
   public void cargarGestionarUsuarios(){
        this.ruta = "/usuariodelsistema/secretaria/usuariosSistema/List.xhtml";
   }
   /*Rutas para la gestión de pacientes*/
   public void cargarListaPacientes() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/paciente/List.xhtml";
    }
   public void cargarRegistrarPaciente() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/paciente/RegistrarPaciente.xhtml";
    }
    public void cargarEditarPaciente() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/paciente/EditarInfoPaciente.xhtml";
    }
    public void cargarVerPaciente() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/paciente/VerInfoPaciente.xhtml";
    }
    /*Rutas para gestionar recursos*/
    public void cargarRecursos(){
       this.ruta = "/usuariodelsistema/secretaria/inicio/recursos_inicio.xhtml";
    }
     //////////Recursos////////
    public void cargarEps(){
       this.ruta = "/usuariodelsistema/secretaria/recursos/eps/List.xhtml";
    }
    
    public void cargarFacultad(){
       this.ruta = "/usuariodelsistema/secretaria/recursos/facultad/List.xhtml";
    }
    
    public void cargarPrograma(){
       this.ruta = "/usuariodelsistema/secretaria/recursos/programas/List.xhtml";
    }
    public void cargarProcedimientos(){
       this.ruta = "/usuariodelsistema/secretaria/recursos/procedimientosCupsMed/List.xhtml";
    }
    public void cargarEnfermedades(){
       this.ruta = "/usuariodelsistema/secretaria/recursos/enfermedadesCie10Med/List.xhtml";
    }
    public void cargarMedicamentos(){
       this.ruta = "/usuariodelsistema/secretaria/recursos/medicamentoMed/List.xhtml";
    }
    public void cargarAlergenos(){
       this.ruta = "/usuariodelsistema/secretaria/recursos/alergenoMed/List.xhtml";
    }
    /////////
    /*Rutas para la gestión de citas*/
   public void cargarGestionarCitas() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/citaMedicaMed/List.xhtml";
    }
    //Ruta para reportes
    public void cargarReportes()  {
        this.ruta="/usuariodelsistema/secretaria/estadisticasMedicas/EstadisticasMEDICAS.xhtml";
    }
     public void cargarReportesTop10()  {
        this.ruta="/usuariodelsistema/secretaria/estadisticasMedicas/Top10Diagnosticos.xhtml";
    }
   
   
}
