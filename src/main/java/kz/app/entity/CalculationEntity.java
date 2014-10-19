/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.entity;

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
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Дамир
 */
@Entity
@Table(name = "calculation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalculationEntity.findAll", query = "SELECT c FROM CalculationEntity c"),
    @NamedQuery(name = "CalculationEntity.findById", query = "SELECT c FROM CalculationEntity c WHERE c.id = :id")})
public class CalculationEntity implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 255)
    @Column(name = "info")
    private String info;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ves_chasti")
    private Double vesChasti = 0.0;
    @Column(name = "cena_za_kg")
    private Double cenaZaKg = 0.0;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public CalculationEntity() {
    }

    public CalculationEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalculationEntity)) {
            return false;
        }
        CalculationEntity other = (CalculationEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kz.app.entity.CalculationEntity[ id=" + id + " ]";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getVesChasti() {
        return vesChasti;
    }

    public void setVesChasti(Double vesChasti) {
        this.vesChasti = vesChasti;
    }

    public Double getCenaZaKg() {
        return cenaZaKg;
    }

    public void setCenaZaKg(Double cenaZaKg) {
        this.cenaZaKg = cenaZaKg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
