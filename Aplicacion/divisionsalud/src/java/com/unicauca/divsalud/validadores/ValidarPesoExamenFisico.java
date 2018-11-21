package com.unicauca.divsalud.validadores;

import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarPesoExamenFisico")
public class ValidarPesoExamenFisico implements Validator
{
   private static double MAX_VALOR=999.9;
   private static int MAX_DECIMALES=1;
   
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        BigDecimal pesoBig = (BigDecimal) value;
        if(pesoBig==null)
            return;
        double peso = pesoBig.doubleValue();
        if(peso ==0)
        {
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","El campo solo se permiten valores positivos");
            throw new ValidatorException(msg);
        }
        
        if(peso>MAX_VALOR)
        {
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","El valor máximo permitido es "+MAX_VALOR);
            throw new ValidatorException(msg);
        }
        
        int decimales = pesoBig.scale();
        if(decimales>MAX_DECIMALES)
        {
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"","El campo solo admite "+MAX_DECIMALES+" decimal (es)");
            throw new ValidatorException(msg);
        }
        
    }
}
