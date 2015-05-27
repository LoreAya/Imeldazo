/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
 * @author Jesus
 */
@Entity
@Table(name = "picture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Picture.findAll", query = "SELECT p FROM Picture p"),
    @NamedQuery(name = "Picture.findByIdPicture", query = "SELECT p FROM Picture p WHERE p.idPicture = :idPicture"),
    @NamedQuery(name = "Picture.findByUrlPicture", query = "SELECT p FROM Picture p WHERE p.urlPicture = :urlPicture"),
    @NamedQuery(name = "Picture.findByDateHour", query = "SELECT p FROM Picture p WHERE p.dateHour = :dateHour")})
public class Picture implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPicture")
    private Integer idPicture;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "urlPicture")
    private String urlPicture;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_hour")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHour;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "User_idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User useridUser;

    public Picture() {
    }

    public Picture(Integer idPicture) {
        this.idPicture = idPicture;
    }

    public Picture(Integer idPicture, String urlPicture, Date dateHour) {
        this.idPicture = idPicture;
        this.urlPicture = urlPicture;
        this.dateHour = dateHour;
    }

    public Integer getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(Integer idPicture) {
        this.idPicture = idPicture;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public Date getDateHour() {
        return dateHour;
    }

    public void setDateHour(Date dateHour) {
        this.dateHour = dateHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUseridUser() {
        return useridUser;
    }

    public void setUseridUser(User useridUser) {
        this.useridUser = useridUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPicture != null ? idPicture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Picture)) {
            return false;
        }
        Picture other = (Picture) object;
        if ((this.idPicture == null && other.idPicture != null) || (this.idPicture != null && !this.idPicture.equals(other.idPicture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Picture[ idPicture=" + idPicture + " ]";
    }
    
}
