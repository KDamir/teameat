/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Дамир
 */
@Entity
@Table(name = "receiver")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receiver.findAll", query = "SELECT r FROM ReceiverEntity r"),
    @NamedQuery(name = "Receiver.findByCompanyName", query = "SELECT r FROM ReceiverEntity r WHERE r.companyName = :companyName"),
    @NamedQuery(name = "Receiver.findById", query = "SELECT r FROM ReceiverEntity r WHERE r.id = :id")})
public class ReceiverEntity implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "reward")
    private Double reward;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "company_name")
    private String companyName ;
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id ;

    @Size(max = 10)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic(optional = true)
    @NotNull
    @Size(max = 50)
    @Column(name = "address")
    private String address;
    @Basic(optional = true)
    @NotNull
    @Size(max = 1000)
    @Column(name = "note")
    private String note;
    
//    @OneToMany(mappedBy = "receiverId")
//    private Collection<Invoice> invoiceCollection;

    public ReceiverEntity() {
    }

    public ReceiverEntity(String companyName) {
        this.companyName = companyName;
    }

    public ReceiverEntity(String companyName, Integer id, Double reward) {
        this.companyName = companyName;
        this.id = id;
        this.reward = reward;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
        hash += (companyName != null ? companyName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReceiverEntity)) {
            return false;
        }
        ReceiverEntity other = (ReceiverEntity) object;
        if ((this.companyName == null && other.companyName != null) || (this.companyName != null && !this.companyName.equals(other.companyName))) {
            return false;
    }
        return true;
    }

    @Override
    public String toString() {
//        return "kz.app.entity.ReceiverEntity[ companyName=" + companyName + " ]";
        return String.valueOf(id);
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
    
    
    
}
