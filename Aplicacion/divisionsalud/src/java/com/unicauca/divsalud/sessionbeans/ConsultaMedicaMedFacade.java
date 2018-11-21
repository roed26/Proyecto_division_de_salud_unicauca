/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.AlergenoMed;
import com.unicauca.divsalud.entidades.ConsultaMedicaMed;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Danilo
 */
@Stateless
public class ConsultaMedicaMedFacade extends AbstractFacade<ConsultaMedicaMed> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaMedicaMedFacade() {
        super(ConsultaMedicaMed.class);
    }
    
    public List<AlergenoMed> buscarAlergenoEjb(String alergeno, String tipoAlergeno) {
        System.out.println("buscando alergeno");        
        Query query = getEntityManager().createNamedQuery("ConsultaMedicaMed.findByNombreTipo");
        query.setParameter("nombre", "%" + alergeno + "%");
        query.setParameter("tipo",tipoAlergeno);
        List<AlergenoMed> resultList = query.getResultList();                        
        System.out.println("buscando alergeno  " + resultList.toString());
        return resultList;
    }
    
    public List<String> buscarHabitoOtrosEjb(String habitoOtros) {
        Query query = getEntityManager().createNamedQuery("ConsultaMedicaMed.findHabitoOtros");
        query.setParameter("texto", "%" + habitoOtros + "%");
        query.setParameter("tipo", "OTROS");
        List<String> resultList = query.getResultList();
        System.out.println("buscando habitos ortros  " + resultList.toString());
        resultList.add(0,"OTROS");
        return resultList;
    }
    
    public ConsultaMedicaMed guardar(ConsultaMedicaMed consultaMedica){
        em.persist(consultaMedica);
        em.flush();
        return consultaMedica;
    }    
}
