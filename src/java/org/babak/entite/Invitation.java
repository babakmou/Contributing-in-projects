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
@Table(name = "invitation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitation.findAll", query = "SELECT i FROM Invitation i")
    , @NamedQuery(name = "Invitation.findByIdContributeur", query = "SELECT i FROM Invitation i WHERE i.invitationPK.idContributeur = :idContributeur")
    , @NamedQuery(name = "Invitation.findByIdProjet", query = "SELECT i FROM Invitation i WHERE i.invitationPK.idProjet = :idProjet")
    , @NamedQuery(name = "Invitation.findByDateInvitation", query = "SELECT i FROM Invitation i WHERE i.dateInvitation = :dateInvitation")
    , @NamedQuery(name = "Invitation.findByStatut", query = "SELECT i FROM Invitation i WHERE i.statut = :statut")})
public class Invitation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InvitationPK invitationPK;
    @Column(name = "date_invitation")
    @Temporal(TemporalType.DATE)
    private Date dateInvitation;
    @Basic(optional = false)
    @Column(name = "statut")
    private String statut;
    @JoinColumn(name = "id_contributeur", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membre membre;
    @JoinColumn(name = "id_projet", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Projet projet;

    public Invitation() {
    }

    public Invitation(InvitationPK invitationPK) {
        this.invitationPK = invitationPK;
    }

    public Invitation(InvitationPK invitationPK, String statut) {
        this.invitationPK = invitationPK;
        this.statut = statut;
    }

    public Invitation(String idContributeur, String idProjet) {
        this.invitationPK = new InvitationPK(idContributeur, idProjet);
    }

    public InvitationPK getInvitationPK() {
        return invitationPK;
    }

    public void setInvitationPK(InvitationPK invitationPK) {
        this.invitationPK = invitationPK;
    }

    public Date getDateInvitation() {
        return dateInvitation;
    }

    public void setDateInvitation(Date dateInvitation) {
        this.dateInvitation = dateInvitation;
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
        hash += (invitationPK != null ? invitationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invitation)) {
            return false;
        }
        Invitation other = (Invitation) object;
        if ((this.invitationPK == null && other.invitationPK != null) || (this.invitationPK != null && !this.invitationPK.equals(other.invitationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.babak.entite.Invitation[ invitationPK=" + invitationPK + " ]";
    }
    
}
