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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "user_actions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserActions.findAll", query = "SELECT u FROM UserActions u"),
    @NamedQuery(name = "UserActions.findById", query = "SELECT u FROM UserActions u WHERE u.id = :id"),
    @NamedQuery(name = "UserActions.findByDatetime", query = "SELECT u FROM UserActions u WHERE u.datetime = :datetime"),
    @NamedQuery(name = "UserActions.findByDescription", query = "SELECT u FROM UserActions u WHERE u.description = :description")})
public class UserActions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    @ManyToOne
    private UserSessions sessionId;

    public UserActions() {
    }

    public UserActions(Integer id) {
        this.id = id;
    }

    public UserActions(Integer id, Date datetime) {
        this.id = id;
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserSessions getSessionId() {
        return sessionId;
    }

    public void setSessionId(UserSessions sessionId) {
        this.sessionId = sessionId;
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
        if (!(object instanceof UserActions)) {
            return false;
        }
        UserActions other = (UserActions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kz.app.entity.UserActions[ id=" + id + " ]";
    }
    
}
