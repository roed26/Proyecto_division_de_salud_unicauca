/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.Diagnosticocie10Odo;
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
public class Diagnosticocie10OdoFacade extends AbstractFacade<Diagnosticocie10Odo> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Diagnosticocie10OdoFacade() {
        super(Diagnosticocie10Odo.class);
    }

    public List<Diagnosticocie10Odo> buscarPorNombreId(String nombreId) {
        Query query = getEntityManager().createNamedQuery("Diagnosticocie10Odo.findByNombreId");
        query.setParameter("nombreId", "%" + nombreId + "%");
        List<Diagnosticocie10Odo> resultList = query.getResultList();
        return resultList;
    }
}
