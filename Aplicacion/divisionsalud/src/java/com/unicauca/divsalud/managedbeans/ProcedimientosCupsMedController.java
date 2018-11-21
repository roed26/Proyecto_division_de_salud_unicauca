package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.ProcedimientosCupsMed;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.ProcedimientosCupsMedFacade;

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

@Named("procedimientosCupsMedController")
@SessionScoped
public class ProcedimientosCupsMedController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.ProcedimientosCupsMedFacade ejbFacade;
    private List<ProcedimientosCupsMed> items = null;
    private ProcedimientosCupsMed selected;
    private String datoBusqueda;

    public ProcedimientosCupsMedController() {
    }

    public ProcedimientosCupsMed getSelected() {
        return selected;
    }

    public void setSelected(ProcedimientosCupsMed selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProcedimientosCupsMedFacade getFacade() {
        return ejbFacade;
    }
    
    public void seleccionarProcedimientos(ProcedimientosCupsMed selected) {
            this.selected = selected;  
            //if(selected.getGrupoUsuarioTipoCollection().toArray().length>0){
              //  GrupoUsuarioTipo grupoUsuarioTipo = (GrupoUsuarioTipo) usuarioSistema.getGrupoUsuarioTipoCollection().toArray()[0];
                //this.tipoUsuario = grupoUsuarioTipo.getTipoUsuario();
        //}
    }
    public String getDatoBusqueda() {
        return datoBusqueda;
    }

    public void setDatoBusqueda(String datoBusqueda) {
        this.datoBusqueda = datoBusqueda;
    }
    
    public void buscarProcedimiento() {
        this.items = ejbFacade.buscarProcedimiento(this.datoBusqueda.toLowerCase());
    }

    public ProcedimientosCupsMed prepareCreate() {
        selected = new ProcedimientosCupsMed();
        initializeEmbeddableKey();
        return selected;
    }

   
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleProcedimientosCups").getString("ProcedimientosCupsMedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleProcedimientosCups").getString("ProcedimientosCupsMedUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleProcedimientosCups").getString("ProcedimientosCupsMedDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ProcedimientosCupsMed> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleProcedimientosCups").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleProcedimientosCups").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ProcedimientosCupsMed getProcedimientosCupsMed(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<ProcedimientosCupsMed> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ProcedimientosCupsMed> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public void inicializar() {
        this.selected = new ProcedimientosCupsMed();
    }

    @FacesConverter(forClass = ProcedimientosCupsMed.class)
    public static class ProcedimientosCupsMedControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProcedimientosCupsMedController controller = (ProcedimientosCupsMedController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "procedimientosCupsMedController");
            return controller.getProcedimientosCupsMed(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProcedimientosCupsMed) {
                ProcedimientosCupsMed o = (ProcedimientosCupsMed) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProcedimientosCupsMed.class.getName()});
                return null;
            }
        }

    }

}
