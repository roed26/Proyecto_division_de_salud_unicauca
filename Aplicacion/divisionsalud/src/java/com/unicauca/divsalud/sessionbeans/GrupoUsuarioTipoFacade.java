/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.sessionbeans;

import com.unicauca.divsalud.entidades.GrupoUsuarioTipo;
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
public class GrupoUsuarioTipoFacade extends AbstractFacade<GrupoUsuarioTipo> {

    @PersistenceContext(unitName = "divisionsaludPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoUsuarioTipoFacade() {
        super(GrupoUsuarioTipo.class);
    }
    
    public List<GrupoUsuarioTipo> buscarPorNombreUsuario(String login)
    {
        Query query = getEntityManager().createNamedQuery("GrupoUsuarioTipo.findByLogin");
        query.setParameter("login", login);
        List<GrupoUsuarioTipo> resultList = query.getResultList();
        return resultList;
    }
    
    public GrupoUsuarioTipo buscarPorNombreDeUsuario(String login)
    {
        Query query = getEntityManager().createNamedQuery("GrupoUsuarioTipo.findByLogin");
        query.setParameter("login", login);
        List<GrupoUsuarioTipo> resultList = query.getResultList();
        if(resultList.size()>0){
            return resultList.get(0);
        }else{
        return null;
        }
        
    }
    
}
