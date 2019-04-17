package com.desarrollo.adminhoras.api.modelo.peticion;

/**
 *
 * @author Alejo Gomez
 */
public class PeticionVerifcarRegistros {

    private Integer ingresoArchivo;

    public PeticionVerifcarRegistros() {
    }

    public PeticionVerifcarRegistros(Integer ingresoArchivo) {
        this.ingresoArchivo = ingresoArchivo;
    }

    /**
     * @return the ingresoArchivo
     */
    public Integer getIngresoArchivo() {
        return ingresoArchivo;
    }

    /**
     * @param ingresoArchivo the ingresoArchivo to set
     */
    public void setIngresoArchivo(Integer ingresoArchivo) {
        this.ingresoArchivo = ingresoArchivo;
    }
    
    
    
}
