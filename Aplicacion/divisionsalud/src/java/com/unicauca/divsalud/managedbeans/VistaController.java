/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;


public abstract class VistaController{

    protected String ruta;

    public String getRuta() {
        return ruta;
    }
    

    public abstract String cargarRegistrarPaciente();

    public abstract void cargarEditarPaciente();

    public abstract void cargarVerPaciente();
    
    public abstract void cargarListarPaciente();

    public abstract void cargarPerfilUsuario() ;

    

}
