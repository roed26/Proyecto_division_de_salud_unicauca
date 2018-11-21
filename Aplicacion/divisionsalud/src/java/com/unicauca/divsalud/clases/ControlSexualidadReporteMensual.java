package com.unicauca.divsalud.clases;

import java.io.Serializable;


public class ControlSexualidadReporteMensual extends ReporteSexualidad implements Serializable
{
    private String mes;
   

    public ControlSexualidadReporteMensual() {
    }
    
    

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    
    
}
