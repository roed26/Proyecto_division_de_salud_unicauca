/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.AlergenoMed;
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
public class AlergenoMedFacade extends AbstractFacade<AlergenoMed> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlergenoMedFacade() {
        super(AlergenoMed.class);
    }
    
    public List<AlergenoMed> buscarPorId(Integer  id) {
        Query query = getEntityManager().createNamedQuery("AlergenoMed.findByIdx");
        query.setParameter("id", id);
        List<AlergenoMed> resultList = query.getResultList();
        return resultList;
    }
    
    public List<AlergenoMed> buscarPorNombre(String nombre) {
        Query query = getEntityManager().createNamedQuery("AlergenoMed.findByNombre");
        query.setParameter("nombre", nombre);
        List<AlergenoMed> resultList = query.getResultList();
        return resultList;
    }
    
    public List<AlergenoMed> buscarPorTipo(Integer TipoAlergeno) {
        Query query = getEntityManager().createNamedQuery("AlergenoMed.findByTipo");
        query.setParameter("TipoAlergeno", TipoAlergeno);
        List<AlergenoMed> resultList = query.getResultList();
        return resultList;
    }
    
    public List<AlergenoMed> buscarAlergeno(String datoBusqueda) {
        Query query = getEntityManager().createNamedQuery("AlergenoMed.findByAlergeno");
        query.setParameter("busqueda", "%" + datoBusqueda + "%");
        List<AlergenoMed> resultList = query.getResultList();
        return resultList;
    }
    
}
