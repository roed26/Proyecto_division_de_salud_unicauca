/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.EnfermedadesCie10Med;
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
public class EnfermedadesCie10MedFacade extends AbstractFacade<EnfermedadesCie10Med> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnfermedadesCie10MedFacade() {
        super(EnfermedadesCie10Med.class);
    }
    
    public List<EnfermedadesCie10Med> buscarPorId(String codigo) {
        Query query = getEntityManager().createNamedQuery("EnfermedadesCie10Med.findByCodigo");
        query.setParameter("codigo", codigo);
        List<EnfermedadesCie10Med> resultList = query.getResultList();
        return resultList;
    }
    
    public List<EnfermedadesCie10Med> buscarPorNombre(String descripcion) {
        Query query = getEntityManager().createNamedQuery("EnfermedadesCie10Med.findByDescripcion");
        query.setParameter("descripcion", descripcion);
        List<EnfermedadesCie10Med> resultList = query.getResultList();
        return resultList;
    }
    
    public List<EnfermedadesCie10Med> buscarDiagnostico(String datoBusqueda) {
        Query query = getEntityManager().createNamedQuery("EnfermedadesCie10Med.findByDiagnostico");
        query.setParameter("busqueda", "%" + datoBusqueda + "%");
        List<EnfermedadesCie10Med> resultList = query.getResultList();
        return resultList;
    }
    
}
