/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.OtrosExamenesDiagnosticosPacienteSexual;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author acer_acer
 */
@Stateless
public class OtrosExamenesDiagnosticosPacienteSexualFacade extends AbstractFacade<OtrosExamenesDiagnosticosPacienteSexual> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OtrosExamenesDiagnosticosPacienteSexualFacade() {
        super(OtrosExamenesDiagnosticosPacienteSexual.class);
    }
    
      public List< OtrosExamenesDiagnosticosPacienteSexual> findById(int id){
        Query query = em.createNamedQuery("OtrosExamenesDiagnosticosPacienteSexual.findById");
        query.setParameter("id",id);
        List<OtrosExamenesDiagnosticosPacienteSexual> list;
         try {
           list = (List<OtrosExamenesDiagnosticosPacienteSexual>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
               
        return list;
    }
    
    
    
}
