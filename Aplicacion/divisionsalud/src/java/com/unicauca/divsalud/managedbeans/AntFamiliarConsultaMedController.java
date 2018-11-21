package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.AntFamiliarConsultaMed;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.AntFamiliarConsultaMedFacade;

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

@Named("antFamiliarConsultaMedController")
@SessionScoped
public class AntFamiliarConsultaMedController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.AntFamiliarConsultaMedFacade ejbFacade;
    private List<AntFamiliarConsultaMed> items = null;
    private AntFamiliarConsultaMed selected;

    public AntFamiliarConsultaMedController() {
    }

    public AntFamiliarConsultaMed getSelected() {
        return selected;
    }

    public void setSelected(AntFamiliarConsultaMed selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AntFamiliarConsultaMedFacade getFacade() {
        return ejbFacade;
    }

    public AntFamiliarConsultaMed prepareCreate() {
        selected = new AntFamiliarConsultaMed();
        initializeEmbeddableKey();
        return selected;
    }        

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleAntFamiliarConsultaMed").getString("AntFamiliarConsultaMedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleAntFamiliarConsultaMed").getString("AntFamiliarConsultaMedUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleAntFamiliarConsultaMed").getString("AntFamiliarConsultaMedDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AntFamiliarConsultaMed> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleAntFamiliarConsultaMed").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleAntFamiliarConsultaMed").getString("PersistenceErrorOccured"));
            }
        }
    }

    public AntFamiliarConsultaMed getAntFamiliarConsultaMed(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AntFamiliarConsultaMed> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AntFamiliarConsultaMed> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AntFamiliarConsultaMed.class)
    public static class AntFamiliarConsultaMedControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AntFamiliarConsultaMedController controller = (AntFamiliarConsultaMedController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "antFamiliarConsultaMedController");
            return controller.getAntFamiliarConsultaMed(getKey(value));
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
            if (object instanceof AntFamiliarConsultaMed) {
                AntFamiliarConsultaMed o = (AntFamiliarConsultaMed) object;
                return getStringKey(o.getIdx());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AntFamiliarConsultaMed.class.getName()});
                return null;
            }
        }

    }

}
