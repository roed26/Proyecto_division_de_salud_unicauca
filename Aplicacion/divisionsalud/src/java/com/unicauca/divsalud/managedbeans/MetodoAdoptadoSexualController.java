/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.HistoriaModuloSexualidad;
import com.unicauca.divsalud.entidades.MetodoPlanificacion;
import com.unicauca.divsalud.entidades.MetodoAdoptadoSexual;
import com.unicauca.divsalud.entidades.MetodoAdoptadoSexualPK;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.sessionbeans.HistoriaModuloSexualidadFacade;
import com.unicauca.divsalud.sessionbeans.MetodoPlanificacionFacade;
import com.unicauca.divsalud.sessionbeans.MetodoAdoptadoSexualFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Camilo
 */
@Named(value = "metodoAdoptadoSexualController")
@SessionScoped
public class MetodoAdoptadoSexualController implements Serializable{
    
    private MetodoAdoptadoSexual selected;
    @EJB
    private MetodoAdoptadoSexualFacade ejbFacade;
    @EJB
    private HistoriaModuloSexualidadFacade hms_facade;
    
    boolean viendo ;

    public boolean isViendo() {
        return viendo;
    }

    public void setViendo(boolean viendo) {
        this.viendo = viendo;
    }
    public MetodoAdoptadoSexualController() {
    }

    public MetodoAdoptadoSexualFacade getFacade() {
        return ejbFacade;
    }

    public MetodoAdoptadoSexual getSelected() {
        if (selected==null){
            selected = new MetodoAdoptadoSexual();
        }
        return selected;
    }

    public void setSelected(MetodoAdoptadoSexual selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public MetodoAdoptadoSexual prepareCreate() {
        selected = new MetodoAdoptadoSexual();
        initializeEmbeddableKey();
        return selected;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public void create() {
        System.out.println("Create");
        ejbFacade.create(selected);
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MetodoAdoptadoSexualCreated"));
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MetodoAdoptadoSexualUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MetodoAdoptadoSexualDeleted"));

    }
        
    public Date getMinDate(){
        return new Date();
    }
    
    public void registrar(int id_historia_modulo_sexualidad)
    {
        try
        {
            HistoriaModuloSexualidad h = hms_facade.find(id_historia_modulo_sexualidad);
            selected.setHistoriaModuloSexualidad(h);
            
            MetodoAdoptadoSexualPK pk = new MetodoAdoptadoSexualPK();
            pk.setId(id_historia_modulo_sexualidad);
            pk.setIdMetodoPlanificacion(selected.getMetodoPlanificacion().getIdMetodoPlanificacion());
            selected.setMetodoAdoptadoSexualPK(pk);
            
            ejbFacade.create(selected);
        }
        catch (EJBException e) {
            @SuppressWarnings("ThrowableResultIgnored")
            Exception cause = e.getCausedByException();
            if (cause instanceof ConstraintViolationException) {
                @SuppressWarnings("ThrowableResultIgnored")
                ConstraintViolationException cve = (ConstraintViolationException) e.getCausedByException();
                for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<? extends Object> v = it.next();
                    System.err.println(v);
                    System.err.println("==>>"+v.getMessage());
                }
            }
        }
    }
    
    public void limpiar(){
        this.selected = new MetodoAdoptadoSexual();
        this.viendo = false;
    }
    
    public void cargarMetodoAdoptado(int id ){
        this.selected = this.ejbFacade.getByIdPacient(id);
        this.viendo = true;
    }
}
