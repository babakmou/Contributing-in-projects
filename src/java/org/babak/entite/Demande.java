/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.entite;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "demande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demande.findAll", query = "SELECT d FROM Demande d")
    , @NamedQuery(name = "Demande.findByIdContributeur", query = "SELECT d FROM Demande d WHERE d.demandePK.idContributeur = :idContributeur")
    , @NamedQuery(name = "Demande.findByIdProjet", query = "SELECT d FROM Demande d WHERE d.demandePK.idProjet = :idProjet")
    , @NamedQuery(name = "Demande.findByDateDemande", query = "SELECT d FROM Demande d WHERE d.dateDemande = :dateDemande")
    , @NamedQuery(name = "Demande.findByStatut", query = "SELECT d FROM Demande d WHERE d.statut = :statut")})
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DemandePK demandePK;
    @Column(name = "date_demande")
    @Temporal(TemporalType.DATE)
    private Date dateDemande;
    @Basic(optional = false)
    @Column(name = "statut")
    private String statut;
    @JoinColumn(name = "id_contributeur", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membre membre;
    @JoinColumn(name = "id_projet", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projet projet;

    public Demande() {
    }

    public Demande(DemandePK demandePK) {
        this.demandePK = demandePK;
    }

    public Demande(DemandePK demandePK, String statut) {
        this.demandePK = demandePK;
        this.statut = statut;
    }

    public Demande(String idContributeur, String idProjet) {
        this.demandePK = new DemandePK(idContributeur, idProjet);
    }

    public DemandePK getDemandePK() {
        return demandePK;
    }

    public void setDemandePK(DemandePK demandePK) {
        this.demandePK = demandePK;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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
        hash += (demandePK != null ? demandePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.demandePK == null && other.demandePK != null) || (this.demandePK != null && !this.demandePK.equals(other.demandePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.babak.entite.Demande[ demandePK=" + demandePK + " ]";
    }
    
}
