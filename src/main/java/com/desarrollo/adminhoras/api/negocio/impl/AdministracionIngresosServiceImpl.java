package com.desarrollo.adminhoras.api.negocio.impl;

import com.desarrollo.adminhoras.api.datos.dao.AdminHorasDao;
import com.desarrollo.adminhoras.api.datos.dominio.AdhCore;
import com.desarrollo.adminhoras.api.datos.dominio.AdhInar;
import com.desarrollo.adminhoras.api.datos.dominio.AdhRein;
import com.desarrollo.adminhoras.api.modelo.IngresoArchivo;
import com.desarrollo.adminhoras.api.negocio.service.AdministracionIngresosService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alejo Gomez
 */
public class AdministracionIngresosServiceImpl implements AdministracionIngresosService {

    @Autowired
    private AdminHorasDao adminHorasDaoImpl;

    @Override
    public void verificarIngresoArchivo(Integer identificadorIngreso) {
        List<AdhRein> registrosIngreso = this.adminHorasDaoImpl.obtenerRegistrosPorIdentificadorDeIngreso(identificadorIngreso);
        if (registrosIngreso.isEmpty()) {
            System.out.println("Error, no se encuentran registros para este ingreso de archivo");
        }
        AdhInar ingresoDeArchivo = this.adminHorasDaoImpl.obtenerIngresoDeArchivoPorIdentificador(identificadorIngreso);
        if (Objects.isNull(ingresoDeArchivo)) {
            System.out.println("Error ingres de archivo no existe");
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
                        registrarConflicto(registroAnterior.getIdrein(), "Empleado solo registra Entrada en un día.");
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
                        registrarConflicto(registroAValidar.getIdrein(), "Empleado con dos registros de Entrada seguidos");
                        registroAnterior.setEstado('C');
                        registrarConflicto(registroAnterior.getIdrein(), "Empleado con dos registros de Entrada seguidos");
                        
                    }
                }
            }
            // registro de salida
            if (registroAValidar.getTipoin().compareTo('S') == 0) {
                if (Objects.isNull(registroAnterior)) {
                    // primer registro del día es una salida
                    registroAValidar.setEstado('C');
                } else {
                    if (registroAnterior.getTipoin().compareTo('S') == 0) {
                        // dos registros de salidas iguales
                        registroAValidar.setEstado('C');
                        registrarConflicto(registroAValidar.getIdrein(), "Empleado con dos registros de Salida seguidos");
                        registroAnterior.setEstado('C');
                        registrarConflicto(registroAnterior.getIdrein(), "Empleado con dos registros de Salida seguidos");
                    } else {
                        int diferencia = (int) ((registroAValidar.getFechar().getTime() - registroAnterior.getFechar().getTime()) / 1000);
                        int horas = (int) Math.floor(diferencia / 3600);
                        // falta donde calcular horas
                        System.out.println("diferencia: " + horas);
                    }
                }
            }
            registroAnterior = registroAValidar;
        }
        actualizarRegistrosDeIngreso(registrosIngreso);
    }

    private void registrarConflicto(Integer idregistro, String descripcion) {
        AdhCore conflictoRegistro = new AdhCore();
        conflictoRegistro.setDescri(descripcion);
        conflictoRegistro.setEstlog('A');
        conflictoRegistro.setIdesco('R');
        conflictoRegistro.setIdrein(new AdhRein(idregistro));
        this.adminHorasDaoImpl.insertarConflictoDeRegistro(conflictoRegistro);
    }

    private void actualizarRegistrosDeIngreso(List<AdhRein> registrosAActualizar) {
        try {
            this.adminHorasDaoImpl.actualizarRegistrosDeIngreso(registrosAActualizar);
        } catch (Exception ex) {
            System.out.println("error actualizando registros");
        }
    }
    
    @Override
    public List<IngresoArchivo> obtenerIngresosDeArchivos(){
        List<AdhInar> ingresos = this.adminHorasDaoImpl.obtenerListaDeIngresoDeArchivos();
        List<IngresoArchivo> ingresosModelo = new ArrayList<>();
        for(AdhInar ingreso:ingresos){
            ingresosModelo.add(new IngresoArchivo(ingreso));
        }
        return ingresosModelo;
    }
}
