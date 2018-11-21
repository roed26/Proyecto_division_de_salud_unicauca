/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

// @author acer_acer
import com.unicauca.divsalud.clases.OtroTipoExamen;
import com.unicauca.divsalud.entidades.ExamenesDiagnosticosPacienteSexual;
import com.unicauca.divsalud.entidades.OtrosExamenesDiagnosticosPacienteSexual;
import com.unicauca.divsalud.entidades.OtrosTipoExamDiagnosticoSexual;
import com.unicauca.divsalud.sessionbeans.OtroTipoExamenFisicoSexualFacade;
import com.unicauca.divsalud.sessionbeans.OtrosTipoExamDiagnosticoSexualFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named("examenesDiagnosticosPacienteSexualController")
@SessionScoped

public class ExamenesDiagnosticosPacienteSexualController implements Serializable {

    private ExamenesDiagnosticosPacienteSexual examen;
    @EJB
    private com.unicauca.divsalud.sessionbeans.ExamenesDiagnosticosPacienteSexualFacade ejbFacade;
    @EJB
    private com.unicauca.divsalud.sessionbeans.OtrosTipoExamDiagnosticoSexualFacade ejbFacadeOtroTipo;
    @EJB
    private com.unicauca.divsalud.sessionbeans.OtrosExamenesDiagnosticosPacienteSexualFacade ejbFacadeOtroTipoExam;
    @EJB
    private OtrosTipoExamDiagnosticoSexualFacade ejbOtrosTiposDiagnosticoSexualFacade;

    private List<OtroTipoExamen> otrosExamenes;

    private List<ExamenesDiagnosticosPacienteSexual> itemsExamenesNormales;

    private List<OtrosExamenesDiagnosticosPacienteSexual> itemsOtrosExamenes;

    public List<ExamenesDiagnosticosPacienteSexual> getItemsExamenesNormales() {
        return itemsExamenesNormales;
    }

    public void setItemsExamenesNormales(List<ExamenesDiagnosticosPacienteSexual> itemsExamenesNormales) {
        this.itemsExamenesNormales = itemsExamenesNormales;
    }

    public List<OtrosExamenesDiagnosticosPacienteSexual> getItemsOtrosExamenes() {
        return itemsOtrosExamenes;
    }

    public void setItemsOtrosExamenes(List<OtrosExamenesDiagnosticosPacienteSexual> itemsOtrosExamenes) {
        this.itemsOtrosExamenes = itemsOtrosExamenes;
    }

    String tmp1;
    String tmp2;

    public ExamenesDiagnosticosPacienteSexualController() {
        this.otrosExamenes = new ArrayList<>();
        tmp1 = "";
        tmp2 = "";
    }

    public void registro(int idHistoria) {
        for (OtroTipoExamen otroExamen : otrosExamenes) {
            OtrosTipoExamDiagnosticoSexual aux = ejbFacadeOtroTipo.findByNombre(otroExamen.getTipo());
            if (aux == null) {
                ejbFacadeOtroTipo.create(new OtrosTipoExamDiagnosticoSexual(otroExamen.getTipo()));
                aux = ejbFacadeOtroTipo.findByNombre(otroExamen.getTipo());
            }
            OtrosExamenesDiagnosticosPacienteSexual otro = new OtrosExamenesDiagnosticosPacienteSexual(idHistoria, aux.getIdOtroExamDiag());
            otro.setEspecificacionExamenFisico(otroExamen.getDescripcion());
            ejbFacadeOtroTipoExam.create(otro);
        }
    }

    private boolean verificarDiagnosticoEnLista(String tipo) {
        for (OtroTipoExamen otro : otrosExamenes) {
            if (otro.getTipo().equals(tipo)) {
                return true;
            }
        }
        return false;
    }

    public void añadirExamen() {
        boolean estaMetodo = verificarDiagnosticoEnLista(tmp1);

        if (estaMetodo) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('DiagnosticoNoAgregado').show()");
            //limpiarCampos();
            return;
        }
        otrosExamenes.add(new OtroTipoExamen(tmp1, tmp2));
        limpiarCampos();
    }

    public ExamenesDiagnosticosPacienteSexual getExamen() {
        return examen;
    }

    public void setExamen(ExamenesDiagnosticosPacienteSexual examen) {
        this.examen = examen;
    }

    public List<OtroTipoExamen> getOtrosExamenes() {
        return otrosExamenes;
    }

    public void setOtrosExamenes(ArrayList<OtroTipoExamen> otrosExamenes) {
        this.otrosExamenes = otrosExamenes;
    }

    public String getTmp1() {
        return tmp1;
    }

    public void setTmp1(String tmp1) {
        this.tmp1 = tmp1;
    }

    public String getTmp2() {
        return tmp2;
    }

    public void setTmp2(String tmp2) {
        this.tmp2 = tmp2;
    }

    private void limpiarCampos() {
        tmp1 = "";
        tmp2 = "";

    }

    public void limpiar() {
        this.itemsExamenesNormales = new ArrayList<>();
        this.itemsOtrosExamenes = new ArrayList<>();
        this.otrosExamenes = new ArrayList<>();
    }

    public void removeExamen(OtroTipoExamen otroExamen) {
        System.out.println(" SE INVOCÓ REMOVER DIAGNÓSTICO");

        for (int i = 0; i < this.otrosExamenes.size(); i++) {

            if (Objects.equals(otrosExamenes.get(i).getTipo(), otroExamen.getTipo())) {
                otrosExamenes.remove(i);
                return;
            }
        }

    }

    public void cargarExamenesDiagnosticos(int id) {
        this.itemsExamenesNormales = this.ejbFacade.findById(id);
        this.itemsOtrosExamenes = this.ejbFacadeOtroTipoExam.findById(id);
        for (OtrosExamenesDiagnosticosPacienteSexual e : itemsOtrosExamenes) {
            int idotro = (int) e.getOtrosExamenesDiagnosticosPacienteSexualPK().getIdOtroExamDiag();
            System.out.println(".->>>>>> " + idotro);
            e.setNombre(ejbOtrosTiposDiagnosticoSexualFacade.findByIdOtroExamDiag(idotro).getNombre());
        }
    }

}
