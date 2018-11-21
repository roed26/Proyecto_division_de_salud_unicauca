package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.HistoricoGinecostetricos;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.HistoricoGinecostetricosFacade;

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

@Named("historicoGinecostetricosController")
@SessionScoped
public class HistoricoGinecostetricosController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.HistoricoGinecostetricosFacade ejbFacade;
    private List<HistoricoGinecostetricos> items = null;
    private HistoricoGinecostetricos selected;

    public HistoricoGinecostetricosController() {
    }

    public HistoricoGinecostetricos getSelected() {
        return selected;
    }

    public void setSelected(HistoricoGinecostetricos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getHistoricoGinecostetricosPK().setIdxGinecostetricos(selected.getGinecostetricosMed().getIdx());
        selected.getHistoricoGinecostetricosPK().setIdxConsulta(selected.getConsultaMedicaMed().getIdx());
    }

    protected void initializeEmbeddableKey() {
        selected.setHistoricoGinecostetricosPK(new com.unicauca.divsalud.entidades.HistoricoGinecostetricosPK());
    }

    private HistoricoGinecostetricosFacade getFacade() {
        return ejbFacade;
    }

    public HistoricoGinecostetricos prepareCreate() {
        selected = new HistoricoGinecostetricos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleHistoricoGinecostetricosMed").getString("HistoricoGinecostetricosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleHistoricoGinecostetricosMed").getString("HistoricoGinecostetricosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleHistoricoGinecostetricosMed").getString("HistoricoGinecostetricosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<HistoricoGinecostetricos> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleHistoricoGinecostetricosMed").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleHistoricoGinecostetricosMed").getString("PersistenceErrorOccured"));
            }
        }
    }

    public HistoricoGinecostetricos getHistoricoGinecostetricos(com.unicauca.divsalud.entidades.HistoricoGinecostetricosPK id) {
        return getFacade().find(id);
    }

    public List<HistoricoGinecostetricos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<HistoricoGinecostetricos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = HistoricoGinecostetricos.class)
    public static class HistoricoGinecostetricosControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HistoricoGinecostetricosController controller = (HistoricoGinecostetricosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "historicoGinecostetricosController");
            return controller.getHistoricoGinecostetricos(getKey(value));
        }

        com.unicauca.divsalud.entidades.HistoricoGinecostetricosPK getKey(String value) {
            com.unicauca.divsalud.entidades.HistoricoGinecostetricosPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.unicauca.divsalud.entidades.HistoricoGinecostetricosPK();
            key.setIdxConsulta(Integer.parseInt(values[0]));
            key.setIdxGinecostetricos(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.unicauca.divsalud.entidades.HistoricoGinecostetricosPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdxConsulta());
            sb.append(SEPARATOR);
            sb.append(value.getIdxGinecostetricos());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof HistoricoGinecostetricos) {
                HistoricoGinecostetricos o = (HistoricoGinecostetricos) object;
                return getStringKey(o.getHistoricoGinecostetricosPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), HistoricoGinecostetricos.class.getName()});
                return null;
            }
        }

    }

}
