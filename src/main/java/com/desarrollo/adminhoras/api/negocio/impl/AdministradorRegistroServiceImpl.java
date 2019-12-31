package com.desarrollo.adminhoras.api.negocio.impl;

import com.desarrollo.adminhoras.api.datos.dominio.AdhRein;
import java.util.Date;
import com.desarrollo.adminhoras.api.negocio.control.AdministradorRegistroService;

/**
 *
 * @author Alejo Gomez
 */
public class AdministradorRegistroServiceImpl implements AdministradorRegistroService {

    @Override
    public void administrarNuevoRegistro(String[] datosRegistro) {
        if (datosRegistro.length < 3) {
            AdhRein registroIngreso = new AdhRein();
            registroIngreso.setEstado('A');
            registroIngreso.setFechar(new Date());
        } else {
            System.out.println("Registros incompletos");
        }
    }
}
