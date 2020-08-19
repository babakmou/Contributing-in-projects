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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "projet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projet.findAll", query = "SELECT p FROM Projet p")
    , @NamedQuery(name = "Projet.findById", query = "SELECT p FROM Projet p WHERE p.id = :id")
    , @NamedQuery(name = "Projet.findByNom", query = "SELECT p FROM Projet p WHERE p.nom = :nom")})
public class Projet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projet")
    private Collection<Contribution> contributionCollection;
    @JoinColumn(name = "id_membre", referencedColumnName = "id")
    @ManyToOne
    private Membre idMembre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projet")
    private Collection<Invitation> invitationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projet")
    private Collection<Demande> demandeCollection;

    public Projet() {
    }

    public Projet(String id) {
        this.id = id;
    }

    public Projet(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public Collection<Contribution> getContributionCollection() {
        return contributionCollection;
    }

    public void setContributionCollection(Collection<Contribution> contributionCollection) {
        this.contributionCollection = contributionCollection;
    }

    public Membre getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Membre idMembre) {
        this.idMembre = idMembre;
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
        if (!(object instanceof Projet)) {
            return false;
        }
        Projet other = (Projet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.babak.entite.Projet[ id=" + id + " ]";
    }
    
}
