package com.unicauca.divsalud.validadores;

import com.unicauca.divsalud.entidades.Paciente;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import com.unicauca.divsalud.entidades.TipoIdentificacion;
import com.unicauca.divsalud.managedbeans.PacienteController;
import com.unicauca.divsalud.sessionbeans.PacienteFacade;
import java.util.List;
import javax.ejb.EJB;
@FacesValidator(value="ValidarPacienteRegistrado")
public class ValidarPacienteRegistrado implements Validator
{
    @EJB
    private PacienteFacade facade;
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String identificacion = (String) value;
        UIComponent component_tipo_id = component.findComponent("tipoIdentificacion");
        TipoIdentificacion tipo_identificacion = (TipoIdentificacion) component_tipo_id.getAttributes().get("value");
        
        UIComponent component_id = component.findComponent("id_paciente");
        String id_paciente = String.valueOf(component_id.getAttributes().get("value"));
        
        System.out.println("id: "+id_paciente);
        if(identificacion!=null && identificacion.length()==0)
            return;
        
        if((tipo_identificacion.getId()!=null && identificacion!=null && esAlfaNumerica(identificacion)))
        {
            List<Paciente> pacientes = facade.findByIdentificacionTipoIdentificacion(identificacion, tipo_identificacion.getId());
            //Significa que está registrando
            if(id_paciente == null || String.valueOf(id_paciente).equalsIgnoreCase("null"))
            {
                System.out.println("tipo: "+tipo_identificacion.getNombre()+" id:"+identificacion);
                if(pacientes.size()>0)
                {
                    FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","El paciente con "+tipo_identificacion.getNombre()+" "+identificacion+" ya se encuentra registrado");
                    throw new ValidatorException(msg);
                }
            }
            //Significa que está editando
            else
            {
                System.out.println("Id paciente "+id_paciente);
                Paciente editando = facade.find(Integer.parseInt(id_paciente));
                if(pacientes.size()>0 && !pacientes.get(0).getIdentificacion().equals(editando.getIdentificacion()))
                {
                    FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","El paciente con "+tipo_identificacion.getNombre()+" "+identificacion+" ya se encuentra registrado");
                    throw new ValidatorException(msg);
                }
                
            }
            
        }               
    }
    private boolean esAlfaNumerica(String cadena) 
    {
        for(int i = 0; i < cadena.length(); ++i) {
            char caracter = cadena.charAt(i);

            if(!Character.isLetterOrDigit(caracter)) {
                return false;
            }
        }
        return true;
    }
}
