
package com.desarrollo.adminhoras.api.datos.dao;

import com.desarrollo.adminhoras.api.datos.dominio.AdhCore;
import com.desarrollo.adminhoras.api.datos.dominio.AdhHore;
import com.desarrollo.adminhoras.api.datos.dominio.GenEmpl;
import com.desarrollo.adminhoras.api.datos.dominio.AdhInar;
import com.desarrollo.adminhoras.api.datos.dominio.AdhRein;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alejo Gomez
 */
public interface AdminHorasDao {
    
    List<GenEmpl> obtenerListaEmpleados();
    
    AdhInar registrarIngresoDeRegistros(Date fechaIngreso,String descripcion);
    
    AdhRein insertarRegistroDeIngreso(AdhRein registro);
    
    List<AdhRein> obtenerRegistrosPorIdentificadorDeIngreso(Integer idinar);
    
    AdhInar obtenerIngresoDeArchivoPorIdentificador(Integer identificador);
    
    int actualizarRegistrosDeIngreso(List<AdhRein> registros);
    
    AdhCore insertarConflictoDeRegistro(AdhCore conflictoRegistro);
    
    List<AdhInar> obtenerListaDeIngresoDeArchivos();
    
    List<AdhCore> obtenerConflictosDeRegistroPorIngreso(Integer idingreso);
    
    AdhHore insertarHorasRegistradas(AdhHore horasRegistradas);
    
}
