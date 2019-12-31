package com.desarrollo.adminhoras.api.utilidades;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Alejo Gómez
 */
public class Tool {
    
    private Tool(){
        
    }

    public static Date sumarHorasAFecha(Date fecha, int horas) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.HOUR, horas);
        return calendar.getTime();
    }

}
