package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.SistemaCuerpoMed;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.SistemaCuerpoMedFacade;

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

@Named("sistemaCuerpoMedController")
@SessionScoped
public class SistemaCuerpoMedController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.SistemaCuerpoMedFacade ejbFacade;
    private List<SistemaCuerpoMed> items = null;
    private SistemaCuerpoMed selected;

    public SistemaCuerpoMedController() {
    }

    public SistemaCuerpoMed getSelected() {
        return selected;
    }

    public void setSelected(SistemaCuerpoMed selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SistemaCuerpoMedFacade getFacade() {
        return ejbFacade;
    }

    public SistemaCuerpoMed prepareCreate() {
        selected = new SistemaCuerpoMed();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleSistemaCuerpoMed").getString("SistemaCuerpoMedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleSistemaCuerpoMed").getString("SistemaCuerpoMedUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleSistemaCuerpoMed").getString("SistemaCuerpoMedDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SistemaCuerpoMed> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleSistemaCuerpoMed").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleSistemaCuerpoMed").getString("PersistenceErrorOccured"));
            }
        }
    }

    public SistemaCuerpoMed getSistemaCuerpoMed(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<SistemaCuerpoMed> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SistemaCuerpoMed> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SistemaCuerpoMed.class)
    public static class SistemaCuerpoMedControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SistemaCuerpoMedController controller = (SistemaCuerpoMedController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sistemaCuerpoMedController");
            return controller.getSistemaCuerpoMed(getKey(value));
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
            if (object instanceof SistemaCuerpoMed) {
                SistemaCuerpoMed o = (SistemaCuerpoMed) object;
                return getStringKey(o.getIdx());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SistemaCuerpoMed.class.getName()});
                return null;
            }
        }

    }

}
