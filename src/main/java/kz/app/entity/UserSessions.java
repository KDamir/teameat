/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Дамир
 */
@Entity
@Table(name = "user_sessions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSessions.findAll", query = "SELECT u FROM UserSessions u"),
    @NamedQuery(name = "UserSessions.findById", query = "SELECT u FROM UserSessions u WHERE u.id = :id"),
    @NamedQuery(name = "UserSessions.findByBeginTime", query = "SELECT u FROM UserSessions u WHERE u.beginTime = :beginTime"),
    @NamedQuery(name = "UserSessions.findByEndTime", query = "SELECT u FROM UserSessions u WHERE u.endTime = :endTime"),
    @NamedQuery(name = "UserSessions.findByIp", query = "SELECT u FROM UserSessions u WHERE u.ip = :ip")})
public class UserSessions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "beginTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "endTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Size(max = 255)
    @Column(name = "ip")
    private String ip;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;
//    @OneToMany(mappedBy = "sessionId")
//    private Collection<UserActions> userActionsCollection;

    public UserSessions() {
    }

    public UserSessions(Integer id) {
        this.id = id;
    }

    public UserSessions(Integer id, Date beginTime, Date endTime) {
        this.id = id;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

//    @XmlTransient
//    public Collection<UserActions> getUserActionsCollection() {
//        return userActionsCollection;
//    }
//
//    public void setUserActionsCollection(Collection<UserActions> userActionsCollection) {
//        this.userActionsCollection = userActionsCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSessions)) {
            return false;
        }
        UserSessions other = (UserSessions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kz.app.entity.UserSessions[ id=" + id + " ]";
    }
    
}
