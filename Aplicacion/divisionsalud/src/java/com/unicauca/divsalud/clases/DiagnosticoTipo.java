/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.clases;

import com.unicauca.divsalud.entidades.Diagnosticocie10Odo;
import com.unicauca.divsalud.entidades.Tipodiagnostico;



/**
 *
 * @author ROED26
 */
public class DiagnosticoTipo {
    private Diagnosticocie10Odo diagnosticoCie10;
    private Tipodiagnostico diagnostico;
    

    public Diagnosticocie10Odo getDiagnosticoCie10() {
        return diagnosticoCie10;
    }

    public void setDiagnosticoCie10(Diagnosticocie10Odo diagnosticoCie10) {
        this.diagnosticoCie10 = diagnosticoCie10;
    }

    public Tipodiagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Tipodiagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    
}
