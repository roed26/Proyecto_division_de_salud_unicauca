package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.Municipio;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.MunicipioFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;

@Named("municipioController")
@SessionScoped
public class MunicipioController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.MunicipioFacade ejbFacade;
    private List<Municipio> items = null;
    private List<Municipio> listaMunicipioNacimiento;
    private List<Municipio> listaMunicipioResidencia;
    private Municipio selected;

    public MunicipioController() {
        listaMunicipioNacimiento = new ArrayList<>();
        listaMunicipioResidencia = new ArrayList<>();
    }
    
    @PostConstruct
    public void init(){
     listaMunicipioNacimiento= ejbFacade.findAll();
     listaMunicipioResidencia=ejbFacade.findAll();
             
    }

    public Municipio getSelected() {
        return selected;
    }

    public void setSelected(Municipio selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public List<Municipio> getListaMunicipioNacimiento() {
        return listaMunicipioNacimiento;
    }

    public void setListaMunicipioNacimiento(List<Municipio> listaMunicipioNacimiento) {
        this.listaMunicipioNacimiento = listaMunicipioNacimiento;
    }

    public List<Municipio> getListaMunicipioResidencia() {
        return listaMunicipioResidencia;
    }

    public void setListaMunicipioResidencia(List<Municipio> listaMunicipioResidencia) {
        this.listaMunicipioResidencia = listaMunicipioResidencia;
    }

    private MunicipioFacade getFacade() {
        return ejbFacade;
    }

    public Municipio prepareCreate() {
        selected = new Municipio();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MunicipioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MunicipioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MunicipioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Municipio> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void seleccionDepartamentoResidencia(ValueChangeEvent e) {

        String departamentoSeleccionado = e.getNewValue().toString();

        switch (departamentoSeleccionado) {
            case "1":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(1);
                break;
            case "2":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(2);
                break;
            case "3":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(3);
                break;
            case "4":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(4);
                break;
            case "5":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(5);
                break;
            case "6":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(6);
                break;
            case "7":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(7);
                break;
            case "8":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(8);
                break;
            case "9":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(9);
                break;
            case "10":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(10);
                break;
            case "11":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(11);
                break;
            case "12":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(12);
                break;
            case "13":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(13);
                break;
            case "14":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(14);
                break;
            case "15":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(15);
                break;
            case "16":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(16);
                break;
            case "17":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(17);
                break;
            case "18":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(18);
                break;
            case "19":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(19);
                break;
            case "20":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(20);
                break;
            case "21":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(21);
                break;
            case "22":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(22);
                break;
            case "23":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(23);
                break;
            case "24":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(24);
                break;
            case "25":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(25);
                break;
            case "26":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(26);
                break;
            case "27":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(27);
                break;
            case "28":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(28);
                break;
            case "29":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(29);
                break;
            case "30":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(30);
                break;
            case "31":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(31);
                break;
            case "32":
                listaMunicipioResidencia = getFacade().buscarPorIdDepartamento(32);
                break;

        }

    }

    public void seleccionDepartamentoNacimiento(ValueChangeEvent e) {

        String departamentoSeleccionado = e.getNewValue().toString();

        switch (departamentoSeleccionado) {
            case "1":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(1);
                break;
            case "2":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(2);
                break;
            case "3":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(3);
                break;
            case "4":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(4);
                break;
            case "5":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(5);
                break;
            case "6":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(6);
                break;
            case "7":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(7);
                break;
            case "8":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(8);
                break;
            case "9":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(9);
                break;
            case "10":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(10);
                break;
            case "11":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(11);
                break;
            case "12":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(12);
                break;
            case "13":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(13);
                break;
            case "14":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(14);
                break;
            case "15":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(15);
                break;
            case "16":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(16);
                break;
            case "17":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(17);
                break;
            case "18":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(18);
                break;
            case "19":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(19);
                break;
            case "20":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(20);
                break;
            case "21":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(21);
                break;
            case "22":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(22);
                break;
            case "23":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(23);
                break;
            case "24":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(24);
                break;
            case "25":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(25);
                break;
            case "26":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(26);
                break;
            case "27":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(27);
                break;
            case "28":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(28);
                break;
            case "29":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(29);
                break;
            case "30":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(30);
                break;
            case "31":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(31);
                break;
            case "32":
                listaMunicipioNacimiento = getFacade().buscarPorIdDepartamento(32);
                break;

        }

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

    public Municipio getMunicipio(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Municipio> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Municipio> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Municipio.class)
    public static class MunicipioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MunicipioController controller = (MunicipioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "municipioController");
            return controller.getMunicipio(getKey(value));
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
            if (object instanceof Municipio) {
                Municipio o = (Municipio) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Municipio.class.getName()});
                return null;
            }
        }

    }

}
