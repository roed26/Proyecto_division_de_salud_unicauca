package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.*;
import com.unicauca.divsalud.sessionbeans.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.primefaces.context.RequestContext;

@Named(value = "controlSexualController")
@SessionScoped

public class ControlSexualController implements Serializable {

    @EJB
    PacienteFacade ejbPacienteFacade;
    @EJB
    private SintomasControlSexualFacade sintomas_facade;
    @EJB
    private HistoriaModuloSexualidadFacade hms_facade;
    @EJB
    private ControlPacienteSexualFacade control_facade;
    @Resource
    private UserTransaction transaction;

    private List<SintomasControlSexual> sintomas;

    private boolean viendo;

    private Paciente selected;

    private ControlPacienteSexual control;

    private static String NO = "No";

    public ControlSexualController() {
        limpiar();
        viendo = false;
    }

    public boolean isViendo() {
        return viendo;
    }

    public void setViendo(boolean viendo) {
        this.viendo = viendo;
    }

    public String modificarFecha(Date fechaDate) {
        SimpleDateFormat formateador = new SimpleDateFormat(
                "dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
        String fecha = formateador.format(fechaDate);
        return fecha;
    }

    public ControlPacienteSexual getControl() {
        return control;
    }

    public void setControl(ControlPacienteSexual control) {
        this.control = control;
    }

    public List<SintomasControlSexual> getSintomas() {
        System.out.println("obteniendo sintomas");
        if (sintomas == null) {
            System.out.println("estaban nulos");
            sintomas = sintomas_facade.findAll();
            /*for(SintomasControlSexual s: sintomas)
                s.setSeleccionado(false);*/
        }
        return sintomas;
    }

    public void setSintomas(List<SintomasControlSexual> sintomas) {
        this.sintomas = sintomas;
    }

    /*@PostConstruct
    public void init() {
        limpiar();
    }*/
    public void limpiar() {
        viendo = false;
        control = new ControlPacienteSexual();
        control.setFechaControl(new Date());
        sintomas = null;
        control.setSatisfaccionMetodo(NO);
        control.setCambioMetodo(NO);
    }

    public void registrar(PacienteController paciente,
            ExamenFisicoController examen_fisico) throws IllegalStateException, SecurityException, SystemException {
        HistoriaModuloSexualidad h = hms_facade.find(paciente.getSelected().getId());
        control.setId(h);

        if (sintomas != null) {
            List<SintomasControlSexual> list = new ArrayList<>();
            for (SintomasControlSexual s : sintomas) {
                if (s.isSeleccionado()) {
                    list.add(s);
                }
            }
            control.setSintomasControlSexualList(list);
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        try {
            transaction.begin();
            control_facade.create(control);
            examen_fisico.getActual().setIdControlSexual(control);
            examen_fisico.registrar(h.getId(), true);

            transaction.commit();
            examen_fisico.limpiar();
            this.limpiar();
            control_facade.clear();
            requestContext.execute("PF('RegistroExitoso').show()");

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
        } catch (Exception e) {
            transaction.rollback();
            requestContext.execute("PF('RegistroNoExitoso').show()");
        }

    }

    public String cargar_control(long id, ExamenFisicoController e) {
        control = control_facade.find(id);
        e.cargar_examen_fisico(control.getExamenFisicoSexualList().get(0));
        sintomas = control.getSintomasControlSexualList();
        for (SintomasControlSexual s : sintomas) {
            s.setSeleccionado(true);
        }
        return "/usuariodelsistema/sexualidad/control/view.xhtml?faces-redirect=true";
    }
}
