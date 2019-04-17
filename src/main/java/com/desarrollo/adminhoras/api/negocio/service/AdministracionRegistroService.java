package com.desarrollo.adminhoras.api.negocio.service;

import com.desarrollo.adminhoras.api.datos.dominio.AdhCore;
import com.desarrollo.adminhoras.api.modelo.ConflictoRegistro;
import java.util.List;

/**
 *
 * @author Alejo Gomez
 */
public interface AdministracionRegistroService {

    void administrarNuevoRegistro(String[] datosRegistro);
    List<ConflictoRegistro> obtenerConflictosDeRegistrosPorIngreso(Integer idingreso);
    
}
