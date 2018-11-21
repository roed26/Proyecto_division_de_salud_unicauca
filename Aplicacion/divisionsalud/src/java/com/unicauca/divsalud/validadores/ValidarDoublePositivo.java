
package com.unicauca.divsalud.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarDoublePositivo")
public class ValidarDoublePositivo implements Validator
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        System.out.println("texto "+texto);
        if(texto.length()==0 || texto.equals(""))
            return;
        
        try
        {
            double num = Double.parseDouble(texto);
            if(num<0)
            {
                FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Campo sólo admite números decimales positivos.");
                throw new ValidatorException(msg);
            }
        }
        catch(Exception e)
        {
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Campo sólo admite números decimales positivos.");
            throw new ValidatorException(msg);
        }
        
    }
    
    
    
}
