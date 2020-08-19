/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.service;

import java.sql.SQLException;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.babak.dao.ContributionDAO;
import org.babak.dao.MembreDAO;
import org.babak.entite.Contribution;
import org.babak.entite.ContributionPK;
import org.babak.entite.Projet;

/**
 *
 * @author Babak
 */
@javax.ejb.Stateless
@Path("contribution")
public class ContributionFacadeREST {

    @DELETE
    @Path("{email}")
    public Response supprimerContributeur(@PathParam("email") String email, Projet projet) {
        ContributionPK contributionPK = null;
        Contribution contribution = null;
        Response response = null;
        try {
            contributionPK.setIdMembre(MembreDAO.getByEmail(email).getId());
            contributionPK.setIdProjet(projet.getId());
            contribution.setContributionPK(contributionPK);
            ContributionDAO.supprimer(contribution);
            response = Response.ok().build();
        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }

}
