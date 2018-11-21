/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.ControlPacienteSexual;
import com.unicauca.divsalud.entidades.HistoriaModuloSexualidad;
import com.unicauca.divsalud.entidades.HistorialMetodosUsadosSexual;
import com.unicauca.divsalud.entidades.MetodoAdoptadoSexual;
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
public class MetodoAdoptadoSexualFacade extends AbstractFacade<MetodoAdoptadoSexual> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MetodoAdoptadoSexualFacade() {
        super(MetodoAdoptadoSexual.class);
    }
    
    public MetodoAdoptadoSexual getByIdPacient(int id ){
        Query query = em.createNamedQuery("MetodoAdoptadoSexual.findById");
        query.setParameter("id",id);MetodoAdoptadoSexual his;
        try {
             his =  (MetodoAdoptadoSexual) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return his;
    }
    
    public List<MetodoAdoptadoSexual> countByIdMetodo(int id) {
        Query query = em.createNamedQuery("MetodoAdoptadoSexual.findByIdMetodoPlanificacion");
        query.setParameter("idMetodoPlanificacion",id);
        List<MetodoAdoptadoSexual> list= query.getResultList();
        return list;        
    }
    
    
}
