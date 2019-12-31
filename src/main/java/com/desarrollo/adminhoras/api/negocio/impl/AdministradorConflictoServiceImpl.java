package com.desarrollo.adminhoras.api.negocio.impl;

import com.desarrollo.adminhoras.api.datos.dao.AdminHorasDao;
import com.desarrollo.adminhoras.api.datos.dominio.AdhCore;
import com.desarrollo.adminhoras.api.datos.dominio.AdhRein;
import com.desarrollo.adminhoras.api.modelo.ConflictoRegistro;
import com.desarrollo.adminhoras.api.negocio.control.AdministradorConflictoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alejo Gómez
 */
public class AdministradorConflictoServiceImpl implements AdministradorConflictoService {

    @Autowired
    private AdminHorasDao adminHorasDaoImpl;

    /**
     *
     * @param idregistro
     * @param descripcion
     */
    @Override
    public void registrarConflicto(Integer idregistro, String descripcion) {
        AdhCore conflictoRegistro = new AdhCore();
        conflictoRegistro.setDescri(descripcion);
        conflictoRegistro.setEstlog('A');
        conflictoRegistro.setIdesco('R');
        conflictoRegistro.setIdrein(new AdhRein(idregistro));
        this.adminHorasDaoImpl.insertarConflictoDeRegistro(conflictoRegistro);
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
