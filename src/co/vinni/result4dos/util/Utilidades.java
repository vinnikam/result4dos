/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.vinni.result4dos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinni
 */
public class Utilidades {
    public static Date obtenerFecha(String formato, String fecha){
        
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        if (fecha != null){
            try {
                return sdf.parse(fecha);
            } catch (ParseException ex) {
                Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
        
    }
    public static String formatoFecha(String formato, Date fecha){
        
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        if (fecha != null){
            return sdf.format(fecha);
        }
        return "N/A";
        
    }
    public static String formatoFecha(Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (fecha != null){
            return sdf.format(fecha);
        }
        return "N/A";
    }
    public static Long convertirNumeroL(String numero){
        Long rta = 0L;
        try {
            rta = Long.parseLong(numero);
        }catch(Exception e){
            
        }
        return rta;
        
    }
    public static int convertirNumeroI(String numero){
        Long rta = convertirNumeroL(numero);
        return rta.intValue();
        
    }
    
}
