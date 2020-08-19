/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.StringTokenizer;
import org.babak.dao.MembreDAO;
import org.babak.entite.Membre;

/**
 *
 * @author Babak
 */
public class AuthenticationService {

    public boolean authenticate(String authCredentials) {

        if (null == authCredentials) {
            return false;
        }
        // header value format will be "Basic encodedstring" for Basic
        // authentication. Example "Basic YWRtaW46YWRtaW4="
        final String encodedUserPassword = authCredentials.replaceFirst("Basic"
                + " ", "");
        String usernameAndPassword = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(
                    encodedUserPassword);
            usernameAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final StringTokenizer tokenizer = new StringTokenizer(
                usernameAndPassword, ":");
        final String email = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        Membre membre;
        boolean authenticationStatus = false;

        try {
            membre = MembreDAO.getByEmail(email);
            authenticationStatus = membre.getPassword().equals(password);

        } catch (SQLException ex) {
        } finally {
            return authenticationStatus;
        }
    }
}
