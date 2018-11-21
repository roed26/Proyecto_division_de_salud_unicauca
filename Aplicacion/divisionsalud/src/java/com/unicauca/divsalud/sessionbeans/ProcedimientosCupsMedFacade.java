/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.ProcedimientosCupsMed;
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
public class ProcedimientosCupsMedFacade extends AbstractFacade<ProcedimientosCupsMed> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcedimientosCupsMedFacade() {
        super(ProcedimientosCupsMed.class);
    }
    
    public List<ProcedimientosCupsMed> buscarPorCodigo(String codigo) {
        Query query = getEntityManager().createNamedQuery("ProcedimientosCupsMed.findByCodigo");
        query.setParameter("codigo", codigo);
        List<ProcedimientosCupsMed> resultList = query.getResultList();
        return resultList;
    }

    public List<ProcedimientosCupsMed> buscarPorDescripcion(String descripcion) {
        Query query = getEntityManager().createNamedQuery("ProcedimientosCupsMed.findByDescripcion");
        query.setParameter("descripcion", descripcion);
        List<ProcedimientosCupsMed> resultList = query.getResultList();
        return resultList;
    }
    
    public List<ProcedimientosCupsMed> buscarProcedimiento(String datoBusqueda) {
        Query query = getEntityManager().createNamedQuery("ProcedimientosCupsMed.findByProcedimientos");
        query.setParameter("busqueda", "%" + datoBusqueda + "%");
        List<ProcedimientosCupsMed> resultList = query.getResultList();
        return resultList;
    }
    
}
