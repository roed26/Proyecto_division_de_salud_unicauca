package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.AlergenoMed;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.AlergenoMedFacade;

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

@Named("alergenoMedController")
@SessionScoped
public class AlergenoMedController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.AlergenoMedFacade ejbFacade;
    private List<AlergenoMed> items = null;
    private AlergenoMed selected;
    private String datoBusqueda;


    public AlergenoMedController() {
    }

    public AlergenoMed getSelected() {
        return selected;
    }

    public void setSelected(AlergenoMed selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AlergenoMedFacade getFacade() {
        return ejbFacade;
    }
    
     public String getDatoBusqueda() {
        return datoBusqueda;
    }

    public void setDatoBusqueda(String datoBusqueda) {
        this.datoBusqueda = datoBusqueda;
    }
    
    public void buscarAlergeno() {
        this.items = ejbFacade.buscarAlergeno(this.datoBusqueda.toLowerCase());
    }


    /*public AlergenoMed prepareCreate() {
        selected = new AlergenoMed();
        initializeEmbeddableKey();
        return selected;
    }
*/

public void seleccionarAlergeno(AlergenoMed alergenoMed) {
        this.selected = alergenoMed;
        //this.tipoUsuario = ejbTipoUsuario.find(this.ejbGrupoUsuarioTipo.buscarPorNombreUsuario(usuarioSistema.getLogin()).get(0).getTipoUsuario());
//        if(alergenoMed.getGrupoUsuarioTipoCollection().toArray().length>0){
//            GrupoUsuarioTipo grupoUsuarioTipo = (GrupoUsuarioTipo) usuarioSistema.getGrupoUsuarioTipoCollection().toArray()[0];
//            this.tipoUsuario = grupoUsuarioTipo.getTipoUsuario();
//        }
        
    }
    
    public void inicirObjetoAlergeno() {
        selected = new AlergenoMed();
        selected.setEstado(true);
    }


    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleAlergeno").getString("AlergenoMedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleAlergeno").getString("AlergenoMedUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleAlergeno").getString("AlergenoMedDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AlergenoMed> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleAlergeno").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleAlergeno").getString("PersistenceErrorOccured"));
            }
        }
    }

    public AlergenoMed getAlergenoMed(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AlergenoMed> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AlergenoMed> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public void inicializar() {
        this.selected = new AlergenoMed();
    }

    @FacesConverter(forClass = AlergenoMed.class)
    public static class AlergenoMedControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AlergenoMedController controller = (AlergenoMedController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "alergenoMedController");
            return controller.getAlergenoMed(getKey(value));
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
            if (object instanceof AlergenoMed) {
                AlergenoMed o = (AlergenoMed) object;
                return getStringKey(o.getIdx());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AlergenoMed.class.getName()});
                return null;
            }
        }

    }

}
