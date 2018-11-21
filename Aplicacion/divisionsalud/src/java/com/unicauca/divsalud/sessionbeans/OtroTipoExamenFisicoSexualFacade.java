/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.OtroTipoExamenFisicoSexual;
import com.unicauca.divsalud.entidades.OtrosTipoExamDiagnosticoSexual;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author acer_acer
 */
@Stateless
public class OtroTipoExamenFisicoSexualFacade extends AbstractFacade<OtroTipoExamenFisicoSexual> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OtroTipoExamenFisicoSexualFacade() {
        super(OtroTipoExamenFisicoSexual.class);
    }
    
   
}
