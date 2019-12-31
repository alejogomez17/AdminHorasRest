package com.desarrollo.adminhoras.api.negocio.control;

import com.desarrollo.adminhoras.api.datos.dominio.AdhRein;

/**
 *
 * @author Alejo Gómez
 */
public interface CalculoHorasService {
    void realizarCalculoDeHoras(AdhRein ingresoDesde, AdhRein ingresoHasta);
}
