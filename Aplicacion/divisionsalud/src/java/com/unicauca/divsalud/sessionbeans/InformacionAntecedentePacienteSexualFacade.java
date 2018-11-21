/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.InformacionAntecedentePacienteSexual;
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
public class InformacionAntecedentePacienteSexualFacade extends AbstractFacade<InformacionAntecedentePacienteSexual> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformacionAntecedentePacienteSexualFacade() {
        super(InformacionAntecedentePacienteSexual.class);
    }
    
    public List<InformacionAntecedentePacienteSexual> findById(int id){
    Query query = em.createNamedQuery("InformacionAntecedentePacienteSexual.findById");
        query.setParameter("id",id);
        List<InformacionAntecedentePacienteSexual> list;
         try {
           list = (List<InformacionAntecedentePacienteSexual>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
        
        
        return list;
    }
    
}
