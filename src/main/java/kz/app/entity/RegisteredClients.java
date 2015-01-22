/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Дамир
 */
@Entity
@Table(name = "registered_clients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegisteredClients.findAll", query = "SELECT r FROM RegisteredClients r"),
    @NamedQuery(name = "RegisteredClients.findByReceiverId", query = "SELECT r FROM RegisteredClients r WHERE r.receiverId = :receiverId"),
    @NamedQuery(name = "RegisteredClients.findByReward", query = "SELECT r FROM RegisteredClients r WHERE r.reward = :reward"),
    @NamedQuery(name = "RegisteredClients.findByTotalAmount", query = "SELECT r FROM RegisteredClients r WHERE r.totalAmount = :totalAmount")})
public class RegisteredClients implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "receiver_id")
    @Id
    private Integer receiverId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "reward")
    private Double reward;
    @Column(name = "totalAmount")
    private Double totalAmount;

    public RegisteredClients() {
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
}
