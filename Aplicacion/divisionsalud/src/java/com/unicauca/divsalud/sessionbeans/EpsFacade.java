/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.Eps;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Albert Muñoz
 */
@Stateless
public class EpsFacade extends AbstractFacade<Eps> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EpsFacade() {
        super(Eps.class);
    }
    
     public List<Eps> buscarPorId(Integer  id) {
        Query query = getEntityManager().createNamedQuery("Eps.findById");
        query.setParameter("id", id);
        List<Eps> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Eps> buscarPorNombre(String nombre) {
        Query query = getEntityManager().createNamedQuery("Eps.findByNombre");
        query.setParameter("nombre", nombre);
        List<Eps> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Eps> buscarEps(String datoBusqueda) {
        Query query = getEntityManager().createNamedQuery("Eps.findByEps");
        query.setParameter("busqueda", "%" + datoBusqueda + "%");
        List<Eps> resultList = query.getResultList();
        return resultList;
    }
    
    
}
