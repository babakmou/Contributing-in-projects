/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.entite;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Babak
 */
@Embeddable
public class InvitationPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_contributeur")
    private String idContributeur;
    @Basic(optional = false)
    @Column(name = "id_projet")
    private String idProjet;

    public InvitationPK() {
    }

    public InvitationPK(String idContributeur, String idProjet) {
        this.idContributeur = idContributeur;
        this.idProjet = idProjet;
    }

    public String getIdContributeur() {
        return idContributeur;
    }

    public void setIdContributeur(String idContributeur) {
        this.idContributeur = idContributeur;
    }

    public String getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(String idProjet) {
        this.idProjet = idProjet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContributeur != null ? idContributeur.hashCode() : 0);
        hash += (idProjet != null ? idProjet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvitationPK)) {
            return false;
        }
        InvitationPK other = (InvitationPK) object;
        if ((this.idContributeur == null && other.idContributeur != null) || (this.idContributeur != null && !this.idContributeur.equals(other.idContributeur))) {
            return false;
        }
        if ((this.idProjet == null && other.idProjet != null) || (this.idProjet != null && !this.idProjet.equals(other.idProjet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.babak.entite.InvitationPK[ idContributeur=" + idContributeur + ", idProjet=" + idProjet + " ]";
    }
    
}
