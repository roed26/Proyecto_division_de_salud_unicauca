/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.clases.DetalleServicioPresupuesto;
import com.unicauca.divsalud.clases.DiagnosticoTipo;
import com.unicauca.divsalud.clases.OdontogramaTemp;
import com.unicauca.divsalud.clases.RespuestasAntecedentesFamiliares;
import com.unicauca.divsalud.clases.RespuestasAntecedentesPersonales;
import com.unicauca.divsalud.clases.RespuestasExamenEstomatologico;
import com.unicauca.divsalud.clases.RespuestasExamenOral;
import com.unicauca.divsalud.entidades.ActualizacionOdo;
import com.unicauca.divsalud.entidades.AntOdo;
import com.unicauca.divsalud.entidades.CuadroSintesis;
import com.unicauca.divsalud.entidades.DiagnosticoOdo;
import com.unicauca.divsalud.entidades.Diagnosticocie10Odo;
import com.unicauca.divsalud.entidades.EvolucionOdo;
import com.unicauca.divsalud.entidades.ExaEstomatologico;
import com.unicauca.divsalud.entidades.ExaOral;
import com.unicauca.divsalud.entidades.Higiene;
import com.unicauca.divsalud.entidades.ListadoAntOdon;
import com.unicauca.divsalud.entidades.ListadoEstomatologico;
import com.unicauca.divsalud.entidades.ListadoExamenOral;
import com.unicauca.divsalud.entidades.MotivoOdo;
import com.unicauca.divsalud.entidades.ObsAntOdo;
import com.unicauca.divsalud.entidades.ObsExaOral;
import com.unicauca.divsalud.entidades.ObsOdontograma;
import com.unicauca.divsalud.entidades.Odontograma;
import com.unicauca.divsalud.entidades.Paciente;
import com.unicauca.divsalud.entidades.ServiciosOdo;
import com.unicauca.divsalud.entidades.Tipodiagnostico;
import com.unicauca.divsalud.entidades.UsuariosSistema;
import com.unicauca.divsalud.sessionbeans.ActualizacionOdoFacade;
import com.unicauca.divsalud.sessionbeans.AntOdoFacade;
import com.unicauca.divsalud.sessionbeans.CuadroSintesisFacade;
import com.unicauca.divsalud.sessionbeans.DiagnosticoOdoFacade;
import com.unicauca.divsalud.sessionbeans.Diagnosticocie10OdoFacade;
import com.unicauca.divsalud.sessionbeans.EvolucionOdoFacade;
import com.unicauca.divsalud.sessionbeans.ExaEstomatologicoFacade;
import com.unicauca.divsalud.sessionbeans.ExaOralFacade;
import com.unicauca.divsalud.sessionbeans.HigieneFacade;
import com.unicauca.divsalud.sessionbeans.ListadoAntOdonFacade;
import com.unicauca.divsalud.sessionbeans.ListadoEstomatologicoFacade;
import com.unicauca.divsalud.sessionbeans.ListadoExamenOralFacade;
import com.unicauca.divsalud.sessionbeans.MotivoOdoFacade;
import com.unicauca.divsalud.sessionbeans.ObsAntOdoFacade;
import com.unicauca.divsalud.sessionbeans.ObsExaOralFacade;
import com.unicauca.divsalud.sessionbeans.ObsOdontogramaFacade;
import com.unicauca.divsalud.sessionbeans.OdontogramaFacade;
import com.unicauca.divsalud.sessionbeans.ParentescoFacade;
import com.unicauca.divsalud.sessionbeans.UsuariosSistemaFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ROED26
 */
@Named(value = "aperturaHOdontologicaController")
@SessionScoped
public class AperturaHOdontologicaController implements Serializable {

    @EJB
    private ParentescoFacade ejbParentesco;
    @EJB
    private ListadoAntOdonFacade ejbListadoAntecedentesOdon;
    @EJB
    private ListadoEstomatologicoFacade ejbListadoEstomatologicos;
    @EJB
    private ListadoExamenOralFacade ejbListadoExamenOral;
    @EJB
    private ActualizacionOdoFacade ejbActualizacionOdo;
    @EJB
    private UsuariosSistemaFacade usuarioEJB;
    @EJB
    private MotivoOdoFacade ejbMotivoOdo;
    @EJB
    private AntOdoFacade ejbAntOdo;
    @EJB
    private ObsAntOdoFacade ejbObsAntOdo;
    @EJB
    private ExaEstomatologicoFacade ejbExaEstomatologico;
    @EJB
    private ExaOralFacade ejbExaOral;
    @EJB
    private ObsExaOralFacade ejbObsExaOral;
    @EJB
    private HigieneFacade ejbHigiene;
    @EJB
    private EvolucionOdoFacade ejbEvolucionOdo;
    @EJB
    private Diagnosticocie10OdoFacade ejbDiagnosticocie10Odo;
    @EJB
    private DiagnosticoOdoFacade ejbDiagnosticoOdo;
    @EJB
    private OdontogramaFacade ejbOdontograma;
    @EJB
    private ObsOdontogramaFacade ejbObsOdontograma;
    @EJB
    private CuadroSintesisFacade ejbCuadroSintesis;

    //variables booleanas
    private boolean conAcompaniante;
    private boolean conAntecedentesFamiliares;
    private boolean conAntecedentesPersonales;
    private boolean conExamenEstomatologico;
    private boolean conExamenPulpar;
    private boolean conExamenTejidosDent;
    private boolean conAlteraciones;
    private boolean conPosgrado;
    private boolean pacienteSeleccionado;
    private boolean seleccionDienteNormal;
    private boolean seleccionDienteCaries;
    private boolean seleccionDienteAmalgama;
    private boolean seleccionDienteObstPlastica;
    private boolean seleccionDienteObstTemporal;
    private boolean seleccionDienteConSellante;
    private boolean seleccionDienteSinSellante;
    private boolean seleccionDienteFaltante;
    private boolean seleccionDienteFaltanteExt;
    private boolean seleccionDienteExodoncia;
    private boolean seleccionDienteProtesis;
    private boolean seleccionDienteProtesisTotal;
    private boolean seleccionDienteNecendodoncia;
    private boolean seleccionDienteTtoEndodoncia;
    private boolean seleccionDienteBolsaPer;

    //listas
    private List<ListadoAntOdon> listadoAntecedentesFamiliares;
    private List<ListadoAntOdon> listadoAntecedentesPersonales;
    private List<ListadoEstomatologico> listadoEstomatologicos;
    private List<ListadoExamenOral> listadoExamenOralPulpar;
    private List<ListadoExamenOral> listadoExamenOralDentario;
    private List<ListadoExamenOral> listadoExamenOralPeriodontal;
    private List<OdontogramaTemp> odontogramaPaciente;
    private List<OdontogramaTemp> odontogramaPacienteAdultoSuperior;
    private List<OdontogramaTemp> odontogramaPacienteAdultoInferior;
    private List<String> listadoRespuestasAntFam;
    private List<String> listadoRespuestasAntPer;
    private List<String> listadoRespuestasExmEst;
    private List<String> listadoObservacionesExmEst;
    private List<String> listadoRespuestasExamenOral;
    private List<DiagnosticoTipo> listaDiagnosticosTipo;
    private ArrayList<DetalleServicioPresupuesto> listaServicios;
    private ArrayList<DetalleServicioPresupuesto> listaServiciosTemporal;

    //String
    private String respuestaAntOdo = "No";
    private String motivoConsulta;
    private String descripcionEvolucion;
    private String busquedaPaciente;
    private String fechaRegistro;
    private String tipoActualizacion;
    private String observacionAntecendetesFam;
    private String observacionAntecendetesPer;
    private String observacionGenExmEst;
    private String diagnosticoDiente;
    private String diagnosticoPrincipal;

    //fechas
    private Date fechaApertura;
    private SimpleDateFormat formatoFecha;

    //numeros
    private int contCaries = 0;
    private int dienteEvolucion;
    double totalPagar = 0;

    //objetos 
    private Paciente paciente;
    private Higiene higiene;
    private CuadroSintesis cuadroSintesis;
    private ActualizacionOdo actualizacionOdo;
    private RespuestasAntecedentesFamiliares respuestasAntecedentesFamiliares;
    private RespuestasAntecedentesPersonales respuestasAntecedentesPersonales;
    private RespuestasExamenEstomatologico respuestasExamenEstomatologico;
    private RespuestasExamenOral respuestasExamenOral;
    private Tipodiagnostico tipoDiagnostico;
    private ObsOdontograma obsOdontograma;
    private DiagnosticoTipo diagnosticoTipo;
    private DetalleServicioPresupuesto detalleServicioPresupuestoEliminar;

    private List<Boolean> contadoresCariesDiente;
    private List<Boolean> contadoresPerdidosDiente;
    private List<Boolean> contadoresObturadosDiente;

    public AperturaHOdontologicaController() {
        this.formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        //inicilizacion string
        this.higiene = new Higiene();
        listadoAntecedentesFamiliares = new ArrayList<>();
        listadoAntecedentesPersonales = new ArrayList<>();
        listadoEstomatologicos = new ArrayList<>();
        listadoExamenOralPulpar = new ArrayList<>();
        listadoExamenOralDentario = new ArrayList<>();
        listadoExamenOralPeriodontal = new ArrayList<>();
        listadoRespuestasAntFam = new ArrayList<>();
        listadoRespuestasAntPer = new ArrayList<>();
        listadoRespuestasExmEst = new ArrayList<>();
        listadoRespuestasExamenOral = new ArrayList<>();
        listadoObservacionesExmEst = new ArrayList<>();
        listaDiagnosticosTipo = new ArrayList<>();
        listaServicios = new ArrayList<>();
        listaServiciosTemporal = new ArrayList<>();
        respuestasAntecedentesFamiliares = new RespuestasAntecedentesFamiliares();
        respuestasAntecedentesPersonales = new RespuestasAntecedentesPersonales();
        respuestasExamenEstomatologico = new RespuestasExamenEstomatologico();
        respuestasExamenOral = new RespuestasExamenOral();
        this.tipoDiagnostico = new Tipodiagnostico();
        odontogramaPaciente = new ArrayList<>();
        odontogramaPacienteAdultoSuperior = new ArrayList<>();
        odontogramaPacienteAdultoInferior = new ArrayList<>();
        pacienteSeleccionado = false;
        inicializarVariablesBooleanas();
    }

    @PostConstruct
    private void init() {
        this.actualizacionOdo = new ActualizacionOdo();
        this.higiene = new Higiene();
        this.obsOdontograma = new ObsOdontograma();
        this.obsOdontograma.setOclusion("Normal");
        this.obsOdontograma.setCaries("0");
        this.obsOdontograma.setObturados("0");
        this.obsOdontograma.setPerdidos("0");
        this.tipoDiagnostico = new Tipodiagnostico();
        this.cuadroSintesis = new CuadroSintesis();
        this.diagnosticoDiente = "normal";
        cargarAntecedentesFamiliares();
        cargarAntecedentesPersonales();
        cargarEstomatologicos();
        cargarListaExamenOralPulpar();
        cargarListaExamenOralDentario();
        cargarListaExamenOralPeriodontal();
        inicializarOdontograma();
        inicializarCuadroSintesis();
        inicializarVariablesBooleanas();

    }

    public ActualizacionOdo getActualizacionOdo() {
        return actualizacionOdo;
    }

    public void setActualizacionOdo(ActualizacionOdo actualizacionOdo) {
        this.actualizacionOdo = actualizacionOdo;
    }

    public List<OdontogramaTemp> getOdontogramaPacienteAdultoSuperior() {
        return odontogramaPacienteAdultoSuperior;
    }

    public void setOdontogramaPacienteAdultoSuperior(List<OdontogramaTemp> odontogramaPacienteAdultoSuperior) {
        this.odontogramaPacienteAdultoSuperior = odontogramaPacienteAdultoSuperior;
    }

    public List<OdontogramaTemp> getOdontogramaPacienteAdultoInferior() {
        return odontogramaPacienteAdultoInferior;
    }

    public void setOdontogramaPacienteAdultoInferior(List<OdontogramaTemp> odontogramaPacienteAdultoInferior) {
        this.odontogramaPacienteAdultoInferior = odontogramaPacienteAdultoInferior;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public CuadroSintesis getCuadroSintesis() {
        return cuadroSintesis;
    }

    public void setCuadroSintesis(CuadroSintesis cuadroSintesis) {
        this.cuadroSintesis = cuadroSintesis;
    }

    public int getDienteEvolucion() {
        return dienteEvolucion;
    }

    public void setDienteEvolucion(int dienteEvolucion) {
        this.dienteEvolucion = dienteEvolucion;
    }

    public ObsOdontograma getObsOdontograma() {
        return obsOdontograma;
    }

    public void setObsOdontograma(ObsOdontograma obsOdontograma) {
        this.obsOdontograma = obsOdontograma;
    }

    public List<ListadoAntOdon> getListadoAntecedentesFamiliares() {
        return listadoAntecedentesFamiliares;
    }

    public void setListadoAntecedentesFamiliares(List<ListadoAntOdon> listadoAntecedentesFamiliares) {
        this.listadoAntecedentesFamiliares = listadoAntecedentesFamiliares;
    }

    public List<ListadoAntOdon> getListadoAntecedentesPersonales() {
        return listadoAntecedentesPersonales;
    }

    public void setListadoAntecedentesPersonales(List<ListadoAntOdon> listadoAntecedentesPersonales) {
        this.listadoAntecedentesPersonales = listadoAntecedentesPersonales;
    }

    public List<ListadoEstomatologico> getListadoEstomatologicos() {
        return listadoEstomatologicos;
    }

    public void setListadoEstomatologicos(List<ListadoEstomatologico> listadoEstomatologicos) {
        this.listadoEstomatologicos = listadoEstomatologicos;
    }

    public List<ListadoExamenOral> getListadoExamenOralPulpar() {
        return listadoExamenOralPulpar;
    }

    public void setListadoExamenOralPulpar(List<ListadoExamenOral> listadoExamenOralPulpar) {
        this.listadoExamenOralPulpar = listadoExamenOralPulpar;
    }

    public List<ListadoExamenOral> getListadoExamenOralDentario() {
        return listadoExamenOralDentario;
    }

    public void setListadoExamenOralDentario(List<ListadoExamenOral> listadoExamenOralDentario) {
        this.listadoExamenOralDentario = listadoExamenOralDentario;
    }

    public List<ListadoExamenOral> getListadoExamenOralPeriodontal() {
        return listadoExamenOralPeriodontal;
    }

    public String getDiagnosticoPrincipal() {
        return diagnosticoPrincipal;
    }

    public void setDiagnosticoPrincipal(String diagnosticoPrincipal) {
        this.diagnosticoPrincipal = diagnosticoPrincipal;
    }

    public String getDescripcionEvolucion() {
        return descripcionEvolucion;
    }

    public void setDescripcionEvolucion(String descripcionEvolucion) {
        this.descripcionEvolucion = descripcionEvolucion;
    }

    public void setListadoExamenOralPeriodontal(List<ListadoExamenOral> listadoExamenOralPeriodontal) {
        this.listadoExamenOralPeriodontal = listadoExamenOralPeriodontal;
    }

    public RespuestasAntecedentesFamiliares getRespuestasAntecedentesFamiliares() {
        return respuestasAntecedentesFamiliares;
    }

    public void setRespuestasAntecedentesFamiliares(RespuestasAntecedentesFamiliares respuestasAntecedentesFamiliares) {
        this.respuestasAntecedentesFamiliares = respuestasAntecedentesFamiliares;
    }

    public RespuestasExamenOral getRespuestasExamenOral() {
        return respuestasExamenOral;
    }

    public void setRespuestasExamenOral(RespuestasExamenOral respuestasExamenOral) {
        this.respuestasExamenOral = respuestasExamenOral;
    }

    public String getRespuestaAntOdo() {
        return respuestaAntOdo;
    }

    public void setRespuestaAntOdo(String respuestaAntOdo) {
        this.respuestaAntOdo = respuestaAntOdo;
    }

    public String getObservacionAntecendetesFam() {
        return observacionAntecendetesFam;
    }

    public void setObservacionAntecendetesFam(String observacionAntecendetesFam) {
        this.observacionAntecendetesFam = observacionAntecendetesFam;
    }

    public boolean isConAcompaniante() {
        return conAcompaniante;
    }

    public void setConAcompaniante(boolean conAcompaniante) {
        this.conAcompaniante = conAcompaniante;
    }

    public boolean isSeleccionDienteCaries() {
        return seleccionDienteCaries;
    }

    public void setSeleccionDienteCaries(boolean seleccionDienteCaries) {
        this.seleccionDienteCaries = seleccionDienteCaries;
    }

    public boolean isSeleccionDienteConSellante() {
        return seleccionDienteConSellante;
    }

    public void setSeleccionDienteConSellante(boolean seleccionDienteConSellante) {
        this.seleccionDienteConSellante = seleccionDienteConSellante;
    }

    public boolean isConPosgrado() {
        return conPosgrado;
    }

    public void setConPosgrado(boolean conPosgrado) {
        this.conPosgrado = conPosgrado;
    }

    public boolean isSeleccionDienteSinSellante() {
        return seleccionDienteSinSellante;
    }

    public void setSeleccionDienteSinSellante(boolean seleccionDienteSinSellante) {
        this.seleccionDienteSinSellante = seleccionDienteSinSellante;
    }

    public boolean isSeleccionDienteFaltante() {
        return seleccionDienteFaltante;
    }

    public void setSeleccionDienteFaltante(boolean seleccionDienteFaltante) {
        this.seleccionDienteFaltante = seleccionDienteFaltante;
    }

    public boolean isSeleccionDienteFaltanteExt() {
        return seleccionDienteFaltanteExt;
    }

    public void setSeleccionDienteFaltanteExt(boolean seleccionDienteFaltanteExt) {
        this.seleccionDienteFaltanteExt = seleccionDienteFaltanteExt;
    }

    public boolean isSeleccionDienteExodoncia() {
        return seleccionDienteExodoncia;
    }

    public void setSeleccionDienteExodoncia(boolean seleccionDienteExodoncia) {
        this.seleccionDienteExodoncia = seleccionDienteExodoncia;
    }

    public boolean isSeleccionDienteProtesis() {
        return seleccionDienteProtesis;
    }

    public void setSeleccionDienteProtesis(boolean seleccionDienteProtesis) {
        this.seleccionDienteProtesis = seleccionDienteProtesis;
    }

    public boolean isSeleccionDienteProtesisTotal() {
        return seleccionDienteProtesisTotal;
    }

    public void setSeleccionDienteProtesisTotal(boolean seleccionDienteProtesisTotal) {
        this.seleccionDienteProtesisTotal = seleccionDienteProtesisTotal;
    }

    public boolean isSeleccionDienteNecendodoncia() {
        return seleccionDienteNecendodoncia;
    }

    public void setSeleccionDienteNecendodoncia(boolean seleccionDienteNecendodoncia) {
        this.seleccionDienteNecendodoncia = seleccionDienteNecendodoncia;
    }

    public boolean isSeleccionDienteTtoEndodoncia() {
        return seleccionDienteTtoEndodoncia;
    }

    public void setSeleccionDienteTtoEndodoncia(boolean seleccionDienteTtoEndodoncia) {
        this.seleccionDienteTtoEndodoncia = seleccionDienteTtoEndodoncia;
    }

    public boolean isSeleccionDienteBolsaPer() {
        return seleccionDienteBolsaPer;
    }

    public void setSeleccionDienteBolsaPer(boolean seleccionDienteBolsaPer) {
        this.seleccionDienteBolsaPer = seleccionDienteBolsaPer;
    }

    public boolean isConExamenTejidosDent() {
        return conExamenTejidosDent;
    }

    public boolean isSeleccionDienteObstTemporal() {
        return seleccionDienteObstTemporal;
    }

    public void setSeleccionDienteObstTemporal(boolean seleccionDienteObstTemporal) {
        this.seleccionDienteObstTemporal = seleccionDienteObstTemporal;
    }

    public void setConExamenTejidosDent(boolean conExamenTejidosDent) {
        this.conExamenTejidosDent = conExamenTejidosDent;
    }

    public String getBusquedaPaciente() {
        return busquedaPaciente;
    }

    public void setBusquedaPaciente(String busquedaPaciente) {
        this.busquedaPaciente = busquedaPaciente;
    }

    public RespuestasExamenEstomatologico getRespuestasExamenEstomatologico() {
        return respuestasExamenEstomatologico;
    }

    public void setRespuestasExamenEstomatologico(RespuestasExamenEstomatologico respuestasExamenEstomatologico) {
        this.respuestasExamenEstomatologico = respuestasExamenEstomatologico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Tipodiagnostico getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(Tipodiagnostico tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
    }

    public boolean isConAntecedentesFamiliares() {
        return conAntecedentesFamiliares;
    }

    public void setConAntecedentesFamiliares(boolean conAntecedentesFamiliares) {
        this.conAntecedentesFamiliares = conAntecedentesFamiliares;
    }

    public boolean isConAntecedentesPersonales() {
        return conAntecedentesPersonales;
    }

    public boolean isConExamenPulpar() {
        return conExamenPulpar;
    }

    public void setConExamenPulpar(boolean conExamenPulpar) {
        this.conExamenPulpar = conExamenPulpar;
    }

    public boolean isConExamenEstomatologico() {
        return conExamenEstomatologico;
    }

    public boolean isConAlteraciones() {
        return conAlteraciones;
    }

    public boolean isSeleccionDienteObstPlastica() {
        return seleccionDienteObstPlastica;
    }

    public void setSeleccionDienteObstPlastica(boolean seleccionDienteObstPlastica) {
        this.seleccionDienteObstPlastica = seleccionDienteObstPlastica;
    }

    public boolean isSeleccionDienteNormal() {
        return seleccionDienteNormal;
    }

    public void setSeleccionDienteNormal(boolean seleccionDienteNormal) {
        this.seleccionDienteNormal = seleccionDienteNormal;
    }

    public void setConAlteraciones(boolean conAlteraciones) {
        this.conAlteraciones = conAlteraciones;
    }

    public boolean isSeleccionDienteAmalgama() {
        return seleccionDienteAmalgama;
    }

    public void setSeleccionDienteAmalgama(boolean seleccionDienteAmalgama) {
        this.seleccionDienteAmalgama = seleccionDienteAmalgama;
    }

    public void setConExamneEstomatologico(boolean conExamenEstomatologico) {
        this.conExamenEstomatologico = conExamenEstomatologico;
    }

    public void setConAntecedentesPersonales(boolean conAntecedentesPersonales) {
        this.conAntecedentesPersonales = conAntecedentesPersonales;
    }

    public String getObservacionAntecendetesPer() {
        return observacionAntecendetesPer;
    }

    public void setObservacionAntecendetesPer(String observacionAntecendetesPer) {
        this.observacionAntecendetesPer = observacionAntecendetesPer;
    }

    public String getObservacionGenExmEst() {
        return observacionGenExmEst;
    }

    public void setObservacionGenExmEst(String observacionGenExmEst) {
        this.observacionGenExmEst = observacionGenExmEst;
    }

    public RespuestasAntecedentesPersonales getRespuestasAntecedentesPersonales() {
        return respuestasAntecedentesPersonales;
    }

    public void setRespuestasAntecedentesPersonales(RespuestasAntecedentesPersonales respuestasAntecedentesPersonales) {
        this.respuestasAntecedentesPersonales = respuestasAntecedentesPersonales;
    }

    public Higiene getHigiene() {
        return higiene;
    }

    public void setHigiene(Higiene higiene) {
        this.higiene = higiene;
    }

    public boolean isPacienteSeleccionado() {
        return pacienteSeleccionado;
    }

    public void setPacienteSeleccionado(boolean pacienteSeleccionado) {
        this.pacienteSeleccionado = pacienteSeleccionado;
    }

    public SimpleDateFormat getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(SimpleDateFormat formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ArrayList<DetalleServicioPresupuesto> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(ArrayList<DetalleServicioPresupuesto> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public void seleccionarPaciente(Paciente paciente, CargarVistaController cargarVista) {
        this.tipoActualizacion = "Apertura";
        this.pacienteSeleccionado = true;
        this.paciente = paciente;
        this.fechaRegistro = formatoFecha.format(this.paciente.getFechaApertura());
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        requestContext.update("InformacionPaciente");
        requestContext.update("aperturaHistoriaOdontologica");
        //requestContext.execute("PF('seleccionPacienteDialog').hide()");
        cargarVista.cargarAperturaHistoriaOdontologica();

    }

    public void seleccionarPaciente(Paciente paciente) {
        this.tipoActualizacion = "Apertura";
        this.pacienteSeleccionado = true;
        this.paciente = paciente;
        this.fechaRegistro = formatoFecha.format(this.paciente.getFechaApertura());
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        requestContext.update("InformacionPaciente");
        requestContext.update("aperturaHistoriaOdontologica");
        requestContext.execute("PF('seleccionPacienteDialog').hide()");

    }

    public void cambiarEstadoBool() {
        this.pacienteSeleccionado = false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('seleccionPacienteDialog').hide()");

    }

    public void respuesta(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conAcompaniante = true;
        } else {
            conAcompaniante = false;
        }

    }

    public void respuestaAcompanante(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conAcompaniante = true;
        } else {
            conAcompaniante = false;
        }

    }

    public void respuestaTipoEducacion(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conPosgrado = true;
        } else {
            conPosgrado = false;
        }

    }

    public void respuestaAntecedenteF(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conAntecedentesFamiliares = true;
        } else {
            conAntecedentesFamiliares = false;
        }

    }

    public void asignarTipoDiagnostico(ValueChangeEvent e) {
        this.tipoDiagnostico = (Tipodiagnostico) e.getNewValue();
    }

    public void respuestaExamenE(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conExamenEstomatologico = true;
        } else {
            conExamenEstomatologico = false;
        }

    }

    public void respuestaExamenPulpar(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conExamenPulpar = true;
        } else {
            conExamenPulpar = false;
        }

    }

    public void respuestaTejidosDentarios(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conExamenTejidosDent = true;
        } else {
            conExamenTejidosDent = false;
        }
    }

    public void respuestaAlteraciones(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conAlteraciones = true;
        } else {
            conAlteraciones = false;
        }

    }

    public void respuestaAntecedenteP(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conAntecedentesPersonales = true;
        } else {
            conAntecedentesPersonales = false;
        }

    }

    public void cargarAntecedentesFamiliares() {
        List<ListadoAntOdon> listaAntecedentesOdon = ejbListadoAntecedentesOdon.findAll();

        for (ListadoAntOdon listadoAntOdon : listaAntecedentesOdon) {

            if (listadoAntOdon.getFamiliar().equalsIgnoreCase("S")) {
                listadoAntecedentesFamiliares.add(listadoAntOdon);
            }
        }

    }

    public void cargarAntecedentesPersonales() {
        List<ListadoAntOdon> listaAntecedentesOdon = ejbListadoAntecedentesOdon.findAll();
        for (ListadoAntOdon listadoAntOdon : listaAntecedentesOdon) {
            if (listadoAntOdon.getPersonal().equalsIgnoreCase("S")) {
                listadoAntecedentesPersonales.add(listadoAntOdon);
            }
        }

    }

    public void cargarEstomatologicos() {
        this.listadoEstomatologicos = ejbListadoEstomatologicos.findAll();

    }

    public void cargarListaExamenOralPulpar() {
        List<ListadoExamenOral> listaExamenOral = ejbListadoExamenOral.findAll();
        for (ListadoExamenOral listadoExamenOral : listaExamenOral) {
            if (listadoExamenOral.getPulpar().equalsIgnoreCase("S")) {
                listadoExamenOralPulpar.add(listadoExamenOral);
            }
        }

    }

    public void cargarListaExamenOralDentario() {
        List<ListadoExamenOral> listaExamenOral = ejbListadoExamenOral.findAll();
        for (ListadoExamenOral listadoExamenOral : listaExamenOral) {
            if (listadoExamenOral.getDentarios().equalsIgnoreCase("S")) {
                listadoExamenOralDentario.add(listadoExamenOral);
            }
        }

    }

    public void cargarListaExamenOralPeriodontal() {
        List<ListadoExamenOral> listaExamenOral = ejbListadoExamenOral.findAll();
        for (ListadoExamenOral listadoExamenOral : listaExamenOral) {
            if (listadoExamenOral.getPeriodontales().equalsIgnoreCase("S")) {
                listadoExamenOralPeriodontal.add(listadoExamenOral);
            }
        }

    }

    public void guardarAperturaHistoria() {
        registrarActualizacion();//registro actualizacion 
        registrarMotivoConsulta();//registro motivo de consulta
        registrarAntecedentesFamiliares();//Registro antecendentes familiares
        registrarAntecedentesPersonales();//Registro antecendentes personales
        registrarExamenEstomatologico();//Registro examen estomatologico
        registrarExamenOral();//registro examen oral
        registrarHigieneOral();//registro de higiene oral
        registrarOdontogramaCopia();//registro odontograma
        registrarDiagnostico();//registro diagnostico
        registrarEvolucion();//registro evolucion
        registrarObservacionOdontograma();
        registrarCuadroSintesis();

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('registroExitosoDialog').show()");

        reiniciarVariables();

        requestContext.update("aperturaHistoriaOdontologica");

    }

    private void asignarFecha() {
        GregorianCalendar c = new GregorianCalendar();
        fechaApertura = c.getTime();
    }

    public void cargarRespuestasAntecedentesFam() {
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta1());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta2());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta3());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta4());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta5());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta6());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta7());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta8());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta9());
    }

    public void cargarRespuestasAntecedentesPer() {
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta1());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta2());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta3());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta4());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta5());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta6());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta7());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta8());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta9());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta10());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta11());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta12());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta13());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta14());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta15());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta16());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta17());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta18());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta19());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta20());
    }

    public void cargarRespuestasExamenEst() {
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta1());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta2());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta3());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta4());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta5());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta6());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta7());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta8());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta9());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta10());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta11());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta12());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta13());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta14());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta15());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta16());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta17());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta18());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta19());

    }

    public void cargarRespuestasObsExamenEst() {
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion1());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion2());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion3());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion4());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion5());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion6());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion7());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion8());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion9());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion10());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion11());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion12());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion13());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion14());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion15());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion16());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion17());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion18());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion19());
    }

    public void cargarRespuestasExamenOral() {
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta1());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta2());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta3());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta4());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta5());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta6());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta7());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta8());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta9());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta10());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta11());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta12());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta13());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta14());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta15());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta16());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getObservacionExamenPulpar());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getObservacionTejidosDentarios());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getObservacionAlteracionesPer());

    }

    public void cambiarSeleccionDiagnosticoDent(String diagnosticoDiente) {
        this.diagnosticoDiente = diagnosticoDiente;

        if (this.diagnosticoDiente.equalsIgnoreCase("normal")) {
            seleccionDienteNormal = true;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("caries")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = true;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("amalgama")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = true;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {

            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = true;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("obst_temporal")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = true;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("sellante")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = true;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("sinsellante")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = true;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("faltante")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = true;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = true;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = true;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("protesis")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = true;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("protesistotal")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = true;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("necendodoncia")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = true;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = true;
            this.seleccionDienteBolsaPer = false;
        } else if (this.diagnosticoDiente.equalsIgnoreCase("bolsa_per")) {
            seleccionDienteNormal = false;
            seleccionDienteCaries = false;
            seleccionDienteAmalgama = false;
            seleccionDienteObstPlastica = false;
            seleccionDienteObstTemporal = false;
            this.seleccionDienteConSellante = false;
            this.seleccionDienteSinSellante = false;
            this.seleccionDienteFaltante = false;
            this.seleccionDienteFaltanteExt = false;
            this.seleccionDienteExodoncia = false;
            this.seleccionDienteProtesis = false;
            this.seleccionDienteProtesisTotal = false;
            this.seleccionDienteNecendodoncia = false;
            this.seleccionDienteTtoEndodoncia = false;
            this.seleccionDienteBolsaPer = true;
        }

    }

    private UsuariosSistema usuarioDeLaSesion() {
        UsuariosSistema usuario = new UsuariosSistema();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() != null) {
            usuario = this.usuarioEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0);
        }
        return usuario;
    }

    private void inicializarOdontograma() {
        this.odontogramaPacienteAdultoSuperior = new ArrayList<>();
        this.odontogramaPacienteAdultoInferior = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            OdontogramaTemp odontogramaTemp = new OdontogramaTemp();
            odontogramaTemp.setArriba("arriba_normal.png");
            odontogramaTemp.setAbajo("abajo_normal.png");
            odontogramaTemp.setIzquierda("izq_normal.png");
            odontogramaTemp.setDerecha("der_normal.png");
            odontogramaTemp.setCentro("centro_normal.png");
            odontogramaTemp.setAfueraEnd("fuera.png");
            odontogramaTemp.setAfueraNecEnd("fuera.png");
            odontogramaTemp.setAfueraBolsaPer("fuera.png");
            odontogramaPaciente.add(odontogramaTemp);
            if (i >= 0 && i < 16) {
                odontogramaPacienteAdultoSuperior.add(odontogramaTemp);
            }
            if (i >= 16 && i < 32) {
                odontogramaPacienteAdultoInferior.add(odontogramaTemp);
            }

        }
    }

    private String idDiagnostico(String diagnostico) {
        List<Diagnosticocie10Odo> diagnosticosCieOdo = new ArrayList<>();
        diagnosticosCieOdo = ejbDiagnosticocie10Odo.findAll();
        String idDiagnosticoCie = "";

        for (int i = 0; i < diagnosticosCieOdo.size(); i++) {
            if (diagnostico.equalsIgnoreCase(diagnosticosCieOdo.get(i).getNombre())) {
                idDiagnosticoCie = diagnosticosCieOdo.get(i).getId();
            }
        }

        return idDiagnosticoCie;
    }

    public void cambiarImgOdontogramaF(String posicion, int posDienteList, String lista) {
        OdontogramaTemp odontogramaTemp = new OdontogramaTemp();
        String nombreImagen = posicion;

        if (diagnosticoDiente.equalsIgnoreCase("caries")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_caries.png");
                }

                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_caries.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

            contarCariesCopia(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_normal.png");
                }

                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_normal.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
            verificarConteoCOP(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_amalgama.png");
                }

                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_amalgama.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

            contarObturados(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_plastica.png");
                }
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_plastica.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
            contarObturados(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("obst_temporal")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_temporal.png");
                }

                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_temporal.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("sellante")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sellante.png");
                }
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sellante.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("sinsellante")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sinsellante.png");
                }
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sinsellante.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("faltante")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante.png");
                odontogramaTemp.setAbajo("abajo_faltante.png");
                odontogramaTemp.setCentro("centro_faltante.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante.png");
                odontogramaTemp.setAbajo("abajo_faltante.png");
                odontogramaTemp.setCentro("centro_faltante.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
            contarDientesPerdidos(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante_ext.png");
                odontogramaTemp.setAbajo("abajo_faltante_ext.png");
                odontogramaTemp.setCentro("centro_faltante_ext.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante_ext.png");
                odontogramaTemp.setAbajo("abajo_faltante_ext.png");
                odontogramaTemp.setCentro("centro_faltante_ext.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

            contarDientesPerdidos(posDienteList, lista);

        } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_exodoncia.png");
                odontogramaTemp.setAbajo("abajo_exodoncia.png");
                odontogramaTemp.setCentro("centro_exodoncia.png");
                odontogramaTemp.setIzquierda("izq_exodoncia.png");
                odontogramaTemp.setDerecha("der_exodoncia.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_exodoncia.png");
                odontogramaTemp.setAbajo("abajo_exodoncia.png");
                odontogramaTemp.setCentro("centro_exodoncia.png");
                odontogramaTemp.setIzquierda("izq_exodoncia.png");
                odontogramaTemp.setDerecha("der_exodoncia.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("protesis")) {

            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesis.png");
                odontogramaTemp.setAbajo("abajo_protesis.png");
                odontogramaTemp.setCentro("centro_protesis.png");
                odontogramaTemp.setIzquierda("izq_protesis.png");
                odontogramaTemp.setDerecha("der_protesis.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesis.png");
                odontogramaTemp.setAbajo("abajo_protesis.png");
                odontogramaTemp.setCentro("centro_protesis.png");
                odontogramaTemp.setIzquierda("izq_protesis.png");
                odontogramaTemp.setDerecha("der_protesis.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("protesistotal")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesistotal.png");
                odontogramaTemp.setAbajo("abajo_protesistotal.png");
                odontogramaTemp.setCentro("centro_protesistotal.png");
                odontogramaTemp.setIzquierda("izq_protesistotal.png");
                odontogramaTemp.setDerecha("der_protesistotal.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesistotal.png");
                odontogramaTemp.setAbajo("abajo_protesistotal.png");
                odontogramaTemp.setCentro("centro_protesistotal.png");
                odontogramaTemp.setIzquierda("izq_protesistotal.png");
                odontogramaTemp.setDerecha("der_protesistotal.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
        } else if (diagnosticoDiente.equalsIgnoreCase("necendodoncia")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setAfueraNecEnd("necendodoncia_A.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setAfueraNecEnd("necendodoncia_A.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
        } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setAfueraEnd("ttoendodoncia_A.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setAfueraEnd("ttoendodoncia_A.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("bolsa_per")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setAfueraBolsaPer("bolsa_per_A.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setAfueraBolsaPer("bolsa_per_A.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("aperturaHistoriaOdontologica:odontogramaGrid");
        requestContext.update("aperturaHistoriaOdontologica:pnlCuadro");
        requestContext.update("aperturaHistoriaOdontologica:pnlOclusion");
    }

    private void verificarConteoCOP(int posDiente, String posicion) {
        if (posicion.compareTo("AdultoInferior") == 0) {
            //posDiente = posDiente + 16;
        }
        boolean validacionDienteNormalSuperior = false;
        boolean validacionDienteConCariesSuperior = false;
        boolean validacionDienteConObturacionSuperior = false;
        boolean validacionDienteNormalInferior = false;
        boolean validacionDienteConCariesInferior = false;
        boolean validacionDienteConObturacionInferior = false;
        if (posicion.compareTo("AdultoSuperior") == 0) {
            validacionDienteNormalSuperior = odontogramaPacienteAdultoSuperior.get(posDiente).getAbajo().compareTo("abajo_normal.png") == 0
                    && odontogramaPacienteAdultoSuperior.get(posDiente).getArriba().compareTo("arriba_normal.png") == 0
                    && odontogramaPacienteAdultoSuperior.get(posDiente).getIzquierda().compareTo("izq_normal.png") == 0
                    && odontogramaPacienteAdultoSuperior.get(posDiente).getDerecha().compareTo("der_normal.png") == 0
                    && odontogramaPacienteAdultoSuperior.get(posDiente).getCentro().compareTo("centro_normal.png") == 0;
            validacionDienteConCariesSuperior = odontogramaPacienteAdultoSuperior.get(posDiente).getAbajo().compareTo("abajo_caries.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getArriba().compareTo("arriba_caries.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getIzquierda().compareTo("izq_caries.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getDerecha().compareTo("der_caries.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getCentro().compareTo("centro_caries.png") == 0;
            validacionDienteConObturacionSuperior = odontogramaPacienteAdultoSuperior.get(posDiente).getAbajo().compareTo("abajo_amalgama.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getArriba().compareTo("arriba_amalgama.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getIzquierda().compareTo("izq_amalgama.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getDerecha().compareTo("der_amalgama.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getCentro().compareTo("centro_amalgama.png") == 0;
        } else if (posicion.compareTo("AdultoInferior") == 0) {
            validacionDienteNormalInferior = odontogramaPacienteAdultoInferior.get(posDiente).getAbajo().compareTo("abajo_normal.png") == 0
                    && odontogramaPacienteAdultoInferior.get(posDiente).getArriba().compareTo("arriba_normal.png") == 0
                    && odontogramaPacienteAdultoInferior.get(posDiente).getIzquierda().compareTo("izq_normal.png") == 0
                    && odontogramaPacienteAdultoInferior.get(posDiente).getDerecha().compareTo("der_normal.png") == 0
                    && odontogramaPacienteAdultoInferior.get(posDiente).getCentro().compareTo("centro_normal.png") == 0;
            validacionDienteConCariesInferior = odontogramaPacienteAdultoInferior.get(posDiente).getAbajo().compareTo("abajo_caries.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getArriba().compareTo("arriba_caries.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getIzquierda().compareTo("izq_caries.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getDerecha().compareTo("der_caries.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getCentro().compareTo("centro_caries.png") == 0;
            validacionDienteConObturacionInferior = odontogramaPacienteAdultoInferior.get(posDiente).getAbajo().compareTo("abajo_amalgama.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getArriba().compareTo("arriba_amalgama.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getIzquierda().compareTo("izq_amalgama.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getDerecha().compareTo("der_amalgama.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getCentro().compareTo("centro_amalgama.png") == 0;
        }
        if (posicion.compareTo("AdultoInferior") == 0) {
            posDiente = posDiente + 16;
        }
        if (posDiente >= 0 && posDiente <= 15 && validacionDienteNormalSuperior || posDiente >= 0 && posDiente <= 15 && validacionDienteConObturacionSuperior && !validacionDienteConCariesSuperior) {
            if (contadoresCariesDiente.get(posDiente)) {
                this.contadoresCariesDiente.set(posDiente, false);
                int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) - 1;
                int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) - 1;
                this.obsOdontograma.setCaries("" + contadorCaries);
                this.cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
            } else if (contadoresObturadosDiente.get(posDiente)) {
                contadoresObturadosDiente.set(posDiente, false);
                int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) - 1;
                int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) - 1;
                obsOdontograma.setObturados("" + contadorObturados);
                cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
            } else if (contadoresPerdidosDiente.get(posDiente)) {
                contadoresPerdidosDiente.set(posDiente, false);
                int contadorPerdidos = Integer.parseInt(obsOdontograma.getObturados()) - 1;
                int contadorPerdidosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) - 1;
                obsOdontograma.setPerdidos("" + contadorPerdidos);
                cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperios);
            }

        } else if (posDiente > 15 && posDiente <= 31 && validacionDienteNormalInferior || posDiente > 15 && posDiente <= 31 && validacionDienteConCariesInferior && !validacionDienteConObturacionInferior) {
            if (contadoresCariesDiente.get(posDiente)) {
                contadoresCariesDiente.set(posDiente, false);
                int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) - 1;
                int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) - 1;
                obsOdontograma.setCaries("" + contadorCaries);
                cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
            } else if (contadoresObturadosDiente.get(posDiente)) {
                contadoresObturadosDiente.set(posDiente, false);
                int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) - 1;
                int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) - 1;
                obsOdontograma.setObturados("" + contadorObturados);
                cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
            } else if (contadoresPerdidosDiente.get(posDiente)) {
                contadoresPerdidosDiente.set(posDiente, false);
                int contadorPerdidos = Integer.parseInt(obsOdontograma.getObturados()) - 1;
                int contadorPerdidosInf = Integer.parseInt(cuadroSintesis.getObturacionInf()) - 1;
                obsOdontograma.setPerdidos("" + contadorPerdidos);
                cuadroSintesis.setFaltantesInf("" + contadorPerdidosInf);
            }
        }

    }

    private void contarCariesCopia(int posDiente, String posicion) {
        if (posicion.compareTo("AdultoInferior") == 0) {
            posDiente = posDiente + 16;
        }
        if (posDiente >= 0 && posDiente <= 15 && !contadoresCariesDiente.get(posDiente)) {
            contadoresCariesDiente.set(posDiente, true);
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente > 15 && posDiente <= 31 && !contadoresCariesDiente.get(posDiente)) {
            contadoresCariesDiente.set(posDiente, true);
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        }
    }

    private void contarObturados(int posDiente, String posicion) {
        if (posicion.compareTo("AdultoInferior") == 0) {
            posDiente = posDiente + 16;
        }
        if (posDiente >= 0 && posDiente <= 15 && !contadoresObturadosDiente.get(posDiente)) {
            contadoresObturadosDiente.set(posDiente, true);
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente > 15 && posDiente <= 31 && !contadoresObturadosDiente.get(posDiente)) {
            contadoresObturadosDiente.set(posDiente, true);
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        }
    }

    public void contarDientesPerdidos(int posDiente, String posicion) {
        if (posicion.compareTo("AdultoInferior") == 0) {
            posDiente = posDiente + 16;
        }
        if (posDiente >= 0 && posDiente <= 15 && !contadoresPerdidosDiente.get(posDiente)) {
            contadoresPerdidosDiente.set(posDiente, true);
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorPerdidosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperios);
        } else if (posDiente > 15 && posDiente <= 31 && !contadoresPerdidosDiente.get(posDiente)) {
            contadoresPerdidosDiente.set(posDiente, true);
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorPerdidosInf = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInf);
        }
    }

    private void registrarActualizacion() {
        if (conAcompaniante != true) {
            this.actualizacionOdo = new ActualizacionOdo();
            this.actualizacionOdo.setIdPaciente(paciente);
            this.actualizacionOdo.setAcompanante("Sin acompañante");
            this.actualizacionOdo.setTelefono("");
            this.actualizacionOdo.setCelular("");
            this.actualizacionOdo.setParentesco("");
            this.actualizacionOdo.setTipo(tipoActualizacion);
            this.actualizacionOdo.setIdUsuario(usuarioDeLaSesion());
            asignarFecha();
            this.actualizacionOdo.setFecha(fechaApertura);
        } else {
            this.actualizacionOdo.setIdPaciente(paciente);
            this.actualizacionOdo.setTipo(tipoActualizacion);
            this.actualizacionOdo.setIdUsuario(usuarioDeLaSesion());
            asignarFecha();
            this.actualizacionOdo.setFecha(fechaApertura);
            this.conAcompaniante = false;
        }

        this.ejbActualizacionOdo.create(actualizacionOdo);
    }

    private void registrarMotivoConsulta() {
        MotivoOdo motivoOdo = new MotivoOdo();

        motivoOdo.setIdActualizacion(actualizacionOdo);
        motivoOdo.setMotivo(motivoConsulta);

        ejbMotivoOdo.create(motivoOdo);
    }

    private void registrarAntecedentesFamiliares() {
        cargarRespuestasAntecedentesFam();
        for (int i = 0; i < listadoRespuestasAntFam.size(); i++) {
            AntOdo antOdo = new AntOdo();
            antOdo.setIdActualizacion(actualizacionOdo);
            antOdo.setTipo("FA");
            antOdo.setResultado(listadoRespuestasAntFam.get(i));
            antOdo.setNombre(listadoAntecedentesFamiliares.get(i).getNombre());
            ejbAntOdo.create(antOdo);
        }
        ObsAntOdo obsAntOdo = new ObsAntOdo();
        obsAntOdo.setIdActualizacion(actualizacionOdo);
        obsAntOdo.setObs(observacionAntecendetesFam);
        obsAntOdo.setTipo("FA");
        ejbObsAntOdo.create(obsAntOdo);
    }

    private void registrarAntecedentesPersonales() {
        cargarRespuestasAntecedentesPer();
        for (int i = 0; i < listadoRespuestasAntPer.size(); i++) {
            AntOdo antOdo = new AntOdo();
            antOdo.setIdActualizacion(actualizacionOdo);
            antOdo.setTipo("PE");
            antOdo.setResultado(listadoRespuestasAntPer.get(i));
            antOdo.setNombre(listadoAntecedentesPersonales.get(i).getNombre());
            ejbAntOdo.create(antOdo);
        }
        ObsAntOdo obsAntOdo = new ObsAntOdo();
        obsAntOdo.setIdActualizacion(actualizacionOdo);
        obsAntOdo.setObs(observacionAntecendetesPer);
        obsAntOdo.setTipo("PE");
        ejbObsAntOdo.create(obsAntOdo);
    }

    public void abrirMensajeAdvertenciaServicio(DetalleServicioPresupuesto detallePresupuesto) {
        this.detalleServicioPresupuestoEliminar = detallePresupuesto;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Adventencia", "¿Esta seguro de eliminar el servicio?"));
        requestContext.execute("PF('dlgMensajeAdventenciaServicio').show()");

    }

    private void registrarExamenEstomatologico() {
        cargarRespuestasExamenEst();
        cargarRespuestasObsExamenEst();
        for (int i = 0; i < listadoRespuestasExmEst.size(); i++) {
            ExaEstomatologico exaEstomatologico = new ExaEstomatologico();
            exaEstomatologico.setIdActualizacion(actualizacionOdo);
            exaEstomatologico.setNombre(listadoEstomatologicos.get(i).getNombre());
            exaEstomatologico.setResultado(listadoRespuestasExmEst.get(i));
            exaEstomatologico.setObs(listadoObservacionesExmEst.get(i));

            ejbExaEstomatologico.create(exaEstomatologico);
        }
        ObsAntOdo obsAntOdo = new ObsAntOdo();
        obsAntOdo.setIdActualizacion(actualizacionOdo);
        obsAntOdo.setObs(observacionGenExmEst);
        obsAntOdo.setTipo("ES");
        ejbObsAntOdo.create(obsAntOdo);
    }

    private void registrarExamenOral() {
        cargarRespuestasExamenOral();
        int contExamenPulpar = 0, contExamenDentario = 0, contPeriodontal = 0;
        for (int i = 0; i < listadoRespuestasExamenOral.size(); i++) {
            ExaOral examenOral = new ExaOral();
            examenOral.setIdActualizacion(actualizacionOdo);
            if (i >= 0 && i < 4) {
                examenOral.setCampo(listadoExamenOralPulpar.get(contExamenPulpar).getNombre());
                examenOral.setValor(listadoRespuestasExamenOral.get(i));
                examenOral.setTipo("PU");
                ejbExaOral.create(examenOral);
                contExamenPulpar++;

            } else if (i >= 4 && i < 9) {
                examenOral.setCampo(listadoExamenOralDentario.get(contExamenDentario).getNombre());
                examenOral.setValor(listadoRespuestasExamenOral.get(i));
                examenOral.setTipo("DE");
                ejbExaOral.create(examenOral);
                contExamenDentario++;
            } else if (i >= 9 && i < 16) {
                examenOral.setCampo(listadoExamenOralPeriodontal.get(contPeriodontal).getNombre());
                examenOral.setValor(listadoRespuestasExamenOral.get(i));
                examenOral.setTipo("PE");
                ejbExaOral.create(examenOral);
                contPeriodontal++;
            } else if (i == 16) {
                ObsExaOral obsExaOral = new ObsExaOral();
                obsExaOral.setIdActualizacion(actualizacionOdo);
                obsExaOral.setObs(listadoRespuestasExamenOral.get(i));
                obsExaOral.setTipo("PU");
                ejbObsExaOral.create(obsExaOral);
            } else if (i == 17) {
                ObsExaOral obsExaOral = new ObsExaOral();
                obsExaOral.setIdActualizacion(actualizacionOdo);
                obsExaOral.setObs(listadoRespuestasExamenOral.get(i));
                obsExaOral.setTipo("DE");
                ejbObsExaOral.create(obsExaOral);
            } else if (i == 18) {
                ObsExaOral obsExaOral = new ObsExaOral();
                obsExaOral.setIdActualizacion(actualizacionOdo);
                obsExaOral.setObs(listadoRespuestasExamenOral.get(i));
                obsExaOral.setTipo("PE");
                ejbObsExaOral.create(obsExaOral);
            }
        }
    }

    private void registrarHigieneOral() {
        this.higiene.setIdActualizacion(actualizacionOdo);
        this.ejbHigiene.create(higiene);
    }

    private void registrarOdontogramaCopia() {
        int contDientesArribaIzq = 18, contDientesArribaDer = 21, contDientesAbajoIzq = 48, contDientesAbajoDer = 31;
        for (int i = 0; i < 16; i++) {
            Odontograma odontograma = new Odontograma();
            if (i >= 0 && i < 8) {
                odontograma.setDiente("" + contDientesArribaIzq);
                contDientesArribaIzq--;
            } else if (i >= 8 && i < 16) {
                odontograma.setDiente("" + contDientesArribaDer);
                contDientesArribaDer++;
            }
            odontograma.setIdActualizacion(actualizacionOdo);
            odontograma.setImgAbajo(odontogramaPacienteAdultoSuperior.get(i).getAbajo());
            odontograma.setImgArriba(odontogramaPacienteAdultoSuperior.get(i).getArriba());
            odontograma.setImgCentro(odontogramaPacienteAdultoSuperior.get(i).getCentro());
            odontograma.setImgIzq(odontogramaPacienteAdultoSuperior.get(i).getIzquierda());
            odontograma.setImgDer(odontogramaPacienteAdultoSuperior.get(i).getDerecha());
            odontograma.setImgFueraBolPer(odontogramaPacienteAdultoSuperior.get(i).getAfueraBolsaPer());
            odontograma.setImgFueraEnd(odontogramaPacienteAdultoSuperior.get(i).getAfueraEnd());
            odontograma.setImgFueraNEnd(odontogramaPacienteAdultoSuperior.get(i).getAfueraNecEnd());
            ejbOdontograma.create(odontograma);
        }
        for (int i = 0; i < 16; i++) {
            Odontograma odontograma = new Odontograma();
            if (i >= 0 && i < 8) {
                odontograma.setDiente("" + contDientesAbajoIzq);
                contDientesAbajoIzq--;
            } else if (i >= 8 && i < 16) {
                odontograma.setDiente("" + contDientesAbajoDer);
                contDientesAbajoDer++;
            }

            odontograma.setIdActualizacion(actualizacionOdo);
            odontograma.setImgAbajo(odontogramaPacienteAdultoInferior.get(i).getAbajo());
            odontograma.setImgArriba(odontogramaPacienteAdultoInferior.get(i).getArriba());
            odontograma.setImgCentro(odontogramaPacienteAdultoInferior.get(i).getCentro());
            odontograma.setImgIzq(odontogramaPacienteAdultoInferior.get(i).getIzquierda());
            odontograma.setImgDer(odontogramaPacienteAdultoInferior.get(i).getDerecha());
            odontograma.setImgFueraBolPer(odontogramaPacienteAdultoInferior.get(i).getAfueraBolsaPer());
            odontograma.setImgFueraEnd(odontogramaPacienteAdultoInferior.get(i).getAfueraEnd());
            odontograma.setImgFueraNEnd(odontogramaPacienteAdultoInferior.get(i).getAfueraNecEnd());
            ejbOdontograma.create(odontograma);
        }

    }

    private void registrarDiagnostico() {
        for (int i = 0; i < listaDiagnosticosTipo.size(); i++) {
            DiagnosticoOdo diagnosticoOdo = new DiagnosticoOdo();
            diagnosticoOdo.setIdActualizacion(actualizacionOdo);
            diagnosticoOdo.setDx(listaDiagnosticosTipo.get(i).getDiagnosticoCie10().getId());
            diagnosticoOdo.setNdx(listaDiagnosticosTipo.get(i).getDiagnosticoCie10().getNombre());
            diagnosticoOdo.setTipodiagnostico(listaDiagnosticosTipo.get(i).getDiagnostico());

            ejbDiagnosticoOdo.create(diagnosticoOdo);
        }
    }

    private void registrarEvolucion() {
        EvolucionOdo evolucionOdontologica = new EvolucionOdo();
        evolucionOdontologica.setEvolucion(descripcionEvolucion);
        evolucionOdontologica.setNumeroDiente(dienteEvolucion);
        evolucionOdontologica.setIdActualizacion(actualizacionOdo);

        ejbEvolucionOdo.create(evolucionOdontologica);
    }

    private void reiniciarVariables() {
        this.actualizacionOdo = new ActualizacionOdo();
        this.conAcompaniante = false;
        this.conAntecedentesFamiliares = false;
        this.conAntecedentesPersonales = false;
        this.conExamenEstomatologico = false;
        this.conExamenPulpar = false;
        this.conExamenTejidosDent = false;
        this.conAlteraciones = false;

        this.motivoConsulta = "";

        this.higiene = new Higiene();
        this.obsOdontograma = new ObsOdontograma();
        this.obsOdontograma.setOclusion("Normal");
        this.obsOdontograma.setCaries("0");
        this.obsOdontograma.setObturados("0");
        this.obsOdontograma.setPerdidos("0");
        listadoAntecedentesFamiliares = new ArrayList<>();
        listadoAntecedentesPersonales = new ArrayList<>();
        listadoEstomatologicos = new ArrayList<>();
        listadoExamenOralPulpar = new ArrayList<>();
        listadoExamenOralDentario = new ArrayList<>();
        listadoExamenOralPeriodontal = new ArrayList<>();
        listadoRespuestasAntFam = new ArrayList<>();
        listadoRespuestasAntPer = new ArrayList<>();
        listadoRespuestasExmEst = new ArrayList<>();
        listadoRespuestasExamenOral = new ArrayList<>();
        listadoObservacionesExmEst = new ArrayList<>();
        respuestasAntecedentesFamiliares = new RespuestasAntecedentesFamiliares();
        respuestasAntecedentesPersonales = new RespuestasAntecedentesPersonales();
        respuestasExamenEstomatologico = new RespuestasExamenEstomatologico();
        respuestasExamenOral = new RespuestasExamenOral();
        cargarAntecedentesFamiliares();
        cargarAntecedentesPersonales();
        cargarEstomatologicos();
        cargarListaExamenOralPulpar();
        cargarListaExamenOralDentario();
        cargarListaExamenOralPeriodontal();
        inicializarOdontograma();
    }

    private void inicializarCuadroSintesis() {
        this.cuadroSintesis.setPresentesSup("16");
        this.cuadroSintesis.setPresentesInf("16");
        this.cuadroSintesis.setFaltantesSup("0");
        this.cuadroSintesis.setFaltantesInf("0");
        this.cuadroSintesis.setCariadosSup("0");
        this.cuadroSintesis.setCariadosInf("0");
        this.cuadroSintesis.setExtraIndSup("0");
        this.cuadroSintesis.setExtraIndInf("0");
        this.cuadroSintesis.setObturacionSup("0");
        this.cuadroSintesis.setObturacionInf("0");
        this.cuadroSintesis.setProtesisFijaSup("0");
        this.cuadroSintesis.setProtesisFijaInf("0");
        this.cuadroSintesis.setProtesisRemSup("0");
        this.cuadroSintesis.setProtesisRemInf("0");
        this.cuadroSintesis.setProtesisTotSup("0");
        this.cuadroSintesis.setProtesisTotInf("0");
        this.cuadroSintesis.setTratamientoCondSup("0");
        this.cuadroSintesis.setTratamientoCondInf("0");
        this.cuadroSintesis.setAnomaliasNSup("0");
        this.cuadroSintesis.setAnomaliasNInf("0");
        this.cuadroSintesis.setAnomaliasForSup("0");
        this.cuadroSintesis.setAnomaliasForInf("0");
        this.cuadroSintesis.setAnomaliasPosSup("0");
        this.cuadroSintesis.setAnomaliasPosInf("0");
        this.cuadroSintesis.setEnfermedadPeriodentalSup("0");
        this.cuadroSintesis.setEnfermedadPeriodentalInf("0");
        this.cuadroSintesis.setNucleosSup("0");
        this.cuadroSintesis.setNucleosInf("0");

    }

    public List<DiagnosticoTipo> getListaDiagnosticosTipo() {
        return listaDiagnosticosTipo;
    }

    public void eliminarDiagnosticoTipo(DiagnosticoTipo diagnosticoTipo) {
        RequestContext requestcontext = RequestContext.getCurrentInstance();

        listaDiagnosticosTipo.remove(diagnosticoTipo);
        requestcontext.update("aperturaHistoriaOdontologica:datalistDiagnosticosTipo");

    }

    public void eliminarServicioDeLista() {
        RequestContext requestcontext = RequestContext.getCurrentInstance();

        listaServicios.remove(this.detalleServicioPresupuestoEliminar);
        calcularPrecioTotal();
        requestcontext.update("aperturaHistoriaOdontologica:datalistTratamiento");
        requestcontext.update("aperturaHistoriaOdontologica:panelPago");
        requestcontext.execute("PF('dlgMensajeAdventenciaServicio').hide()");

    }

    private void registrarObservacionOdontograma() {
        obsOdontograma.setIdActualizacion(actualizacionOdo);
        ejbObsOdontograma.create(obsOdontograma);
    }

    private void registrarCuadroSintesis() {
        this.cuadroSintesis.setIdActualizacion(actualizacionOdo);
        ejbCuadroSintesis.create(cuadroSintesis);
    }

    public void seleccionarDiagnostico(Diagnosticocie10Odo diagnostico) {
        RequestContext requestcontext = RequestContext.getCurrentInstance();
        diagnosticoTipo = new DiagnosticoTipo();

        diagnosticoTipo.setDiagnosticoCie10(diagnostico);
        requestcontext.execute("PF('SeleccionarTipoDiagnotico').show()");

    }

    public void seleccionarTipoDiagnostico(Tipodiagnostico tipodiagnostico) {
        RequestContext requestcontext = RequestContext.getCurrentInstance();
        diagnosticoTipo.setDiagnostico(tipodiagnostico);
        requestcontext.execute("PF('SeleccionarTipoDiagnotico').hide()");

        listaDiagnosticosTipo.add(diagnosticoTipo);
        tipoDiagnostico = new Tipodiagnostico();
        requestcontext.update("aperturaHistoriaOdontologica:datalistDiagnosticosTipo");
    }

    public void seleccionarTrataminto(ServiciosOdo serviciosOdo) {
        RequestContext requestcontext = RequestContext.getCurrentInstance();
        DetalleServicioPresupuesto detalleServicio = new DetalleServicioPresupuesto();
        detalleServicio.setServicioOdo(serviciosOdo);
        detalleServicio.setCantidad(1);
        if (!conPosgrado) {
            detalleServicio.calcularPrecioPregrado();
        } else {
            detalleServicio.calcularPrecioPosgrado();
        }

        listaServiciosTemporal.add(detalleServicio);
        copiarListas();
        calcularPrecioTotal();
        requestcontext.update("aperturaHistoriaOdontologica:datalistTratamiento");
        requestcontext.update("aperturaHistoriaOdontologica:panelPago");

    }

    public void calcularPrecioTotal() {
        totalPagar = 0;
        for (int i = 0; i < listaServicios.size(); i++) {
            float total = listaServicios.get(i).getPrecio();
            totalPagar = totalPagar + total;
        }
    }

    public void sumaCantidad(DetalleServicioPresupuesto detalleServicioP) {

        for (int i = 0; i < listaServiciosTemporal.size(); i++) {
            if (listaServicios.get(i).equals(detalleServicioP)) {
                int cantidadActual = detalleServicioP.getCantidad();
                detalleServicioP.setCantidad(cantidadActual + 1);
                if (!conPosgrado) {
                    detalleServicioP.calcularPrecioPregrado();
                } else {
                    detalleServicioP.calcularPrecioPosgrado();
                }

                listaServiciosTemporal.set(i, detalleServicioP);
            }

        }
        copiarListas();
        calcularPrecioTotal();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("aperturaHistoriaOdontologica:datalistTratamiento");
        requestContext.update("aperturaHistoriaOdontologica:panelPago");

    }

    public boolean verificarApertura(Paciente paciente) {
        return ejbActualizacionOdo.buscarPorPacienteBool(paciente.getId());
    }

    public void menosCantidad(DetalleServicioPresupuesto detalleServicioP) {

        for (int i = 0; i < listaServiciosTemporal.size(); i++) {
            if (listaServicios.get(i).equals(detalleServicioP)) {
                int cantidadActual = detalleServicioP.getCantidad();
                detalleServicioP.setCantidad(cantidadActual - 1);
                if (!conPosgrado) {
                    detalleServicioP.calcularPrecioPregrado();
                } else {
                    detalleServicioP.calcularPrecioPosgrado();
                }
                listaServiciosTemporal.set(i, detalleServicioP);
            }

        }
        copiarListas();
        calcularPrecioTotal();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("aperturaHistoriaOdontologica:datalistTratamiento");
        requestContext.update("aperturaHistoriaOdontologica:panelPago");

    }

    public void terminarApertura(CargarVistaController cargarVista) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('registroExitosoDialog').hide()");

        inicializarVariables();
        requestContext.update("InformacionPaciente");
        requestContext.update("aperturaHistoriaOdontologica");
        cargarVista.cargarListaPaciente();

    }

    public void copiarListas() {
        listaServicios = new ArrayList<>();
        for (int i = 0; i < listaServiciosTemporal.size(); i++) {
            listaServicios.add(crearCopiaObjeto(listaServiciosTemporal.get(i)));
        }
    }

    public DetalleServicioPresupuesto crearCopiaObjeto(DetalleServicioPresupuesto detalleServicioPresupuesto) {
        DetalleServicioPresupuesto copia = new DetalleServicioPresupuesto();
        copia.setCantidad(detalleServicioPresupuesto.getCantidad());
        copia.setPrecio(detalleServicioPresupuesto.getPrecio());
        copia.setServicioOdo(copiaServicio(detalleServicioPresupuesto.getServicioOdo()));

        return copia;
    }

    public ServiciosOdo copiaServicio(ServiciosOdo serviciosOdo) {
        ServiciosOdo serviciosOdoCopia = new ServiciosOdo();
        serviciosOdoCopia.setId(serviciosOdo.getId());
        serviciosOdoCopia.setNombre(serviciosOdo.getNombre());
        if (!conPosgrado) {
            serviciosOdoCopia.setValorPre(serviciosOdo.getValorPre());
        } else {
            serviciosOdoCopia.setValorPre(serviciosOdo.getValorPos());
        }

        return serviciosOdoCopia;

    }

    private void inicializarVariablesBooleanas() {
        this.contadoresCariesDiente = new ArrayList<>();
        this.contadoresObturadosDiente = new ArrayList<>();
        this.contadoresPerdidosDiente = new ArrayList<>();
        this.conAcompaniante = false;
        this.conAntecedentesFamiliares = false;
        this.conAntecedentesPersonales = false;
        this.conExamenEstomatologico = false;
        this.conExamenPulpar = false;
        this.conExamenTejidosDent = false;
        this.conAlteraciones = false;
        this.seleccionDienteNormal = false;
        this.seleccionDienteCaries = false;
        this.seleccionDienteAmalgama = false;
        this.seleccionDienteObstPlastica = false;
        this.seleccionDienteObstTemporal = false;
        this.seleccionDienteConSellante = false;
        this.seleccionDienteSinSellante = false;
        this.seleccionDienteFaltante = false;
        this.seleccionDienteFaltanteExt = false;
        this.seleccionDienteExodoncia = false;
        this.seleccionDienteProtesis = false;
        this.seleccionDienteProtesisTotal = false;
        this.seleccionDienteNecendodoncia = false;
        this.seleccionDienteTtoEndodoncia = false;
        this.seleccionDienteBolsaPer = false;
        this.conAcompaniante = false;
        this.conAntecedentesFamiliares = false;
        this.conAntecedentesPersonales = false;
        this.conExamenEstomatologico = false;
        this.conExamenPulpar = false;
        this.conExamenTejidosDent = false;
        this.conAlteraciones = false;
        this.seleccionDienteNormal = false;
        this.seleccionDienteCaries = false;
        this.seleccionDienteAmalgama = false;
        this.seleccionDienteObstPlastica = false;
        this.seleccionDienteObstTemporal = false;
        this.seleccionDienteConSellante = false;
        this.seleccionDienteSinSellante = false;
        this.seleccionDienteFaltante = false;
        this.seleccionDienteFaltanteExt = false;
        this.seleccionDienteExodoncia = false;
        this.seleccionDienteProtesis = false;
        this.seleccionDienteProtesisTotal = false;
        this.seleccionDienteNecendodoncia = false;
        this.seleccionDienteTtoEndodoncia = false;
        this.seleccionDienteBolsaPer = false;
        for (int i = 0; i < 32; i++) {
            contadoresCariesDiente.add(false);
            contadoresObturadosDiente.add(false);
            contadoresPerdidosDiente.add(false);
        }

    }

    public void inicializarVariables() {
        this.contadoresCariesDiente = new ArrayList<>();
        this.contadoresObturadosDiente = new ArrayList<>();
        this.contadoresPerdidosDiente = new ArrayList<>();
        this.actualizacionOdo = new ActualizacionOdo();
        inicializarVariablesBooleanas();

        //inicilizacion string
        paciente = new Paciente();
        pacienteSeleccionado = false;
        this.higiene = new Higiene();
        listadoAntecedentesFamiliares = new ArrayList<>();
        listadoAntecedentesPersonales = new ArrayList<>();
        listadoEstomatologicos = new ArrayList<>();
        listadoExamenOralPulpar = new ArrayList<>();
        listadoExamenOralDentario = new ArrayList<>();
        listadoExamenOralPeriodontal = new ArrayList<>();
        listadoRespuestasAntFam = new ArrayList<>();
        listadoRespuestasAntPer = new ArrayList<>();
        listadoRespuestasExmEst = new ArrayList<>();
        listadoRespuestasExamenOral = new ArrayList<>();
        listadoObservacionesExmEst = new ArrayList<>();
        listaDiagnosticosTipo = new ArrayList<>();
        listaServicios = new ArrayList<>();
        listaServiciosTemporal = new ArrayList<>();
        respuestasAntecedentesFamiliares = new RespuestasAntecedentesFamiliares();
        respuestasAntecedentesPersonales = new RespuestasAntecedentesPersonales();
        respuestasExamenEstomatologico = new RespuestasExamenEstomatologico();
        respuestasExamenOral = new RespuestasExamenOral();
        this.tipoDiagnostico = new Tipodiagnostico();
        odontogramaPaciente = new ArrayList<>();

        this.actualizacionOdo = new ActualizacionOdo();
        this.higiene = new Higiene();
        this.obsOdontograma = new ObsOdontograma();
        this.obsOdontograma.setOclusion("Normal");
        this.obsOdontograma.setCaries("0");
        this.obsOdontograma.setObturados("0");
        this.obsOdontograma.setPerdidos("0");
        this.tipoDiagnostico = new Tipodiagnostico();
        this.cuadroSintesis = new CuadroSintesis();
        this.diagnosticoDiente = "normal";
        cargarAntecedentesFamiliares();
        cargarAntecedentesPersonales();
        cargarEstomatologicos();
        cargarListaExamenOralPulpar();
        cargarListaExamenOralDentario();
        cargarListaExamenOralPeriodontal();
        inicializarOdontograma();
        inicializarCuadroSintesis();
        for (int i = 0; i < 32; i++) {
            contadoresCariesDiente.add(false);
            contadoresObturadosDiente.add(false);
            contadoresPerdidosDiente.add(false);
        }

    }
}
