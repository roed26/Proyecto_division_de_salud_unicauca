/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.ExamenesDiagnosticosPacienteSexual;
import com.unicauca.divsalud.entidades.OtrosTipoExamDiagnosticoSexual;
import com.unicauca.divsalud.entidades.Paciente;
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
public class ExamenesDiagnosticosPacienteSexualFacade extends AbstractFacade<ExamenesDiagnosticosPacienteSexual> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamenesDiagnosticosPacienteSexualFacade() {
        super(ExamenesDiagnosticosPacienteSexual.class);
    }

    
       public List< ExamenesDiagnosticosPacienteSexual> findById(int id){
        Query query = em.createNamedQuery("ExamenesDiagnosticosPacienteSexual.findById");
        query.setParameter("id",id);
        List<ExamenesDiagnosticosPacienteSexual> list;
         try {
           list = (List<ExamenesDiagnosticosPacienteSexual>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
               
        return list;
    }


}
