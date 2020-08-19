/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.babak.entite.Contribution;
import org.babak.entite.ContributionPK;

/**
 *
 * @author Babak
 */
public class ContributionDAO {

    public static List<String> getAllContributionsParMembre(String email) throws SQLException {
        List<String> liste = new ArrayList<>();
        String idProjet;

        String requete = "SELECT id_projet FROM contribution WHERE email=?";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, email);
        ResultSet resultats = stm.executeQuery();

        while (resultats.next()) {
            idProjet = resultats.getString("id_projet");
            liste.add(idProjet);
        }
        return liste;
    }
    
    public static List<String> getAllContributeursDeProjet(String idProjet) throws SQLException {
        List<String> liste = new ArrayList<>();
        String idMembre;

        String requete = "SELECT id_Membre FROM contribution WHERE id_projet=?";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, idProjet);
        ResultSet resultats = stm.executeQuery();

        while (resultats.next()) {
            idProjet = resultats.getString("id_Membre");
            liste.add(idProjet);
        }
        return liste;
    }

    public static int create(Contribution contribution) throws SQLException {
        int resultat = 0;

        String requete = "INSERT INTO contribution (id_membre, id_projet, date_contribution)"
                + " VALUES (?,?,?)";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, contribution.getContributionPK().getIdMembre());
        stm.setString(2, contribution.getContributionPK().getIdProjet());
        stm.setDate(3, (Date) contribution.getDateContribution());

        resultat = stm.executeUpdate();

        return resultat;
    }

    public static int supprimer(Contribution contribution) throws SQLException {
        int resultat = 0;

        String requete = "delete from contribution "
                + "where id_membre=? and id_projet=?";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, contribution.getContributionPK().getIdMembre());
        stm.setString(2, contribution.getContributionPK().getIdProjet());

        resultat = stm.executeUpdate();

        return resultat;
    }
}
