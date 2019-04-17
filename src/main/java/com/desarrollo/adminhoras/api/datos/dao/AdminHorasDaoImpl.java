package com.desarrollo.adminhoras.api.datos.dao;

import com.desarrollo.adminhoras.api.datos.dominio.AdhCore;
import com.desarrollo.adminhoras.api.datos.dominio.GenEmpl;
import com.desarrollo.adminhoras.api.datos.dominio.AdhInar;
import com.desarrollo.adminhoras.api.datos.dominio.AdhRein;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejo Gomez
 */
public class AdminHorasDaoImpl implements AdminHorasDao {

    @Autowired
    private EntityManager EntityManager;

    @Override
    public List<GenEmpl> obtenerListaEmpleados() {
        List<GenEmpl> empleados = new ArrayList<>();
        try {
            Query query = this.EntityManager.createNamedQuery("GenEmpl.findAll", GenEmpl.class);
            empleados = query.getResultList();
        } catch (Exception ex) {

        }
        return empleados;
    }

    @Override
    @Transactional
    public AdhInar registrarIngresoDeRegistros(Date fechaIngreso, String descripcion) {
        AdhInar ingresoRegistro = null;
        try {
            ingresoRegistro = new AdhInar(fechaIngreso, descripcion);
            ingresoRegistro = this.EntityManager.merge(ingresoRegistro);
        } catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
        }

        return ingresoRegistro;
    }

    @Override
    @Transactional
    public AdhRein insertarRegistroDeIngreso(AdhRein registro) {
        try {
            registro = this.EntityManager.merge(registro);
        } catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
        }
        return registro;
    }

    @Override
    public List<AdhRein> obtenerRegistrosPorIdentificadorDeIngreso(Integer idinar) {
        List<AdhRein> registros = new ArrayList<>();
        try {
            Query query = this.EntityManager.createNamedQuery("AdhRein.findByIdinar", AdhRein.class);
            query.setParameter("idinar", idinar);
            registros = query.getResultList();
        } catch (Exception ex) {
            System.out.println("error " + ex.getMessage());
        }
        return registros;
    }

    @Override
    public AdhInar obtenerIngresoDeArchivoPorIdentificador(Integer identificador) {
        AdhInar ingresoArchivo = null;
        try {
            Query query = this.EntityManager.createNamedQuery("AdhInar.findByIdinar", AdhInar.class);
            query.setParameter("idinar", identificador);
            List<AdhInar> ingresos = query.getResultList();
            if (!ingresos.isEmpty()) {
                ingresoArchivo = ingresos.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error en consulta de ingresos de archivo");
        }
        return ingresoArchivo;
    }

    @Override
    @Transactional
    public int actualizarRegistrosDeIngreso(List<AdhRein> registros) {
        registros.forEach((registro) -> {
            this.EntityManager.merge(registro);
        });

        return 0;
    }
    
    @Override
    @Transactional
    public AdhCore insertarConflictoDeRegistro(AdhCore conflictoRegistro){
        try{
            conflictoRegistro = this.EntityManager.merge(conflictoRegistro);
        }catch(Exception ex){
            System.out.println("Error en registro de conflicto de registro. Descripción: "+ex.getMessage());
        }
        return conflictoRegistro;
    }
    
    @Override
    public List<AdhInar> obtenerListaDeIngresoDeArchivos(){
        List<AdhInar> ingresos = new ArrayList<>();
        try{
            Query query = this.EntityManager.createNamedQuery("AdhInar.findAll", AdhInar.class);
            ingresos = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
        return ingresos;
    }
    
    @Override
    public List<AdhCore> obtenerConflictosDeRegistroPorIngreso(Integer idingreso){
        List<AdhCore> conflictos = new ArrayList<>();
        try{
            Query query = this.EntityManager.createNamedQuery("AdhCore.findByIdinar",AdhCore.class);
            query.setParameter("idinar", idingreso);
            conflictos = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
        return conflictos;
    }
}
