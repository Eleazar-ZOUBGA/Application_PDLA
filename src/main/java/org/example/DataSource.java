package org.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataSource {


    // Paramètres de connexion à la base de données
    private static final String DB_URL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_032";
    private static final String DB_USER = "projet_gei_032";
    private static final String DB_PASSWORD = "NeN8eeTa";

    // Singleton pour la connexion à la base de données
    private static Connection connection;

    // Méthode pour récupérer une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) { // Se connecte à la BDD si la connexion est inexistante
            try {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Connexion réussie à la base de données !");
            } catch (SQLException  e) {
                System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    public static void startConnection() {
        try {
            if (connection == null || connection.isClosed()) { // Se connecte à la BDD si la connexion est inexistante
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {

                    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                } catch (SQLException e) {
                    System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
                    throw e;
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Erreur lors de la verification de l'état de la connexion");
            System.err.println(e.getMessage());
            System.exit(1);
        }        
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

    public static boolean verifyCredentials(String mailAdress, String password) { // Cette méthodee permet de vérifier si les identifiants rentrés sont conformes à ce qu'il y a dans la BDD
        String query = "SELECT * FROM User WHERE mailAdress = ? AND password = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, mailAdress);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery(); // Résultat d'exécution de la requête SQL

            return rs.next(); // Vérifie si les identifiants correspondent

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false si les requêtes sql ne sont pas bien exécutées
        }
    }

    public static boolean registerUser(String fName, String lName, String email, String password, String role) {
        String query = "INSERT INTO User (fname, lname, mailAdress, password, role) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

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

    public static User getUser(String mailAdress, String password) { // Cette méthodee permet de vérifier si les identifiants rentrés sont conformes à ce qu'il y a dans la BDD
        String query = "SELECT * FROM User WHERE mailAdress = ? AND password = ?";
        User user = null;
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, mailAdress);
            stmt.setString(2, password);


            ResultSet rs = stmt.executeQuery(); // Résultat d'exécution de la requête SQL
            rs.next();
            if (!rs.wasNull()) {
                int id = rs.getInt("id_User");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String role = rs.getString("role");
                if (role.equals("Benevole")){
                    user = new Benevole(id, fName, lName, mailAdress, password);
                }
                else if (role.equals("Validator")){
                    user = new Validator(id, fName, lName, mailAdress, password);
                }
                else if (role.equals("Vulnerable User")){
                    user = new VulnerableUser(id, fName, lName, mailAdress, password);
                }
                else {
                    System.out.println("probleme role");
                    System.out.println(role);
                }
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Retourne null si les requêtes sql ne sont pas bien exécutées
        }
    }

    public static boolean registerMission(Mission mission, User user) {
        String query = "INSERT INTO Mission (title, description, startDate, endDate, statutMission, Id_VulnerableUser) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
                
            // Paramétrage de la requête
            stmt.setString(1, mission.getTitle());
            stmt.setString(2, mission.getDescription());
            stmt.setDate(3, new java.sql.Date(mission.getStartDate().getTime()));
            if (mission.getEndDate()==null){
                stmt.setDate(4, null);
            } else {
                stmt.setDate(4, new java.sql.Date(mission.getEndDate().getTime()));
            }
            stmt.setString(5, mission.getStatutMission());
            stmt.setInt(6 , user.getId_User());

            int rowsAffected = stmt.executeUpdate(); // Exécution de la requête sql
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Mission> getMissionsByStatus(String status) { // Cette méthode récupère les missions non validées
        String query = "SELECT * FROM Mission WHERE statutMission = ? ";
        ArrayList<Mission> missions = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, status);

            ResultSet rs = stmt.executeQuery(); // Résultat d'exécution de la requête SQL
            while (rs.next()) {
                int id = rs.getInt("id_Mission");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                String motif = rs.getString("motifMissionNonValidee");
                Mission mission = new Mission(id, title, description, startDate, endDate, null,status);
                mission.setMotifMissionNonValidee(motif);
                missions.add(mission); 
            }
            return missions;

        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Retourne false si les requêtes sql ne sont pas bien exécutées
        }
    }

    public static ArrayList<Mission> getMissionsByVulnerableUser(User user) {
        String query = "SELECT * FROM Mission WHERE Id_VulnerableUser = ?";
        ArrayList<Mission> missions = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, user.getId_User());

            ResultSet rs = stmt.executeQuery(); // Résultat d'exécution de la requête SQL
            while (rs.next()) {
                int id = rs.getInt("id_Mission");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                String motif = rs.getString("motifMissionNonValidee");
                String status = rs.getString("statutMission");
                Mission mission = new Mission(id, title, description, startDate, endDate, null, status);
                mission.setMotifMissionNonValidee(motif);
                missions.add(mission);
            }
            return missions;

        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Retourne false si les requêtes sql ne sont pas bien exécutées
        }
    }

    public static ArrayList<Mission> getAllMissions() { // Méthode pour récupérer toutes les missions de la bdd
        String query = "SELECT * FROM Mission";
        ArrayList<Mission> missions = new ArrayList<>();

        try (PreparedStatement stmt = getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_Mission");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                String status = rs.getString("statutMission");
                String motif = rs.getString("motifMissionNonValidee");

                Mission mission = new Mission(id, title, description, startDate, endDate, status);
                mission.setMotifMissionNonValidee(motif);
                missions.add(mission);
            }
            return missions;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateStatusMission(Mission mission) {
        String query = "UPDATE Mission SET statutMission = ? WHERE id_Mission = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
                
            // Paramétrage de la requête
            stmt.setString(1, mission.getStatutMission());
            stmt.setInt(2, mission.getIdMission());

            int rowsAffected = stmt.executeUpdate(); // Exécution de la requête sql
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean setMotifMission(Mission mission) {
        String query = "UPDATE Mission SET motifMissionNonValidee = ? WHERE id_Mission = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
                
            // Paramétrage de la requête
            stmt.setString(1, mission.getMotifMissionNonValidee());
            stmt.setInt(2, mission.getIdMission());

            int rowsAffected = stmt.executeUpdate(); // Exécution de la requête sql
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
