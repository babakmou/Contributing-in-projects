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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.babak.entite.Demande;

/**
 *
 * @author Babak
 */
public class DemandeDAO {
    public static int create(Demande demande) throws SQLException {
        int resultat = 0;

        String requete = "INSERT INTO demande (id_contributeur, id_projet, date_invitation)"
                + " VALUES (?,?,?)";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, demande.getDemandePK().getIdContributeur());
        stm.setString(2, demande.getDemandePK().getIdProjet());
        stm.setDate(3, (Date) demande.getDateDemande());

        resultat = stm.executeUpdate();

        return resultat;
    }
    
    public static int supprimer(Demande demande) throws SQLException {
        int resultat = 0;

        String requete = "delete from demande "
                + "where id_contributeur=? and id_projet=?";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, demande.getDemandePK().getIdContributeur());
        stm.setString(2, demande.getDemandePK().getIdProjet());

        resultat = stm.executeUpdate();

        return resultat;
    }

    public static List<String> getAllProjetsDemande(String idContributeur) throws SQLException {
        List<String> projetsDemandes = new ArrayList<>();

        String requete = "SELECT * FROM demande where id_contributeur=?";
        Statement stm = Connexion.getConnexion().createStatement();
        ResultSet resultats = stm.executeQuery(requete);
        while (resultats.next()) {

            projetsDemandes.add(resultats.getString("id_projet"));
        }

        return projetsDemandes;
    }
    
}
