package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.EnfermedadesCie10Med;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.EnfermedadesCie10MedFacade;

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

@Named("enfermedadesCie10MedController")
@SessionScoped
public class EnfermedadesCie10MedController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.EnfermedadesCie10MedFacade ejbFacade;
    private List<EnfermedadesCie10Med> items = null;
    private EnfermedadesCie10Med selected;
    private String datoBusqueda;

    public EnfermedadesCie10MedController() {
    }

    public EnfermedadesCie10Med getSelected() {
        return selected;
    }

    public void setSelected(EnfermedadesCie10Med selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EnfermedadesCie10MedFacade getFacade() {
        return ejbFacade;
    }
public void inicirObjetoEnfermedad() {
        selected = new EnfermedadesCie10Med();
        selected.setEstado(true);
    }

   public void seleccionarEnfermedades(EnfermedadesCie10Med enfermedades) {
        this.selected = enfermedades;
        //this.tipoUsuario = ejbTipoUsuario.find(this.ejbGrupoUsuarioTipo.buscarPorNombreUsuario(usuarioSistema.getLogin()).get(0).getTipoUsuario());
//        if(alergenoMed.getGrupoUsuarioTipoCollection().toArray().length>0){
//            GrupoUsuarioTipo grupoUsuarioTipo = (GrupoUsuarioTipo) usuarioSistema.getGrupoUsuarioTipoCollection().toArray()[0];
//            this.tipoUsuario = grupoUsuarioTipo.getTipoUsuario();
//        }
        
    }

    
     public String getDatoBusqueda() {
        return datoBusqueda;
    }

    public void setDatoBusqueda(String datoBusqueda) {
        this.datoBusqueda = datoBusqueda;
    }
    
    public void buscarDiagnostico() {
        this.items = ejbFacade.buscarDiagnostico(this.datoBusqueda.toLowerCase());
    }

    public EnfermedadesCie10Med prepareCreate() {
        selected = new EnfermedadesCie10Med();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleEnfermedadesCie10").getString("EnfermedadesCie10MedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleEnfermedadesCie10").getString("EnfermedadesCie10MedUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleEnfermedadesCie10").getString("EnfermedadesCie10MedDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<EnfermedadesCie10Med> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleEnfermedadesCie10").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleEnfermedadesCie10").getString("PersistenceErrorOccured"));
            }
        }
    }

    public EnfermedadesCie10Med getEnfermedadesCie10Med(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<EnfermedadesCie10Med> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<EnfermedadesCie10Med> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public void inicializar(){
 this.selected= new EnfermedadesCie10Med();
}

    @FacesConverter(forClass = EnfermedadesCie10Med.class)
    public static class EnfermedadesCie10MedControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EnfermedadesCie10MedController controller = (EnfermedadesCie10MedController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "enfermedadesCie10MedController");
            return controller.getEnfermedadesCie10Med(getKey(value));
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
            if (object instanceof EnfermedadesCie10Med) {
                EnfermedadesCie10Med o = (EnfermedadesCie10Med) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EnfermedadesCie10Med.class.getName()});
                return null;
            }
        }

    }

}
