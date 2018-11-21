
package com.unicauca.divsalud.clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;


public class GenerarExcel 
{
    public void generarPlantilaControlSexualMensual(Plantilla p, String file_name)
    {
        Map<String, Plantilla> beans = new HashMap<String, Plantilla>();
        XLSTransformer transformer = new XLSTransformer();
        beans.put("plantilla", p);
        try {
            InputStream stream = GenerarExcel.class.getClassLoader().getResourceAsStream(file_name);
            System.out.println("encontre");
            try {
                    Workbook workbook = transformer.transformXLS(stream, beans);
                    /*Esto puede ser escrito dentro de un archivo o simplementee enviado para
                    que sea exportado*/
                    OutputStream reportFile = new FileOutputStream(file_name);
                    workbook.write(reportFile);
                    generarDescarga(file_name);
                } catch (Exception e) {
                    System.out.println("Plantilla Excel encontrada, pero se detectó un error");
                    e.printStackTrace();
                }
        } catch (Exception e) {
            System.out.println("no encontre el archivo");
        }
    }
    private void generarDescarga(String file_name) throws FileNotFoundException, IOException
    {
        System.out.println("llegue a descargar");
        File ficheroXLS = new File(file_name);
        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = new FileInputStream(ficheroXLS);
        byte[] bytes = new byte[1000];
        int read = 0;

        if (!ctx.getResponseComplete()) {
           String fileName = ficheroXLS.getName();
           String contentType = "application/vnd.ms-excel";
           //String contentType = "application/pdf";
           HttpServletResponse response =(HttpServletResponse) ctx.getExternalContext().getResponse();

           response.setContentType(contentType);

           response.setHeader("Content-Disposition",
                              "attachment;filename=\"" + fileName + "\"");

           ServletOutputStream out = response.getOutputStream();

           while ((read = fis.read(bytes)) != -1) {
                out.write(bytes, 0, read);
           }

           out.flush();
           out.close();
           System.out.println("\nDescargado\n");
           ctx.responseComplete();
            }
    }
    
}
