package com.unicauca.divsalud.managedbeans;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import com.unicauca.divsalud.entidades.*;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.PaginationHelper;
import com.unicauca.divsalud.sessionbeans.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@Named("examenFisicoController")
@SessionScoped
@ManagedBean
public class ExamenFisicoController implements Serializable {

    private static final String NORMAL = "Normal";
    private static final String ANORMAL = "Anormal";
    
    private static final String SI = "Si";
    private static final String NO = "No";
    
    private static final String SUBNORMAL = "Subnormal <19.8";
    private static final String NORMAL_IMC = "Normal 19.8 a 26";
    private static final String SOBREPESO = "Sobrepeso >26 a 29";
    private static final String OBESIDAD = "Obesidad > de 29";
    
    private static final double SUP_SUBNORMAL = 19.8;
    private static final double SUP_NORMAL = 26;
    private static final double SUP_SOBREPESO = 29;
    
    private ExamenFisicoSexual actual;
    private DataModel items = null;
    @EJB
    private ExamenFisicoSexualFacade examen_fisico_facade;
    @EJB
    private TipoExamenFisicoSexualFacade tipo_examen_facade;
    @EJB
    private ExamenTipoResultadoSexualFacade examen_tipo_resultado_facade;
    @EJB
    private HistoriaModuloSexualidadFacade hms_facade;
        
    private PaginationHelper pagination;
    private int selectedItemIndex;
    /**
     * Clasificación del IMC
     */
    private String clasificacion_imc;
    
    
    /**
     * Resultados de los examenes del examen físico
     */
    private List<ExamenTipoResultadoSexual> resultados_examenes;
    
    /**
     * Resultados de los examenes del examen físico
     */
    private List<TipoExamenFisicoSexual> examenes;
    
    private OtroTipoExamenFisicoSexual otro_examen;
    
    private boolean viendo;
    
    public ExamenFisicoController() {
        viendo = false;
    }
    @PostConstruct
    public void init() {
        limpiar();
    }

    public boolean isViendo() {
        return viendo;
    }

    public void setViendo(boolean viendo) {
        this.viendo = viendo;
    }
    
    
    
    public OtroTipoExamenFisicoSexual getOtro_examen() {
        if(otro_examen==null)
            otro_examen = new OtroTipoExamenFisicoSexual();
        return otro_examen;
    }

    public void setOtro_examen(OtroTipoExamenFisicoSexual otro_examen) {
        this.otro_examen = otro_examen;
    }

    
    
    public void agregar_otro_examen()
    {
        System.out.println("otro: "+otro_examen.getNombreExamen());
    }
    
    public ExamenFisicoSexual getSelected() {
        if (actual == null) {
            actual = new ExamenFisicoSexual();
            selectedItemIndex = -1;
        }
        return actual;
    }

        
    private ExamenFisicoSexualFacade getFacade() {
        return examen_fisico_facade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String getClasificacion_imc() {
        return clasificacion_imc;
    }

    public void setClasificacion_imc(String clasificacion_imc) {
        this.clasificacion_imc = clasificacion_imc;
    }

   

    public List<TipoExamenFisicoSexual> getExamenes() {
        if(examenes == null)
        {
            examenes = tipo_examen_facade.findAll();
        }
        return examenes;
    }

    public void setExamenes(List<TipoExamenFisicoSexual> examenes) {
        this.examenes = examenes;
    }
    
    
    
    
    
    
    public String prepareView() {
        actual = (ExamenFisicoSexual) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        actual = new ExamenFisicoSexual();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(actual);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenFisicoSexualCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        actual = (ExamenFisicoSexual) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(actual);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenFisicoSexualUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        actual = (ExamenFisicoSexual) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(actual);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenFisicoSexualDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            actual = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public ExamenFisicoSexual getActual() {
        return actual;
    }

    public void setActual(ExamenFisicoSexual actual) {
        this.actual = actual;
    }
    
    
    
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(examen_fisico_facade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(examen_fisico_facade.findAll(), true);
    }

    public ExamenFisicoSexual getExamenFisicoSexual(java.lang.Long id) {
        return examen_fisico_facade.find(id);
    }
    
    public void calcular_imc()
    {
        try
        {
            double peso = actual.getPeso().doubleValue();
            double talla = actual.getTalla().doubleValue();
            if(peso>0 && talla>0)
            {
               double imc = peso / (Math.pow(talla, 2));
               actual.setImc(new BigDecimal(imc));
               clasificar();
            }
            
        }
        catch(Exception e)
        {
            
        }
    }
    private void clasificar()
    {
        double imc = actual.getImc().doubleValue();
        if(imc<SUP_SUBNORMAL)
            clasificacion_imc = SUBNORMAL;
        else if(imc<=SUP_NORMAL)
            clasificacion_imc = NORMAL;
        else if(imc<SUP_SOBREPESO)
            clasificacion_imc = SOBREPESO;
        else
            clasificacion_imc = OBESIDAD;
    }
    public List<ExamenTipoResultadoSexual> getResultados_examenes() {
        
        if(resultados_examenes==null)
        {
            
            resultados_examenes = new ArrayList<>();
            
            List<TipoExamenFisicoSexual> examenes = tipo_examen_facade.findAll();
            
            for(TipoExamenFisicoSexual e: examenes)
            {
                ExamenTipoResultadoSexual resultado = new ExamenTipoResultadoSexual();
                resultado.setTipoExamenFisicoSexual(e);
                resultado.setExamenFisicoSexual(actual);
                resultado.setResultadoExamenFisico(NORMAL);
                resultados_examenes.add(resultado);
            }
            actual.setExamenTipoResultadoSexualList(resultados_examenes);
            
        }
        return resultados_examenes;
    }

    public void setResultados_examenes(List<ExamenTipoResultadoSexual> resultados_examenes) {
        this.resultados_examenes = resultados_examenes;
    }
    public void cargarEnfermedades()
    {
        if(tipo_examen_facade==null)
            System.out.println("nulo");
        else
            System.out.println("no nulo");
        
    }
    public void limpiar()
    {
        viendo = false;
        actual = new ExamenFisicoSexual();
        actual.setOtrosHallazgos("");
        getResultados_examenes();
        resultados_examenes = null;
        clasificacion_imc="";
    }
    
    public void registrar(int id_historia_modulo_sexualidad, boolean es_control)
    {
        try
        {
           if(!es_control)
           {
               HistoriaModuloSexualidad h = hms_facade.find(id_historia_modulo_sexualidad);
               actual.setId(h);
           }

           actual.setExamenTipoResultadoSexualList(null);
           examen_fisico_facade.create(actual);
           examen_fisico_facade.flush();

           for(ExamenTipoResultadoSexual e:resultados_examenes)
            {
                ExamenTipoResultadoSexualPK pk = new ExamenTipoResultadoSexualPK();
                pk.setIdExamen(e.getTipoExamenFisicoSexual().getIdExamen());
                System.out.println("PK Examen físico: "+actual.getIdExamenFisicoSexual());
                pk.setIdExamenFisicoSexual(actual.getIdExamenFisicoSexual());
                e.setExamenTipoResultadoSexualPK(pk);
                e.setFechaExamenFisico(new Date());
                e.setExamenFisicoSexual(actual);
                examen_tipo_resultado_facade.create(e);
            }
        }
       catch (EJBException e) {
            @SuppressWarnings("ThrowableResultIgnored")
            Exception cause = e.getCausedByException();
            if (cause instanceof ConstraintViolationException) {
                @SuppressWarnings("ThrowableResultIgnored")
                ConstraintViolationException cve = (ConstraintViolationException) e.getCausedByException();
                for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<? extends Object> v = it.next();
                    System.err.println(v);
                    System.err.println("==>>"+v.getMessage());
                }
            }
        //Assert.fail("ejb exception");
        }
           
        limpiar();
    }
    
    public void cargar_examen_fisicoIDPaciente(int  id)
    {
        this.actual = this.examen_fisico_facade.findById( id);
        this.viendo = true;
        clasificar();
        resultados_examenes = examen_tipo_resultado_facade.findByIdExamenFisicoSexual(actual.getIdExamenFisicoSexual());
        //System.out.println("-------------->"+resultados_examenes);
    }
    public void cargar_examen_fisico(ExamenFisicoSexual e)
    {
        actual = e;
        cargar();
    }
    
    public void cargar()
    {
        clasificar();
        resultados_examenes = actual.getExamenTipoResultadoSexualList();
    }
    @FacesConverter(forClass = ExamenFisicoSexual.class)
    public static class ExamenFisicoSexualControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ExamenFisicoController controller = (ExamenFisicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "examenFisicoController");
            return controller.getExamenFisicoSexual(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ExamenFisicoSexual) {
                ExamenFisicoSexual o = (ExamenFisicoSexual) object;
                return getStringKey(o.getIdExamenFisicoSexual());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ExamenFisicoSexual.class.getName());
            }
        }

    }
    



}