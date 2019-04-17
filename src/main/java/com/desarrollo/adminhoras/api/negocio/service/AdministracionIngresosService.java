
package com.desarrollo.adminhoras.api.negocio.service;

import com.desarrollo.adminhoras.api.datos.dominio.AdhInar;
import com.desarrollo.adminhoras.api.modelo.IngresoArchivo;
import java.util.List;

/**
 *
 * @author Alejo Gomez
 */
public interface AdministracionIngresosService {
    void verificarIngresoArchivo(Integer identificadorIngreso);
    
    List<IngresoArchivo> obtenerIngresosDeArchivos();
}
