package com.desarrollo.adminhoras.api.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Alejo Gómez
 */
public class EstructuraFechaRegistro {

    private Date fecha;
    private int dia;
    private int mes;
    private int año;
    private int hora;
    private int minutos;
    private int segundos;

    public EstructuraFechaRegistro() {
    }

    public EstructuraFechaRegistro(Date fecha) {
        this.fecha = fecha;
        Calendar calendario = new GregorianCalendar();
        calendario.setTime(fecha);
        this.dia = calendario.get(Calendar.DAY_OF_MONTH);
        this.mes = calendario.get(Calendar.MONTH);
        this.año = calendario.get(Calendar.YEAR);
        this.hora = calendario.get(Calendar.HOUR_OF_DAY);
        this.minutos = calendario.get(Calendar.MINUTE);
        this.segundos = calendario.get(Calendar.SECOND);
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAño() {
        return año;
    }

    public int getHora() {
        return hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public Date getFecha() {
        return fecha;
    }
}
