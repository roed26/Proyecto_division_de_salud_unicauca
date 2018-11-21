/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.UsuariosSistema;
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
public class UsuariosSistemaFacade extends AbstractFacade<UsuariosSistema> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosSistemaFacade() {
        super(UsuariosSistema.class);
    }

    public List<UsuariosSistema> buscarPorNombreUsuario(String login) {
        Query query = getEntityManager().createNamedQuery("UsuariosSistema.findByLogin");
        query.setParameter("login", login);
        List<UsuariosSistema> resultList = query.getResultList();
        return resultList;
    }

    public boolean buscarPorEmail(String login) {
        Query query = getEntityManager().createNamedQuery("UsuariosSistema.findByLogin");
        query.setParameter("login", login);
        List<UsuariosSistema> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<UsuariosSistema> buscarPorNumeroIdentificacion(String identificacion) {
        Query query = getEntityManager().createNamedQuery("UsuariosSistema.findByIdentificacion");
        query.setParameter("identificacion", identificacion);
        List<UsuariosSistema> resultList = query.getResultList();
        return resultList;
    }
    
    public List<UsuariosSistema> buscarUsuarioEjb(String datoBusqueda) {
        Query query = getEntityManager().createNamedQuery("UsuariosSistema.findByUsuarios");
        query.setParameter("busqueda", "%" + datoBusqueda + "%");
        List<UsuariosSistema> resultList = query.getResultList();
        return resultList;
    }
}
