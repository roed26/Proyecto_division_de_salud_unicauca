/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.HistoriaModuloSexualidad;
import com.unicauca.divsalud.entidades.Paciente;
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
public class HistoriaModuloSexualidadFacade extends AbstractFacade<HistoriaModuloSexualidad> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriaModuloSexualidadFacade() {
        super(HistoriaModuloSexualidad.class);
    }

     public List<HistoriaModuloSexualidad> findByYearMonth(int anio,int month) {
        Query query = em.createNamedQuery("HistoriaModuloSexualidad.findByYearMes");
        query.setParameter("year", anio);
        query.setParameter("month", month);
        List<HistoriaModuloSexualidad> list= query.getResultList();
        return list;
    }
    public List<HistoriaModuloSexualidad> findByYear(int anio) {
        Query query = em.createNamedQuery("HistoriaModuloSexualidad.findByYear");
        query.setParameter("year", anio);
        List<HistoriaModuloSexualidad> list= query.getResultList();
        return list;
    }
    
    public HistoriaModuloSexualidad findById(int id) {
        Query query = em.createNamedQuery("HistoriaModuloSexualidad.findById");
        query.setParameter("id",id);
         HistoriaModuloSexualidad his;
        try {
           his = (HistoriaModuloSexualidad) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
        return his;
    }
    
    
}
