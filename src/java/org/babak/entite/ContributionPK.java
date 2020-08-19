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
public class ContributionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_membre")
    private String idMembre;
    @Basic(optional = false)
    @Column(name = "id_projet")
    private String idProjet;

    public ContributionPK() {
    }

    public ContributionPK(String idMembre, String idProjet) {
        this.idMembre = idMembre;
        this.idProjet = idProjet;
    }

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
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
        hash += (idMembre != null ? idMembre.hashCode() : 0);
        hash += (idProjet != null ? idProjet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContributionPK)) {
            return false;
        }
        ContributionPK other = (ContributionPK) object;
        if ((this.idMembre == null && other.idMembre != null) || (this.idMembre != null && !this.idMembre.equals(other.idMembre))) {
            return false;
        }
        if ((this.idProjet == null && other.idProjet != null) || (this.idProjet != null && !this.idProjet.equals(other.idProjet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.babak.entite.ContributionPK[ idMembre=" + idMembre + ", idProjet=" + idProjet + " ]";
    }
    
}
