
package com.unicauca.divsalud.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarSoloNumeros")
public class ValidarSoloNumeros implements Validator
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        System.out.println("texto "+texto);
        if(texto.length()==0)
            return;
        if(!tieneSoloNumeros(texto))
        {
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Campo sólo admite números.");
            throw new ValidatorException(msg);
        }
        
    }
    
    private boolean tieneSoloNumeros(String cadena) 
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
