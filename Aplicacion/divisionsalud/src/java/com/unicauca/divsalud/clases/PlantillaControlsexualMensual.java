
package com.unicauca.divsalud.clases;

import java.util.List;


public class PlantillaControlsexualMensual implements Plantilla
{
    List<ControlSexualidadReporteMensual> reportes;
    int anio;

    public PlantillaControlsexualMensual(List<ControlSexualidadReporteMensual> reportes,int anio) {
        this.reportes = reportes;
        this.anio = anio;
    }

    
    
    public List<ControlSexualidadReporteMensual> getReportes() {
        return reportes;
    }

    public void setReportes(List<ControlSexualidadReporteMensual> reportes) {
        this.reportes = reportes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    
    
}
