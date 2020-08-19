/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.babak.dao.ContributionDAO;
import org.babak.dao.MembreDAO;
import org.babak.dao.ProjetDAO;
import org.babak.entite.Contribution;
import org.babak.entite.ContributionPK;
import org.babak.entite.Membre;
import org.babak.entite.Projet;

/**
 *
 * @author Babak
 */
@javax.ejb.Stateless
@Path("projet")
public class ProjetFacadeREST {

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Projet projet) {
        Response response = null;
        try {
            if (ProjetDAO.getByNom(projet.getNom()) != null) {
                response = Response.status(Response.Status.FORBIDDEN).build();
                return response;
            }
            projet.setId(UUID.randomUUID().toString());
            ProjetDAO.create(projet);

            Contribution contribution = null;
            ContributionPK contributionPK = null;
            contributionPK.setIdMembre(projet.getIdMembre().getId());
            contributionPK.setIdProjet(projet.getId());
            contribution.setContributionPK(contributionPK);
            contribution.setDateContribution(new Date());
            ContributionDAO.create(contribution);

            response = Response.status(Response.Status.CREATED).build();
        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        } finally {
            return response;
        }
    }

    @GET
    @Path("{idMembre}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllProjets(@PathParam("idMembre") String idMembre) {
        Membre membre;
        Projet projet;
        Response response = null;
        List<String> projetsContribues;
        List<Projet> projets = null;
        try {
            projetsContribues = ContributionDAO.getAllContributionsParMembre(idMembre);
            for (String idProjet : projetsContribues) {
                projet = ProjetDAO.getProjet(idProjet);
                projets.add(projet);
            }
            response = Response.ok(projets, MediaType.APPLICATION_JSON).build();

        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            return response;
        }
    }

    @GET
    @Path("{nomProjet}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getDetailsProjet(@PathParam("nomProjet") String nomProjet) {
        List<String> listeContributeurs = null;
        Membre membre = null;
        List<Membre> liste = null;
        Response response = null;
        try {
            listeContributeurs = ContributionDAO.getAllContributeursDeProjet(nomProjet);
            for (String email : listeContributeurs) {
                membre = MembreDAO.getByEmail(email);
                liste.add(membre);
            }
            response = Response.ok(liste, MediaType.APPLICATION_JSON).build();

        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            return response;
        }
    }

}
