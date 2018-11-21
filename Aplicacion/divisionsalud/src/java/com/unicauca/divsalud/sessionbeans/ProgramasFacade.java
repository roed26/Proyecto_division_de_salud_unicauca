/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.Programas;
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
public class ProgramasFacade extends AbstractFacade<Programas> {
    
    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramasFacade() {
        super(Programas.class);
    }
   
    
    public List<Programas> buscarPorId(String id) {
        Query query = getEntityManager().createNamedQuery("Programas.findById");
        query.setParameter("id", id);
        List<Programas> resultList = query.getResultList();
        return resultList;
    }

    public List<Programas> buscarPorNombre(String nombre) {
        Query query = getEntityManager().createNamedQuery("Programas.findByNombre");
        query.setParameter("nombre", nombre);
        List<Programas> resultList = query.getResultList();
        return resultList;
    }

    public List<Programas> buscarPorFacultad(String nombreFacult) {
        Query query = getEntityManager().createNamedQuery("Facultad.findByIdPrograma");
        query.setParameter("facultad", nombreFacult);
        List<Programas> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Programas> buscarProgramasEjb(String datoBusqueda) {
        Query query = getEntityManager().createNamedQuery("Programas.findByProgramas");
        query.setParameter("busqueda", "%" + datoBusqueda + "%");
        List<Programas> resultList = query.getResultList();
        return resultList;
    }
}
