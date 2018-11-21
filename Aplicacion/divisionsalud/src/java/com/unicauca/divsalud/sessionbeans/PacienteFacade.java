/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.Paciente;
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
public class PacienteFacade extends AbstractFacade<Paciente> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteFacade() {
        super(Paciente.class);
    }

    public List<Paciente> buscarPacienteEjb(String buscarPaciente) {
        Query query = getEntityManager().createNamedQuery("Paciente.findByPacientesActivos");
        query.setParameter("busqueda", "%" + buscarPaciente + "%");
        List<Paciente> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Paciente> findByIdentificacionTipoIdentificacion(String id, String tipo_identificacion_id) {
        Query query = em.createNamedQuery("Paciente.findByIdentificacionTipoIdentificacion");
        query.setParameter("identificacion", id);
        query.setParameter("tipoIdentificacion", tipo_identificacion_id);
        List<Paciente> resultList = query.getResultList();
        return resultList;
    }

    public List<Paciente> findByPacientesConHistoria(int[] range) {
        Query query = em.createNamedQuery("Paciente.findByPacientesConHistoria");
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Paciente> resultList = query.getResultList();
        return resultList;
    }

    public int numeroPacientesConHistoria() {
        Query query = em.createNamedQuery("Paciente.findByPacientesConHistoria");
        List<Paciente> resultList = query.getResultList();
        return resultList.size();
    }

    public List<Paciente> findByPacientesSinHistoria(int[] range) {
        Query query = em.createNamedQuery("Paciente.findByPacientesSinHistoria");
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Paciente> resultList = query.getResultList();
        return resultList;
    }

    public int numeroPacientesSinHistoria() {
        Query query = em.createNamedQuery("Paciente.findByPacientesSinHistoria");
        List<Paciente> resultList = query.getResultList();
        return resultList.size();
    }
    
    public List<Paciente> buscarPacienteWithRange(String buscarPaciente, int[] range) {
        Query query = getEntityManager().createNamedQuery("Paciente.findByPacientesActivos");
        query.setParameter("busqueda", "%" + buscarPaciente + "%");
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Paciente> resultList = query.getResultList();
        return resultList;
    }
    
    public int numeroPacientesBusqueda(String buscarPaciente) {
        Query query = getEntityManager().createNamedQuery("Paciente.findByPacientesActivos");
        query.setParameter("busqueda", "%" + buscarPaciente + "%");
        List<Paciente> resultList = query.getResultList();
        return resultList.size();
    }
    
    public List<Paciente> findByPacientesConHistoriaBusqueda(String buscarPaciente, int[] range) {
        Query query = em.createNamedQuery("Paciente.findByPacientesConHistoriaBusqueda");
        query.setParameter("busqueda", "%" + buscarPaciente + "%");
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Paciente> resultList = query.getResultList();
        return resultList;
    }
       
    public int numeroPacientesConHistoriaBusqueda(String buscarPaciente) {
        Query query = em.createNamedQuery("Paciente.findByPacientesConHistoriaBusqueda");
        query.setParameter("busqueda", "%" + buscarPaciente + "%");
        List<Paciente> resultList = query.getResultList();
        return resultList.size();
    }
    
    public List<Paciente> findByPacientesSinHistoriaBusqueda(String buscarPaciente, int[] range) {
        Query query = em.createNamedQuery("Paciente.findByPacientesSinHistoriaBusqueda");
        query.setParameter("busqueda", "%" + buscarPaciente + "%");
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Paciente> resultList = query.getResultList();
        return resultList;
    }

    public int numeroPacientesSinHistoriaBusqueda(String buscarPaciente) {
        Query query = em.createNamedQuery("Paciente.findByPacientesSinHistoriaBusqueda");
        query.setParameter("busqueda", "%" + buscarPaciente + "%");
        List<Paciente> resultList = query.getResultList();
        return resultList.size();
    }
}
