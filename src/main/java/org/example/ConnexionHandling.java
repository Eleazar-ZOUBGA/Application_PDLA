package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnexionHandling {

    public boolean verifyCredentials(String mailAdress, String password) { // Cette méthodee permet de vérifier si les identifiants rentrés sont conformes à ce qu'il y a dans la BDD
        String query = "SELECT * FROM User WHERE mailAdress = ? AND password = ?";

        try (Connection conn = DataSource.getConnection(); // Connexion à la BDD
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, mailAdress);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery(); // Résultat d'exécution de la requête SQL

            return rs.next(); // Vérifie si les identifiants correspondent

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false si les requêtes sql ne sont pas bien exécutées
        }
    }
}
