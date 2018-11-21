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

@Named(value = "cargarVistaController")
@SessionScoped
public class CargarVistaController implements Serializable {

    private String ruta;

    public String getRuta() {
        return ruta;
    }

    public CargarVistaController() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/paciente/ListaDePacientes.xhtml";
    }

    public void cargarInicio() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/paciente/ListaDePacientes.xhtml";
    }

    /*public void cargarRegistrarPaciente() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/paciente/Create.xhtml";
    }

    public void cargarEditarPaciente() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/paciente/Edit.xhtml";
    }

    public void cargarVerPaciente() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/paciente/View.xhtml";
    }*/
    

    public void cargarGestionarAgenda() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/citaMedicaMed/Agend.xhtml";
    }

    /*HISTORIAS MEDICAS*/
    public void cargarHistoriaMedicaMed() {
        this.ruta = "/usuariodelsistema/profesionalsalud/medicina/consultaMedicaMed/Create.xhtml";
    }

    //Ruta para reportes
    public void cargarReportes() {
        this.ruta = "/usuariodelsistema/secretaria/estadisticasMedicas/EstadisticasMEDICAS.xhtml";
    }

    public void cargarReportesTop10() {
        this.ruta = "/usuariodelsistema/secretaria/estadisticasMedicas/Top10Diagnosticos.xhtml";
    }

    public void cargarRegistrarPaciente() {

        this.ruta = "/usuariodelsistema/profesionalsalud/odontologo/paciente/RegistrarPaciente.xhtml";
    }

    public void cargarEditarPaciente() {
        this.ruta = "/usuariodelsistema/profesionalsalud/odontologo/paciente/EditarInfoPaciente.xhtml";
    }

    public void cargarListaPaciente() {
        this.ruta = "/usuariodelsistema/profesionalsalud/enComun/paciente/ListaDePacientes.xhtml";
    }

    public void cargarVerPaciente() {
        this.ruta = "/usuariodelsistema/profesionalsalud/odontologo/paciente/VerInfoPaciente.xhtml";
    }

    public void cargarAperturaHistoriaOdontologica() {
        this.ruta = "/usuariodelsistema/profesionalsalud/odontologo/historiaOdontologica/aperturaHistoriaOdontologica.xhtml";
    }

    public void cargarEvolucionHistoriaOdontologica() {
        this.ruta = "/usuariodelsistema/profesionalsalud/odontologo/historiaOdontologica/evolucionHistoriaOdontologica.xhtml";
    }

    public void cargarGestionHistoriaOdontologica() {
        this.ruta = "/usuariodelsistema/profesionalsalud/odontologo/historiaOdontologica/listaHistoriaOdontologicaPorPaciente.xhtml";
    }

    public void cargarPerfilUsuario() {
        this.ruta = "/usuariodelsistema/perfilUsuario.xhtml";
    }

    //estadisticas
    public void cargarEstadisticaFacultadDiagnostico() {
        this.ruta = "/usuariodelsistema/profesionalsalud/odontologo/estadisticas/diagnosticosFacultades.xhtml";
    }

    public void cargarIndiceCOP() {
        this.ruta = "/usuariodelsistema/profesionalsalud/odontologo/estadisticas/indiceCOP.xhtml";
    }

    public void cargarRegistrarPagos() {
        this.ruta = "/usuariodelsistema/profesionalsalud/odontologo/pagos/registroDePagos.xhtml";
    }
}
