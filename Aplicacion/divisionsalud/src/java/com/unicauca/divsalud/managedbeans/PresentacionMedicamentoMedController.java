package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.PresentacionMedicamentoMed;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.PresentacionMedicamentoMedFacade;

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

@Named("presentacionMedicamentoMedController")
@SessionScoped
public class PresentacionMedicamentoMedController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.PresentacionMedicamentoMedFacade ejbFacade;
    private List<PresentacionMedicamentoMed> items = null;
    private PresentacionMedicamentoMed selected;

    public PresentacionMedicamentoMedController() {
    }

    public PresentacionMedicamentoMed getSelected() {
        return selected;
    }

    public void setSelected(PresentacionMedicamentoMed selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PresentacionMedicamentoMedFacade getFacade() {
        return ejbFacade;
    }

    public PresentacionMedicamentoMed prepareCreate() {
        selected = new PresentacionMedicamentoMed();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleMedicamentosMed").getString("PresentacionMedicamentoMedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleMedicamentosMed").getString("PresentacionMedicamentoMedUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleMedicamentosMed").getString("PresentacionMedicamentoMedDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PresentacionMedicamentoMed> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleMedicamentosMed").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleMedicamentosMed").getString("PersistenceErrorOccured"));
            }
        }
    }

    public PresentacionMedicamentoMed getPresentacionMedicamentoMed(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PresentacionMedicamentoMed> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PresentacionMedicamentoMed> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PresentacionMedicamentoMed.class)
    public static class PresentacionMedicamentoMedControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PresentacionMedicamentoMedController controller = (PresentacionMedicamentoMedController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "presentacionMedicamentoMedController");
            return controller.getPresentacionMedicamentoMed(getKey(value));
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
            if (object instanceof PresentacionMedicamentoMed) {
                PresentacionMedicamentoMed o = (PresentacionMedicamentoMed) object;
                return getStringKey(o.getIdx());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PresentacionMedicamentoMed.class.getName()});
                return null;
            }
        }

    }

}
