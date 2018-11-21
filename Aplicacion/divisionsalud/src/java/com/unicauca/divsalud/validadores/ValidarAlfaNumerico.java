
package com.unicauca.divsalud.validadores;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="ValidarAlfaNumerico")
public class ValidarAlfaNumerico implements Validator 
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
             
        if(!esAlfaNumerica(texto))
        {
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Solo se permiten caracteres alfa-númericos.");
            throw new ValidatorException(msg);
        }
    }
    private boolean esAlfaNumerica(String cadena) {
    for(int i = 0; i < cadena.length(); ++i) {
        char caracter = cadena.charAt(i);
 
        if(!Character.isLetterOrDigit(caracter)) {
            return false;
        }
    }
    return true;
}
    
}