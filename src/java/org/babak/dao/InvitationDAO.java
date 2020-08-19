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
import org.babak.entite.Invitation;

/**
 *
 * @author Babak
 */
public class InvitationDAO {

    public static int create(Invitation invitation) throws SQLException {
        int resultat = 0;

        String requete = "INSERT INTO invitation (id_contributeur, id_projet, date_invitation)"
                + " VALUES (?,?,?)";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, invitation.getInvitationPK().getIdContributeur());
        stm.setString(2, invitation.getInvitationPK().getIdProjet());
        stm.setDate(3, (Date) invitation.getDateInvitation());

        resultat = stm.executeUpdate();

        return resultat;
    }

    public static int supprimer(Invitation invitation) throws SQLException {
        int resultat = 0;

        String requete = "delete from invitation "
                + "where id_contributeur=? and id_projet=?";
        PreparedStatement stm = Connexion.getConnexion().prepareStatement(requete);
        stm.setString(1, invitation.getInvitationPK().getIdContributeur());
        stm.setString(2, invitation.getInvitationPK().getIdProjet());

        resultat = stm.executeUpdate();

        return resultat;
    }

    public static List<String> getAllProjetsInvites(String idContributeur) throws SQLException {
        List<String> projetsInvites = new ArrayList<>();

        String requete = "SELECT * FROM invtation where id_contributeur=?";
        Statement stm = Connexion.getConnexion().createStatement();
        ResultSet resultats = stm.executeQuery(requete);
        while (resultats.next()) {

            projetsInvites.add(resultats.getString("id_projet"));
        }

        return projetsInvites;

    }

}
