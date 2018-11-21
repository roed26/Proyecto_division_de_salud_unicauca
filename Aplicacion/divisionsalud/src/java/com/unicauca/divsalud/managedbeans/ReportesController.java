/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

/**
 *
 * @author acer_acer
 */
import com.unicauca.divsalud.clases.ControlSexualReporteFacultad;
import com.unicauca.divsalud.clases.ControlSexualidadReporteMensual;
import com.unicauca.divsalud.clases.ReporteMetodos;
import com.unicauca.divsalud.entidades.MetodoPlanificacion;
import com.unicauca.divsalud.sessionbeans.MetodoAdoptadoSexualFacade;
import com.unicauca.divsalud.sessionbeans.MetodoPlanificacionFacade;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named("reportesController")
@SessionScoped
public class ReportesController implements Serializable {

    @EJB
    private MetodoPlanificacionFacade metodoFacade;
    @EJB
    private MetodoAdoptadoSexualFacade metodoAdoptadoFacade;
    private int anio;
    private BarChartModel atendidosFacultad;
    private BarChartModel atendidosPrograma;
    private BarChartModel atendidosMeses;
    private BarChartModel iniciarFacultad;
    private BarChartModel iniciarPrograma;
    private BarChartModel iniciarMeses;
    private BarChartModel graficaMetodos;
    private List<ControlSexualReporteFacultad> reporteFacultad;
    private List<ControlSexualidadReporteMensual> reporteMes;
    ArrayList<Integer> totalHombres;
    ArrayList<Integer> totalMujeres;
    ArrayList<Integer> totalPlanificacion;
    ArrayList<Integer> totalControl;
    int rngMayorFacultad;
    int rngMayorControles;
    int rngMayorMetodos;

    @PostConstruct
    public void init() {
        //Por defecto el actual
        anio = 2017;
        rngMayorFacultad = 0;
        totalPlanificacion = new ArrayList<>();
        totalControl = new ArrayList<>();
        totalHombres = new ArrayList<>();
        totalMujeres = new ArrayList<>();
    }

    public List<ControlSexualReporteFacultad> obtenerReporte() {
        reporteFacultad.get(0).setFacultad("Fac. Artes");
        reporteFacultad.get(1).setFacultad("Fac. Agrarias ");
        reporteFacultad.get(2).setFacultad("Fac. Salud");
        reporteFacultad.get(3).setFacultad("Fac. Contables");
        reporteFacultad.get(4).setFacultad("Fac. Humanas");
        reporteFacultad.get(5).setFacultad("Fac. Educación");
        reporteFacultad.get(6).setFacultad("Fac. Derecho");
        reporteFacultad.get(7).setFacultad("Fac. Civil");
        reporteFacultad.get(8).setFacultad("Fac. Electrónica");
        return reporteFacultad;
    }

    public BarChartModel getGraficaMetodos() {
        return graficaMetodos;
    }

    public void setGraficaMetodos(BarChartModel graficaMetodos) {
        this.graficaMetodos = graficaMetodos;
    }

    public List<ControlSexualReporteFacultad> getReporteFacultad() {
        return reporteFacultad;
    }

    public void setReporteFacultad(List<ControlSexualReporteFacultad> reporteFacultad) {
        this.reporteFacultad = reporteFacultad;
    }

    public BarChartModel getIniciarMeses() {
        return iniciarMeses;
    }

    public void setIniciarMeses(BarChartModel iniciarMeses) {
        this.iniciarMeses = iniciarMeses;
    }

    public BarChartModel getIniciarFacultad() {
        return iniciarFacultad;
    }

    public void setIniciarFacultad(BarChartModel iniciarFacultad) {
        this.iniciarFacultad = iniciarFacultad;
    }

    public BarChartModel getAtendidosMeses() {
        return atendidosMeses;
    }

    public void setAtendidosMeses(BarChartModel atendidosMeses) {
        this.atendidosMeses = atendidosMeses;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public BarChartModel getAtendidosFacultad() {
        return atendidosFacultad;
    }

    public void setAtendidosFacultad(BarChartModel atendidosFacultad) {
        this.atendidosFacultad = atendidosFacultad;
    }

    public void cargarReportes(ExportarExcelController controller) {
        System.out.println("AÑO" + anio);
        reporteFacultad = controller.obtenerReporteFacultad(anio);
        reporteMes = controller.obtenerReporteMes(anio);
        crearGraficos();
    }

    private BarChartModel iniciarGraficaMetodos() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Método");
        ArrayList<ReporteMetodos> reporteMetodos = reporteMetodos();

        boys.set("OralCombinado", reporteMetodos.get(0).getNumPersonas());
        boys.set("InyecCombinado", reporteMetodos.get(1).getNumPersonas());
        boys.set("SoloProgestina", reporteMetodos.get(2).getNumPersonas());
        boys.set("Espermicida", reporteMetodos.get(3).getNumPersonas());
        boys.set("DispIntrauterino", reporteMetodos.get(4).getNumPersonas());
        boys.set("Preservativo", reporteMetodos.get(5).getNumPersonas());
        boys.set("Jadelle", reporteMetodos.get(6).getNumPersonas());
        boys.set("Otro", reporteMetodos.get(7).getNumPersonas());

        model.addSeries(boys);        
        return model;
    }

    private BarChartModel iniciarAtendidosFacultad() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Hombres");
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Mujeres");
        porFacultad();
        boys.set("Artes", totalHombres.get(0));
        girls.set("Artes", totalMujeres.get(0));
        boys.set("Agrarias", totalHombres.get(1));
        girls.set("Agrarias", totalMujeres.get(1));
        boys.set("Salud", totalHombres.get(2));
        girls.set("Salud", totalMujeres.get(2));
        boys.set("Contables", totalHombres.get(3));
        girls.set("Contables", totalMujeres.get(3));
        boys.set("Humanas", totalHombres.get(4));
        girls.set("Humanas", totalMujeres.get(4));
        boys.set("Educación", totalHombres.get(5));
        girls.set("Educación", totalMujeres.get(5));
        boys.set("Derecho", totalHombres.get(6));
        girls.set("Derecho", totalMujeres.get(6));
        boys.set("Civil", totalHombres.get(7));
        girls.set("Civil", totalMujeres.get(7));
        boys.set("Electrónica", totalHombres.get(8));
        girls.set("Electrónica", totalMujeres.get(8));
        model.addSeries(boys);
        model.addSeries(girls);
        

        return model;
    }

    private BarChartModel iniciarControlFacultad() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Método de planificacion");
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Control");
        porFacultad();
        boys.set("Artes", totalPlanificacion.get(0));
        girls.set("Artes", totalControl.get(0));
        boys.set("Agrarias", totalPlanificacion.get(1));
        girls.set("Agrarias", totalControl.get(1));
        boys.set("Salud", totalPlanificacion.get(2));
        girls.set("Salud", totalControl.get(2));
        boys.set("Contables", totalPlanificacion.get(3));
        girls.set("Contables", totalControl.get(3));
        boys.set("Humanas", totalPlanificacion.get(4));
        girls.set("Humanas", totalControl.get(4));
        boys.set("Educación", totalPlanificacion.get(5));
        girls.set("Educación", totalControl.get(5));
        boys.set("Derecho", totalPlanificacion.get(6));
        girls.set("Derecho", totalControl.get(6));
        boys.set("Civil", totalPlanificacion.get(7));
        girls.set("Civil", totalControl.get(7));
        boys.set("Electrónica", totalPlanificacion.get(8));
        girls.set("Electrónica", totalControl.get(8));
        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private BarChartModel iniciarAtendidosMeses() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Hombres");
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Mujeres");
        boys.set("Ene", reporteMes.get(0).getHombres());
        girls.set("Ene", reporteMes.get(0).getMujeres());
        boys.set("Feb", reporteMes.get(1).getHombres());
        girls.set("Feb", reporteMes.get(1).getMujeres());
        boys.set("Mar", reporteMes.get(2).getHombres());
        girls.set("Mar", reporteMes.get(2).getMujeres());
        boys.set("Abr", reporteMes.get(3).getHombres());
        girls.set("Abr", reporteMes.get(3).getMujeres());
        boys.set("May", reporteMes.get(4).getHombres());
        girls.set("May", reporteMes.get(4).getMujeres());
        boys.set("Jun", reporteMes.get(5).getHombres());
        girls.set("Jun", reporteMes.get(5).getMujeres());
        boys.set("Jul", reporteMes.get(6).getHombres());
        girls.set("Jul", reporteMes.get(6).getMujeres());
        boys.set("Ago", reporteMes.get(7).getHombres());
        girls.set("Ago", reporteMes.get(7).getMujeres());
        boys.set("Sep", reporteMes.get(8).getHombres());
        girls.set("Sep", reporteMes.get(8).getMujeres());
        boys.set("Oct", reporteMes.get(9).getHombres());
        girls.set("Oct", reporteMes.get(9).getMujeres());
        boys.set("Nov", reporteMes.get(10).getHombres());
        girls.set("Nov", reporteMes.get(10).getMujeres());
        boys.set("Dic", reporteMes.get(11).getHombres());
        girls.set("Dic", reporteMes.get(11).getMujeres());
        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private BarChartModel iniciarControlMeses() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Método de planificacion");
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Control");
        boys.set("Ene", reporteMes.get(0).getInicio_metodos_planificacion());
        girls.set("Ene", reporteMes.get(0).getControles());
        boys.set("Feb", reporteMes.get(1).getInicio_metodos_planificacion());
        girls.set("Feb", reporteMes.get(1).getControles());
        boys.set("Mar", reporteMes.get(2).getInicio_metodos_planificacion());
        girls.set("Mar", reporteMes.get(2).getControles());
        boys.set("Abr", reporteMes.get(3).getInicio_metodos_planificacion());
        girls.set("Abr", reporteMes.get(3).getControles());
        boys.set("May", reporteMes.get(4).getInicio_metodos_planificacion());
        girls.set("May", reporteMes.get(4).getControles());
        boys.set("Jun", reporteMes.get(5).getInicio_metodos_planificacion());
        girls.set("Jun", reporteMes.get(5).getControles());
        boys.set("Jul", reporteMes.get(6).getInicio_metodos_planificacion());
        girls.set("Jul", reporteMes.get(6).getControles());
        boys.set("Ago", reporteMes.get(7).getInicio_metodos_planificacion());
        girls.set("Ago", reporteMes.get(7).getControles());
        boys.set("Sep", reporteMes.get(8).getInicio_metodos_planificacion());
        girls.set("Sep", reporteMes.get(8).getControles());
        boys.set("Oct", reporteMes.get(9).getInicio_metodos_planificacion());
        girls.set("Oct", reporteMes.get(9).getControles());
        boys.set("Nov", reporteMes.get(10).getInicio_metodos_planificacion());
        girls.set("Nov", reporteMes.get(10).getControles());
        boys.set("Dic", reporteMes.get(11).getInicio_metodos_planificacion());
        girls.set("Dic", reporteMes.get(11).getControles());
        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void crearGraficos() {
        atendidosFacultad = iniciarAtendidosFacultad();
        atendidosFacultad.setTitle("Gráfica");
        atendidosFacultad.setAnimate(true);
        atendidosFacultad.setLegendPosition("ne");
        Axis xAxis = atendidosFacultad.getAxis(AxisType.X);
        xAxis.setTickAngle(-90);
        
        xAxis.setLabel("Facultad");

        Axis yAxis = atendidosFacultad.getAxis(AxisType.Y);
        yAxis.setLabel("Atendidos");
        yAxis.setMin(0);
        yAxis.setMax(rngMayorFacultad + 5);

        atendidosMeses = iniciarAtendidosMeses();        
        atendidosMeses.setTitle("Gráfica");
        atendidosMeses.setAnimate(true);
        atendidosMeses.setLegendPosition("ne");
        Axis x2Axis = atendidosMeses.getAxis(AxisType.X);
        x2Axis.setTickAngle(-90);
        x2Axis.setLabel("Mes");

        Axis y2Axis = atendidosMeses.getAxis(AxisType.Y);
        y2Axis.setLabel("Atendidos");
        y2Axis.setMin(0);
        y2Axis.setMax(mayorMeses() + 5);

        iniciarFacultad = iniciarControlFacultad();
        iniciarFacultad.setTitle("Gráfica");
        iniciarFacultad.setAnimate(true);
        iniciarFacultad.setLegendPosition("ne");
        Axis x3Axis = iniciarFacultad.getAxis(AxisType.X);
        x3Axis.setLabel("Facultad");
        x3Axis.setTickAngle(-90);
        Axis y3Axis = iniciarFacultad.getAxis(AxisType.Y);
        y3Axis.setLabel("Creados");
        y3Axis.setMin(0);
        y3Axis.setMax(rngMayorControles + 5);

        iniciarMeses = iniciarControlMeses();
        iniciarMeses.setTitle("Gráfica");
        iniciarMeses.setAnimate(true);
        iniciarMeses.setLegendPosition("ne");
        Axis x4Axis = iniciarMeses.getAxis(AxisType.X);
        x4Axis.setLabel("Mes");
        x4Axis.setTickAngle(-90);
        Axis y4Axis = iniciarMeses.getAxis(AxisType.Y);
        y4Axis.setLabel("Creados");
        y4Axis.setMin(0);
        y4Axis.setMax(mayorMesesControl() + 5);

        graficaMetodos = iniciarGraficaMetodos();
        
        graficaMetodos.setTitle("Gráfica");
        graficaMetodos.setAnimate(true);
        graficaMetodos.setLegendPosition("ne");
        Axis x5Axis = graficaMetodos.getAxis(AxisType.X);
        x5Axis.setLabel("Método");
        x5Axis.setTickAngle(-90);
        Axis y5Axis = graficaMetodos.getAxis(AxisType.Y);
        y5Axis.setLabel("Utilizados");
        y5Axis.setMin(0);
        y5Axis.setMax(rngMayorMetodos + 5);
    }

    private void porFacultad() {

        int sumH = 0;
        int sumM = 0;
        int sumPlanificacion = 0;
        int sumControles = 0;
        for (int i = 0; i < reporteFacultad.size(); i++) {
            for (int j = 0; j < reporteFacultad.get(i).getDepartamentos().size(); j++) {
                sumH += reporteFacultad.get(i).getDepartamentos().get(j).getHombres();
                sumM += reporteFacultad.get(i).getDepartamentos().get(j).getMujeres();
                sumPlanificacion += reporteFacultad.get(i).getDepartamentos().get(j).getInicio_metodos_planificacion();
                sumControles += reporteFacultad.get(i).getDepartamentos().get(j).getControles();
            }
            mayorRango(sumH, sumM, sumPlanificacion, sumControles);
            totalPlanificacion.add(sumPlanificacion);
            totalControl.add(sumControles);
            totalHombres.add(sumH);
            totalMujeres.add(sumM);
            sumPlanificacion = 0;
            sumControles = 0;
            sumH = 0;
            sumM = 0;
        }
    }

    private void mayorRango(int sumH, int sumM, int sumPl, int sumCon) {

        if (sumH > rngMayorFacultad) {
            rngMayorFacultad = sumH;
        }
        if (sumM > rngMayorFacultad) {
            rngMayorFacultad = sumM;
        }
        if (sumPl > rngMayorControles) {
            rngMayorControles = sumPl;
        }
        if (sumCon > rngMayorControles) {
            rngMayorControles = sumCon;
        }
    }

    private int mayorMeses() {
        int mayor = 0;
        for (int i = 0; i < reporteMes.size(); i++) {
            if (reporteMes.get(i).getHombres() > mayor) {
                mayor = reporteMes.get(i).getHombres();
            }
            if (reporteMes.get(i).getMujeres() > mayor) {
                mayor = reporteMes.get(i).getMujeres();
            }
        }
        return mayor;
    }

    private int mayorMesesControl() {
        int mayor = 0;
        for (int i = 0; i < reporteMes.size(); i++) {
            if (reporteMes.get(i).getInicio_metodos_planificacion() > mayor) {
                mayor = reporteMes.get(i).getInicio_metodos_planificacion();
            }
            if (reporteMes.get(i).getControles() > mayor) {
                mayor = reporteMes.get(i).getControles();
            }
        }
        return mayor;
    }

    private ArrayList<ReporteMetodos> reporteMetodos() {
        List<MetodoPlanificacion> metodos = metodoFacade.findAll();
        ArrayList<ReporteMetodos> reporte = new ArrayList<>();
        rngMayorMetodos = 0;
        for (int i = 0; i < metodos.size(); i++) {
            if (rngMayorMetodos < metodoAdoptadoFacade.countByIdMetodo(metodos.get(i).getIdMetodoPlanificacion()).size()) {
                rngMayorMetodos = metodoAdoptadoFacade.countByIdMetodo(metodos.get(i).getIdMetodoPlanificacion()).size();
            }
            reporte.add(new ReporteMetodos(metodos.get(i).getNombreMetodo(), metodoAdoptadoFacade.countByIdMetodo(metodos.get(i).getIdMetodoPlanificacion()).size()));
        }
        return reporte;
    }

}
