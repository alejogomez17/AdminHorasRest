
package com.desarrollo.adminhoras.api.modelo;

import com.desarrollo.adminhoras.api.datos.dominio.AdhInar;

/**
 *
 * @author Alejo Gomez
 */
public class IngresoArchivo {
    private int id;
    private String fechaIngreso;
    private String descripcion;
    private String estado;

    public IngresoArchivo() {
    }
    
    public IngresoArchivo(AdhInar ingresoArchivo){
        this.id = ingresoArchivo.getIdinar();
        this.fechaIngreso = ingresoArchivo.getFecing().toString();
        this.descripcion = ingresoArchivo.getDescri();
        this.estado = ingresoArchivo.getEstado().toString();
    }

    public IngresoArchivo(int id, String fechaIngreso, String descripcion, String estado) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the fechaIngreso
     */
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
