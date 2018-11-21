/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.HistorialMetodosUsadosSexual;
import java.util.ArrayList;
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
public class HistorialMetodosUsadosSexualFacade extends AbstractFacade<HistorialMetodosUsadosSexual> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialMetodosUsadosSexualFacade() {
        super(HistorialMetodosUsadosSexual.class);
    }
    
    public List<HistorialMetodosUsadosSexual> findById(int id){
        Query query = em.createNamedQuery("HistorialMetodosUsadosSexual.findById");
        query.setParameter("id",id);
        List<HistorialMetodosUsadosSexual> list;
         try {
           list = (List<HistorialMetodosUsadosSexual>) query.getResultList();
        } catch (Exception e) {
            return null;
        }
        
        
        return list;
    }
    
}
