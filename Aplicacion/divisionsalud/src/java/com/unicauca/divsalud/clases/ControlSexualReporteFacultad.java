package com.unicauca.divsalud.clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ControlSexualReporteFacultad implements Serializable
{
    private String facultad;
    private List<ControlSexualReportePrograma> departamentos;

    public ControlSexualReporteFacultad() {
        departamentos = new ArrayList<>();
    }

    public void add(ControlSexualReportePrograma c)
    {
        departamentos.add(c);
    }
    
    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public List<ControlSexualReportePrograma> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<ControlSexualReportePrograma> departamentos) {
        this.departamentos = departamentos;
    }

    @Override
    public String toString() {
        String r=  "ControlSexualReporteFacultad{" + "facultad=" + facultad +  '}';
        for(ControlSexualReportePrograma c: departamentos)
        {
            r +="\n"+c.toString();
        }
        return r;
    }
    
    
    
}
