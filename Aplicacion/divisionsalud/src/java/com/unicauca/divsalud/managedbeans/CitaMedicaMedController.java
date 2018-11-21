package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.CitaMedicaMed;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.CitaMedicaMedFacade;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named("citaMedicaMedController")
@SessionScoped
public class CitaMedicaMedController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.CitaMedicaMedFacade ejbFacade;
    private List<CitaMedicaMed> items = null;
    private CitaMedicaMed selected;
    private com.unicauca.divsalud.sessionbeans.UsuariosSistemaFacade ejbFacadeUsuarios;
    private com.unicauca.divsalud.sessionbeans.PacienteFacade ejbFacadePaciente;
    private com.unicauca.divsalud.sessionbeans.TipoCitaMedFacade ejbFacadeTipoCita;
    
    public CitaMedicaMedController() {
        this.selected = new CitaMedicaMed();
    }
    
    public CitaMedicaMed getSelected() {
        return selected;
    }

    public void setSelected(CitaMedicaMed selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CitaMedicaMedFacade getFacade() {
        return ejbFacade;
    }

    public CitaMedicaMed prepareCreate() {
        selected = new CitaMedicaMed();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setEstado("espera");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CitaMedicaMedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void atender(){
        selected.setEstado("atendida");
        //Date now = new Date(System.currentTimeMillis());
        //SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        //selected.setHoraInicio(now);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CitaMedicaMedUpdated"));
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CitaMedicaMedUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CitaMedicaMedDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CitaMedicaMed> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public void manejarFechaSelec(SelectEvent event) 
    {
        RequestContext.getCurrentInstance().execute("PF('lista').filter()");
    }
    
    public void manejarFechaSelec2(SelectEvent event) 
    {
        RequestContext.getCurrentInstance().execute("PF('lista2').filter()");
    }
    
    public List<CitaMedicaMed> getAgend() {
        ArrayList<CitaMedicaMed> itemsMedico = new ArrayList<>();
        int idSingleton = UsuarioSessionController.getActualSingleton().getId();
        if (items == null) {
            items = getFacade().findAll();
        }
        for (CitaMedicaMed citaMedicaMed : items) {
            int idUsuarioCita = citaMedicaMed.getUsuariossistemaID().getId();
            if (idSingleton==idUsuarioCita ) {
                itemsMedico.add(citaMedicaMed);
            }
        }
        return itemsMedico;
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

    public CitaMedicaMed getCitaMedicaMed(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CitaMedicaMed> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CitaMedicaMed> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CitaMedicaMed.class)
    public static class CitaMedicaMedControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CitaMedicaMedController controller = (CitaMedicaMedController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "citaMedicaMedController");
            return controller.getCitaMedicaMed(getKey(value));
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
            if (object instanceof CitaMedicaMed) {
                CitaMedicaMed o = (CitaMedicaMed) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CitaMedicaMed.class.getName()});
                return null;
            }
        }

    }

}
