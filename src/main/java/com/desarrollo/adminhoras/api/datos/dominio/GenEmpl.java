package com.desarrollo.adminhoras.api.datos.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alejo Gomez
 */
@Entity
@Table(name = "gen_empl")
@NamedQueries({
    @NamedQuery(name = "GenEmpl.findAll", query = ""
            + "SELECT e "
            + "FROM GenEmpl e ")
})
public class GenEmpl implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "empl_idempl")
    private Integer idempl;

    @Column(name = "empl_nombre")
    private String nombre;

    @Column(name = "empl_docume")
    private String docume;

    @Column(name = "empl_nrotar")
    private String nrotar;

    public GenEmpl() {
    }

    public GenEmpl(Integer idempl, String nombre, String docume, String nrotar) {
        this.idempl = idempl;
        this.nombre = nombre;
        this.docume = docume;
        this.nrotar = nrotar;
    }

    /**
     * @return the idempl
     */
    public Integer getIdempl() {
        return idempl;
    }

    /**
     * @param idempl the idempl to set
     */
    public void setIdempl(Integer idempl) {
        this.idempl = idempl;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the docume
     */
    public String getDocume() {
        return docume;
    }

    /**
     * @param docume the docume to set
     */
    public void setDocume(String docume) {
        this.docume = docume;
    }

    /**
     * @return the nrotar
     */
    public String getNrotar() {
        return nrotar;
    }

    /**
     * @param nrotar the nrotar to set
     */
    public void setNrotar(String nrotar) {
        this.nrotar = nrotar;
    }

}
