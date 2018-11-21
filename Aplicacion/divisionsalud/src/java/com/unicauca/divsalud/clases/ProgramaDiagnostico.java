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
public class ProgramaDiagnostico {
    
    private String Programa;
    private int contDiagnosticoPrograma;
    private int contHombresP;
    private int contMujeresP;
    
    public ProgramaDiagnostico(){
        this.contDiagnosticoPrograma = 0;
        this.contHombresP = 0;
        this.contMujeresP = 0;
    }

    public String getPrograma() {
        return Programa;
    }

    public void setPrograma(String Programa) {
        this.Programa = Programa;
    }

    public int getContDiagnosticoPrograma() {
        return contDiagnosticoPrograma;
    }

    public void setContDiagnosticoPrograma(int contDiagnosticoPrograma) {
        this.contDiagnosticoPrograma = contDiagnosticoPrograma;
    }

    public int getContHombresP() {
        return contHombresP;
    }

    public void setContHombresP(int contHombresP) {
        this.contHombresP = contHombresP;
    }

    public int getContMujeresP() {
        return contMujeresP;
    }

    public void setContMujeresP(int contMujeresP) {
        this.contMujeresP = contMujeresP;
    }
    
    
    
}
