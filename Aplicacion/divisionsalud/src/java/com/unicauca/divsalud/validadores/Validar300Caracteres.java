package com.unicauca.divsalud.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="Validar300Caracteres")
public class Validar300Caracteres implements Validator
{
   

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        
        if(texto.length()>300)
        {
             FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","El campo admite un máximo de 300 caracteres");
             throw new ValidatorException(msg);  
        }           
        
    }
}
