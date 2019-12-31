package com.desarrollo.adminhoras.api.negocio.impl;

import com.desarrollo.adminhoras.api.datos.dao.AdminHorasDao;
import com.desarrollo.adminhoras.api.datos.dominio.AdhCore;
import com.desarrollo.adminhoras.api.datos.dominio.AdhInar;
import com.desarrollo.adminhoras.api.datos.dominio.AdhRein;
import com.desarrollo.adminhoras.api.modelo.IngresoArchivo;
import com.desarrollo.adminhoras.api.negocio.control.AdministradorConflictoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import com.desarrollo.adminhoras.api.negocio.control.AdministradorIngresosService;
import com.desarrollo.adminhoras.api.negocio.control.CalculoHorasService;

/**
 *
 * @author Alejo Gomez
 */
public class AdministradorIngresosServiceImpl implements AdministradorIngresosService {

    @Autowired
    private AdminHorasDao adminHorasDaoImpl;

    @Autowired
    private AdministradorConflictoService administradorConflictoServiceImpl;

    @Autowired
    private CalculoHorasService calculoHorasServiceImpl;

    @Override
    public void verificarIngresoArchivo(Integer identificadorIngreso) {
        List<AdhRein> registrosIngreso = this.adminHorasDaoImpl.obtenerRegistrosPorIdentificadorDeIngreso(identificadorIngreso);
        if (registrosIngreso.isEmpty()) {
            System.out.println("Error, no se encuentran registros para este ingreso de archivo");
        }
        AdhInar ingresoDeArchivo = this.adminHorasDaoImpl.obtenerIngresoDeArchivoPorIdentificador(identificadorIngreso);
        if (Objects.isNull(ingresoDeArchivo)) {
            System.out.println("Error ingreso de archivo no existe");
        }
        ingresoDeArchivo.setEstado('P');
        Integer idEmpleadoAValidar = 0;
        AdhRein registroAnterior = null;
        for (AdhRein registroAValidar : registrosIngreso) {
            //el identificador del empleado sigue siendo el mismo o es apenas el primero
            if (!Objects.equals(idEmpleadoAValidar, registroAValidar.getIdempl()) || idEmpleadoAValidar == 0) {
                //confirma que hubo cambia de usuario
                if (Objects.nonNull(registroAnterior)) {
                    // empleado anterior no terminó con un registro de salida
                    if (registroAnterior.getTipoin().compareTo('E') == 0) {
                        registroAnterior.setEstado('C');
                        this.administradorConflictoServiceImpl.registrarConflicto(registroAnterior.getIdrein(), "Empleado solo registra Entrada en un día.");
                    }
                } else {
                    //cambio de empleado
                    idEmpleadoAValidar = registroAValidar.getIdempl();
                }

            }

            // si registro es de tipo entrada
            if (registroAValidar.getTipoin().compareTo('E') == 0) {
                if (Objects.nonNull(registroAnterior)) {
                    //conflicto dos entradas seguidas
                    if (registroAnterior.getTipoin().compareTo('E') == 0) {
                        //dos registros de entrada juntos
                        registroAValidar.setEstado('C');
                        administradorConflictoServiceImpl.registrarConflicto(registroAValidar.getIdrein(), "Empleado con dos registros de Entrada seguidos");
                        registroAnterior.setEstado('C');
                        administradorConflictoServiceImpl.registrarConflicto(registroAnterior.getIdrein(), "Empleado con dos registros de Entrada seguidos");

                    }
                }
            }
            // registro de salida
            if (registroAValidar.getTipoin().compareTo('S') == 0) {
                if (Objects.isNull(registroAnterior)) {
                    // primer registro del día es una salida OJO
                    registroAValidar.setEstado('C');
                } else {
                    if (registroAnterior.getTipoin().compareTo('S') == 0) {
                        // dos registros de salidas iguales
                        registroAValidar.setEstado('C');
                        administradorConflictoServiceImpl.registrarConflicto(registroAValidar.getIdrein(), "Empleado con dos registros de Salida seguidos");
                        registroAnterior.setEstado('C');
                        administradorConflictoServiceImpl.registrarConflicto(registroAnterior.getIdrein(), "Empleado con dos registros de Salida seguidos");
                    } else {
                        this.calculoHorasServiceImpl.realizarCalculoDeHoras(registroAnterior, registroAValidar);
                    }
                }
            }
            registroAnterior = registroAValidar;
        }
        actualizarRegistrosDeIngreso(registrosIngreso);
    }

    private void actualizarRegistrosDeIngreso(List<AdhRein> registrosAActualizar) {
        try {
            this.adminHorasDaoImpl.actualizarRegistrosDeIngreso(registrosAActualizar);
        } catch (Exception ex) {
            System.out.println("error actualizando registros");
        }
    }

    @Override
    public List<IngresoArchivo> obtenerIngresosDeArchivos() {
        List<AdhInar> ingresos = this.adminHorasDaoImpl.obtenerListaDeIngresoDeArchivos();
        List<IngresoArchivo> ingresosModelo = new ArrayList<>();
        for (AdhInar ingreso : ingresos) {
            ingresosModelo.add(new IngresoArchivo(ingreso));
        }
        return ingresosModelo;
    }
}
