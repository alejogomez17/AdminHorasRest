package com.desarrollo.adminhoras.api.negocio.impl;

import com.desarrollo.adminhoras.api.datos.dao.AdminHorasDao;
import com.desarrollo.adminhoras.api.datos.dominio.AdhCore;
import com.desarrollo.adminhoras.api.datos.dominio.AdhRein;
import com.desarrollo.adminhoras.api.modelo.ConflictoRegistro;
import com.desarrollo.adminhoras.api.negocio.service.AdministracionRegistroService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alejo Gomez
 */
public class AdministracionRegistroServiceImpl implements AdministracionRegistroService {

    @Autowired
    private AdminHorasDao adminHorasDaoImpl;

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
    
    @Override
    public List<ConflictoRegistro> obtenerConflictosDeRegistrosPorIngreso(Integer idingreso){
        List<AdhCore> conflictos = this.adminHorasDaoImpl.obtenerConflictosDeRegistroPorIngreso(idingreso);
        List<ConflictoRegistro> conflictosModelo = new ArrayList<>();
        for(AdhCore conflicto:conflictos){
            conflictosModelo.add(new ConflictoRegistro(conflicto));
        }
        return conflictosModelo;
    }
}
