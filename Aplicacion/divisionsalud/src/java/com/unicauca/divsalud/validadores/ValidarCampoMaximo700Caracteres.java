package com.unicauca.divsalud.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarCampoMaximo700Caracteres")
public class ValidarCampoMaximo700Caracteres implements Validator
{
   

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        
        if(texto.length()>700)
        {
             FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","El campo admite máximo 700 caracteres.");
             throw new ValidatorException(msg);  
        }           
        
    }
}