package com.desarrollo.adminhoras.api.datos.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Alejo Gómez
 */
@Entity
@Table(name = "gen_esco")
public class GenEsco implements Serializable{
    @Id
    @Basic(optional = false)
    @Column(name = "esco_idesco")
    private Integer idesco;
    
    @Column(name = "esco_nombre")
    private String nombre;

    public GenEsco() {
    }

    public GenEsco(Integer idesco, String nombre) {
        this.idesco = idesco;
        this.nombre = nombre;
    }

    public Integer getIdesco() {
        return idesco;
    }

    public void setIdesco(Integer idesco) {
        this.idesco = idesco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
