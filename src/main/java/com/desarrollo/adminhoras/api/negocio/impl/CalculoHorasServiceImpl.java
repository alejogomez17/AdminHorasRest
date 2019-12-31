package com.desarrollo.adminhoras.api.negocio.impl;

import com.desarrollo.adminhoras.api.datos.dao.AdminHorasDao;
import com.desarrollo.adminhoras.api.datos.dominio.AdhHore;
import com.desarrollo.adminhoras.api.datos.dominio.AdhRein;
import com.desarrollo.adminhoras.api.modelo.EstructuraFechaRegistro;
import com.desarrollo.adminhoras.api.negocio.control.CalculoHorasService;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alejo Gómez
 */
public class CalculoHorasServiceImpl implements CalculoHorasService {

    @Autowired
    private AdminHorasDao adminHorasDaoImpl;

    @Override
    public void realizarCalculoDeHoras(AdhRein ingresoDesde, AdhRein ingresoHasta) {
        EstructuraFechaRegistro fechaDesde = new EstructuraFechaRegistro(ingresoDesde.getFechar());
        EstructuraFechaRegistro fechaHasta = new EstructuraFechaRegistro(ingresoHasta.getFechar());
        int diferencia = (int) ((fechaHasta.getFecha().getTime() - fechaDesde.getFecha().getTime()) / 1000);
        int horas = (int) Math.floor(diferencia / 3600);
        int extras = 0;
        if (horas > 8) {
            // más de 8 horas de un día
            extras = horas - 8;
        } else {
            if (horas >= 24) {
                // OJO pasó un día completo trabajando ? 
            }
        }
        AdhHore registroHoras = new AdhHore();
        registroHoras.setIdempl(ingresoDesde.getIdempl());
        registroHoras.setIdreid(ingresoDesde.getIdrein());
        registroHoras.setIdreih(ingresoHasta.getIdrein());
        registroHoras.setTothor(horas);
        registroHoras.setHorext(extras);
        adminHorasDaoImpl.insertarHorasRegistradas(registroHoras);
    }

    private Calendar crearCalendarioDeFecha(Date fecha) {
        Calendar calendario = new GregorianCalendar();
        calendario.setTime(fecha);
        return calendario;
    }
}
