/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.divsalud.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author David
 */
@FacesValidator(value="ValidarLongitudMaximo2Numeros")

public class ValidadCampoMaximo2Numeros implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        
        if(texto.length()==0)
            return;
        
        if(texto.length()>2)
        {
             FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","El valor del campo tiene como longitud máxima 2 números.");
             throw new ValidatorException(msg);  
        }           
        
    }
}
