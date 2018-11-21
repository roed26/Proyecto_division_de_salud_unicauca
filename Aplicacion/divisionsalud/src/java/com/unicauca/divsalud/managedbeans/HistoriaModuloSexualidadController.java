package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.ControlPacienteSexual;
import com.unicauca.divsalud.entidades.ExamenesDiagnosticosPacienteSexual;
import com.unicauca.divsalud.entidades.ExamenesDiagnosticosPacienteSexualPK;
import com.unicauca.divsalud.entidades.HistoriaModuloSexualidad;
import com.unicauca.divsalud.entidades.Paciente;
import com.unicauca.divsalud.entidades.TipoExamDiagnosticoSexual;
import com.unicauca.divsalud.sessionbeans.HistoriaModuloSexualidadFacade;
import com.unicauca.divsalud.sessionbeans.PacienteFacade;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import javax.inject.Named;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.primefaces.context.RequestContext;

@Named("historiaModuloSexualidadController")
@SessionScoped
@ManagedBean
public class HistoriaModuloSexualidadController implements Serializable {

    private static final String SI = "si";
    private static final String NO = "no";
    private Paciente selected;
    private HistoriaModuloSexualidad historia;
    @EJB
    private com.unicauca.divsalud.sessionbeans.ExamenesDiagnosticosPacienteSexualFacade ejbFacadeExamen;
    @EJB
    private com.unicauca.divsalud.sessionbeans.HistoriaModuloSexualidadFacade ejbFacade;
    @EJB
    private com.unicauca.divsalud.sessionbeans.TipoExamDiagnosticoSexualFacade ejbFacadeTipo;
    @EJB
    private com.unicauca.divsalud.sessionbeans.ControlPacienteSexualFacade ejbFacadeControl;
    @EJB
    PacienteFacade ejbFacadePaciente;
    /**
     * Resultados de los examenes del examen físico
     */
    private List<ExamenesDiagnosticosPacienteSexual> resultadoExamenes;
    @Resource
    private UserTransaction transaction; 

    public Paciente getSelected() {
        
        return selected;
        
    }

    public void setSelected(Paciente selected) {
        this.selected = selected;
    }
    
    public List<ControlPacienteSexual> obtenerControles(int id){        
        return ejbFacadeControl.findByIdHistoria(id);
    }

    public List<ExamenesDiagnosticosPacienteSexual> getResultadoExamenes(int idHistoria) {
        if (resultadoExamenes == null) {
            resultadoExamenes = new ArrayList<>();

            List<TipoExamDiagnosticoSexual> examenes = ejbFacadeTipo.findAll();

            for (TipoExamDiagnosticoSexual e : examenes) {
                ExamenesDiagnosticosPacienteSexual resultado = new ExamenesDiagnosticosPacienteSexual();
                resultado.setTipoExamDiagnosticoSexual(e);
                resultado.setHistoriaModuloSexualidad(new HistoriaModuloSexualidad(idHistoria));
                resultado.setResultadoExamenFisico(NO);
                resultadoExamenes.add(resultado);
            }
            historia.setExamenesDiagnosticosPacienteSexualList(resultadoExamenes);
        }
        return resultadoExamenes;
    }


    
    public void registro(int idHistoria) {
        for (ExamenesDiagnosticosPacienteSexual resultado : resultadoExamenes) {
            ExamenesDiagnosticosPacienteSexualPK tmp = new ExamenesDiagnosticosPacienteSexualPK(idHistoria, resultado.getTipoExamDiagnosticoSexual().getIdExamDiag());
            resultado.setExamenesDiagnosticosPacienteSexualPK(tmp);
            ejbFacadeExamen.create(resultado);
        }
    }

    public HistoriaModuloSexualidadController() {
        historia = new HistoriaModuloSexualidad();
    }

    public void registrar(AntecedentesPersonalesSexualController antecedentes,
                          ExamenFisicoController examen_fisico,
                          ExamenesDiagnosticosPacienteSexualController examen_diagnostico,
                          MetodoAdoptadoSexualController metodo_adoptado,
                          HistorialMetodosUsadosSexualController metodos_usados) throws IllegalStateException, SecurityException, SystemException {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        try {
            transaction.begin();
            registro(historia.getId());
            historia.setFechaCreacion(new Date());
            ejbFacade.create(historia);
            ejbFacade.flush();
            
            antecedentes.Registrar(historia.getId());
            examen_fisico.registrar(historia.getId(),false);
            examen_diagnostico.registro(historia.getId());
            
            metodo_adoptado.registrar(historia.getId());
            System.out.println("metodo adoptado");
            metodos_usados.Registrar(historia.getId());
            System.out.println("metodos usados");
            
            transaction.commit();
            examen_fisico.limpiar();
            antecedentes.limpiar();
            ejbFacade.clear();
            requestContext.execute("PF('RegistroExitoso').show()");
        } catch (Exception e) {
            transaction.rollback();
            requestContext.execute("PF('RegistroNoExitoso').show()");
        }
    }

    public HistoriaModuloSexualidad getHistoria() {
        return historia;
    }

    public void setHistoria(HistoriaModuloSexualidad current) {
        this.historia = current;
    }

    public String modificarId(int id) {
        System.out.println("id cambiadO: "+id);
        if(historia==null)
            historia = new HistoriaModuloSexualidad();
        historia.setId(id);
        selected = ejbFacadePaciente.find(id);
        return "/usuariodelsistema/sexualidad/metodo_planificacion/create.xhtml?faces-redirect=true";
    }
    
    
    public boolean pacientetieneHistoria(int idPaciente){
        return this.ejbFacade.findById(idPaciente) != null;
    }

}
