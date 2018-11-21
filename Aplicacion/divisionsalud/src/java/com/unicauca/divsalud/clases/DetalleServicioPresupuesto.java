/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.clases;

import com.unicauca.divsalud.entidades.ServiciosOdo;
/**
 *
 * @author Mi
 */
public class DetalleServicioPresupuesto {
    private ServiciosOdo servicioOdo;
    private int cantidad;
    private float precio;
    
    public DetalleServicioPresupuesto(){
      
    }

    public ServiciosOdo getServicioOdo() {
        return servicioOdo;
    }

    public void setServicioOdo(ServiciosOdo servicioOdo) {
        this.servicioOdo = servicioOdo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public void calcularPrecioPregrado(){
        this.precio = cantidad*Integer.parseInt(servicioOdo.getValorPre());
    }
    
    public void calcularPrecioPosgrado(){
        this.precio = cantidad*Integer.parseInt(servicioOdo.getValorPos());
    }
}
