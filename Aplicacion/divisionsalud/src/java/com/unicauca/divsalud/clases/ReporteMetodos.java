/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.clases;

/**
 *
 * @author acer_acer
 */
public class ReporteMetodos {
    String metodo;
    int numPersonas;

    public ReporteMetodos(String metodo, int numPersonas) {
        this.metodo = metodo;
        this.numPersonas = numPersonas;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }
    
    
}
