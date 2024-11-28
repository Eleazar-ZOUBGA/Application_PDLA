package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InscriptionHandling {

    public boolean registerUser(String fName, String lName, String email, String password, String role) {
        String query = "INSERT INTO User (fname, lname, mailAdress, password, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Paramétrage de la requête
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, role);

            int rowsAffected = stmt.executeUpdate(); // Exécution de la requête sql
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
