package com.unicauca.divsalud.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarLongitudMaximo3Numeros")
public class ValidarLongitudMaximo3Numeros implements Validator
{
   

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        
        System.out.println("Texto: "+texto);
        
        if(value==null || texto.length()==0 )
            return;
        
        if(texto.length()>3)
        {
             FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","El valor del campo tiene como longitud máxima 3 números.");
             throw new ValidatorException(msg);  
        }           
        
    }
}
