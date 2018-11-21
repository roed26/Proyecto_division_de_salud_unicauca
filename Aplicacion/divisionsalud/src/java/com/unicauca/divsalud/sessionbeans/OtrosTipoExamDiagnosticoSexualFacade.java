/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.OtrosTipoExamDiagnosticoSexual;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author acer_acer
 */
@Stateless
public class OtrosTipoExamDiagnosticoSexualFacade extends AbstractFacade<OtrosTipoExamDiagnosticoSexual> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OtrosTipoExamDiagnosticoSexualFacade() {
        super(OtrosTipoExamDiagnosticoSexual.class);
    }

    public OtrosTipoExamDiagnosticoSexual findByNombre(String nombre) {
        Query query = em.createNamedQuery("OtrosTipoExamDiagnosticoSexual.findByNombre");
        query.setParameter("nombre", nombre);
        try {
        OtrosTipoExamDiagnosticoSexual result = (OtrosTipoExamDiagnosticoSexual) query.getSingleResult();
        return result;
        } catch (NoResultException e){
            return null;
        }
    }
    
     public OtrosTipoExamDiagnosticoSexual findByIdOtroExamDiag(int id){
      Query query = em.createNamedQuery("OtrosTipoExamDiagnosticoSexual.findByIdOtroExamDiag");
        query.setParameter("idOtroExamDiag",id);
       OtrosTipoExamDiagnosticoSexual otro;
        try {
          otro= (OtrosTipoExamDiagnosticoSexual) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("==========");
            System.out.println(e.toString());
            System.out.println("===========");
            return null;
        }
        
        return otro;
    }
    
   
}
