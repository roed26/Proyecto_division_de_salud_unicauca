/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.clases;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@ManagedBean(name = "resulestadisticas")
@ApplicationScoped

public class resultadoEstadistica implements Serializable{
    
    private String diagnostico;
    private int cantidadHombres;
    private int cantidadMujeres;
   
    
    public resultadoEstadistica(){}
            
    

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getCantidadHombres() {
        return cantidadHombres;
    }

    public void setCantidadHombres(int cantidadHombres) {
        this.cantidadHombres = cantidadHombres;
    }

    public int getCantidadMujeres() {
        return cantidadMujeres;
    }

    public void setCantidadMujeres(int cantidadMujeres) {
        this.cantidadMujeres = cantidadMujeres;
    }
    
    public void aumentarcontador(int genero)  //Si es 1 aumenta el contador de hombres en 1 y si es 0 aumenta el contador de mujeres en 1
    {
    if (genero==1)
        cantidadHombres++;
    else
        if(genero==0)
            cantidadMujeres++;
    
    }
    
    public int total(){
        int total=cantidadHombres+cantidadMujeres;
        return total;
    }
    
            }
