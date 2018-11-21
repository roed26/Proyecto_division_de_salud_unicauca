package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.clases.ControlSexualReportePrograma;
import com.unicauca.divsalud.clases.ControlSexualReporteFacultad;
import com.unicauca.divsalud.clases.ControlSexualidadReporteMensual;
import com.unicauca.divsalud.clases.GenerarExcel;
import com.unicauca.divsalud.clases.PlantillaControlSexualFacultad;
import com.unicauca.divsalud.clases.PlantillaControlsexualMensual;
import com.unicauca.divsalud.clases.ReporteSexualidad;
import com.unicauca.divsalud.entidades.ControlPacienteSexual;
import com.unicauca.divsalud.entidades.Facultad;
import com.unicauca.divsalud.entidades.HistoriaModuloSexualidad;
import com.unicauca.divsalud.entidades.Paciente;
import com.unicauca.divsalud.entidades.Programas;
import com.unicauca.divsalud.sessionbeans.ControlPacienteSexualFacade;
import com.unicauca.divsalud.sessionbeans.FacultadFacade;
import com.unicauca.divsalud.sessionbeans.HistoriaModuloSexualidadFacade;
import com.unicauca.divsalud.sessionbeans.PacienteFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@Named("exportarExcelController")
@SessionScoped
@ManagedBean
public class ExportarExcelController implements Serializable {

    private static String SI = "Sí";
    private static String NO = "No";
    private static int PRIMER_ANIO = 2017;

    private boolean facultades;
    private int anio;
    @EJB
    private ControlPacienteSexualFacade control_facade;
    @EJB
    private PacienteFacade paciente_facade;
    @EJB
    private HistoriaModuloSexualidadFacade hms_facade;
    @EJB
    private FacultadFacade facultad_facade;

    private List anios;

    public ExportarExcelController() {
        facultades = false;
    }

    public boolean getFacultades() {
        return facultades;
    }

    public void setFacultades(boolean facultades) {
        this.facultades = facultades;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public List getAnios() {
        if (anios == null) {
            anios = new ArrayList();
            Calendar cal = Calendar.getInstance();
            int actual = cal.get(cal.YEAR);
            for (int i = PRIMER_ANIO; i <= actual; i++) {
                anios.add(i);
            }
        }
        return anios;
    }

    public void setAnios(List anios) {
        this.anios = anios;
    }

    private List<ControlSexualidadReporteMensual> reporte_sin_facultades(int anio) {
        List<ControlSexualidadReporteMensual> reportes = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            reportes.add(contar_sexo_anio_mes(anio, i));
        }
        return reportes;
    }

    private ControlSexualidadReporteMensual contar_sexo_anio_mes(int anio, int mes) {
        ControlSexualidadReporteMensual reporte = new ControlSexualidadReporteMensual();
        //Controles mes en ese año
        List<ControlPacienteSexual> controles = control_facade.findByYearMonth(anio, mes);
        List<Paciente> pacientes_control = getPacientesControl(controles);
        contarSexo(reporte, pacientes_control);

        //Inicios mes en ese año
        List<HistoriaModuloSexualidad> historias = hms_facade.findByYearMonth(anio, mes);
        List<Paciente> pacientes_historias = getPacientesHistoria(historias);
        contarSexo(reporte, pacientes_historias);

        //conteo Final
        reporte.setControles(controles.size());
        reporte.setInicio_metodos_planificacion(historias.size());
        reporte.setTotal_atendidos(controles.size() + historias.size());

        //Asignación mes
        reporte.setMes(getNombreMes(mes));
        return reporte;
    }

    private String getNombreMes(int mes) {
        switch (mes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
        }
        return "ERROR";
    }

    private void contarSexo(ReporteSexualidad r, List<Paciente> pacientes) {
        int hombres = 0;
        int mujeres = 0;

        for (Paciente p : pacientes) {
            if (p.getSexo() == 'F') {
                mujeres++;
            } else {
                hombres++;
            }
        }
        r.setHombres(hombres + r.getHombres());
        r.setMujeres(mujeres + r.getMujeres());
    }

    private List<Paciente> getPacientesControl(List<ControlPacienteSexual> controles) {
        List<Paciente> pacientes = new ArrayList<Paciente>();
        for (ControlPacienteSexual c : controles) {
            Paciente p = paciente_facade.find(c.getId().getId());
            pacientes.add(p);
        }
        return pacientes;
    }

    private List<Paciente> getPacientesHistoria(List<HistoriaModuloSexualidad> historias) {
        List<Paciente> pacientes = new ArrayList<Paciente>();
        for (HistoriaModuloSexualidad c : historias) {
            Paciente p = paciente_facade.find(c.getId());
            pacientes.add(p);
        }
        return pacientes;
    }

    private List<ControlSexualReporteFacultad> reporte_con_facultades(int anio) {
        List<Facultad> facultades = facultad_facade.findAll();
        List<ControlSexualReporteFacultad> reportes = new ArrayList<>();

        for (Facultad f : facultades) {
            ControlSexualReporteFacultad r = new ControlSexualReporteFacultad();
            r.setFacultad(f.getNombre());
            for (Programas p : f.getProgramasCollection()) {
                r.add(reporte_programa(anio, p));
            }
            reportes.add(r);
        }
        return reportes;
    }

    private ControlSexualReportePrograma reporte_programa(int anio, Programas p) {
        ControlSexualReportePrograma c = new ControlSexualReportePrograma();
        //Controles
        List<ControlPacienteSexual> controles = control_facade.findByYear(anio);
        List<Paciente> pacientes_control = getPacientesControl(controles);
        filtrar_programa(pacientes_control, p);
        contarSexo(c, pacientes_control);

        //Inicios
        List<HistoriaModuloSexualidad> historias = hms_facade.findByYear(anio);
        List<Paciente> pacientes_historias = getPacientesHistoria(historias);
        filtrar_programa(pacientes_historias, p);
        contarSexo(c, pacientes_historias);

        //conteo Final
        c.setControles(pacientes_control.size());
        c.setInicio_metodos_planificacion(pacientes_historias.size());
        c.setTotal_atendidos(pacientes_control.size() + pacientes_historias.size());

        c.setPrograma(p.getNombre());
        return c;
    }

    private void filtrar_programa(List<Paciente> pacientes_control, Programas programa) {
        int i = 0;
        while (i < pacientes_control.size()) {
            Paciente p = pacientes_control.get(i);
            if (p.getPrograma().getId().intValue() != programa.getId().intValue()) {
                pacientes_control.remove(p);
                i--;
            }
            i++;
        }
    }

    public void consultar() {
        System.out.println("falcultad " + facultades);
        if (facultades) {
            List<ControlSexualReporteFacultad> r = reporte_con_facultades(anio);
            PlantillaControlSexualFacultad p = new PlantillaControlSexualFacultad(anio, r);
            GenerarExcel excel = new GenerarExcel();
            excel.generarPlantilaControlSexualMensual(p, "PlantillaControlsexualFacultad.xlsx");
            /*for(ControlSexualReporteFacultad v:r)
            {
                System.out.println(v.toString()+"\n");
            }*/
        } else {
            List<ControlSexualidadReporteMensual> r = reporte_sin_facultades(anio);
            PlantillaControlsexualMensual p = new PlantillaControlsexualMensual(r, anio);
            GenerarExcel excel = new GenerarExcel();
            excel.generarPlantilaControlSexualMensual(p, "PlantillaControlsexualMensual.xlsx");
            /*for(ControlSexualidadReporteMensual v:r)
            {
                System.out.println(v.toString());
                System.out.println("\n");
            }*/
        }
    }

    public List<ControlSexualReporteFacultad> obtenerReporteFacultad(int anio) {
        List<ControlSexualReporteFacultad> r = reporte_con_facultades(anio);
        return r;
    }

    public List<ControlSexualidadReporteMensual> obtenerReporteMes(int anio) {
        List<ControlSexualidadReporteMensual> r = reporte_sin_facultades(anio);
        return r;
    }

}
