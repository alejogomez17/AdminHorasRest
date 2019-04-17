package com.desarrollo.adminhoras.api.datos.dominio;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "adh_rein")
@NamedQueries({
    @NamedQuery(name = "AdhRein.findByIdinar", query = ""
            + "SELECT r "
            + "FROM AdhRein r "
            + "WHERE r.idinar = :idinar "
            + "ORDER BY r.idempl ASC, r.fechar ASC ")
})
public class AdhRein implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "rein_idrein")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrein;

    @Column(name = "rein_fechar")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechar;

    @Column(name = "rein_idempl")
    private Integer idempl;

    @Column(name = "rein_idinar")
    private Integer idinar;

    @Column(name = "rein_nrotar")
    private String nrotar;

    @Column(name = "rein_estado")
    private Character estado;
    
    @Column(name = "rein_tipoin")
    private Character tipoin;

    public AdhRein() {
    }
    
    public AdhRein(Integer idrein){
        this.idrein = idrein;
    }

    public AdhRein(Integer idrein, Date fechar, Integer idempl, Integer idinre, String nrotar, Character estado) {
        this.idrein = idrein;
        this.fechar = fechar;
        this.idempl = idempl;
        this.idinar = idinre;
        this.nrotar = nrotar;
        this.estado = estado;
    }

    /**
     * @return the idrein
     */
    public Integer getIdrein() {
        return idrein;
    }

    /**
     * @param idrein the idrein to set
     */
    public void setIdrein(Integer idrein) {
        this.idrein = idrein;
    }

    /**
     * @return the fechar
     */
    public Date getFechar() {
        return fechar;
    }

    /**
     * @param fechar the fechar to set
     */
    public void setFechar(Date fechar) {
        this.fechar = fechar;
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
     * @return the idinre
     */
    public Integer getIdinar() {
        return idinar;
    }

    /**
     * @param idinre the idinre to set
     */
    public void setIdinar(Integer idinre) {
        this.idinar = idinre;
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

    /**
     * @return the tipoin
     */
    public Character getTipoin() {
        return tipoin;
    }

    /**
     * @param tipoin the tipoin to set
     */
    public void setTipoin(Character tipoin) {
        this.tipoin = tipoin;
    }
    
    

}
