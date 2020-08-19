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
import org.babak.dao.DemandeDAO;
import org.babak.dao.MembreDAO;
import org.babak.dao.ProjetDAO;
import org.babak.entite.Contribution;
import org.babak.entite.ContributionPK;
import org.babak.entite.Demande;
import org.babak.entite.DemandePK;
import org.babak.entite.Membre;
import org.babak.entite.Projet;

/**
 *
 * @author Babak
 */
@javax.ejb.Stateless
@Path("demande")
public class DemandeFacadeREST {

    @GET
    @Path("{nomProjet}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response demanderContribution(@PathParam("nomProjet") String nomProjet, Membre membre) {
        Demande demande = null;
        DemandePK demandePK = null;
        Response response;
        try {
            demandePK.setIdContributeur(membre.getId());
            demandePK.setIdProjet(ProjetDAO.getByNom(nomProjet).getId());
            demande.setDemandePK(demandePK);
            demande.setDateDemande(new Date());
            DemandeDAO.create(demande);
            response = Response.status(Response.Status.CREATED).build();
        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }

    @GET
    @Path("{email}")
    public Response accepterDemande(@PathParam("email") String email, Projet projet) {
        Response response = null;
        Contribution contribution = null;
        ContributionPK contributionPK = null;
        try {
            contributionPK.setIdMembre(MembreDAO.getByEmail(email).getId());
            contributionPK.setIdProjet(projet.getId());
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
    @Path("{email}")
    public Response refuserDemande(@PathParam("email") String email, Projet projet) {
        DemandePK demandePK = null;
        Demande demande = null;
        Response response;
        try {
            demandePK.setIdContributeur(MembreDAO.getByEmail(email).getId());
            demandePK.setIdProjet(projet.getId());
            demande.setDemandePK(demandePK);
            DemandeDAO.supprimer(demande);
            response = Response.ok().build();
        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
@GET
    @Path("{idContributeur}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllDemandes(@PathParam("idContributeur") String idContributeur) {
        Response response = null;
        List<String> projetsDemande;
        List<String> projets = null;
        String nomProjetDemande;
        try {
            projetsDemande = DemandeDAO.getAllProjetsDemande(idContributeur);
            for (String idProjet : projetsDemande) {
                nomProjetDemande = ProjetDAO.getProjet(idProjet).getNom();
                projets.add(nomProjetDemande);
            }
            response = Response.ok(projets, MediaType.APPLICATION_JSON).build();

        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            return response;
        }
    }

}
