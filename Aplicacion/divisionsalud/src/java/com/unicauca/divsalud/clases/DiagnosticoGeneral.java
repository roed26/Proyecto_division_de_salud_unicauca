/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.clases;

/**
 *
 * @author JUAN
 */
public class DiagnosticoGeneral {
    private int diagnosticoTotal;
    private int hombresTotales;
    private int mujeresTotales;
    
    public DiagnosticoGeneral(){
        diagnosticoTotal = 0;
        hombresTotales = 0;
        mujeresTotales = 0;
    }

    public int getDiagnosticoTotal() {
        return diagnosticoTotal;
    }

    public void setDiagnosticoTotal(int diagnosticoTotal) {
        this.diagnosticoTotal = diagnosticoTotal;
    }

    public int getHombresTotales() {
        return hombresTotales;
    }

    public void setHombresTotales(int hombresTotales) {
        this.hombresTotales = hombresTotales;
    }

    public int getMujeresTotales() {
        return mujeresTotales;
    }

    public void setMujeresTotales(int mujeresTotales) {
        this.mujeresTotales = mujeresTotales;
    }
    
    
}
