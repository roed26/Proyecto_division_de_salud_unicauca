/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.Facultad;
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
public class FacultadFacade extends AbstractFacade<Facultad> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacultadFacade() {
        super(Facultad.class);
    }
    
     public List<Facultad> buscarPorId(Integer  id) {
        Query query = getEntityManager().createNamedQuery("Facultad.findById");
        query.setParameter("id", id);
        List<Facultad> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Facultad> buscarPorNombre(String nombre) {
        Query query = getEntityManager().createNamedQuery("Facultad.findByNombre");
        query.setParameter("nombre", nombre);
        List<Facultad> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Facultad> buscarFacultad(String datoBusqueda) {
        Query query = getEntityManager().createNamedQuery("Facultad.findByFacultades");
        query.setParameter("busqueda", "%" + datoBusqueda + "%");
        List<Facultad> resultList = query.getResultList();
        return resultList;
    }
    
}
