/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.clases.DiagnosticoCantidad;
import com.unicauca.divsalud.clases.DiagnosticoGeneral;
import com.unicauca.divsalud.clases.FacultadDiagnostico;
import com.unicauca.divsalud.clases.IndiceCOP;
import com.unicauca.divsalud.clases.ProgramaDiagnostico;
import com.unicauca.divsalud.entidades.DiagnosticoOdo;
import com.unicauca.divsalud.entidades.Facultad;
import com.unicauca.divsalud.entidades.ObsOdontograma;
import com.unicauca.divsalud.entidades.Programas;

import com.unicauca.divsalud.entidades.ConsultaMedicaMed;
import com.unicauca.divsalud.entidades.Paciente;
import com.unicauca.divsalud.clases.ReportesMedicos;
import com.unicauca.divsalud.clases.resultadoEstadistica;
import com.unicauca.divsalud.entidades.Diagnosticos;

import com.unicauca.divsalud.sessionbeans.ActualizacionOdoFacade;
import com.unicauca.divsalud.sessionbeans.DiagnosticoOdoFacade;
import com.unicauca.divsalud.sessionbeans.FacultadFacade;
import com.unicauca.divsalud.sessionbeans.ObsOdontogramaFacade;
import com.unicauca.divsalud.sessionbeans.ProgramasFacade;
import com.unicauca.divsalud.sessionbeans.ConsultaMedicaMedFacade;
import com.unicauca.divsalud.sessionbeans.DiagnosticosFacade;
import com.unicauca.divsalud.sessionbeans.PacienteFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;
import java.lang.String;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@Named(value = "estadisticasMedController")
@SessionScoped
public class EstadisticasMedController implements Serializable {

    @EJB
    private FacultadFacade ejbFacultad;

    @EJB
    private ProgramasFacade ejbProgramas;
    @EJB
    private ConsultaMedicaMedFacade ejbConsultaMedicaMed;
    @EJB
    private PacienteFacade ejbPaciente;
    @EJB
    private DiagnosticosFacade ejbDiagnosticos;
    @EJB
    private FacultadFacade ejbFacultades;

    private List<Diagnosticos> listaDiagnosticos;
    private List<ConsultaMedicaMed> listaConsultaMedicaMed;
    private List<Paciente> listaPaciente;
    private List<Facultad> listaFacultades;
    private List<Programas> listaProgramas;
    private List<FacultadDiagnostico> listaDiagnosticoFacultades;
    private List<ProgramaDiagnostico> listaDiagnosticoProgramas;
    private boolean tipoReporte;
    
    
    private List<Diagnosticos> Artes = new ArrayList<>();
    private List<Diagnosticos> Agrarias = new ArrayList<>();
    private List<Diagnosticos> Salud = new ArrayList<>();
    private List<Diagnosticos> Contables = new ArrayList<>();
    private List<Diagnosticos> Humanas = new ArrayList<>();
    private List<Diagnosticos> Educacion = new ArrayList<>();
    private List<Diagnosticos> Derecho = new ArrayList<>();
    private List<Diagnosticos> Civil = new ArrayList<>();
    private List<Diagnosticos> Electronica = new ArrayList<>();
    
    //@ManagedProperty("#{resulestadisticas}")
    private List<resultadoEstadistica> rGeneral= new ArrayList<>();
    private List<resultadoEstadistica> rArtes= new ArrayList<>();
    private List<resultadoEstadistica> rAgrarias= new ArrayList<>();
    private List<resultadoEstadistica> rSalud= new ArrayList<>();
    private List<resultadoEstadistica> rContable= new ArrayList<>();
    private List<resultadoEstadistica> rHumanas= new ArrayList<>();
    private List<resultadoEstadistica> rEducacion= new ArrayList<>();
    private List<resultadoEstadistica> rDerecho= new ArrayList<>();
    private List<resultadoEstadistica> rCivil= new ArrayList<>();
    private List<resultadoEstadistica> rElectronica = new ArrayList<>();

    private int contadordiagnosticosmedicas;
    private int contadormujeres;
    private int contadorhombres;
    private int hombres = 0;
    private int mujeres = 0;
    private int contadorD = 0;
    private ReportesMedicos reportesmedicos;
    private DiagnosticoGeneral diagnosticoGeneral;
    private double porcentajeHombres = 0;
    private double porcentajeMujeres = 0;
    

    private BarChartModel barModel;
    private PieChartModel modeloResultadosMedicas;
    private PieChartModel modeloResultadosHombres;
    private PieChartModel modeloResultadosMujeres;
    private PieChartModel pieModel2; //nueva grafica
    
    private boolean banderaTabla;
    private boolean banderaMensaje;//

    public EstadisticasMedController() {
        
        banderaTabla=false;
        banderaMensaje=false;
        
        //diagnosticos = new ArrayList<>();
        listaDiagnosticos = new ArrayList<>();
        listaConsultaMedicaMed = new ArrayList<>();
        listaPaciente = new ArrayList<>();
        listaFacultades = new ArrayList<>();
        listaProgramas = new ArrayList<>();
        listaDiagnosticoProgramas = new ArrayList<>();
        tipoReporte = true;
        reportesmedicos = new ReportesMedicos();
        diagnosticoGeneral = new DiagnosticoGeneral();
        listaDiagnosticoFacultades = new ArrayList<>();
        modeloResultadosMedicas = new PieChartModel();
        modeloResultadosHombres = new PieChartModel();
        modeloResultadosMujeres = new PieChartModel();
        pieModel2 = new PieChartModel();
    }

    public boolean isBanderaTabla() {
        return banderaTabla;
    }

    public void setBanderaTabla(boolean banderaTabla) {
        this.banderaTabla = banderaTabla;
    }

    public boolean isBanderaMensaje() {
        return banderaMensaje;
    }

    public void setBanderaMensaje(boolean banderaMensaje) {
        this.banderaMensaje = banderaMensaje;
    }
    
    

    public List<resultadoEstadistica> getrGeneral() {
        return rGeneral;
    }

    public void setrGeneral(List<resultadoEstadistica> rGeneral) {
        this.rGeneral = rGeneral;
    }

    
    
    public List<Diagnosticos> getArtes() {
        return Artes;
    }

    public void setArtes(List<Diagnosticos> Artes) {
        this.Artes = Artes;
    }

    
    
    public List<resultadoEstadistica> getrArtes() {
        return rArtes;
    }

    public void setrArtes(List<resultadoEstadistica> rArtes) {
        this.rArtes = rArtes;
    }

    public List<resultadoEstadistica> getrAgrarias() {
        return rAgrarias;
    }

    public void setrAgrarias(List<resultadoEstadistica> rAgrarias) {
        this.rAgrarias = rAgrarias;
    }

    public List<resultadoEstadistica> getrSalud() {
        return rSalud;
    }

    public void setrSalud(List<resultadoEstadistica> rSalud) {
        this.rSalud = rSalud;
    }

    public List<resultadoEstadistica> getrContable() {
        return rContable;
    }

    public void setrContable(List<resultadoEstadistica> rContable) {
        this.rContable = rContable;
    }

    public List<resultadoEstadistica> getrHumanas() {
        return rHumanas;
    }

    public void setrHumanas(List<resultadoEstadistica> rHumanas) {
        this.rHumanas = rHumanas;
    }

    public List<resultadoEstadistica> getrEducacion() {
        return rEducacion;
    }

    public void setrEducacion(List<resultadoEstadistica> rEducacion) {
        this.rEducacion = rEducacion;
    }

    public List<resultadoEstadistica> getrDerecho() {
        return rDerecho;
    }

    public void setrDerecho(List<resultadoEstadistica> rDerecho) {
        this.rDerecho = rDerecho;
    }

    public List<resultadoEstadistica> getrCivil() {
        return rCivil;
    }

    public void setrCivil(List<resultadoEstadistica> rCivil) {
        this.rCivil = rCivil;
    }

    public List<resultadoEstadistica> getrElectronica() {
        return rElectronica;
    }

    public void setrElectronica(List<resultadoEstadistica> rElectronica) {
        this.rElectronica = rElectronica;
    }

    

    

    
    
    @PostConstruct
    public void init() {
        createPieModel2(0, 0);
        listaDiagnosticos = ejbDiagnosticos.findAll();
        listaConsultaMedicaMed = ejbConsultaMedicaMed.findAll();
        listaPaciente = ejbPaciente.findAll();
        listaFacultades = ejbFacultad.findAll();
        listaProgramas = ejbProgramas.findAll();
    }

    public DiagnosticoGeneral getDiagnosticoGeneral() {
        return diagnosticoGeneral;
    }

    public void setDiagnosticoGeneral(DiagnosticoGeneral diagnosticoGeneral) {
        this.diagnosticoGeneral = diagnosticoGeneral;
    }
    
    public int getHombres() {
        return hombres;
    }

    public void setHombres(int hombres) {
        this.hombres = hombres;
    }

    public int getMujeres() {
        return mujeres;
    }

    public void setMujeres(int mujeres) {
        this.mujeres = mujeres;
    }

    public int getContadorD() {
        return contadorD;
    }

    public void setContadorD(int contadorD) {
        this.contadorD = contadorD;
    }

    
    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public PieChartModel getModeloResultadosHombres() {
        return modeloResultadosHombres;
    }

    public void setModeloResultadosHombres(PieChartModel modeloResultadosHombres) {
        this.modeloResultadosHombres = modeloResultadosHombres;
    }

    public PieChartModel getModeloResultadosMujeres() {
        return modeloResultadosMujeres;
    }

    public void setModeloResultadosMujeres(PieChartModel modeloResultadosMujeres) {
        this.modeloResultadosMujeres = modeloResultadosMujeres;
    }
    
    public ReportesMedicos getReportesmedicos() {
        return reportesmedicos;
    }

    public List<FacultadDiagnostico> getListaDiagnosticoFacultades() {
        return listaDiagnosticoFacultades;
    }

    public void setListaDiagnosticoFacultades(List<FacultadDiagnostico> listaDiagnosticoFacultades) {
        this.listaDiagnosticoFacultades = listaDiagnosticoFacultades;
    }

    public List<ProgramaDiagnostico> getListaDiagnosticoProgramas() {
        return listaDiagnosticoProgramas;
    }

    public boolean isTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(boolean tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
    
    public void setListaDiagnosticoProgramas(List<ProgramaDiagnostico> listaDiagnosticoProgramas) {
        this.listaDiagnosticoProgramas = listaDiagnosticoProgramas;
    }

    public void setReportesmedicos(ReportesMedicos reportesmedicos) {
        this.reportesmedicos = reportesmedicos;
    }

    public List<Facultad> getListaFacultades() {
        return listaFacultades;
    }

    public void setListaFacultades(List<Facultad> listaFacultades) {
        this.listaFacultades = listaFacultades;
    }

    public List<ConsultaMedicaMed> getListaConsultaMedicaMed() {
        return listaConsultaMedicaMed;
    }

    public void setListaConsultaMedicaMed(List<ConsultaMedicaMed> listaConsultaMedicaMed) {
        this.listaConsultaMedicaMed = listaConsultaMedicaMed;
    }

    public List<Paciente> getListaPaciente() {
        return listaPaciente;
    }

    public void setListaPaciente(List<Paciente> listaPaciente) {
        this.listaPaciente = listaPaciente;
    }

    public PieChartModel getModeloResultadosMedicas() {
        return modeloResultadosMedicas;
    }

    public void setModeloResultadosMedicas(PieChartModel modeloResultadosMedicas) {
        this.modeloResultadosMedicas = modeloResultadosMedicas;
    }

    public List<Programas> getListaProgramas() {
        return listaProgramas;
    }

    public void setListaProgramas(List<Programas> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    // de vuelve una lista con los diagnosticos que coinciden con el diagnostico del filtro
    public List<Diagnosticos> diagnosticoElegido() {
        listaDiagnosticos = ejbDiagnosticos.findAll();
        contadordiagnosticosmedicas = 0;
        List<Diagnosticos> diagnosticosfiltro = new ArrayList<>();   //lista que almacena los diagnosticos iguales al seleccionado en el filtro
        System.out.println("hola1");

        for (int i = 0; i < listaDiagnosticos.size(); i++) {
            System.out.println("hola2");
            if (listaDiagnosticos.get(i).getDiagnosticosPK().getCodigoCie10().equals(reportesmedicos.getDiagnostico().getCodigo())) {
                diagnosticosfiltro.add(listaDiagnosticos.get(i));
                System.out.println(diagnosticosfiltro.size());
            }
        }
        return diagnosticosfiltro;
    }

    //devuelve una lista con las consultas de los diagnosticos seleccionados
    public List<ConsultaMedicaMed> filtroConsultaMedica(List<Diagnosticos> filtroDiagnosticos) {
        int compararfechadesde = 0;
        int compararfechahasta = 0;

        List<ConsultaMedicaMed> filtroConsultaMedicas = new ArrayList<>();
        for (int i = 0; i < filtroDiagnosticos.size(); i++) {
            for (int j = 0; j < listaConsultaMedicaMed.size(); j++) {
                int idConsultaMedicadeDiagnostico = filtroDiagnosticos.get(i).getDiagnosticosPK().getIdxConsulta();
                int idConsultaMedica = listaConsultaMedicaMed.get(j).getIdx();
                if (idConsultaMedicadeDiagnostico == idConsultaMedica) {
                    compararfechadesde = listaConsultaMedicaMed.get(j).getFecha().compareTo(reportesmedicos.getFechadesde());
                    compararfechahasta = listaConsultaMedicaMed.get(j).getFecha().compareTo(reportesmedicos.getFechahasta());
                    if (compararfechadesde > 0 || compararfechadesde == 0) {
                        if (compararfechahasta < 0 || compararfechahasta == 0) {
                            filtroConsultaMedicas.add(listaConsultaMedicaMed.get(j));
                        }
                    }
                }
            }
        }
        return filtroConsultaMedicas;
    }
    
    public List<Diagnosticos> validarFecha(List<Diagnosticos> filtroDiagnosticos)
    {
    int compararfechadesde = 0;
    int compararfechahasta = 0;
    List<Diagnosticos> diagnosticosEntreFecha=new ArrayList<>();
    for (int i = 0; i < filtroDiagnosticos.size(); i++) {
                    compararfechadesde = filtroDiagnosticos.get(i).getConsultaMedicaMed().getFecha().compareTo(reportesmedicos.getFechadesde());
                    compararfechahasta = filtroDiagnosticos.get(i).getConsultaMedicaMed().getFecha().compareTo(reportesmedicos.getFechahasta());
                    if (compararfechadesde > 0 || compararfechadesde == 0) {
                        if (compararfechahasta < 0 || compararfechahasta == 0) {
                            diagnosticosEntreFecha.add(filtroDiagnosticos.get(i));
                        }
                        
                    }
    }
    return diagnosticosEntreFecha;
    }
    //devuelve una lista de consultas sin repetir pacientes con el mismo diagnostico
    public List<ConsultaMedicaMed> EliminarDuplicados(List<ConsultaMedicaMed> filtroConsultas)
    {
        int bandera=0;
        int idPacienteEnConsulta=0;
        int idPacienteNoRepetido=0;
        List<ConsultaMedicaMed> lista2 = new ArrayList<>();
        lista2.add(filtroConsultas.get(0));
        
        for(int i=1;i<filtroConsultas.size();i++)
        {
            bandera=0;
            for(int j=0;j<lista2.size();j++)
            {
                idPacienteEnConsulta=filtroConsultas.get(i).getPacienteIdx().getId();
                idPacienteNoRepetido=lista2.get(j).getPacienteIdx().getId();
                if(idPacienteEnConsulta==idPacienteNoRepetido)
                {
                    bandera=1;
                    j=lista2.size();
                }
            }
            if(bandera==0)
            {
                lista2.add(filtroConsultas.get(i));
            }
            
        }
        return lista2;
    }
            

   
    //devuelve una lista de pacientes que estan en una lista de consulta
    public List<Paciente> filtroPaciente(List<ConsultaMedicaMed> filtroConsultas) {

        List<Paciente> filtroPacientes = new ArrayList<>();
        List<ConsultaMedicaMed> auxiliar = new ArrayList<>();
        auxiliar = EliminarDuplicados(filtroConsultas);


        for (int i = 0; i < auxiliar.size(); i++) {
            for (int j = 0; j < listaPaciente.size(); j++) {
                System.out.println("pacientes en consulta" + auxiliar.get(i).getPacienteIdx().getId());
                if (auxiliar.get(i).getPacienteIdx().getId() == listaPaciente.get(j).getId()) {
                    filtroPacientes.add(listaPaciente.get(j));
                }
            }
        }
        return filtroPacientes;
    }

    public List<Paciente> programaEstudiantes(List<Paciente> filtroPacientes) {
        List<Paciente> filtroPacientesPrograma = new ArrayList<>();
        for (int i = 0; i < filtroPacientes.size(); i++) {
            if (filtroPacientes.get(i).getPrograma().getNombre().equals(reportesmedicos.getPrograma())) {
                filtroPacientesPrograma.add(filtroPacientes.get(i));
            }
        }
        return filtroPacientesPrograma;
    }

    public void generoDelPaciente(List<Paciente> listaPaciente) {

        contadormujeres = 0;
        contadorhombres = 0;
        int idconsultapaciente = 0;
        int idpaciente = 0;
        String mujeres = "F";
        String hombres = "M";
        String pacientes = "";

        for (int j = 0; j < listaPaciente.size(); j++) {
            pacientes = listaPaciente.get(j).getSexo().toString();

            if (pacientes.equals(hombres)) {

                contadorhombres++;
            } else if (pacientes.equals(mujeres)) {
                contadormujeres++;
            }

        }

        reportesmedicos.setContadorhombres(contadorhombres);
        reportesmedicos.setContadormujeres(contadormujeres);

    }
     public void generarEstadisticaFacultadDiagnostico() {

        List<Diagnosticos> listDiagnosticosFiltro = new ArrayList<>();
        List<Facultad> listaFacultadDiagnostico = new ArrayList<>();
        diagnosticoGeneral = new DiagnosticoGeneral();

        listDiagnosticosFiltro = ejbDiagnosticos.listadoDiagnosticoFecha(reportesmedicos.getDiagnostico(), reportesmedicos.getFechadesde(),reportesmedicos.getFechahasta());

        listaFacultadDiagnostico = ejbFacultad.findAll();
        String facultad = "";
        listaDiagnosticoFacultades.clear();

        for (int i = 0; i < listaFacultadDiagnostico.size(); i++) {
            FacultadDiagnostico facultadDiag = new FacultadDiagnostico();
            facultadDiag.setFacultad(listaFacultadDiagnostico.get(i).getNombre());
            listaDiagnosticoFacultades.add(facultadDiag);
        }
            int contDiagnosticoF = 0;
            int contHombresF = 0;
            int contMujeresF = 0;
            
        for (int i = 0; i < listDiagnosticosFiltro.size(); i++) {
            
            facultad = listDiagnosticosFiltro.get(i).getConsultaMedicaMed().getPacienteIdx().getFacultad().getNombre();

            for (int j = 0; j < listaDiagnosticoFacultades.size(); j++) {
                if (facultad.equals(listaDiagnosticoFacultades.get(j).getFacultad())) {
                        contadorD++;
                        contDiagnosticoF = listaDiagnosticoFacultades.get(i).getContDiagnosticoFacultad();
                        contDiagnosticoF++;
                        System.out.println("Contador Facultad:"+ contDiagnosticoF);
                        listaDiagnosticoFacultades.get(j).setContDiagnosticoFacultad(contDiagnosticoF);
                        System.out.println("Revisar: ********************");
                    
                    if (listDiagnosticosFiltro.get(i).getConsultaMedicaMed().getPacienteIdx().getSexo().compareTo('M') == 0) {
                        hombres++;
                        contHombresF = listaDiagnosticoFacultades.get(i).getContHombreF();
                        contHombresF++;
                        System.out.println("Contador FacultadH:"+ contHombresF);
                        listaDiagnosticoFacultades.get(j).setContHombreF(contHombresF);
                    }
                    if (listDiagnosticosFiltro.get(i).getConsultaMedicaMed().getPacienteIdx().getSexo().compareTo('F') == 0) {
                        mujeres++;
                        contMujeresF = listaDiagnosticoFacultades.get(i).getContMujerF();
                        contMujeresF++;
                        System.out.println("Contador FacultadM:"+ contMujeresF);
                        listaDiagnosticoFacultades.get(j).setContMujerF(contMujeresF);
                    }
                }
                
            }
        }
        diagnosticoGeneral.setDiagnosticoTotal(contadorD);
        diagnosticoGeneral.setHombresTotales(hombres);
        diagnosticoGeneral.setMujeresTotales(mujeres);
                
        //porcentajeHombres = calcularporcentaje(diagnosticoGeneral.getDiagnosticoTotal(), diagnosticoGeneral.getHombresTotales());

        //cargarModelosGrafica();
    }
    
    public void generarEstadisticaProgramaDiagnostico() {

        List<Diagnosticos> listDiagnosticosFiltro = new ArrayList<>();
        List<Programas> listaProgramaDiagnostico = new ArrayList<>();

        listDiagnosticosFiltro = ejbDiagnosticos.listadoDiagnosticoFecha(reportesmedicos.getDiagnostico(), reportesmedicos.getFechadesde(),reportesmedicos.getFechahasta());

        listaProgramaDiagnostico = ejbProgramas.findAll();
        String programa = "";
        listaDiagnosticoProgramas.clear();

        for (int i = 0; i < listaProgramaDiagnostico.size(); i++) {
            ProgramaDiagnostico programaDiag = new ProgramaDiagnostico();
            programaDiag.setPrograma(listaProgramaDiagnostico.get(i).getNombre());
            listaDiagnosticoProgramas.add(programaDiag);
        }
            int contDiagnosticoP = 0;
            int contHombresP = 0;
            int contMujeresP = 0;
        for (int i = 0; i < listDiagnosticosFiltro.size(); i++) {
            
            programa = listDiagnosticosFiltro.get(i).getConsultaMedicaMed().getPacienteIdx().getPrograma().getNombre();

            for (int j = 0; j < listaDiagnosticoProgramas.size(); j++) {
                if (programa.equals(listaDiagnosticoProgramas.get(j).getPrograma())) {    contDiagnosticoP = listaDiagnosticoProgramas.get(i).getContDiagnosticoPrograma();
                        contDiagnosticoP++;
                        System.out.println("Contador DiagnosticoP:"+ contDiagnosticoP);
                        listaDiagnosticoProgramas.get(j).setContDiagnosticoPrograma(contDiagnosticoP);
                        System.out.println("Revisar: ******************");
                    
                    if (listDiagnosticosFiltro.get(i).getConsultaMedicaMed().getPacienteIdx().getSexo().compareTo('M') == 0) {
                        contHombresP = listaDiagnosticoProgramas.get(i).getContHombresP();
                        contHombresP++;
                        System.out.println("Contador ProgramaH:"+ contHombresP);
                        listaDiagnosticoProgramas.get(j).setContHombresP(contHombresP);
                    }
                    if (listDiagnosticosFiltro.get(i).getConsultaMedicaMed().getPacienteIdx().getSexo().compareTo('F') == 0) {
                        contMujeresP = listaDiagnosticoProgramas.get(i).getContMujeresP();
                        contMujeresP++;
                        System.out.println("Contador ProgramaM:"+ contMujeresP);
                        listaDiagnosticoProgramas.get(j).setContMujeresP(contMujeresP);
                    }
                }
            }
        }
        //porcentajeHombres = calcularporcentaje(contDiagnosticoP, contHombresP);
    }

    public void tipoReporteDiagnostico(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            tipoReporte = true;
        } else {
            tipoReporte = false;
        }
    }

    private void createBarModels() {
        createBarModel();

    }

    public void createBarModel() {

        barModel = new BarChartModel();
        ChartSeries M = new ChartSeries();
        ChartSeries F = new ChartSeries();

        M.setLabel("Hombres");
        M.set("genero", 120);
        M.set("genq", 150);

        F.setLabel("Mujeres");
        F.set("genero", 125);
        F.set("genq", 135);

        barModel.addSeries(M);

        barModel.addSeries(F);

        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Hombres");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("cantidad");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    /**
     *
     * @param fecha
     * @return
     */
    public java.sql.Date convertirFecha(Date fecha) {
        long convertir = 0;
        convertir = fecha.getTime();
        java.sql.Date sqlDate = new java.sql.Date(convertir);
        return sqlDate;
    }

    public void programaElegido() {
        reportesmedicos.getPrograma();
        System.out.println("programa" + reportesmedicos.getPrograma());

    }

    public double calcularPorcentajePacientesMedicas(int cantidadporgenero, int totalpacientes) {
        double porcentaje;
        porcentaje = (100 * cantidadporgenero) / totalpacientes;

        return porcentaje;
    }

    public void GenerarReporteMed() {

        List<Diagnosticos> filtroDiagnosticos = new ArrayList<>();
        List<ConsultaMedicaMed> filtroConsultaMedicaMed = new ArrayList<>();
        List<Paciente> filtroPaciente = new ArrayList<>();
        List<Facultad> filtroFacultades = new ArrayList<>();
        List<Programas> filtroProgramas = new ArrayList<>();
        
        filtroDiagnosticos.clear();
        filtroConsultaMedicaMed.clear();
        filtroPaciente.clear(); 
        filtroFacultades.clear();
        filtroProgramas.clear();
                
        filtroDiagnosticos = this.diagnosticoElegido();
        filtroConsultaMedicaMed = this.filtroConsultaMedica(filtroDiagnosticos);
        filtroPaciente = this.filtroPaciente(filtroConsultaMedicaMed);
        filtroPaciente = this.programaEstudiantes(filtroPaciente);
        generarEstadisticaFacultadDiagnostico();
        generarEstadisticaProgramaDiagnostico();

        reportesmedicos.setCantidad(filtroPaciente.size());
        this.generoDelPaciente(filtroPaciente);
        // createPieModel2(reportesmedicos.getContadorhombres(),reportesmedicos.getContadormujeres());
        //  this.generoDelPaciente();
        //this.cargarModeloMedicas();
        //this.createBarModel();
        pieModel2.clear();
        pieModel2.set("Hombres", reportesmedicos.getContadorhombres());
        pieModel2.set("Mujeres", reportesmedicos.getContadormujeres());

    }
    
    public int IntegerToBoolean(Boolean booleano){
    
        if(booleano==true)
            return 1;
        else
            return 0;
            
    }
    
    public void generarTop10(){

       
       Date fechaInicio=reportesmedicos.getFechadesde();
       Date fechaFin=reportesmedicos.getFechahasta();
        if(fechaInicio.compareTo(fechaFin)>0){
                     FacesContext.getCurrentInstance().addMessage("formdiez:fechahasta", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha Desde no puede ser mayor a la fecha Hasta.","La fecha Desde no puede ser mayor a la fecha Hasta."));
            }
        else
        {    
        List<Diagnosticos> diagnosticos = ejbDiagnosticos.findAll();
        rGeneral=OrganizarLista(listarDiagnosticos(validarFecha(listaDiagnosticos)));
        
            for(int i=0;i<diagnosticos.size();i++)
            {
                
                int DiagnosticoIdFacultad=diagnosticos.get(i).getConsultaMedicaMed().getPacienteIdx().getFacultad().getId();
                if (DiagnosticoIdFacultad==1)
                {
                    Artes.add(diagnosticos.get(i));
                }
                if (DiagnosticoIdFacultad==2)
                {
                    Agrarias.add(diagnosticos.get(i));
                }
                if (DiagnosticoIdFacultad==3)
                {
                    Salud.add(diagnosticos.get(i));
                }
                if (DiagnosticoIdFacultad==4)
                {
                    Contables.add(diagnosticos.get(i));
                }
                if (DiagnosticoIdFacultad==5)
                {
                    Humanas.add(diagnosticos.get(i));
                }
                if (DiagnosticoIdFacultad==6)
                {
                    Educacion.add(diagnosticos.get(i));
                }
                if (DiagnosticoIdFacultad==7)
                {
                    Derecho.add(diagnosticos.get(i));
                }
                if (DiagnosticoIdFacultad==8)
                {
                    Civil.add(diagnosticos.get(i));
                }
                if (DiagnosticoIdFacultad==9)
                {
                    Electronica.add(diagnosticos.get(i));
                }
                
        
             }
               //Artes
            //   System.out.println("Tamaño artes"+Artes.size());
            
               rArtes=OrganizarLista(listarDiagnosticos(validarFecha(Artes)));
               rAgrarias=OrganizarLista(listarDiagnosticos(validarFecha(Agrarias)));
               rHumanas=OrganizarLista(listarDiagnosticos(validarFecha(Humanas)));
               rCivil=OrganizarLista(listarDiagnosticos(validarFecha(Civil)));
               rContable=OrganizarLista(listarDiagnosticos(validarFecha(Contables)));
               rDerecho=OrganizarLista(listarDiagnosticos(validarFecha(Derecho)));
               rEducacion=OrganizarLista(listarDiagnosticos(validarFecha(Educacion)));
               rElectronica=OrganizarLista(listarDiagnosticos(validarFecha(Electronica)));
               rSalud=OrganizarLista(listarDiagnosticos(validarFecha(Salud)));
               Artes.clear();
               Agrarias.clear();
               Humanas.clear();
               Civil.clear();
               Contables.clear();
               Derecho.clear();
               Educacion.clear();
               Electronica.clear();
               Salud.clear();
        }
}
    
    public List<resultadoEstadistica> listarDiagnosticos(List<Diagnosticos> diagnosticos){
        
        List<resultadoEstadistica> resultado =new ArrayList<>();
         System.out.println("Tamaño diagnostico"+diagnosticos.size());
        resultadoEstadistica result=new resultadoEstadistica();
   
        for(int i=0;i<diagnosticos.size();i++)
        {
             System.out.println("Tamaño diagnostico inicio for"+diagnosticos.size());
     
                 result=estaDiagnostico(resultado,diagnosticos.get(i));
                 System.out.println("Diagnostico"+ result.getDiagnostico());
                System.out.println( result==null);
            if(result.getDiagnostico().equals(""))
            {
                System.out.println("Entro en el if");
                
                List<Diagnosticos> auxiliar=new ArrayList<>();
                System.out.println("Entro en el if1");
                if(diagnosticos.get(i).getConsultaMedicaMed().getPacienteIdx().getSexo().equals('F'))
                {   result.aumentarcontador(0);
                System.out.println("Entro en el if2");
                }
                if(diagnosticos.get(i).getConsultaMedicaMed().getPacienteIdx().getSexo().equals('M'))
                {result.aumentarcontador(1);
                System.out.println("Entro en el if3");
                }
                result.setDiagnostico(diagnosticos.get(i).getEnfermedadesCie10Med().getCodigo());
                //resultado.add(result);
                System.out.println("Entro en el if4");
             }
            
            else
            {
                 System.out.println("Entro en el else");
                if(diagnosticos.get(i).getConsultaMedicaMed().getPacienteIdx().getSexo().equals('F'))
                    result.aumentarcontador(0);
                if(diagnosticos.get(i).getConsultaMedicaMed().getPacienteIdx().getSexo().equals('M'))
                    result.aumentarcontador(1);
                //resultado.add(result);
                
            
            }
            resultado.add(result);                
        System.out.println("Tamaño diagnostico fin for"+diagnosticos.size());
        }
        return resultado;
    
    }
    
    public resultadoEstadistica estaDiagnostico(List<resultadoEstadistica> resultado, Diagnosticos diagnostico)
    {
    resultadoEstadistica bandera=new resultadoEstadistica();
    bandera.setDiagnostico("");
        for(int i=0;i<resultado.size();i++)
        {
        
            if(resultado.get(i).getDiagnostico().equals(diagnostico.getEnfermedadesCie10Med().getCodigo()))
            {
                bandera=resultado.get(i);
                resultado.remove(i);
            }
        }   
    
    return bandera;
    }
    
    public List<resultadoEstadistica> OrganizarLista(List<resultadoEstadistica> listReportes)
    {
        List<resultadoEstadistica> aux=new ArrayList<>();
         for(int i = 0; i < listReportes.size()- 1; i++)
        {
            for(int j = 0; j < listReportes.size() - 1; j++)
            {
                if (listReportes.get(j).total() < listReportes.get(j+1).total())
                {
                    resultadoEstadistica tmp = listReportes.get(j+1);
                    listReportes.set(j+1,listReportes.get(j));
                    listReportes.set(j,tmp);
                }
            }
        }
    
         if(listReportes.size()<10)
             aux=listReportes;
         else{
            for(int i=0;i<10;i++)
            {
                aux.add(i, listReportes.get(i));
         
            }
                aux=listReportes;
         }
         return aux;
    }

    
   
    
    
    private double calcularporcentaje(int totalHombres, int hombres) {
        double resultado = (double) hombres / totalHombres;

        return resultado * 100;
    }
    

    public void cargarModeloMedicas() {

        int hombres = reportesmedicos.getContadorhombres();
        int mujeres = reportesmedicos.getContadormujeres();
        int totalpacientes = reportesmedicos.getCantidad();
        modeloResultadosMedicas.set("Hombres", calcularPorcentajePacientesMedicas(hombres, totalpacientes));
        modeloResultadosMedicas.set("Mujeres", calcularPorcentajePacientesMedicas(mujeres, totalpacientes));

        modeloResultadosMedicas.setTitle("Gráfica de Resultados Médicos");
        modeloResultadosMedicas.setLegendPosition("e");
        modeloResultadosMedicas.setShowDataLabels(true);

    }

    private void createPieModel2(int hombres, int mujeres) {//segunda forma
        pieModel2 = new PieChartModel();

        pieModel2.set("Hombres", hombres);
        pieModel2.set("Mujeres", mujeres);

        pieModel2.setTitle("Resultados");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(true);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);

    }
    private void cargarModelosGrafica() {
        modeloResultadosHombres.set("Hombres", porcentajeHombres);
        modeloResultadosHombres.set("Mujeres", 100 - porcentajeHombres);

       
        modeloResultadosHombres.setTitle("Porcentaje Diagnostico");
        modeloResultadosHombres.setLegendPosition("e");
        modeloResultadosHombres.setShowDataLabels(true);
    }
    
   

}
