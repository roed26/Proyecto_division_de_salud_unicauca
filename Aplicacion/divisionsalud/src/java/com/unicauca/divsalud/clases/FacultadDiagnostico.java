/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.clases;

import com.unicauca.divsalud.entidades.Facultad;

/**
 *
 * @author JUAN
 */
public class FacultadDiagnostico {
    private String facultad;
    private int contDiagnosticoFacultad;
    private int contHombreF;
    private int contMujerF;
    
    public FacultadDiagnostico(){
        contDiagnosticoFacultad = 0;
        contHombreF = 0;
        contMujerF = 0;
    }

    public int getContHombreF() {
        return contHombreF;
    }

    public void setContHombreF(int contHombreF) {
        this.contHombreF = contHombreF;
    }

    public int getContMujerF() {
        return contMujerF;
    }

    public void setContMujerF(int contMujerF) {
        this.contMujerF = contMujerF;
    }
    
    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    } 

    public int getContDiagnosticoFacultad() {
        return contDiagnosticoFacultad;
    }

    public void setContDiagnosticoFacultad(int contDiagnosticoFacultad) {
        this.contDiagnosticoFacultad = contDiagnosticoFacultad;
    }
    
    
}
