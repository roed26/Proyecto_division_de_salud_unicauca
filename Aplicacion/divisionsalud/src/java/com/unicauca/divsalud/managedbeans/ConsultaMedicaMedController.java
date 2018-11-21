package com.unicauca.divsalud.managedbeans;

//import com.lowagie.text.pdf.ArabicLigaturizer;
import com.unicauca.divsalud.entidades.AcompanianteMed;
import com.unicauca.divsalud.entidades.AlergenoMed;
import com.unicauca.divsalud.entidades.QuirurgicoMed;
import com.unicauca.divsalud.entidades.HabitosMed;
import com.unicauca.divsalud.entidades.PatologicosMed;
import com.unicauca.divsalud.entidades.GinecostetricosMed;
import com.unicauca.divsalud.entidades.HistoricoGinecostetricos;
import com.unicauca.divsalud.entidades.AntFamiliarConsultaMed;
import com.unicauca.divsalud.entidades.AntFamiliaresMed;
import com.unicauca.divsalud.entidades.CitaMedicaMed;
import com.unicauca.divsalud.entidades.ConsultaAlergenoMed;
import com.unicauca.divsalud.entidades.ConsultaAlergenoMedPK;
import com.unicauca.divsalud.entidades.ConsultaMedicaMed;
import com.unicauca.divsalud.entidades.ConsultaSistemasCuerpoMed;
import com.unicauca.divsalud.entidades.Diagnosticos;
import com.unicauca.divsalud.entidades.DiagnosticosPK;
import com.unicauca.divsalud.entidades.HabitosMedPK;
import com.unicauca.divsalud.entidades.Paciente;
import com.unicauca.divsalud.entidades.PatologicoConsultaMed;
import com.unicauca.divsalud.entidades.ProcedimientosCupsMed;
import com.unicauca.divsalud.entidades.QuirurgicoMed;
import com.unicauca.divsalud.entidades.SistemaCuerpoMed;
import com.unicauca.divsalud.entidades.TipoAlergenoMed;
import com.unicauca.divsalud.entidades.TipoHabito;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.AcompanianteMedFacade;
import com.unicauca.divsalud.sessionbeans.ConsultaMedicaMedFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;

@Named("consultaMedicaMedController")
@SessionScoped
public class ConsultaMedicaMedController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.ConsultaMedicaMedFacade ejbFacade;
    @EJB
    private com.unicauca.divsalud.sessionbeans.AcompanianteMedFacade ejbFacadeAcompaniante;
    @EJB    
    private com.unicauca.divsalud.sessionbeans.AntFamiliarConsultaMedFacade ejbFacadeAntFamiliarConsultaMed;
    @EJB
    private com.unicauca.divsalud.sessionbeans.ConsultaSistemasCuerpoMedFacade ejbFacadeConsultaSistemasCuerpoMed;
    @EJB
    private com.unicauca.divsalud.sessionbeans.DiagnosticosFacade ejbFacadeDiagnosticos;
    
    //Beans facade antecedentes familiares
    @EJB
    private com.unicauca.divsalud.sessionbeans.ConsultaAlergenoMedFacade ejbFacadeConsultaAlergenoMed;        
    @EJB
    private com.unicauca.divsalud.sessionbeans.QuirurgicoMedFacade ejbFacadeQuirurgicoMed;
    @EJB
    private com.unicauca.divsalud.sessionbeans.HabitosMedFacade ejbFacadeHabitosMed;
    @EJB
    private com.unicauca.divsalud.sessionbeans.PatologicoConsultaMedFacade ejbFacadePatologicoConsultaMed;
    @EJB
    private com.unicauca.divsalud.sessionbeans.GinecostetricosMedFacade ejbFacadeGinecostetricosMed;
    @EJB
    private com.unicauca.divsalud.sessionbeans.HistoricoGinecostetricosFacade ejbFacedeHistoricoGinecostetricosMed;
    
    private ConsultaAlergenoMed consultaAlergeno;
    private QuirurgicoMed quirurgico;
    private HabitosMed habitos;
    private PatologicoConsultaMed patologicoConsulta;
    private GinecostetricosMed ginecostetricos;
    private HistoricoGinecostetricos historicoGinecostetricos;
    
    private AlergenoMedController alergenoCon;
    private QuirurgicoMedController quirurgicoCon;
    private HabitosMedController habitosCon;
    private PatologicosMedController patologicosCon;
    private GinecostetricosMedController ginecostetricosCon;
    private HistoricoGinecostetricosController historicoGinecostetricosCon;
    
    /*adiciones para antFamiliares*/
    private List<AntFamiliaresMed> itemsAntFamiliares;
    private AntFamiliaresMedController antFamiliaresCon = new AntFamiliaresMedController();
    private AntFamiliaresMed antFamiliares;
    private AntFamiliarConsultaMed antFamiliarConsulta;
    private AntFamiliarConsultaMedController antFamiliarMedCon = new AntFamiliarConsultaMedController();
    
    private ConsultaSistemasCuerpoMedController consultaSistemasCuerpoMedCon;
    private SistemaCuerpoMedController sistemasCuerpoMedCon;            
    private ConsultaSistemasCuerpoMed consultaSitemasCuerpo;
    
    private List<ConsultaMedicaMed> items = null;
    private ConsultaMedicaMed selected;
    private Paciente paciente;
    private CitaMedicaMed citaMedica;
    private AcompanianteMed acompaniante;

    private DiagnosticosController diagnosticosCon = new DiagnosticosController();
    private Diagnosticos diagnosticos;
    private Diagnosticos diagnosticos_1;
    private Diagnosticos diagnosticos_2;
    private Diagnosticos diagnosticos_3;
    private DiagnosticosPK diagnosticospk;
    private DiagnosticosPK diagnosticospk_1;
    private DiagnosticosPK diagnosticospk_2;
    private DiagnosticosPK diagnosticospk_3;
    
    public ConsultaMedicaMedController() {
    }

    public ConsultaMedicaMed getSelected() {
        return selected;
    }

    public void setSelected(ConsultaMedicaMed selected) {
        this.selected = selected;
    }
    
    public ConsultaSistemasCuerpoMed getConsultaSitemasCuerpo() {
        return consultaSitemasCuerpo;
    }

    public void setConsultaSitemasCuerpo(ConsultaSistemasCuerpoMed consultaSitemasCuerpo) {
        this.consultaSitemasCuerpo = consultaSitemasCuerpo;
    }
    
    public AntFamiliaresMedController getAntFamiliaresCon() {
        return antFamiliaresCon;
    }

    public void setAntFamiliaresCon(AntFamiliaresMedController antFamiliaresCon) {
        this.antFamiliaresCon = antFamiliaresCon;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public CitaMedicaMed getCitaMedica() {
        return citaMedica;
    }

    public void setCitaMedica(CitaMedicaMed citaMedica) {
        this.citaMedica = citaMedica;
    }

    public AcompanianteMed getAcompaniante() {
        return acompaniante;
    }

    public void setAcompaniante(AcompanianteMed acompaniante) {
        this.acompaniante = acompaniante;
    }

    public Diagnosticos getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(Diagnosticos diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public Diagnosticos getDiagnosticos_1() {
        return diagnosticos_1;
    }

    public void setDiagnosticos_1(Diagnosticos diagnosticos_1) {
        this.diagnosticos_1 = diagnosticos_1;
    }

    public Diagnosticos getDiagnosticos_2() {
        return diagnosticos_2;
    }

    public void setDiagnosticos_2(Diagnosticos diagnosticos_2) {
        this.diagnosticos_2 = diagnosticos_2;
    }

    public Diagnosticos getDiagnosticos_3() {
        return diagnosticos_3;
    }

    public void setDiagnosticos_3(Diagnosticos diagnosticos_3) {
        this.diagnosticos_3 = diagnosticos_3;
    }

    public DiagnosticosPK getDiagnosticospk() {
        return diagnosticospk;
    }

    public void setDiagnosticospk(DiagnosticosPK diagnosticospk) {
        this.diagnosticospk = diagnosticospk;
    }

    public DiagnosticosPK getDiagnosticospk_1() {
        return diagnosticospk_1;
    }

    public void setDiagnosticospk_1(DiagnosticosPK diagnosticospk_1) {
        this.diagnosticospk_1 = diagnosticospk_1;
    }

    public DiagnosticosPK getDiagnosticospk_2() {
        return diagnosticospk_2;
    }

    public void setDiagnosticospk_2(DiagnosticosPK diagnosticospk_2) {
        this.diagnosticospk_2 = diagnosticospk_2;
    }

    public DiagnosticosPK getDiagnosticospk_3() {
        return diagnosticospk_3;
    }

    public void setDiagnosticospk_3(DiagnosticosPK diagnosticospk_3) {
        this.diagnosticospk_3 = diagnosticospk_3;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ConsultaMedicaMedFacade getFacade() {
        return ejbFacade;
    }

    public AcompanianteMedFacade getEjbFacadeAcompaniante() {
        return ejbFacadeAcompaniante;
    }

    public void setEjbFacadeAcompaniante(AcompanianteMedFacade ejbFacadeAcompaniante) {
        this.ejbFacadeAcompaniante = ejbFacadeAcompaniante;
    }
    
     public ConsultaSistemasCuerpoMedController getConsultaSistemasCuerpoMedCon() {
        return consultaSistemasCuerpoMedCon;
    }

    public void setConsultaSistemasCuerpoMedCon(ConsultaSistemasCuerpoMedController consultaSistemasCuerpoMedCon) {
        this.consultaSistemasCuerpoMedCon = consultaSistemasCuerpoMedCon;
    }  

    public ConsultaMedicaMed prepareCreate() {
        selected = new ConsultaMedicaMed();
        initializeEmbeddableKey();
        return selected;
    }
    //NO UTILIZADO
    public void create(CargarVistaMedicinaController cargarVista, List<AntFamiliaresMed> item) {
        ejbFacadeAcompaniante.create(acompaniante);        
        selected.setAcompanianteMedIdx(acompaniante);
        selected.setPacienteIdx(paciente);
        ejbFacade.create(selected);
        //Diagnostico
        diagnosticospk.setIdxConsulta(selected.getIdx());
        diagnosticos.setDiagnosticosPK(diagnosticospk);
        ejbFacadeDiagnosticos.create(diagnosticos);        
        /*
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleConsultaMedicaMed").getString("ConsultaMedicaMedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }*/
        this.guardarAntecedentes(selected,item);  
        cargarVista.cargarGestionarAgenda();       
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleConsultaMedicaMed").getString("ConsultaMedicaMedUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleConsultaMedicaMed").getString("ConsultaMedicaMedDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ConsultaMedicaMed> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleConsultaMedicaMed").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleConsultaMedicaMed").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ConsultaMedicaMed getConsultaMedicaMed(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ConsultaMedicaMed> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ConsultaMedicaMed> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }   

    private void guardarAntecedentesPersonales(ConsultaMedicaMed select, List<TipoHabito> itemsHabitos, List<PatologicosMed> itemsPatologicos) {
        this.guardarAlergicos(select);
        this.guardarQuirurgicos(select);
        this.guardarHabitos(select, itemsHabitos);
        this.guardarPatologicos(select, itemsPatologicos);
    }

    private void guardarAlergicos(ConsultaMedicaMed select) {                
//                if(this.listaAlergenos.isEmpty()){
//                    if(!this.preguntadoNegado.equals("")){
//                        consultaAlergeno.setObservaciones(this.preguntadoNegado);
//                        consultaAlergeno.setConsultaAlergenoMedPK(new ConsultaAlergenoMedPK(select.getIdx(),lstAlergenos.getAlergeno().getIdx()));
//                    }
//                }
        for (UtilidadesAntecedentesPersonalesAlergicos lstAlergenos : listaAlergenos) {                    
            consultaAlergeno.setObservaciones("");            
            consultaAlergeno.setConsultaAlergenoMedPK(new ConsultaAlergenoMedPK(select.getIdx(),lstAlergenos.getAlergeno().getIdx()));

            ejbFacadeConsultaAlergenoMed.create(consultaAlergeno);
            System.out.println("antecedentes personales - alergicos almacenados satisefactoriamente");
            consultaAlergeno = new ConsultaAlergenoMed();
        }
        listaAlergenos = new ArrayList<>();
        preguntadoNegado = "";
        tipoAlergenoSelect = null;
        alergenoSelec = null;
    }

    private void guardarQuirurgicos(ConsultaMedicaMed select) {
        for (ProcedimientosCupsMed lstProc : listaProcedimientos) {
            quirurgico.setConsultaMedicaMedIdx(selected);
            quirurgico.setObservaciones("");
            quirurgico.setProcedimientosCupsMedCodigo(lstProc);
            
            ejbFacadeQuirurgicoMed.create(quirurgico);
            System.out.println("antecedentes personales - quirurgicos almacenados satisefactoriamente");
            quirurgico = new QuirurgicoMed();
        }
        if(this.listaProcedimientos.isEmpty()){
            quirurgico.setConsultaMedicaMedIdx(selected);
            if(preguntadoNegadoQuirurgico != null)
                quirurgico.setObservaciones(this.preguntadoNegadoQuirurgico);
            else
                quirurgico.setObservaciones("");
            
            System.out.println("antecedentes personales - quirurgicos, vacio, almacenados satisefactoriamente");
            ejbFacadeQuirurgicoMed.create(quirurgico);
            quirurgico = new QuirurgicoMed();
        }
        listaProcedimientos = new ArrayList<>();
        preguntadoNegadoQuirurgico = "";    
        procedimientoSelec = null;
                
    }

    private void guardarHabitos(ConsultaMedicaMed select, List<TipoHabito> itemsHabitos) {        
        if(habitosAlcohol != 0){            
            habitos.setConsumeSiNoEx(habitosAlcohol);
            habitos.setDescripcion(observacionesConsumeAlcohol);
            for (TipoHabito itemsHabito : itemsHabitos) {
                if(itemsHabito.getNombre().equals("ALCOHOL")){
                    habitos.setHabitosMedPK(new HabitosMedPK(itemsHabito.getId(),select.getIdx()));
                    break;
                }
            }            
            ejbFacadeHabitosMed.create(habitos);
            habitosAlcohol = 0;
            observacionesConsumeAlcohol = "";
            habitos = new HabitosMed();
        }
        
        if(habitosTabaco != 0){
            habitos.setConsumeSiNoEx(habitosTabaco);
            habitos.setDescripcion(observacionesConsumeTabaco);
            for (TipoHabito itemsHabito : itemsHabitos) {
                if(itemsHabito.getNombre().equals("TABACO")){
                    habitos.setHabitosMedPK(new HabitosMedPK(itemsHabito.getId(),select.getIdx()));
                    break;
                }
            }            
            ejbFacadeHabitosMed.create(habitos);
            habitosTabaco = 0;
            observacionesConsumeTabaco = "";
            habitos = new HabitosMed();
        }
        
        if(habitosDeporte != 0){
            habitos.setConsumeSiNoEx(habitosDeporte);
            habitos.setDescripcion(observacionesDeporte);
            for (TipoHabito itemsHabito : itemsHabitos) {
                if(itemsHabito.getNombre().equals("DEPORTE")){
                    habitos.setHabitosMedPK(new HabitosMedPK(itemsHabito.getId(),select.getIdx()));
                    break;
                }
            }            
            ejbFacadeHabitosMed.create(habitos);
            habitosDeporte = 0;
            observacionesDeporte = "";
            habitos = new HabitosMed();
        }
        
        if(habitosOtros != 0){
            habitos.setConsumeSiNoEx(habitosOtros);            
            for (TipoHabito itemsHabito : itemsHabitos) {
                if(itemsHabito.getNombre().equals("OTROS"))
                    habitos.setHabitosMedPK(new HabitosMedPK(itemsHabito.getId(),select.getIdx()));
                if(habitoOtrosSelec.equals("OTROS"))
                    habitos.setDescripcion(nuevoHabitosOtros);                
                else
                    habitos.setDescripcion(habitoOtrosSelec);
            }
            ejbFacadeHabitosMed.create(habitos);
            habitosOtros = 0;
            habitoOtrosSelec = "";
            habitos = new HabitosMed();
        }                
    }

    private void guardarPatologicos(ConsultaMedicaMed select, List<PatologicosMed> itemsPatologicos) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       

    @FacesConverter(forClass = ConsultaMedicaMed.class)
    public static class ConsultaMedicaMedControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConsultaMedicaMedController controller = (ConsultaMedicaMedController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "consultaMedicaMedController");
            return controller.getConsultaMedicaMed(getKey(value));
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
            if (object instanceof ConsultaMedicaMed) {
                ConsultaMedicaMed o = (ConsultaMedicaMed) object;
                return getStringKey(o.getIdx());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ConsultaMedicaMed.class.getName()});
                return null;
            }
        }

    }
            
    
    public void prepareCreateconsultaMedicaMedPaciente(CitaMedicaMed cita, CargarVistaMedicinaController cargarVista){
        selected = new ConsultaMedicaMed();
        acompaniante = new AcompanianteMed();
        antFamiliarConsulta = new AntFamiliarConsultaMed();
        
        initializeEmbeddableKey();        
        paciente = new Paciente();
        paciente = cita.getPacienteID();
        
        antFamiliares = new AntFamiliaresMed();
        antFamiliaresCon.prepareCreate();
        antFamiliarMedCon.prepareCreate();
        
        consultaSitemasCuerpo = new ConsultaSistemasCuerpoMed();   

        diagnosticos = new Diagnosticos();
        diagnosticos_1 = new Diagnosticos();
        diagnosticos_2 = new Diagnosticos();
        diagnosticos_3 = new Diagnosticos();
        diagnosticospk = new DiagnosticosPK();
        diagnosticospk_1 = new DiagnosticosPK();
        diagnosticospk_2 = new DiagnosticosPK();
        diagnosticospk_3 = new DiagnosticosPK();
        //diagnosticosCon.prepareCreate();
        
        prepararObjetosAntecedentesPersonales();
        prepararObjetosControlador_AntecedentesPersonales();
        
        cargarVista.cargarHistoriaMedicaMed();        
    }    
    
    private void prepararObjetosControlador_AntecedentesPersonales() {
        alergenoCon = new AlergenoMedController();
        quirurgicoCon = new QuirurgicoMedController();
        habitosCon = new HabitosMedController();
        patologicosCon = new PatologicosMedController();
        ginecostetricosCon = new GinecostetricosMedController();
        historicoGinecostetricosCon = new HistoricoGinecostetricosController();      
    }
    
    private void prepararObjetosAntecedentesPersonales() {
        consultaAlergeno = new ConsultaAlergenoMed();
        quirurgico = new QuirurgicoMed();
        habitos = new HabitosMed();
        patologicoConsulta = new PatologicoConsultaMed();
        ginecostetricos = new GinecostetricosMed();
        historicoGinecostetricos = new HistoricoGinecostetricos();                        
    }
    
    public void prepareCreateconsultaMedicaMed(CargarVistaMedicinaController cargarVista){
        selected = new ConsultaMedicaMed();
        acompaniante = new AcompanianteMed();
        antFamiliarConsulta = new AntFamiliarConsultaMed();
        
        initializeEmbeddableKey();       
        
        antFamiliares = new AntFamiliaresMed();
        antFamiliaresCon.prepareCreate();
        antFamiliarMedCon.prepareCreate();
        
        consultaSitemasCuerpo = new ConsultaSistemasCuerpoMed();
        
        prepararObjetosControlador_AntecedentesPersonales();
        prepararObjetosAntecedentesPersonales();
        
//        paciente = new Paciente();
//        paciente = cita.getPacienteID();
        cargarVista.cargarHistoriaMedicaMed();        
    }                    
    
    //funciones para guardar antFamiliares
    public List<AntFamiliaresMed> getItemsAntFamiliares() {
        return itemsAntFamiliares;
    }

    public void setItemsAntFamiliares(List<AntFamiliaresMed> itemsAntFamiliares) {
        this.itemsAntFamiliares = itemsAntFamiliares;
    }

    public AntFamiliaresMed getAntFamiliares() {
        return antFamiliares;
    }

    public void setAntFamiliares(AntFamiliaresMed antFamiliares) {
        this.antFamiliares = antFamiliares;
    }

    public AntFamiliarConsultaMed getAntFamiliarConsulta() {
        return antFamiliarConsulta;
    }

    public void setAntFamiliarConsulta(AntFamiliarConsultaMed antFamiliarConsulta) {
        this.antFamiliarConsulta = antFamiliarConsulta;
    }
    
    
    
    /* METODOS PARA AJAX*/
    List<AntFamiliaresMed> padre = new ArrayList<>(); 
    List<AntFamiliaresMed> abueloP = new ArrayList<>(); 
    List<AntFamiliaresMed> abuelaP = new ArrayList<>(); 
    List<AntFamiliaresMed> madre = new ArrayList<>(); 
    List<AntFamiliaresMed> abueloM = new ArrayList<>(); 
    List<AntFamiliaresMed> abuelaM = new ArrayList<>(); 
    List<AntFamiliaresMed> hermanos = new ArrayList<>();
    
    //variables para almacenar las observaciones en la toa de antecedentes familiares
    List<AntFamiliaresMed> observaciones = new ArrayList();            
    List<String> textoObservaciones = new ArrayList();
    
    //variables para almacenar las observaciones en la toa de sistemas_cuerpo_med
    List<SistemaCuerpoMed> normal = new ArrayList<>(); 
    List<SistemaCuerpoMed> hallazgo = new ArrayList<>();
    
    //variables para almacenar hallazgos en antecedentes de examen fisico
    List<SistemaCuerpoMed> hallazgoList = new ArrayList();
    List<SistemaCuerpoMed> hallazgoListCheck = new ArrayList();
    List<String> textoHallazgo = new ArrayList();
    
    public void guardarPadre(AntFamiliaresMed item){
        System.out.println(item.toString());
        if(!padre.contains(item))
            padre.add(item);
        else
            padre.remove(item);        
    }
    
    public void guardarAbueloP(AntFamiliaresMed item){
        System.out.println(item.getIdx() + " " + item.getNombre());                
        if(!abueloP.contains(item))
            abueloP.add(item);
        else
            abueloP.remove(item);
    }
    
    public void guardarAbuelaP(AntFamiliaresMed item){
        System.out.println(item.toString());                
        if(!abuelaP.contains(item))
            abuelaP.add(item);
        else
            abuelaP.remove(item);
    }
    
    public void guardarMadre(AntFamiliaresMed item){
        System.out.println(item.toString());                
        if(!madre.contains(item))
            madre.add(item);
        else
            madre.remove(item);
    }
    
    public void guardarAbueloM(AntFamiliaresMed item){
        System.out.println(item.toString());                
        if(!abueloM.contains(item))
            abueloM.add(item);
        else
            abueloM.remove(item);
    }
    
    public void guardarAbuelaM(AntFamiliaresMed item){
        System.out.println(item.toString());                
        if(!abuelaM.contains(item))
            abuelaM.add(item);
        else
            abuelaM.remove(item);
    }
    
    public void guardarHermanos(AntFamiliaresMed item){
        System.out.println(item.toString());                
        if(!hermanos.contains(item))
            hermanos.add(item);
        else
            hermanos.remove(item);
    }       
    
    private String valorObs = "";
    public void recibirTexto(AjaxBehaviorEvent evt){
        String texto = "" + ((UIOutput)evt.getSource()).getValue();
        this.valorObs = texto;
        System.out.println("presiono en ant familiares " + texto);
    }
    /*con esta funcion se almacena lo que digito el medico eh indico como observaciones,
    en en la toma de antecedentes familiares, en un array
    para posteriormente ser consultado para almacenar en el modelo
    */
    public void guardarObservaciones(AntFamiliaresMed item){
        if(!observaciones.contains(item)){
            observaciones.add(item);
            textoObservaciones.add(this.valorObs);
        }else{           
            int pos = observaciones.indexOf(item);
            textoObservaciones.set(pos, this.valorObs);
        }        
    }
    
    /*con esta fnucion se recibe lo que el usuario ah digitado*/
    private String valorHallazgo = "";
    public void recibirTextoExamenFisico(AjaxBehaviorEvent evt){
        //en esta variable se almacena lo que digito el usuario
        String texto = "" + ((UIOutput)evt.getSource()).getValue();
        this.valorHallazgo = texto;
        System.out.println("presiono en examen fisico " + texto);
    }
    /*con esta funcion se almacena lo que digito el medico eh indico como hallazgo en un array
    para posteriormente ser consultado para almacenar en el modelo
    */
    public void guardarHallazgo(SistemaCuerpoMed item){
        if(!hallazgoList.contains(item)){
            hallazgoList.add(item);
            textoHallazgo.add(this.valorHallazgo);
        }else{           
            int pos = hallazgoList.indexOf(item);
            textoHallazgo.set(pos, this.valorHallazgo);
        }        
    }
    //METODO DE CREACION UTILIZADO
    public void create(CargarVistaMedicinaController cargarVista,   List<AntFamiliaresMed> itemsAntFamiliares, 
                                                            List<SistemaCuerpoMed> itemSistemasCuerpo,                                                            
                                                            List<TipoHabito> itemsHabitos,
                                                            List<PatologicosMed> itemsPatologicos) {
        System.out.println("EN METODO CREAR 2");
        ejbFacadeAcompaniante.create(acompaniante);        
        selected.setAcompanianteMedIdx(acompaniante);
        selected.setPacienteIdx(paciente);
        BigDecimal imc = selected.getPeso().divide(selected.getTalla(), 1, RoundingMode.HALF_UP);
        selected.setImc(imc);
        //Fijar Fecha Consulta
        selected.setFecha(getCurrentDate());        
        /*
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleConsultaMedicaMed").getString("ConsultaMedicaMedCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }*/
        
        selected.setIdMedico("10300000");
//        ejbFacade.create(selected);//anterior forma de guardar
        selected=ejbFacade.guardar(selected);//nueva forma de guardar la consulta para eliminar problemas al agregar los diagnosticos
        this.guardarAntecedentes(selected, itemsAntFamiliares);  
        this.guardarExamenFisico(selected, itemSistemasCuerpo);
        this.guardarAntecedentesPersonales(selected,itemsHabitos,itemsPatologicos);
        
        System.out.println("selected"+selected.getIdx()+" "+selected.getFecha());
        
        //Agregando diagnosticos
        //Diagnostico principal
        diagnosticospk.setIdxConsulta(selected.getIdx());
        System.out.println("se fijo la consulta al diagnostico PK");
        diagnosticos.setDiagnosticosPK(diagnosticospk);
        System.out.println("Se fijo el diagnostico PK al diagnostico");
        ejbFacadeDiagnosticos.create(diagnosticos);
        System.out.println("Se creo el diagnostico");
        //Diagnostico DX 1
        diagnosticospk_1.setIdxConsulta(selected.getIdx());
        diagnosticos_1.setDiagnosticosPK(diagnosticospk_1);
        ejbFacadeDiagnosticos.create(diagnosticos_1);
        //Diagnostico DX 2
        diagnosticospk_2.setIdxConsulta(selected.getIdx());
        diagnosticos_2.setDiagnosticosPK(diagnosticospk_2);
        ejbFacadeDiagnosticos.create(diagnosticos_2);
        //Diagnostico DX 3
        diagnosticospk_3.setIdxConsulta(selected.getIdx());
        diagnosticos_3.setDiagnosticosPK(diagnosticospk_3);
        ejbFacadeDiagnosticos.create(diagnosticos_3);        
        cargarVista.cargarGestionarAgenda();
    }
    
    private void guardarAntecedentes(ConsultaMedicaMed select, List<AntFamiliaresMed> itemsAntFamiliares) {
        int pos = 0;
        for (AntFamiliaresMed it : itemsAntFamiliares) {
            if(padre.contains(it))
                antFamiliarConsulta.setPadre(1);
            else
                antFamiliarConsulta.setPadre(0);
            
            if(abueloP.contains(it))
                antFamiliarConsulta.setAbueloP(1);
            else
                antFamiliarConsulta.setAbueloP(0);
            
            if(abuelaP.contains(it))
                antFamiliarConsulta.setAbuelaP(1);
            else
                antFamiliarConsulta.setAbuelaP(0);
            
            if(abuelaP.contains(it))
                antFamiliarConsulta.setMadre(1);
            else
                antFamiliarConsulta.setMadre(0);
            
            if(abuelaP.contains(it))
                antFamiliarConsulta.setAbueloM(1);
            else
                antFamiliarConsulta.setAbueloM(0);
            
            if(abuelaP.contains(it))
                antFamiliarConsulta.setAbuelaM(1);
            else
                antFamiliarConsulta.setAbuelaM(0);
            
            if(hermanos.contains(it))
                antFamiliarConsulta.setHermanos(1);
            else
                antFamiliarConsulta.setHermanos(0);
            
            if(observaciones.contains(it)){
                pos = observaciones.indexOf(it);
                antFamiliarConsulta.setObservaciones(textoObservaciones.get(pos));
            }
            else
                antFamiliarConsulta.setObservaciones("");            
           
            antFamiliarConsulta.setAntFamiliaresMedIdx(it);
            antFamiliarConsulta.setConsultaMedicaMedIdx(select);
            
            ejbFacadeAntFamiliarConsultaMed.create(antFamiliarConsulta);
            antFamiliarConsulta = new AntFamiliarConsultaMed();
        }                
    }   
    
    private void guardarExamenFisico(ConsultaMedicaMed selected, List<SistemaCuerpoMed> itemSistemasCuerpo) {
        int pos = 0;
        for (SistemaCuerpoMed it : itemSistemasCuerpo) {
            if(hallazgoListCheck.contains(it)){
                if(hallazgoList.contains(it)){                
                    pos = hallazgoList.indexOf(it);
                    consultaSitemasCuerpo.setObservaciones(textoHallazgo.get(pos));
                    consultaSitemasCuerpo.setEstado(1);
                }else{
                    consultaSitemasCuerpo.setObservaciones("");
                    consultaSitemasCuerpo.setEstado(0);
                }
            }else{
                consultaSitemasCuerpo.setObservaciones("");
                consultaSitemasCuerpo.setEstado(0);
            }
            consultaSitemasCuerpo.setSistemaCuerpoMedIdx(it);
            consultaSitemasCuerpo.setConsultaMedicaMedIdx(selected);
            
            //se crea ingresan los sitemas del cuerpo al modelo
            ejbFacadeConsultaSistemasCuerpoMed.create(consultaSitemasCuerpo);
            //inicializar la variable de sistemasCuerpoMed
            consultaSitemasCuerpo = new ConsultaSistemasCuerpoMed();
        }                
    }
    
    public String getEstadoPaciente(){
        if(paciente.getEstado().equals("1")){
            return "Activo";
        }else{
            return "Inactivo";
        }
    }    

    public void checkBox(SistemaCuerpoMed it){
        System.out.println(it.toString());
        if(hallazgoListCheck.contains(it))
            hallazgoListCheck.remove(it);
        else
            hallazgoListCheck.add(it);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    //ANTECEDENTES PERSONALES
    private String preguntadoNegado = "";
    private String preguntadoNegadoQuirurgico = "";
    private AlergenoMed alergenoSelec;
    private List<AlergenoMed> alergenoItems = null;    
    private TipoAlergenoMed tipoAlergenoSelect = null;
    private String console = "";
    private ProcedimientosCupsMed procedimientoSelec = null;
    private List<UtilidadesAntecedentesPersonalesAlergicos> listaAlergenos = new ArrayList<>();
    private List<ProcedimientosCupsMed> listaProcedimientos = new ArrayList<>();

    public TipoAlergenoMed getTipoAlergenoSelect() {
        return tipoAlergenoSelect;
    }

    public void setTipoAlergenoSelect(TipoAlergenoMed tipoAlergenoSelect) {
        this.tipoAlergenoSelect = tipoAlergenoSelect;
    }
        
    public ProcedimientosCupsMed getProcedimientoSelec() {
        return procedimientoSelec;
    }

    public void setProcedimientoSelec(ProcedimientosCupsMed procedimientoSelec) {
        this.procedimientoSelec = procedimientoSelec;
    }   
        
    public List<ProcedimientosCupsMed> getListaProcedimientos() {
        return listaProcedimientos;
    }

    public void setListaProcedimientos(List<ProcedimientosCupsMed> listaProcedimientos) {
        this.listaProcedimientos = listaProcedimientos;
    }   
    
    public String getPreguntadoNegadoQuirurgico() {
        return preguntadoNegadoQuirurgico;
    }

    public void setPreguntadoNegadoQuirurgico(String preguntadoNegadoQuirurgico) {
        this.preguntadoNegadoQuirurgico = preguntadoNegadoQuirurgico;
    }

    
    public List<UtilidadesAntecedentesPersonalesAlergicos> getListaAlergenos() {
        return listaAlergenos;
    }

    public void setListaAlergenos(List<UtilidadesAntecedentesPersonalesAlergicos> listaAlergenos) {
        this.listaAlergenos = listaAlergenos;
    }   
    
    public String getPreguntadoNegado() {        
        return preguntadoNegado;
    }

    public void setPreguntadoNegado(String preguntadoNegado) {
        this.preguntadoNegado = preguntadoNegado;
    }         

    public AlergenoMed getAlergenoSelec() {
        return alergenoSelec;
    }

    public void setAlergenoSelec(AlergenoMed alergenoSelec) {
        this.alergenoSelec = alergenoSelec;
    }

    public List<AlergenoMed> getAlergenoItems() {
        return alergenoItems;
    }

    public void setAlergenoItems(List<AlergenoMed> alergenoItems) {
        this.alergenoItems = alergenoItems;
    }   
    
    //ALERGENO
    public List<AlergenoMed> buscarAlergeno(String consulta){        
        //this.alergenoItems = getFacade().buscarAlergenoEjb(this.alergenoSelec.toLowerCase(),this.tipoAlergenoSelect.getNombre());
        List<String> alergeno = new ArrayList<>();        
        if(this.tipoAlergenoSelect != null){
                System.out.println("buscando alergeno en controlador - 2" );
            this.alergenoItems = getFacade().buscarAlergenoEjb(consulta,this.tipoAlergenoSelect.getNombre());            
                System.out.println("buscando alergeno en controlador 3 " + this.alergenoItems.toString());
            for (AlergenoMed item : this.alergenoItems) {
                alergeno.add(item.getNombre());
            }            
        }else{
            //codigo para mostrar mensaje de advertencia
        }
        System.out.println("resultado " + alergeno.toString());
        return alergenoItems;
    }
    
    public void agregarAlergenoLista(){                
        System.out.println(tipoAlergenoSelect.toString());
        System.out.println("imprimiendo: " + alergenoSelec);
        if(!alergenoSelec.equals("") && tipoAlergenoSelect != null){
            if(!listaAlergenos.contains(new UtilidadesAntecedentesPersonalesAlergicos(tipoAlergenoSelect, alergenoSelec))){
                System.out.println("ingresando en la lista");
                listaAlergenos.add(new UtilidadesAntecedentesPersonalesAlergicos(tipoAlergenoSelect, alergenoSelec));
            }            
        }                
    }
    public void agregarAlergenoLista2(){        
        System.out.println(tipoAlergenoSelect);
        System.out.println("imprimiendo: " + alergenoSelec);
    }
    
    public void eliminarAlergenoLista(UtilidadesAntecedentesPersonalesAlergicos alergeno){
        System.out.println("eliminando " + alergeno);
        if(listaAlergenos.contains(alergeno)){
            listaAlergenos.remove(alergeno);
        }
    }
    
    //PROCEDIMIENTOS QUIRURGICOS
    public void agregarProcedimientoLista(){
        if(procedimientoSelec != null){
            if(!listaProcedimientos.contains(procedimientoSelec)){
                System.out.println("ingresando en la lista");
                listaProcedimientos.add(procedimientoSelec);
                procedimientoSelec = null;
            }
        }        
    }
    
    public void eliminarProcedimientoLista (ProcedimientosCupsMed procedimiento){
        System.out.println("eliminando " + procedimiento.toString());
        if(listaProcedimientos.contains(procedimiento)){
            listaProcedimientos.remove(procedimiento);
        }
    }
    
    
    
    
    
    //HABITOS
    private int habitosAlcohol = 0;
    private String observacionesConsumeAlcohol = "";
    private int habitosTabaco = 0;
    private String observacionesConsumeTabaco = "";
    private int habitosDeporte = 0;
    private String observacionesDeporte = "";
    private int habitosOtros = 0;
    private String nuevoHabitosOtros = "";

    private String habitoOtrosSelec = "";    
    

    public String getHabitoOtrosSelec() {
        return habitoOtrosSelec;
    }

    public void setHabitoOtrosSelec(String habitoOtrosSelec) {
        this.habitoOtrosSelec = habitoOtrosSelec;
    }        
    
    public String getObservacionesConsumeAlcohol() {
        return observacionesConsumeAlcohol;
    }

    public void setObservacionesConsumeAlcohol(String observacionesConsumeAlcohol) {
        this.observacionesConsumeAlcohol = observacionesConsumeAlcohol;
    }

    public int getHabitosTabaco() {
        return habitosTabaco;
    }

    public void setHabitosTabaco(int habitosTabaco) {
        this.habitosTabaco = habitosTabaco;
    }

    public String getObservacionesConsumeTabaco() {
        return observacionesConsumeTabaco;
    }

    public void setObservacionesConsumeTabaco(String observacionesConsumeTabaco) {
        this.observacionesConsumeTabaco = observacionesConsumeTabaco;
    }

    public int getHabitosDeporte() {
        return habitosDeporte;
    }

    public void setHabitosDeporte(int habitosDeporte) {
        this.habitosDeporte = habitosDeporte;
    }

    public String getObservacionesDeporte() {
        return observacionesDeporte;
    }

    public void setObservacionesDeporte(String observacionesDeporte) {
        this.observacionesDeporte = observacionesDeporte;
    }

    public int getHabitosOtros() {
        return habitosOtros;
    }

    public void setHabitosOtros(int habitosOtros) {
        this.habitosOtros = habitosOtros;
    }

    public String getNuevoHabitosOtros() {
        return nuevoHabitosOtros;
    }

    public void setNuevoHabitosOtros(String nuevoHabitosOtros) {
        this.nuevoHabitosOtros = nuevoHabitosOtros;
    }
       
    public int getHabitosAlcohol() {
        return habitosAlcohol;
    }

    public void setHabitosAlcohol(int habitosAlcohol) {
        this.habitosAlcohol = habitosAlcohol;
    }
    
    
    public List<String> buscarHabitosOtros(String consulta){
        List<String> listHabitosOtros = new ArrayList<>();        
        //if(!this.habitoOtrosSelec.equals("")){
            System.out.println("entro en if buscar habitos otros");
            listHabitosOtros = getFacade().buscarHabitoOtrosEjb(consulta);            
        //}
        System.out.println("retornando: " + listHabitosOtros.toString());
        return listHabitosOtros;
    }
    
    public void habitosAlcoholDesc(){
        System.out.println(observacionesConsumeAlcohol);        
    }
    
    public void habitosTabacoDesc(){
        System.out.println(observacionesConsumeTabaco);
    }
    
    public void habitosActividadFisicaDesc(){
        System.out.println(observacionesDeporte);
    }
    
    public void habitosOtrosDesc(){
        System.out.println(nuevoHabitosOtros);
    }
    
    
    //PATOLOGICO
    
    private String preguntadoNegadoPatologico = "";
    private String estadoPatologico = "";
    private List<PatologicosMed> estadoPatologicoList = new ArrayList<>();
    List<String> textoEstadoPatologico = new ArrayList<>();
    private boolean estadoNormalPatologico = true;
    private List<PatologicosMed> estadoListCheck = new ArrayList<>();

    public boolean isEstadoNormalPatologico() {
        return estadoNormalPatologico;
    }

    public void setEstadoNormalPatologico(boolean estadoNormalPatologico) {
        this.estadoNormalPatologico = estadoNormalPatologico;
    }        

    public String getEstadoPatologico() {
        return estadoPatologico;
    }

    public void setEstadoPatologico(String estadoPatologico) {
        this.estadoPatologico = estadoPatologico;
    }

    public List<PatologicosMed> getEstadoPatologicoList() {
        return estadoPatologicoList;
    }

    public void setEstadoPatologicoList(List<PatologicosMed> estadoPatologicoList) {
        this.estadoPatologicoList = estadoPatologicoList;
    }

    public List<String> getTextoEstadoPatologico() {
        return textoEstadoPatologico;
    }

    public void setTextoEstadoPatologico(List<String> textoEstadoPatologico) {
        this.textoEstadoPatologico = textoEstadoPatologico;
    }
    
    public String getPreguntadoNegadoPatologico() {
        return preguntadoNegadoPatologico;
    }

    public void setPreguntadoNegadoPatologico(String preguntadoNegadoPatologico) {
        this.preguntadoNegadoPatologico = preguntadoNegadoPatologico;
    }
    
    
    
    public void checkBoxPatologicos(PatologicosMed it){
        System.out.println(it.toString());
        if(estadoListCheck.contains(it))
            estadoListCheck.remove(it);
        else
            estadoListCheck.add(it);
    }
    
    public void recibirTextoPatologico(AjaxBehaviorEvent evt){
        //en esta variable se almacena lo que digito el usuario
        String texto = "" + ((UIOutput)evt.getSource()).getValue();
        this.estadoPatologico = texto;
        System.out.println("presiono en patologico " + texto);
    }
    
    public void guardarHallazgoPatologico(PatologicosMed item){
        System.out.println("guardanddo estado hallazgo patologico");
        if(!estadoPatologicoList.contains(item)){
            estadoPatologicoList.add(item);
            textoEstadoPatologico.add(this.valorHallazgo);
        }else{           
            int pos = hallazgoList.indexOf(item);
            textoEstadoPatologico.set(pos, this.valorHallazgo);
        }        
    }
 
    public Date getCurrentDate() {
        Date currentDate = null;
        return currentDate;
    }     
    
    
    
    
    
    
    //GUARDAR DATOS
    
}
