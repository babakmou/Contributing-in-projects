/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.babak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AToudeft
 */
public class Connexion {
    private static Connection cnx;
    private static String url = "jdbc:mysql://192.168.2.56:3306/A15?zeroDateTimeBehavior=convertToNull";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String password = "root";
    
    public static Connection getConnexion() {
        try {
            if (cnx==null || cnx.isClosed()) {
                Class.forName(driver);
                cnx = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException ex) {
            System.out.println("ERREUR : "+ex);
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("ERREUR : "+ex);
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnx;
    }
    
    public static void close() {
        try {
            if (cnx!=null) {
                cnx.close();
                cnx = null;
            }
        } catch (SQLException ex) {
            System.out.println("ERREUR : "+ex);
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
