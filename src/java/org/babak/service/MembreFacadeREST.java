/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.babak.dao.ContributionDAO;
import org.babak.entite.Membre;
import org.babak.dao.MembreDAO;
import org.babak.dao.ProjetDAO;
import org.babak.entite.Projet;
import javax.ws.rs.core.Response;

/**
 *
 * @author Babak
 */
@javax.ejb.Stateless
@Path("membre")
public class MembreFacadeREST {

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Membre membre) {
        Response response = null;
        try {
            if (MembreDAO.getByEmail(membre.getEmail()) != null) {
                response = Response.status(Response.Status.FORBIDDEN).build();
                return response;
            }
            membre.setId(UUID.randomUUID().toString());

            MembreDAO.create(membre);
            response = Response.status(Response.Status.CREATED).build();
        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            return response;
        }
    }

//    @GET
//    @Path("{email}/{password}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Response seConnecter(@PathParam("email") String email, @PathParam("password") String password) {
//        Membre membre;
//        Response response = null;
//        try {
//            membre = MembreDAO.getByEmail(email);
//            if (membre != null) {
//                if (membre.getPassword().equals(password)) {
//
//                    response = Response.ok().build();
//                } else {
//                    response = Response.status(Response.Status.FORBIDDEN).build();
//                }
//            } else {
//                response = Response.status(Response.Status.FORBIDDEN).build();
//            }
//        } catch (SQLException ex) {
//            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        } finally {
//            return response;
//        }
}
