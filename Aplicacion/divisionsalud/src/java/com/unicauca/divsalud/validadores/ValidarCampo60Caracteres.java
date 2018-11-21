package com.unicauca.divsalud.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarCampo60Caracteres")
public class ValidarCampo60Caracteres implements Validator
{
   

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        System.out.println("tesxto "+texto +" largo "+texto.length());
        if(texto.length()>60)
        {
             FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","La longitud máxima es de 60 caracteres");
             throw new ValidatorException(msg);  
        }           
        
    }
}