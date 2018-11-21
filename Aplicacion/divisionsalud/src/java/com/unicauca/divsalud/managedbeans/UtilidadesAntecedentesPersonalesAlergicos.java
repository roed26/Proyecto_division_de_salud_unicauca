/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.AlergenoMed;
import com.unicauca.divsalud.entidades.TipoAlergenoMed;

/**
 *
 * @author Danilo
 */
public class UtilidadesAntecedentesPersonalesAlergicos {
    private TipoAlergenoMed tipoAlergeno;
    private AlergenoMed alergeno;

    public UtilidadesAntecedentesPersonalesAlergicos(TipoAlergenoMed tipoAlergeno, AlergenoMed alergeno) {
        this.tipoAlergeno = tipoAlergeno;
        this.alergeno = alergeno;
    }

    public UtilidadesAntecedentesPersonalesAlergicos() {
        this.tipoAlergeno = null;
        this.alergeno = null;
    }

    public TipoAlergenoMed getTipoAlergeno() {
        return tipoAlergeno;
    }

    public void setTipoAlergeno(TipoAlergenoMed tipoAlergeno) {
        this.tipoAlergeno = tipoAlergeno;
    }

    public AlergenoMed getAlergeno() {
        return alergeno;
    }

    public void setAlergeno(AlergenoMed alergeno) {
        this.alergeno = alergeno;
    }
    
    
}
