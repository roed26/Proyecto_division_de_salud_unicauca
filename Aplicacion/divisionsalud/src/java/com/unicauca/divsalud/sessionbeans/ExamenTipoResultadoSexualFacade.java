/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.ExamenTipoResultadoSexual;
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
public class ExamenTipoResultadoSexualFacade extends AbstractFacade<ExamenTipoResultadoSexual> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamenTipoResultadoSexualFacade() {
        super(ExamenTipoResultadoSexual.class);
    }

    public List<ExamenTipoResultadoSexual> findByIdExamenFisicoSexual(long id) {
        Query query = em.createNamedQuery("ExamenTipoResultadoSexual.findByIdExamenFisicoSexual");
        query.setParameter("idExamenFisicoSexual", id);
        List<ExamenTipoResultadoSexual> list;
        try {
            list = query.getResultList();
        } catch (Exception e) {
            list = null;
        }
        return list;
    }
}
