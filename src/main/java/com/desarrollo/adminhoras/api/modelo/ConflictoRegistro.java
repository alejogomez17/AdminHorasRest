package com.desarrollo.adminhoras.api.modelo;

import com.desarrollo.adminhoras.api.datos.dominio.AdhCore;

/**
 *
 * @author Alejo Gomez
 */
public class ConflictoRegistro {

    private int id;
    private String descripcion;
    private String estado;

    public ConflictoRegistro() {
    }
    
    public ConflictoRegistro(AdhCore conflicto) {
        this.id = conflicto.getIdcore();
        this.descripcion = conflicto.getDescri();
        this.estado = conflicto.getIdesco().toString();
    }

    public ConflictoRegistro(int id, String descripcion, String estado) {
        this.id = id;
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
