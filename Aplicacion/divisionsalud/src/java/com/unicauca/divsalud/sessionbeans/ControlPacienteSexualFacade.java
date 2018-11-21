/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.ControlPacienteSexual;
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
public class ControlPacienteSexualFacade extends AbstractFacade<ControlPacienteSexual> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlPacienteSexualFacade() {
        super(ControlPacienteSexual.class);
    }

    public List<ControlPacienteSexual> findByIdHistoria(int id) {
        Query query = em.createNamedQuery("ControlPacienteSexual.findByIdHistoria");
        query.setParameter("id", new HistoriaModuloSexualidad(id));
        List<ControlPacienteSexual> list= query.getResultList();
        return list;        
    }
    public List<ControlPacienteSexual> findByYearMonth(int anio,int month) {
        Query query = em.createNamedQuery("ControlPacienteSexual.findByYearMes");
        query.setParameter("year", anio);
        query.setParameter("month", month);
        List<ControlPacienteSexual> list= query.getResultList();
        return list;
    }
    public List<ControlPacienteSexual> findByYear(int anio) {
        Query query = em.createNamedQuery("ControlPacienteSexual.findByYear");
        query.setParameter("year", anio);
        List<ControlPacienteSexual> list= query.getResultList();
        return list;
    }
}
