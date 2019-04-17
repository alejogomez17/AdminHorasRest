package com.desarrollo.adminhoras.api.datos.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alejo Gomez
 */
@Entity
@Table(name = "adh_core")
@NamedQueries({
    @NamedQuery(name = "AdhCore.findByIdinar", query = ""
            + "SELECT c "
            + "FROM AdhCore c "
            + "WHERE c.idrein.idinar = :idinar "
            + "AND c.estlog <> 'E' ")
})
public class AdhCore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "core_idcore")
    private Integer idcore;

    @ManyToOne
    @JoinColumn(name = "core_idrein", referencedColumnName = "rein_idrein")
    private AdhRein idrein;

    @Column(name = "core_descri")
    private String descri;

    @Column(name = "core_estlog")
    private Character estlog;

    @Column(name = "core_idesco")
    private Character idesco;

    public AdhCore() {
    }

    public AdhCore(Integer idcore, AdhRein idrein, String descri, Character estlog, Character idesco) {
        this.idcore = idcore;
        this.idrein = idrein;
        this.descri = descri;
        this.estlog = estlog;
        this.idesco = idesco;
    }

    /**
     * @return the idcore
     */
    public Integer getIdcore() {
        return idcore;
    }

    /**
     * @param idcore the idcore to set
     */
    public void setIdcore(Integer idcore) {
        this.idcore = idcore;
    }

    /**
     * @return the idrein
     */
    public AdhRein getIdrein() {
        return idrein;
    }

    /**
     * @param idrein the idrein to set
     */
    public void setIdrein(AdhRein idrein) {
        this.idrein = idrein;
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
     * @return the estlog
     */
    public Character getEstlog() {
        return estlog;
    }

    /**
     * @param estlog the estlog to set
     */
    public void setEstlog(Character estlog) {
        this.estlog = estlog;
    }

    /**
     * @return the idesco
     */
    public Character getIdesco() {
        return idesco;
    }

    /**
     * @param idesco the idesco to set
     */
    public void setIdesco(Character idesco) {
        this.idesco = idesco;
    }
    
    
}
