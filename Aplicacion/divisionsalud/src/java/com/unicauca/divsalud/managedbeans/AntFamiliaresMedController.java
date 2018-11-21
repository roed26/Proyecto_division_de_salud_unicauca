package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.AntFamiliaresMed;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.AntFamiliaresMedFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("antFamiliaresMedController")
@SessionScoped
public class AntFamiliaresMedController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.AntFamiliaresMedFacade ejbFacade;
    private List<AntFamiliaresMed> items = null;
    private AntFamiliaresMed selected;

    public AntFamiliaresMedController() {
    }

    public AntFamiliaresMed getSelected() {
        return selected;
    }

    public void setSelected(AntFamiliaresMed selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AntFamiliaresMedFacade getFacade() {
        return ejbFacade;
    }

    public AntFamiliaresMed prepareCreate() {
        selected = new AntFamiliaresMed();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleAntecedenteFamiliarMed").getString("AntFamiliaresMedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleAntecedenteFamiliarMed").getString("AntFamiliaresMedUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleAntecedenteFamiliarMed").getString("AntFamiliaresMedDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AntFamiliaresMed> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleAntecedenteFamiliarMed").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleAntecedenteFamiliarMed").getString("PersistenceErrorOccured"));
            }
        }
    }

    public AntFamiliaresMed getAntFamiliaresMed(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AntFamiliaresMed> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AntFamiliaresMed> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AntFamiliaresMed.class)
    public static class AntFamiliaresMedControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AntFamiliaresMedController controller = (AntFamiliaresMedController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "antFamiliaresMedController");
            return controller.getAntFamiliaresMed(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AntFamiliaresMed) {
                AntFamiliaresMed o = (AntFamiliaresMed) object;
                return getStringKey(o.getIdx());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AntFamiliaresMed.class.getName()});
                return null;
            }
        }

    }

}
