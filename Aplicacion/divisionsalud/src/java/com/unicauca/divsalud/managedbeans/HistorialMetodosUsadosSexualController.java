/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import com.unicauca.divsalud.entidades.Facultad;
import com.unicauca.divsalud.entidades.HistoriaModuloSexualidad;
import com.unicauca.divsalud.entidades.HistorialMetodosUsadosSexual;
import com.unicauca.divsalud.entidades.HistorialMetodosUsadosSexualPK;
import com.unicauca.divsalud.entidades.InformacionAntecedentePacienteSexual;
import com.unicauca.divsalud.entidades.InformacionAntecedentePacienteSexualPK;
import com.unicauca.divsalud.entidades.MetodoPlanificacion;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.sessionbeans.FacultadFacade;
import com.unicauca.divsalud.sessionbeans.HistoriaModuloSexualidadFacade;
import com.unicauca.divsalud.sessionbeans.HistorialMetodosUsadosSexualFacade;
import com.unicauca.divsalud.sessionbeans.MetodoPlanificacionFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lenovo one
 */
@Named(value = "historialMetodosUsadosSexualController")
@SessionScoped

public class HistorialMetodosUsadosSexualController implements Serializable {


    @EJB
    private HistorialMetodosUsadosSexualFacade ejbHistorialMetodosFacade;
    @EJB
    private com.unicauca.divsalud.sessionbeans.MetodoPlanificacionFacade ejbFacadeMetodo;
    @EJB
    HistoriaModuloSexualidadFacade historiaModuloSexualidadFacade;
  
    private List<HistorialMetodosUsadosSexual> items = null;
    private List<MetodoPlanificacion> metodosPlanificacion = null;
    private Short idmetodoPlanificacion;
    private HistorialMetodosUsadosSexual selected;
    private String tiempoUso;
    private String razonSuspension;
    private String nombreComercial;
    private String problemasMetodo;
    private int idAsiganar;
    boolean viendo;

    public boolean isViendo() {
        return viendo;
    }

    public void setViendo(boolean viendo) {
        this.viendo = viendo;
    }
    
    public MetodoPlanificacionFacade getEjbFacadeMetodo() {
        if (this.ejbFacadeMetodo == null) {
            this.ejbFacadeMetodo = new MetodoPlanificacionFacade();
        }
        return this.ejbFacadeMetodo;
    }

    public void setEjbFacadeMetodo(MetodoPlanificacionFacade ejbFacadeMetodo) {
        this.ejbFacadeMetodo = ejbFacadeMetodo;
    }

    public List<MetodoPlanificacion> getMetodosPlanificacion() {
        if (metodosPlanificacion == null) {

            this.metodosPlanificacion = getEjbFacadeMetodo().findAll();
        }

        System.out.println("Metodos de planificacion traidos" + metodosPlanificacion.size());
        return metodosPlanificacion;
    }

    public void cambioMetodo(HistorialMetodosUsadosSexual item, Short id) {
        System.out.println(" se invoco");

    }

    public void setMetodosPlanificacion(List<MetodoPlanificacion> metodosPlanificacion) {
        this.metodosPlanificacion = metodosPlanificacion;
    }

    private List<String> rangosTiempoUso;

    /**
     * Creates a new instance of HistorialMetodosUsadosSexualController
     */
    public HistorialMetodosUsadosSexualController() {

    }

    public String getProblemasMetodo() {
        if (problemasMetodo==null) problemasMetodo = "";
        return problemasMetodo;
    }

    public void setProblemasMetodo(String problemasMetodo) {
        System.out.println("Se invoco problemas metodo" + problemasMetodo);
        this.problemasMetodo = problemasMetodo;
    }
    
    public HistorialMetodosUsadosSexual getSelected() {
        return selected;
    }

    public void setSelected(HistorialMetodosUsadosSexual selected) {
        System.out.println(" se invoco selected");
        this.selected = selected;
    }

    public HistorialMetodosUsadosSexualFacade getFacade() {
        return ejbHistorialMetodosFacade;
    }

    public void setFacade(HistorialMetodosUsadosSexualFacade ejbFacade) {
        this.ejbHistorialMetodosFacade = ejbFacade;
    }

    public List<HistorialMetodosUsadosSexual> getItems() {
        if (items == null) {
            items = new ArrayList();
        }
        return items;
    }

    public HistorialMetodosUsadosSexualFacade getEjbFacade() {
        return ejbHistorialMetodosFacade;
    }

    public void setEjbFacade(HistorialMetodosUsadosSexualFacade ejbFacade) {
        this.ejbHistorialMetodosFacade = ejbFacade;
    }

    public Short getIdmetodoPlanificacion() {
        return idmetodoPlanificacion;
    }

    public void setIdmetodoPlanificacion(Short idmetodoPlanificacion) {
        System.out.println(" Se incoco set id metodo planificacion: " + idmetodoPlanificacion);
        this.idmetodoPlanificacion = idmetodoPlanificacion;
    }

    public void setRangosTiempoUso(List<String> rangosTiempoUso) {
        this.rangosTiempoUso = rangosTiempoUso;
    }

    public String getTiempoUso() {

        return tiempoUso;
    }

    public void setTiempoUso(String tiempoUso) {
        System.out.println("se invico set tiempo uso" + tiempoUso);
        this.tiempoUso = tiempoUso;
    }

    public String getRazonSuspension() {
        if (razonSuspension == null) razonSuspension = "";
        return razonSuspension;
    }

    public void setRazonSuspension(String razonSuspension) {
        System.out.println("se invocó set razon suspension" + razonSuspension);
        this.razonSuspension = razonSuspension;
    }

    public String getNombreComercial() {
        if (nombreComercial==null) nombreComercial = "";
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        System.out.println("se invocó set nobre comercial" + nombreComercial);
        this.nombreComercial = nombreComercial;
    }

    public void setItems(List<HistorialMetodosUsadosSexual> items) {
        System.out.println("se invocó set items");
        this.items = items;
    }

    public void addRegistroHistorial() {
        System.out.println("Se invoco el metodo addRegistro Historial: ");

        HistorialMetodosUsadosSexual nuevo_registro = prepareCreate();
       

        idAsiganar++;

        if (nombreComercial.isEmpty()) {
            this.nombreComercial = "No especificado";
        }
        
        if (problemasMetodo.isEmpty()) {
            this.problemasMetodo = "Sin Descripción";

        }
        if (razonSuspension.isEmpty()) {
            this.razonSuspension = "Sin Descripción";

        }

        if (problemasMetodo.trim().length() == 0) {
            this.problemasMetodo = "Sin Descripción";

        }
        if (razonSuspension.trim().length() == 0) {
            this.razonSuspension = "Sin Descripción";

        }

        MetodoPlanificacion metodo = ejbFacadeMetodo.find(idmetodoPlanificacion);

        boolean estaMetodo=verificarMetodoEnLista(idmetodoPlanificacion);
        
        if(estaMetodo)
        {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('MetodoNoAgregado').show()");
          
            return;
        }
        
        nuevo_registro.setNombreComercial(nombreComercial);
        nuevo_registro.setProblemasMetodo(problemasMetodo);
        nuevo_registro.setRazonSuspension(razonSuspension);
        nuevo_registro.setTiempoUtilizacion(getIntTiempoUso(tiempoUso));
        nuevo_registro.setMetodoPlanificacion(metodo);
        this.items = getItems();

        this.items.add(nuevo_registro);
        limpiarCamposForm();

    }
    
    
     public void limpiarCamposForm()
    {
        
        this.problemasMetodo = "";
        this.razonSuspension = "";
        this.nombreComercial = "";
        this.tiempoUso = "";
        
    }
    
    
    public void limpiarCampos()
    {
        this.items = new ArrayList();
        this.problemasMetodo = "";
        this.razonSuspension = "";
        this.nombreComercial = "";
        this.tiempoUso = "";
        this.viendo=false;
    }
    
    
    private boolean verificarMetodoEnLista(Short idmetodoPlanificacion) {
        for (HistorialMetodosUsadosSexual item : items) {
            if(Objects.equals(item.getMetodoPlanificacion().getIdMetodoPlanificacion(), idmetodoPlanificacion)
                    && item.getNombreComercial().equalsIgnoreCase(nombreComercial)){
                return true;
            }
        }
        
        return false;
    }

    
    

    public void removeRegistroHistorial(MetodoPlanificacion metodo) {
        System.out.println(" SE INVOCÓ REMOVER");
        System.out.println("selected " + metodo.getNombreMetodo());

        for (int i = 0; i < items.size(); i++) {
           
            
             if (Objects.equals(items.get(i).getMetodoPlanificacion().getIdMetodoPlanificacion(), metodo.getIdMetodoPlanificacion()))
             {  
                items.remove(i);
             }
        }

    }

    
  
    
    public List<String> getRangosTiempoUso() {
        if (rangosTiempoUso == null) {
            rangosTiempoUso = new ArrayList();

            rangosTiempoUso.add("Menos de 1 año");
            rangosTiempoUso.add("Entre 1 y 2 años");
            rangosTiempoUso.add("Entre 3 y 4 años");
            rangosTiempoUso.add("Más de 4 años");
        }

        return rangosTiempoUso;
    }

    public Integer getIntTiempoUso(String valor) {

        switch (valor) {
            case "Menos de 1 año":
                return 0;
            case "Entre 1 y 2 años":
                return 1;
            case "Entre 3 y 4 años":
                return 2;
            default:
                return 3;

        }

    }

    public String getStringTiempoUso(Integer valor) {
        switch (valor) {
            case 0:
                return "Menos de 1 año";
            case 1:
                return "Entre 1 y 2 años";
            case 2:
                return "Entre 3 y 4 años";
            default:
                return "Más de 4 años";

        }

    }

    public MetodoPlanificacion buscarMetodo(Short idMetodo) {

        return ejbFacadeMetodo.find(idMetodo);
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public HistorialMetodosUsadosSexual prepareCreate() {
        selected = new HistorialMetodosUsadosSexual();
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
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("HistorialMetodosUsadosSexualCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("HistorialMetodosUsadosSexualUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("HistorialMetodosUsadosSexualDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void Registrar(int id_modulo_historia_sexualidad) {
        try {

            System.out.println("el id que llego " + id_modulo_historia_sexualidad);
            for (int itemx = 0; itemx < items.size(); itemx++) {
                HistorialMetodosUsadosSexual registro = items.get(itemx);
               
                
               
                
                if (historiaModuloSexualidadFacade==null) 
                {
                    System.out.println("facade historia nula ");
                    return;
                }
                HistoriaModuloSexualidad historia= historiaModuloSexualidadFacade.find(id_modulo_historia_sexualidad);
                
                if(historia==null)
                {
                    System.out.println("historia null");
                    return;
                }
                
                 registro.setHistoriaModuloSexualidad(historiaModuloSexualidadFacade.find(id_modulo_historia_sexualidad));

                
                 if(registro.getHistoriaModuloSexualidad()==null)
                {
                    System.out.println("historia null");
                }
                 else
                 {
                      System.out.println("historia encontrada" + registro.getHistoriaModuloSexualidad().getId());
                 }
                
                if(registro.getMetodoPlanificacion()==null)
                {
                    System.out.println("metodo null");
                }
                else
                {
                    System.out.println("el metodo de planificacion" + registro.getMetodoPlanificacion().getIdMetodoPlanificacion());
                }
                HistorialMetodosUsadosSexualPK pk = new HistorialMetodosUsadosSexualPK(registro.getHistoriaModuloSexualidad().getId(), registro.getMetodoPlanificacion().getIdMetodoPlanificacion());
                registro.setHistorialMetodosUsadosSexualPK(pk);
                this.ejbHistorialMetodosFacade.create(registro);
                System.out.println("create metodo usado");
            }

        } catch (EJBException e) {
            @SuppressWarnings("ThrowableResultIgnored")
            Exception cause = e.getCausedByException();
            if (cause instanceof ConstraintViolationException) {
                @SuppressWarnings("ThrowableResultIgnored")
                ConstraintViolationException cve = (ConstraintViolationException) e.getCausedByException();
                for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<? extends Object> v = it.next();
                    System.err.println(v);
                    System.err.println("==>>" + v.getMessage());
                }
            }
        }

    }

 public void cargarMetodosUsados(int id ){
     this.items = this.ejbHistorialMetodosFacade.findById(id);
     this.viendo = true;
 }


}
