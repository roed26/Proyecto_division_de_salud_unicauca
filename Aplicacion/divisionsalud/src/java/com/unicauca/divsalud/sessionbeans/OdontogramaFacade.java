/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.ActualizacionOdo;
import com.unicauca.divsalud.entidades.Odontograma;
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
public class OdontogramaFacade extends AbstractFacade<Odontograma> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OdontogramaFacade() {
        super(Odontograma.class);
    }
    
    public List<Odontograma> buscarPorActualizacion(ActualizacionOdo actualizacionOdo){
        Query query = getEntityManager().createNamedQuery("Odontograma.findByActualizacion");
        query.setParameter("actualizacionOdo", actualizacionOdo);
        List<Odontograma> resultList = query.getResultList();

        return resultList;
    
    }
}
