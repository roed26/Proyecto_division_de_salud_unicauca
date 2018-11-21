/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.ActualizacionOdo;
import com.unicauca.divsalud.entidades.EvolucionOdo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ROED26
 */
@Stateless
public class EvolucionOdoFacade extends AbstractFacade<EvolucionOdo> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvolucionOdoFacade() {
        super(EvolucionOdo.class);
    }
    public List<EvolucionOdo> buscarPorActualizacion(ActualizacionOdo actualizacionOdo) {
        Query query = getEntityManager().createNamedQuery("EvolucionOdo.findByActualizacion");
        query.setParameter("actualizacion", actualizacionOdo);
        List<EvolucionOdo> resultList = query.getResultList();

        return resultList;

    }   
}
