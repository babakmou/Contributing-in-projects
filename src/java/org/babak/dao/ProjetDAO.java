/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.babak.entite.Projet;

/**
 *
 * @author babak
 */
public class ProjetDAO {

    public static List<Projet> getAllProjectsParMembre(String idMembre) throws SQLException {
        Projet projet = null;
        List<Projet> projets = new ArrayList<>();
        
        String requete = "SELECT * FROM projet WHERE id_membre=?";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, idMembre);
        ResultSet resultats = stm.executeQuery();
        while (resultats.next()) {
            projet = new Projet();
            projet.setId(resultats.getString("id"));
            projet.setNom(resultats.getString("nom"));
            projets.add(projet);
        }

        return projets;
    }

    public static List<Projet> getAllProjets() throws SQLException {
        List<Projet> projets = new ArrayList<>();
        Projet projet;

        String requete = "SELECT * FROM projet";
        Statement stm = Connexion.getConnexion().createStatement();
        ResultSet resultats = stm.executeQuery(requete);
        while (resultats.next()) {
            projet = new Projet();
            projet.setId(resultats.getString("id"));
            projet.setNom(resultats.getString("nom"));

            projets.add(projet);
        }

        return projets;
    }

    public static int create(Projet projet) throws SQLException {
        int resultat = 0;

        String requete = "INSERT INTO projet (id, nom, id_membre)"
                + " VALUES (?,?,?)";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, projet.getId());
        stm.setString(2, projet.getNom());
        stm.setString(3, projet.getIdMembre().getId());

        resultat = stm.executeUpdate();

        return resultat;
    }

    public static Projet getByNom(String nom) throws SQLException {
        Projet projet = null;
        String requete = "SELECT * FROM projet WHERE nom=?";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, nom);
        ResultSet resultats = stm.executeQuery();
        if (resultats.next()) {
            projet = new Projet();
            projet.setId(resultats.getString("id"));
            projet.setNom(resultats.getString("nom"));
        }

        return projet;
    }

    public static Projet getProjet(String idProjet) throws SQLException {
        Projet projet = null;
        String requete = "SELECT * FROM projet WHERE id=?";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, idProjet);
        ResultSet resultats = stm.executeQuery();
        if (resultats.next()) {
            projet = new Projet();
            projet.setId(resultats.getString("id"));
            projet.setNom(resultats.getString("nom"));
        }

        return projet;
    }
}
