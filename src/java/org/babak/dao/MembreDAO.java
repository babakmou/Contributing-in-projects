/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.babak.entite.Membre;

/**
 *
 * @author babak
 */
public class MembreDAO {

    public static int create(Membre membre) throws SQLException {
        int resultat = 0;

            String requete = "INSERT INTO membre (id, email, password)"
                    + " VALUES (?,?,?)";
            PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
            stm.setString(1, membre.getId());
            stm.setString(2, membre.getEmail());
            stm.setString(3, membre.getPassword());

            resultat = stm.executeUpdate();

        return resultat;
    }
    
    public static Membre getByEmail(String email) throws SQLException {
        Membre membre = null;
            String requete = "SELECT * FROM membre WHERE email=?";
            PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
            stm.setString(1, email);
            ResultSet resultats = stm.executeQuery();
            if (resultats.next()) {
                membre = new Membre();
                membre.setId(resultats.getString("id"));
                membre.setEmail(resultats.getString("email"));
                membre.setPassword(resultats.getString("password"));
            }

        return membre;
    }

}
