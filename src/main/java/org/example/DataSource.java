package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {

    // Paramètres de connexion à la base de données
    private static final String DB_URL = "Mysql@srv-bdens.insa-toulouse.fr:3306/projet_gei_032";
    private static final String DB_USER = "projet_gei_032";
    private static final String DB_PASSWORD = "NeN8eeTa";

    // Singleton pour la connexion à la base de données
    private static Connection connection;

    // Méthode pour récupérer une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) { // Se connecte à la BDD si la connexion est inexistante
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Connexion réussie à la base de données !");
            } catch (SQLException e) {
                System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    // Méthode utilitaire pour fermer les ressources
    public static void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture du ResultSet : " + e.getMessage());
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture du PreparedStatement : " + e.getMessage());
        }
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
        }
    }

    // Exemple d'exécution de requête SELECT
    public static void executeQueryExample() {
        String query = "SELECT * FROM your_table_name";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Donnée : " + rs.getString("column_name"));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
    }
}
