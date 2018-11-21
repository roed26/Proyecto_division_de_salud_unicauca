
package com.unicauca.divsalud.clases;

import java.util.List;


public class PlantillaControlSexualFacultad implements Plantilla
{
    private int anio;
    List<ControlSexualReporteFacultad> reportes;

    public PlantillaControlSexualFacultad(int anio, List<ControlSexualReporteFacultad> reportes) {
        this.anio = anio;
        this.reportes = reportes;
    }
    
    

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public List<ControlSexualReporteFacultad> getReportes() {
        return reportes;
    }

    public void setReportes(List<ControlSexualReporteFacultad> reportes) {
        this.reportes = reportes;
    }
    
    
}
