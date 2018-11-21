/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

import java.io.Serializable;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.entidades.TipoExamDiagnosticoSexual;
import com.unicauca.divsalud.entidades.TipoIdentificacion;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.sessionbeans.TipoExamDiagnosticoSexualFacade;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author acer_acer
 */
@Named("tipoExamDiagnosticoSexualController")
@SessionScoped
public class TipoExamDiagnosticoSexualController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.TipoExamDiagnosticoSexualFacade ejbFacade;
    private List<TipoExamDiagnosticoSexual> items = null;
    private TipoExamDiagnosticoSexual selected;

    public TipoExamDiagnosticoSexualController() {
    }

    public TipoExamDiagnosticoSexualFacade getFacade() {
        return ejbFacade;
    }

    public TipoExamDiagnosticoSexual getSelected() {
        return selected;
    }

    public void setSelected(TipoExamDiagnosticoSexual selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public TipoExamDiagnosticoSexual prepareCreate() {
        selected = new TipoExamDiagnosticoSexual();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TipoExamDiagnosticoSexualCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TipoExamDiagnosticoSexualUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TipoExamDiagnosticoSexualDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TipoExamDiagnosticoSexual> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
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

    public TipoExamDiagnosticoSexual getTipoIdentificacion(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TipoExamDiagnosticoSexual> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoExamDiagnosticoSexual> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    

}
