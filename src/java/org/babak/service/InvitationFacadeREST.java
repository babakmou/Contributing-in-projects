/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.babak.dao.ContributionDAO;
import org.babak.dao.InvitationDAO;
import org.babak.dao.MembreDAO;
import org.babak.dao.ProjetDAO;
import org.babak.entite.Contribution;
import org.babak.entite.ContributionPK;
import org.babak.entite.Invitation;
import org.babak.entite.InvitationPK;
import org.babak.entite.Membre;
import org.babak.entite.Projet;

/**
 *
 * @author Babak
 */
@javax.ejb.Stateless
@Path("invitation")
public class InvitationFacadeREST {

    @GET
    @Path("{email}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response inviterContribution(@PathParam("email") String email, Projet projet) {
        Response response;
        Invitation invitation = null;
        InvitationPK invitationPK = null;
        try {
            invitationPK.setIdContributeur(MembreDAO.getByEmail(email).getId());
            invitationPK.setIdProjet(projet.getId());
            invitation.setInvitationPK(invitationPK);
            invitation.setDateInvitation(new Date());
            InvitationDAO.create(invitation);
            response = Response.status(Response.Status.CREATED).build();
        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }

    @GET
    @Path("{nomProjet}")
    public Response accepterInvitation(@PathParam("nomProjet") String nomProjet, Membre membre) {
        Response response = null;
        Contribution contribution = null;
        ContributionPK contributionPK = null;

        try {
            contributionPK.setIdMembre(membre.getId());
            contributionPK.setIdProjet(ProjetDAO.getByNom(nomProjet).getId());
            contribution.setContributionPK(contributionPK);
            contribution.setDateContribution(new Date());
            ContributionDAO.create(contribution);
            response = Response.ok().build();
        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            return response;
        }
    }

    @DELETE
    @Path("{nomProjet}")
    public Response refuserInvitation(@PathParam("nomProjet") String nomProjet, Membre membre) {
        Response response = null;
        InvitationPK invitationPK = null;
        Invitation invitation = null;
        try {
            invitationPK.setIdContributeur(membre.getId());
            invitationPK.setIdProjet(ProjetDAO.getByNom(nomProjet).getId());
            invitation.setInvitationPK(invitationPK);
            InvitationDAO.supprimer(invitation);
            response = Response.ok().build();
        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
    @GET
    @Path("{idContributeur}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllInvitations(@PathParam("idContributeur") String idContributeur) {
        Response response = null;
        List<String> projetsInvites;
        List<String> projets = null;
        String nomProjetInvite;
        try {
            projetsInvites = InvitationDAO.getAllProjetsInvites(idContributeur);
            for (String idProjet : projetsInvites) {
                nomProjetInvite = ProjetDAO.getProjet(idProjet).getNom();
                projets.add(nomProjetInvite);
            }
            response = Response.ok(projets, MediaType.APPLICATION_JSON).build();

        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            return response;
        }
    }

}
