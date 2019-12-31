package com.desarrollo.adminhoras.api.datos.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Alejo Gómez
 */
@Entity
@Table(name = "adh_hore")
public class AdhHore implements Serializable{
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hore_idhore")
    private Integer idhore;
    
    @Column(name = "hore_idempl")
    private Integer idempl;
    
    @Column(name = "hore_idreid")
    private Integer idreid;
    
    @Column(name = "hore_idreih")
    private Integer idreih;
    
    @Column(name = "hore_tothor")
    private Integer tothor;
    
    @Column(name = "hore_horext")
    private Integer horext;

    public AdhHore() {
    }

    public AdhHore(Integer idhore, Integer idempl, Integer idreid, Integer idreih, Integer tothor) {
        this.idhore = idhore;
        this.idempl = idempl;
        this.idreid = idreid;
        this.idreih = idreih;
        this.tothor = tothor;
    }

    public Integer getIdhore() {
        return idhore;
    }

    public void setIdhore(Integer idhore) {
        this.idhore = idhore;
    }

    public Integer getIdempl() {
        return idempl;
    }

    public void setIdempl(Integer idempl) {
        this.idempl = idempl;
    }

    public Integer getIdreid() {
        return idreid;
    }

    public void setIdreid(Integer idreid) {
        this.idreid = idreid;
    }

    public Integer getIdreih() {
        return idreih;
    }

    public void setIdreih(Integer idreih) {
        this.idreih = idreih;
    }

    public Integer getTothor() {
        return tothor;
    }

    public void setTothor(Integer tothor) {
        this.tothor = tothor;
    }

    public Integer getHorext() {
        return horext;
    }

    public void setHorext(Integer horext) {
        this.horext = horext;
    }
}
