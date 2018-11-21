/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.TipoExamDiagnosticoSexual;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author acer_acer
 */
@Stateless
public class TipoExamDiagnosticoSexualFacade extends AbstractFacade<TipoExamDiagnosticoSexual> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoExamDiagnosticoSexualFacade() {
        super(TipoExamDiagnosticoSexual.class);
    }
    
}
