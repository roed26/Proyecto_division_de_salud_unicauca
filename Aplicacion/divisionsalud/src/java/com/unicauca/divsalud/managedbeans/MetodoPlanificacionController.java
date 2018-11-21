package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.Escolaridad;
import com.unicauca.divsalud.entidades.HistorialMetodosUsadosSexual;
import com.unicauca.divsalud.entidades.MetodoPlanificacion;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.sessionbeans.HistorialMetodosUsadosSexualFacade;
import com.unicauca.divsalud.sessionbeans.MetodoPlanificacionFacade;
import java.io.Serializable;
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
 * @author Andres Vidal Zemanate /* FIET-Ingenieria de Sistemas
 */
@Named(value = "metodoPlanificacionController")
@SessionScoped
public class MetodoPlanificacionController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.MetodoPlanificacionFacade ejbFacade;
    private List<MetodoPlanificacion> items = null;
    private MetodoPlanificacion selected;

    public MetodoPlanificacionController() {
    }

    public MetodoPlanificacionFacade getFacade() {
        return ejbFacade;
    }

    public List<MetodoPlanificacion> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void setItems(List<MetodoPlanificacion> items) {
        this.items = items;
    }

    public MetodoPlanificacion getSelected() {
        return selected;
    }

    public void setSelected(MetodoPlanificacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public MetodoPlanificacion prepareCreate() {
        selected = new MetodoPlanificacion();
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
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MetodoPlanificacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MetodoPlanificacionUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MetodoPlanificacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public MetodoPlanificacion getMetodoPlanificacion(java.lang.Short id) {
        return getFacade().find(id);
    }
    
        @FacesConverter(forClass = MetodoPlanificacion.class)
    public static class EscolaridadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MetodoPlanificacionController controller = (MetodoPlanificacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "metodoPlanificacionController");
            return controller.getMetodoPlanificacion(getKey(value));
        }

        java.lang.Short getKey(String value) {
            java.lang.Short key;
            key = Short.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Short value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MetodoPlanificacion) {
                MetodoPlanificacion o = (MetodoPlanificacion) object;
                return getStringKey(o.getIdMetodoPlanificacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Escolaridad.class.getName()});
                return null;
            }
        }

    }

}
