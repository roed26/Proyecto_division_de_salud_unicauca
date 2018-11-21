package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.clases.DiagnosticoCantidad;
import com.unicauca.divsalud.clases.IndiceCOP;
import com.unicauca.divsalud.clases.ServicioPrestado;
import com.unicauca.divsalud.entidades.DiagnosticoOdo;
import com.unicauca.divsalud.entidades.Facultad;
import com.unicauca.divsalud.entidades.ObsOdontograma;
import com.unicauca.divsalud.entidades.Programas;
import com.unicauca.divsalud.sessionbeans.ActualizacionOdoFacade;
import com.unicauca.divsalud.sessionbeans.DiagnosticoOdoFacade;
import com.unicauca.divsalud.sessionbeans.FacultadFacade;
import com.unicauca.divsalud.sessionbeans.ObsOdontogramaFacade;
import com.unicauca.divsalud.sessionbeans.ProgramasFacade;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author ROED26
 */
@Named(value = "pagosController")
@SessionScoped
public class PagosController implements Serializable {

    @EJB
    private ActualizacionOdoFacade ejbActualizacionOdo;
    @EJB
    private DiagnosticoOdoFacade ejbDiagnosticoOdo;
    @EJB
    private FacultadFacade ejbFacultad;
    @EJB
    private ObsOdontogramaFacade ejbObsOdontograma;
    @EJB
    private ProgramasFacade ejbProgramas;

    private List<ServicioPrestado> listaServicios;   

    

    public PagosController() {
        

    }

    @PostConstruct
    public void init() {
        
    }

    public List<ServicioPrestado> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<ServicioPrestado> listaServicios) {
        this.listaServicios = listaServicios;
    }

    
}
