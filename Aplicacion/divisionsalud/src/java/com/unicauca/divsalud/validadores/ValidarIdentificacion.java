package com.unicauca.divsalud.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import com.unicauca.divsalud.entidades.TipoIdentificacion;
@FacesValidator(value="validarIdentificacion")
public class ValidarIdentificacion implements Validator
{
    private static final String PASAPORTE = "PASAPORTE";
    private static final String ADULTO_SIN_IDENTIFICACION = "ADULTO SIN IDENTIFICACION";
    private static final String MENOR_SIN_IDENTIFICACION = "MENOR SIN IDENTIFICACION";
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String identificacion = String.valueOf(value);
        UIComponent component_tipo_id = component.findComponent("tipoIdentificacion");
        TipoIdentificacion tipo_identificacion = (TipoIdentificacion) component_tipo_id.getAttributes().get("value");
        String tipo = "";
        
        if(tipo_identificacion!=null)
            tipo = tipo_identificacion.getNombre();
        if(tipo!=null)
        {
            if(tipo.equals("ADULTO SIN IDENTIFICACION")|| tipo.equals("MENOR SIN IDENTIFICACION"))
            {
                if(identificacion.length()>0)
                {
                    
                    FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Paciente sin identificación, el campo debe quedar vacio");
                    throw new ValidatorException(msg);
                }
            }
            else
            {
                if(identificacion.length()==0)
                {
                    FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Campo obligatorio.");
                    throw new ValidatorException(msg);
                }
                if(identificacion.length()>20)
                {
                    FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Se permiten 20 caracteres como máximo.");
                    throw new ValidatorException(msg);
                }
                if(tipo.equals(PASAPORTE))
                {
                    if(!esAlfaNumerica(identificacion))
                    {
                        FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Solo se permiten caracteres alfanuméricos.");
                        throw new ValidatorException(msg);
                    }
                }
                else 
                {
                    if(!esNumerica(identificacion))
                    {
                        FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Solo se permiten caracteres numéricos.");
                        throw new ValidatorException(msg);
                    }
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
    private boolean esNumerica(String cadena) 
    {
        for(int i = 0; i < cadena.length(); ++i) {
            char caracter = cadena.charAt(i);

            if(!Character.isDigit(caracter)) {
                return false;
            }
        }
        return true;
    }
}
