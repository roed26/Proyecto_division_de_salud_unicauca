/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.ExamenFisicoSexual;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author acer_acer
 */
@Stateless
public class ExamenFisicoSexualFacade extends AbstractFacade<ExamenFisicoSexual> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamenFisicoSexualFacade() {
        super(ExamenFisicoSexual.class);
    }
    
     public ExamenFisicoSexual findById(int id){
        Query query = em.createNamedQuery("ExamenFisicoSexual.findById");
        query.setParameter("id",id);
        ExamenFisicoSexual examen ;
         try {
           examen =  (ExamenFisicoSexual) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
        
        return examen;
    }
    
    
    
}
