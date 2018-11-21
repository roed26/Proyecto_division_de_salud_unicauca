/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.Diagnosticos;
import com.unicauca.divsalud.entidades.EnfermedadesCie10Med;
import java.util.Date;
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
public class DiagnosticosFacade extends AbstractFacade<Diagnosticos> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnosticosFacade() {
        super(Diagnosticos.class);
    }
    
    public List <Diagnosticos> listadoDianosticoConsult(EnfermedadesCie10Med enfermedadesCie10) {
        Query query = getEntityManager().createNamedQuery("Diagnosticos.findByCodigoCie10");
        query.setParameter("codigoCie10", enfermedadesCie10.getCodigo());
        List<Diagnosticos> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Diagnosticos> listadoDiagnosticoFecha(EnfermedadesCie10Med enfermedadesCie10, Date fechaInicio, Date fechaFin) {
        Query query = getEntityManager().createNamedQuery("Diagnosticos.findByConsultaFecha");
        query.setParameter("codigoCie10", enfermedadesCie10.getCodigo());
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        List<Diagnosticos> resultList = query.getResultList();
        return resultList;
    }
}
