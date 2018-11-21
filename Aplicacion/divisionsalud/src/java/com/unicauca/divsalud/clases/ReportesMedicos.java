/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.clases;

import com.unicauca.divsalud.entidades.EnfermedadesCie10Med;
import com.unicauca.divsalud.entidades.Facultad;
import com.unicauca.divsalud.entidades.Paciente;
import com.unicauca.divsalud.entidades.Programas;
import java.util.Date;

/**
 *
 * @author Diana
 */
public class ReportesMedicos
{
   
    private EnfermedadesCie10Med diagnostico;
    private Date fechadesde= new Date();
    private Date fechahasta= new Date();
    private Paciente paciente;
    private String facultad;
    private String programa;
    private int cantidad;
    private int contadorhombres;
    private int contadormujeres;
    

    
    public ReportesMedicos()
    {
        this.fechadesde = new Date();
        this.fechahasta = new Date();
        this.diagnostico = new EnfermedadesCie10Med();
        
    }

    public EnfermedadesCie10Med getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(EnfermedadesCie10Med diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Date getFechadesde() {
        return fechadesde;
    }

    public void setFechadesde(Date fechadesde) {
        this.fechadesde = fechadesde;
    }

    public Date getFechahasta() {
        return fechahasta;
    }

    public void setFechahasta(Date fechahasta) {
        this.fechahasta = fechahasta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getContadorhombres() {
        return contadorhombres;
    }

    public void setContadorhombres(int contadorhombres) {
        this.contadorhombres = contadorhombres;
    }

    public int getContadormujeres() {
        return contadormujeres;
    }

    public void setContadormujeres(int contadormujeres) {
        this.contadormujeres = contadormujeres;
    }
    
    

    
  
    
    

    
    
    
}
