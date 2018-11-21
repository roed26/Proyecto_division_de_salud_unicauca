/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Daniela
 */
@Named(value = "cargarVistaSexualidad")
@ViewScoped
public class CargarVistaSexualidadController extends VistaController implements Serializable
{

    
    public CargarVistaSexualidadController()
    {
    }
    @Override
    public String cargarRegistrarPaciente()
    {
        ruta = "/usuariodelsistema/sexualidad/paciente/create.xhtml";
        return ruta;
    }
    public void cargarRegistrarMetodoAnticonceptivo()
    {
        ruta = "/usuariodelsistema/sexualidad/metodo_planificacion/create.xhtml";
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public void cargarEditarPaciente() {
        /*if(c!=null)
        {
            c.cargarPaciente(id);*/
            ruta = "/usuariodelsistema/sexualidad/paciente/edit.xhtml";
        //}
        
    }

    @Override
    public void cargarVerPaciente()
    {
        /*if(c!=null)
        {
            c.cargarPaciente(id);
            c.viendoPaciente();*/
            ruta = "/usuariodelsistema/sexualidad/paciente/view.xhtml";
        //}
        
    }

    @Override
    public void cargarListarPaciente() {
        ruta = "/usuariodelsistema/sexualidad/paciente/list.xhtml";
    }

    @Override
    public void cargarPerfilUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
