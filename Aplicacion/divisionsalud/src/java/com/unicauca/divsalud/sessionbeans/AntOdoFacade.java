/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.ActualizacionOdo;
import com.unicauca.divsalud.entidades.AntOdo;
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
public class AntOdoFacade extends AbstractFacade<AntOdo> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AntOdoFacade() {
        super(AntOdo.class);
    }
    public List<AntOdo> buscarPorActualizacion(ActualizacionOdo actualizacionOdo) {
        Query query = getEntityManager().createNamedQuery("AntOdo.findByActualizacion");
        query.setParameter("actualizacion", actualizacionOdo);
        List<AntOdo> resultList = query.getResultList();

        return resultList;

    }

    public AntOdo buscarPorActualizacionAlergia(ActualizacionOdo actualizacionOdo) {
        Query query = getEntityManager().createNamedQuery("AntOdo.findByActualizacionAlergia");
        query.setParameter("actualizacion", actualizacionOdo);
        query.setParameter("alergia", "ALERGIAS");
        query.setParameter("tipo", "PE");
        List<AntOdo> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }

    }
}
