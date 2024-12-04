package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DataSource.startConnection();
        User user = null;

        System.out.println("Bienvenue à l'aplication");

        System.out.println("Si vous voulez vous connecter écrire 0");
        System.out.println("Si vous voulez vous inscrire écrire 1");
        String todo = scanner.nextLine();

        //se connecté avec un utilisateur existant
        if (todo.equals("0")) {
            boolean verify = false;
            while (!verify) {
                System.out.println("Mail :");
                String mail = scanner.nextLine();
                System.out.println("Mot de passe :");
                String password = scanner.nextLine();
                verify = DataSource.verifyCredentials(mail, password);
                if (!verify){
                    System.out.println("Mail ou mot de passe incorrect");
                }
                else {
                    user = DataSource.getUser(mail, password);
                }
            }
            System.out.println("Connexion établie.");
        }

        //inscription
        if (todo.equals("1")){
            boolean succes = false;
            System.out.println("Pour vous inscrire écrire 0 pour Benevole ; 1 pour Validateur ; 2 pour Demandeur d'aide");
            String role = scanner.nextLine();
            if (role.equals("0")){
                role = "Benevole";
            }
            else if (role.equals("1")){
                role = "Validator";
            }
            else if (role.equals("2")){
                role = "Vulnerable User";
            }
            else {
                System.err.println("Erreur choix inexistant");
                System.exit(1);
            }
            System.out.println("Prénom :");
            String fName = scanner.nextLine();
            System.out.println("Nom :");
            String lName = scanner.nextLine();
            System.out.println("Email :");
            String mail = scanner.nextLine();
            System.out.println("Mot de passe :");
            String password = scanner.nextLine();
            succes = DataSource.registerUser(fName,lName,mail,password,role);
            if (!succes){
                System.out.println("Erreur lors inscription");
                System.exit(2);
            }
            user = DataSource.getUser(mail, password);  
        }

        if (user != null){  
            if (user.getRole().equals("Vulnerable User")){
                System.out.println("Que voulez vous faire? (s : soumettre une mission ; r : regarder les missions)");
                todo = scanner.nextLine(); // s : soumettre une mission ; r : regarder les mission proposé et leurs etats
                if (todo.equals("s")){
                    System.out.println("Donne un titre :");
                    String title = scanner.nextLine();
                    System.out.println("Donne une description :");
                    String description = scanner.nextLine();
                    System.out.println("Commence :");
                    Date start = new Date();
                    System.out.println("Fini :");
                    System.out.println("Où :");
                    String location = "Toulouse";

                    Mission mission = new Mission(title,description, start, location);
                    System.out.println("Veuillez confirmer (y : oui)");
                    boolean conf = scanner.nextLine().equals("y");
                    if (conf){
                        DataSource.registerMission(mission, user);
                        System.out.println("La mission a était enregistré");
                    }
                }
                if (todo.equals("r")){

                }
            }
            if (user.getRole().equals("Validator")){
                ArrayList<Mission> missions = DataSource.getMissionsByStatus("En Attente");
                System.out.println("Voici les mission en attente.");
                for (Mission mission : missions){
                    System.out.println("####################################################");
                    System.out.println(missions.indexOf(mission) + "|" + mission.getTitle());
                    System.out.println(mission.getDescription());
                }
                System.out.println("####################################################");
                System.out.println("Choisi une mission par son numero :");
                int id = scanner.nextInt();
                Mission mission = missions.get(id);
                System.out.println(mission.getTitle());
                System.out.println(mission.getDescription());
                System.out.println("Vous validez? (v : pour valider)");
                boolean validate = scanner.nextLine().equals("v");
                String motif;
                if (!validate){
                    System.out.println("Motif :");
                    motif = scanner.nextLine();
                    mission.setMotifMissionNonValidee(motif);
                }
                //user.validateMission(missions.get(id),validate);
                mission.setStatutMission("Validée");
                DataSource.updateStatusMission(mission);
                if (!validate){
                    DataSource.setMotifMission(mission);
                }

            }
        }
        scanner.close();
    }
}