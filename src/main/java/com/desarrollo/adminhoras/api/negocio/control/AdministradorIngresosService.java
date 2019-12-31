
package com.desarrollo.adminhoras.api.negocio.control;

import com.desarrollo.adminhoras.api.datos.dominio.AdhInar;
import com.desarrollo.adminhoras.api.modelo.IngresoArchivo;
import java.util.List;

/**
 *
 * @author Alejo Gomez
 */
public interface AdministradorIngresosService {
    void verificarIngresoArchivo(Integer identificadorIngreso);
    
    List<IngresoArchivo> obtenerIngresosDeArchivos();
}
