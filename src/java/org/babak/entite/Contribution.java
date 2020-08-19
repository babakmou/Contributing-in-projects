/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.entite;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Babak
 */
@Entity
@Table(name = "contribution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contribution.findAll", query = "SELECT c FROM Contribution c")
    , @NamedQuery(name = "Contribution.findByIdMembre", query = "SELECT c FROM Contribution c WHERE c.contributionPK.idMembre = :idMembre")
    , @NamedQuery(name = "Contribution.findByIdProjet", query = "SELECT c FROM Contribution c WHERE c.contributionPK.idProjet = :idProjet")
    , @NamedQuery(name = "Contribution.findByDateContribution", query = "SELECT c FROM Contribution c WHERE c.dateContribution = :dateContribution")})
public class Contribution implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContributionPK contributionPK;
    @Column(name = "date_contribution")
    @Temporal(TemporalType.DATE)
    private Date dateContribution;
    @JoinColumn(name = "id_membre", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membre membre;
    @JoinColumn(name = "id_projet", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projet projet;

    public Contribution() {
    }

    public Contribution(ContributionPK contributionPK) {
        this.contributionPK = contributionPK;
    }

    public Contribution(String idMembre, String idProjet) {
        this.contributionPK = new ContributionPK(idMembre, idProjet);
    }

    public ContributionPK getContributionPK() {
        return contributionPK;
    }

    public void setContributionPK(ContributionPK contributionPK) {
        this.contributionPK = contributionPK;
    }

    public Date getDateContribution() {
        return dateContribution;
    }

    public void setDateContribution(Date dateContribution) {
        this.dateContribution = dateContribution;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contributionPK != null ? contributionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contribution)) {
            return false;
        }
        Contribution other = (Contribution) object;
        if ((this.contributionPK == null && other.contributionPK != null) || (this.contributionPK != null && !this.contributionPK.equals(other.contributionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.babak.entite.Contribution[ contributionPK=" + contributionPK + " ]";
    }
    
}
