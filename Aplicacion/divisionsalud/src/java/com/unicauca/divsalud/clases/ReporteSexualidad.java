
package com.unicauca.divsalud.clases;


public abstract class ReporteSexualidad {
    
    protected int total_atendidos;
    protected int mujeres;
    protected int hombres;
    protected int inicio_metodos_planificacion;
    protected int controles;

    public int getTotal_atendidos() {
        return total_atendidos;
    }

    public void setTotal_atendidos(int total_atendidos) {
        this.total_atendidos = total_atendidos;
    }

    public int getMujeres() {
        return mujeres;
    }

    public void setMujeres(int mujeres) {
        this.mujeres = mujeres;
    }

    public int getHombres() {
        return hombres;
    }

    public void setHombres(int hombres) {
        this.hombres = hombres;
    }

    public int getInicio_metodos_planificacion() {
        return inicio_metodos_planificacion;
    }

    public void setInicio_metodos_planificacion(int inicio_metodos_planificacion) {
        this.inicio_metodos_planificacion = inicio_metodos_planificacion;
    }

    public int getControles() {
        return controles;
    }

    public void setControles(int controles) {
        this.controles = controles;
    }
    
    
}
