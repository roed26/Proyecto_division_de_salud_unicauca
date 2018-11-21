/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.AntecedentesPersonalesSexual;
import com.unicauca.divsalud.entidades.HistoriaModuloSexualidad;
import com.unicauca.divsalud.entidades.InformacionAntecedentePacienteSexual;
import com.unicauca.divsalud.entidades.InformacionAntecedentePacienteSexualPK;
import com.unicauca.divsalud.entidades.TipoAntecedentePersonalSexual;
import com.unicauca.divsalud.entidades.TipoTranstornoMentrual;
//import com.unicauca.divsalud.entidades.TranstornoMenstrualAntecedente;
//import com.unicauca.divsalud.entidades.TranstornoMenstrualAntecedentePK;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.sessionbeans.AntecedentesPersonalesSexualFacade;
import com.unicauca.divsalud.sessionbeans.HistoriaModuloSexualidadFacade;
import com.unicauca.divsalud.sessionbeans.InformacionAntecedentePacienteSexualFacade;
import com.unicauca.divsalud.sessionbeans.TipoAntecedentePersonalSexualFacade;
import com.unicauca.divsalud.sessionbeans.TipoTranstornoMentrualFacade;
//import com.unicauca.divsalud.sessionbeans.TranstornoMenstrualAntecedenteFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author David
 */
@Named(value = "antecedentesPersonalesSexualController")
@SessionScoped

public class AntecedentesPersonalesSexualController implements Serializable {

    /**
     * Creates a new instance of AntecedentesPersonalesSexualController
     */
    @EJB
    TipoAntecedentePersonalSexualFacade tipoAntecedentePersonalSexualFacade;
    @EJB
    TipoTranstornoMentrualFacade tipoTrastornoMentrualFacade;
    @EJB
    InformacionAntecedentePacienteSexualFacade informacionAntecedentePacienteSexualFacade;
    @EJB
    private com.unicauca.divsalud.sessionbeans.AntecedentesPersonalesSexualFacade ejbAntecedentesFacade;
    @EJB
    HistoriaModuloSexualidadFacade historiaModuloSexualidadFacade;

    private AntecedentesPersonalesSexual selected;
    //TIPOS :
    private List<TipoAntecedentePersonalSexual> tiposDeAntecedentes;

    //LISTA DE ENFERMEDADES Y TRASTORNOS 
    private List<InformacionAntecedentePacienteSexual> informacionAntecedentes;

    private List<TipoTranstornoMentrual> listTranstornosMenstruales;
    
      private boolean viendo;

    public boolean isViendo() {
        return viendo;
    }

    public void setViendo(boolean viendo) {
        this.viendo = viendo;
    }
      
      

    public List<TipoTranstornoMentrual> getListTranstornosMenstruales() {
        if (this.listTranstornosMenstruales == null) {
            this.listTranstornosMenstruales = tipoTrastornoMentrualFacade.findAll();

            for (TipoTranstornoMentrual transtorno : listTranstornosMenstruales) {

                transtorno.setPresenta(false);

            }

        }
        return listTranstornosMenstruales;
    }

    public void setListTranstornosMenstruales(List<TipoTranstornoMentrual> listTranstornosMenstruales) {

        this.listTranstornosMenstruales = listTranstornosMenstruales;

    }

    public List<InformacionAntecedentePacienteSexual> getInformacionAntecedentes() {
        return informacionAntecedentes;
    }

    public void setInformacionAntecedentes(List<InformacionAntecedentePacienteSexual> informacionAntecedentes) {
        this.informacionAntecedentes = informacionAntecedentes;
    }

    public List<TipoAntecedentePersonalSexual> getTiposDeAntecedentes() {
        if (tiposDeAntecedentes == null) {
            List<TipoAntecedentePersonalSexual> enfermedades = tipoAntecedentePersonalSexualFacade.findAll();
            informacionAntecedentes = new ArrayList();
            for (TipoAntecedentePersonalSexual enfermedade : enfermedades) {
                InformacionAntecedentePacienteSexualPK inforpk = null;//TODO;
                InformacionAntecedentePacienteSexual informacion = new InformacionAntecedentePacienteSexual();
                informacion.setTipoAntecedentePersonalSexual(enfermedade);
                informacion.setResultadoAntecedente("no");
                informacion.setAntecedentesPersonalesSexual(selected);
                informacion.setInformacionAntecedentePacienteSexualPK(inforpk);
                this.informacionAntecedentes.add(informacion);
            }

            this.tiposDeAntecedentes = enfermedades;
        }
        return tiposDeAntecedentes;
    }

    public void setTiposDeAntecedentes(List<TipoAntecedentePersonalSexual> tiposDeAntecedentes) {

        this.tiposDeAntecedentes = tiposDeAntecedentes;

    }

    private int Edad_Menarca;

    public int getEdad_Menarca() {

        return Edad_Menarca;

    }

    public void setEdad_Menarca(int Edad_Menarca) {
        this.Edad_Menarca = Edad_Menarca;

    }

    public AntecedentesPersonalesSexualController() {
        System.out.println("CREADO ANTECED");
    }

    public AntecedentesPersonalesSexualFacade getFacade() {
        return ejbAntecedentesFacade;
    }

    public void setFacade(AntecedentesPersonalesSexualFacade ejbFacade) {
        this.ejbAntecedentesFacade = ejbFacade;
    }

    public AntecedentesPersonalesSexual getSelected() {
        if (selected == null) {
            selected = new AntecedentesPersonalesSexual();

        }
        return selected;
    }

    public void setSelected(AntecedentesPersonalesSexual selected) {
        this.selected = selected;
    }

    public List<String> getEnfermedades() {
        List<String> enfermedades = new ArrayList<>();

        enfermedades.add("Enfermedad1");
        enfermedades.add("Enfermedad2");
        enfermedades.add("Enfermedad3");

        return enfermedades;

    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public AntecedentesPersonalesSexual prepareCreate() {
        selected = new AntecedentesPersonalesSexual();
        initializeEmbeddableKey();
        return selected;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AntecedentesPersonalesSexualUpdated"));
    }

    public Date getMaxDate() {
        Calendar cal = Calendar.getInstance();
        return new Date(cal.getTime().getYear(), cal.getTime().getMonth(), cal.getTime().getDate());
    }

    public void Registrar(int id_modulo_historia_sexualidad) {
        try {
            HistoriaModuloSexualidad find = this.historiaModuloSexualidadFacade.find(id_modulo_historia_sexualidad);
            selected.setHistoriaModuloSexualidad(find);
            selected.setId(id_modulo_historia_sexualidad);

            ArrayList<TipoTranstornoMentrual> transtornosPresenta = new ArrayList<>();

            for (int i = 0; i < this.listTranstornosMenstruales.size(); i++) {
                TipoTranstornoMentrual tM = this.listTranstornosMenstruales.get(i);
                if (tM.isPresenta()) {
                    transtornosPresenta.add(tM);
                }
            }
            selected.setTipoTranstornoMentrualList(transtornosPresenta);

            this.ejbAntecedentesFacade.create(selected);

            for (int i = 0; i < informacionAntecedentes.size(); i++) {
                InformacionAntecedentePacienteSexual informacionAntecedentePaciente = informacionAntecedentes.get(i);
                InformacionAntecedentePacienteSexualPK antecedentePacienteSexualPK = new InformacionAntecedentePacienteSexualPK();
                antecedentePacienteSexualPK.setId(selected.getId());
                antecedentePacienteSexualPK.setIdAntecedente(informacionAntecedentePaciente.getTipoAntecedentePersonalSexual().getIdAntecedente());
                informacionAntecedentePaciente.setInformacionAntecedentePacienteSexualPK(antecedentePacienteSexualPK);
                informacionAntecedentePaciente.setAntecedentesPersonalesSexual(selected);
                this.informacionAntecedentePacienteSexualFacade.create(informacionAntecedentePaciente);
            }

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
        }

    }

    public void limpiar() {
        this.selected = new AntecedentesPersonalesSexual();
        this.listTranstornosMenstruales = null;
        this.tiposDeAntecedentes = null;
        this.informacionAntecedentes = null;
        this.viendo=false;
     
    }
    
    
    public void cargarAntecedentes(int id_modulo_historia_sexualidad){
        this.selected = this.ejbAntecedentesFacade.find(id_modulo_historia_sexualidad);
        this.informacionAntecedentes  = this.informacionAntecedentePacienteSexualFacade.findById(this.selected.getId());
        this.listTranstornosMenstruales = this.selected.getTipoTranstornoMentrualList();
        for (TipoTranstornoMentrual trastorno : listTranstornosMenstruales) {
            trastorno.setPresenta(true);
        }
        this.viendo=true;
    }
    

}
