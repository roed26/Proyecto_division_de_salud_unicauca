/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.OtrosTipoExamDiagnosticoSexual;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author acer_acer
 */
@Named("otrosTipoExamDiagnosticoSexualController")
@SessionScoped
public class OtrosTipoExamDiagnosticoSexualController implements Serializable {

    private OtrosTipoExamDiagnosticoSexual selected;

    @EJB
    private com.unicauca.divsalud.sessionbeans.OtrosTipoExamDiagnosticoSexualFacade ejbFacade;

    @PostConstruct
    public void init() {
        selected = new OtrosTipoExamDiagnosticoSexual();
    }

    public OtrosTipoExamDiagnosticoSexual getSelected() {
        return selected;
    }

    public void setSelected(OtrosTipoExamDiagnosticoSexual selected) {
        this.selected = selected;
    }

    public void registrarTipo(int id) {
        System.out.println("Registro");
        RequestContext requestContext = RequestContext.getCurrentInstance();
        selected.setIdOtroExamDiag(new Long(id));
        try {
            ejbFacade.create(selected);
            limpiarCampos();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro de tipo", "El registro del tipo fue exitoso.");
            requestContext.execute("PF('RegistroExitoso').show()");

        } catch (Exception e) {

            requestContext.execute("PF('RegistroNoExitoso').show()");
        }
    }
    


    private void limpiarCampos() {
        selected = new OtrosTipoExamDiagnosticoSexual();
    }

}
