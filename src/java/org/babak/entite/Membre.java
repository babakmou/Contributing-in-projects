/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.entite;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Babak
 */
@Entity
@Table(name = "membre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membre.findAll", query = "SELECT m FROM Membre m")
    , @NamedQuery(name = "Membre.findById", query = "SELECT m FROM Membre m WHERE m.id = :id")
    , @NamedQuery(name = "Membre.findByEmail", query = "SELECT m FROM Membre m WHERE m.email = :email")
    , @NamedQuery(name = "Membre.findByPassword", query = "SELECT m FROM Membre m WHERE m.password = :password")})
public class Membre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membre")
    private Collection<Contribution> contributionCollection;
    @OneToMany(mappedBy = "idMembre")
    private Collection<Projet> projetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membre")
    private Collection<Invitation> invitationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membre")
    private Collection<Demande> demandeCollection;

    public Membre() {
    }

    public Membre(String id) {
        this.id = id;
    }

    public Membre(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Contribution> getContributionCollection() {
        return contributionCollection;
    }

    public void setContributionCollection(Collection<Contribution> contributionCollection) {
        this.contributionCollection = contributionCollection;
    }

    @XmlTransient
    public Collection<Projet> getProjetCollection() {
        return projetCollection;
    }

    public void setProjetCollection(Collection<Projet> projetCollection) {
        this.projetCollection = projetCollection;
    }

    @XmlTransient
    public Collection<Invitation> getInvitationCollection() {
        return invitationCollection;
    }

    public void setInvitationCollection(Collection<Invitation> invitationCollection) {
        this.invitationCollection = invitationCollection;
    }

    @XmlTransient
    public Collection<Demande> getDemandeCollection() {
        return demandeCollection;
    }

    public void setDemandeCollection(Collection<Demande> demandeCollection) {
        this.demandeCollection = demandeCollection;
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
        if (!(object instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.babak.entite.Membre[ id=" + id + " ]";
    }
    
}
