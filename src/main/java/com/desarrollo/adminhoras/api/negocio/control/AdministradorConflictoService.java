package com.desarrollo.adminhoras.api.negocio.control;

import com.desarrollo.adminhoras.api.modelo.ConflictoRegistro;
import java.util.List;

/**
 *
 * @author Alejo Gómez
 */
public interface AdministradorConflictoService {

    void registrarConflicto(Integer idregistro, String descripcion);

    List<ConflictoRegistro> obtenerConflictosDeRegistrosPorIngreso(Integer idingreso);    
}
