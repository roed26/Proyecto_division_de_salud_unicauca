/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.MedicamentoMed;
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
public class MedicamentoMedFacade extends AbstractFacade<MedicamentoMed> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicamentoMedFacade() {
        super(MedicamentoMed.class);
    }
    
    public List<MedicamentoMed> buscarPorCodigo(String codigo) {
        Query query = getEntityManager().createNamedQuery("MedicamentoMed.findByCodigo");
        query.setParameter("codigo", codigo);
        List<MedicamentoMed> resultList = query.getResultList();
        return resultList;
    }
    
     public List<MedicamentoMed> buscarPorNombre(String nombre) {
        Query query = getEntityManager().createNamedQuery("MedicamentoMed.findByNombre");
        query.setParameter("nombre", nombre);
        List<MedicamentoMed> resultList = query.getResultList();
        return resultList;
    }
     
    public List<MedicamentoMed> buscarPorVia(String via) {
        Query query = getEntityManager().createNamedQuery("MedicamentoMed.findByVia");
        query.setParameter("via", via);
        List<MedicamentoMed> resultList = query.getResultList();
        return resultList;
    } 
    
    public List<MedicamentoMed> buscarPorPre(String pre) {
        Query query = getEntityManager().createNamedQuery("MedicamentoMed.findByPre");
        query.setParameter("pre", pre);
        List<MedicamentoMed> resultList = query.getResultList();
        return resultList;
    }
    
    public List<MedicamentoMed> buscarPorConcentracion(String concentracion) {
        Query query = getEntityManager().createNamedQuery("MedicamentoMed.findByConcentracion");
        query.setParameter("concentracion", concentracion);
        List<MedicamentoMed> resultList = query.getResultList();
        return resultList;
    }
    
    public List<MedicamentoMed> buscarMedicamentos(String datoBusqueda) {
        Query query = getEntityManager().createNamedQuery("MedicamentoMed.findByMedicamentos");
        query.setParameter("busqueda", "%" + datoBusqueda + "%");
        List<MedicamentoMed> resultList = query.getResultList();
        return resultList;
    }
}

    
