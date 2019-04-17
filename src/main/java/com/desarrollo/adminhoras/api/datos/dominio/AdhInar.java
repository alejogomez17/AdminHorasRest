package com.desarrollo.adminhoras.api.datos.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Alejo Gomez
 */
@Entity
@Table(name = "adh_inar")
@NamedQueries({
    @NamedQuery(name = "AdhInar.findByIdinar",query = ""
            + "SELECT i "
            + "FROM AdhInar i "
            + "WHERE i.idinar = :idinar"),
    @NamedQuery(name = "AdhInar.findAll",query = ""
            + "SELECT i "
            + "FROM AdhInar i "
    )
})
public class AdhInar implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "inar_idinar")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idinar;

    @Column(name = "inar_fecing")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecing;

    @Column(name = "inar_descri")
    private String descri;
    
    @Column(name = "inar_estado")
    private Character estado;

    public AdhInar() {
    }

    public AdhInar(Integer idinre, Date fecing, String descri) {
        this.idinar = idinre;
        this.fecing = fecing;
        this.descri = descri;
    }
    
    public AdhInar(Date fecing, String descri) {
        this.fecing = fecing;
        this.descri = descri;
    }

    /**
     * @return the idinre
     */
    public Integer getIdinar() {
        return idinar;
    }

    /**
     * @param idinar the idinre to set
     */
    public void setIdinar(Integer idinar) {
        this.idinar = idinar;
    }

    /**
     * @return the fecing
     */
    public Date getFecing() {
        return fecing;
    }

    /**
     * @param fecing the fecing to set
     */
    public void setFecing(Date fecing) {
        this.fecing = fecing;
    }

    /**
     * @return the descri
     */
    public String getDescri() {
        return descri;
    }

    /**
     * @param descri the descri to set
     */
    public void setDescri(String descri) {
        this.descri = descri;
    }

    /**
     * @return the estado
     */
    public Character getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Character estado) {
        this.estado = estado;
    }
    
    

}
