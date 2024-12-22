package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DataSource.startConnection();
        User user = null;

        System.out.println("Bienvenue dans l'application de bénévolat !");

        System.out.println("Si vous voulez vous connecter écrivez 'connexion' ");
        System.out.println("Si vous voulez vous inscrire écrivez 'inscription' ");
        String todo = scanner.nextLine();

        //se connecter avec un utilisateur existant
        if (todo.equals("connexion")) {
            boolean verify = false;
            while (!verify) {
                System.out.println("Saisissez votre adresse email :");
                String mail = scanner.nextLine();
                System.out.println("Saisissez votre mot de passe :");
                String password = scanner.nextLine();
                verify = DataSource.verifyCredentials(mail, password);
                if (!verify){
                    System.out.println("Mail ou mot de passe incorrect");
                }
                else {
                    user = DataSource.getUser(mail, password);
                    System.out.println("Vous êtes bien connecté(e) !");
                    System.out.println("Vous êtes " + user.getRole() );
                }
            }
        }
        else if (todo.equals("inscription")){ //inscription
            boolean succes = false;
            System.out.println("Pour vous inscrire, saisissez : 0 pour Benevole ; 1 pour Validateur ; 2 pour Demandeur d'aide");
            String role = scanner.nextLine();
            switch (role) {
                case "0":
                    role = "Benevole";
                    break;
                case "1":
                    role = "Validator";
                    break;
                case "2":
                    role = "Vulnerable User";
                    break;
                default:
                    System.err.println("Erreur, choix inexistant");
                    System.exit(1);
            }

            System.out.println("Saisissez votre prénom :");
            String fName = scanner.nextLine();
            System.out.println("Saisissez votre nom :");
            String lName = scanner.nextLine();
            System.out.println("Saisissez votre email :");
            String mail = scanner.nextLine();
            System.out.println("Saisissez votre mot de passe :");
            String password = scanner.nextLine();
            succes = DataSource.registerUser(fName,lName,mail,password,role);
            if (!succes){
                System.out.println("Erreur lors inscription");
                System.exit(2);
            }
            user = DataSource.getUser(mail, password);  
        }

        // Une fois que l'utilisateur s'est connecté, l'application se comporte en fonction de son rôle
        if (user != null){
            //Connexion en tant que personne vulnérable :
            if (user.getRole().equals("Vulnerable User")){
                // User utilisateur = DataSource.getUser(this.get); Essayer de récupérer l'utilisateur qui se connecte
                System.out.println("Que voulez vous faire?");
                System.out.println("Pour saisir une mission, saisissez 's' ");
                System.out.println("Pour regarder les missions déjà présentes, saisissez 'r' ");
                todo = scanner.nextLine(); // s : soumettre une mission ; r : regarder les missions proposées et leurs états
                if (todo.equals("s")){
                    System.out.println("Donnez un titre à votre mission :");
                    String title = scanner.nextLine();
                    System.out.println("Donnez une description à votre mission :");
                    String description = scanner.nextLine();
                    System.out.println("Date de début :"); // Transformez le texte des 4 dernières lignes pour qu'il soit convivial
                    Date start = new Date();
                    System.out.println("Saisissez le lieu de la réalisation :");
                    String location = scanner.nextLine();

                    Mission mission = new Mission(title,description, start, location);
                    System.out.println("Écrivez 'oui' pour confirmer");
                    String confirmation;
                    confirmation = scanner.nextLine();
                    if (confirmation.equals("oui")){
                        DataSource.registerMission(mission, user); // Revoir cette partie, car utilisateur a une valeur nulle
                        System.out.println("La mission a été bien enregistrée");
                    }
                    else{
                        System.out.println("Erreur lors de la confirmation.");
                        System.exit(2);
                    }
                }
                else if (todo.equals("r")){ // Récupérer la liste des missions déjà enregistrées dans la bdd et l'afficher :
                    System.out.println("Voici la liste de toutes les missions : ");
                    ArrayList<Mission> missions = DataSource.getMissionsByVulnerableUser(user);
                    if(missions != null){
                        for(Mission mission : missions){
                            System.out.println("Mission's Id : " + mission.getIdMission() + " | Mission's title : " + mission.getTitle() + " | Mission's status :" + mission.getStatutMission());
                        }
                    }
                    else{
                        System.out.println("Erreur lors de la récupération des missions.");
                    }
                }
                else{
                    System.out.println("Votre choix est inconnu. Veuillez réessayer !");
                    System.exit(2);
                }
            }
            //Connexion en tant que personne validatrice :
            else if (user.getRole().equals("Validator")){
                ArrayList<Mission> missions = DataSource.getMissionsByStatus("En Attente");
                System.out.println("Voici la liste des missions en attente : ");
                if(missions != null){
                    for (Mission mission : missions){
                        System.out.println("Mission's Id : " + mission.getIdMission() + " | Mission's title : " + mission.getTitle() + " | Mission's status :" + mission.getStatutMission());
                    }
                }
                else{
                    System.out.println("La liste des missions en attente de validation est vide.");
                }

                System.out.println("Si vous souhaitez valider une mission, ");
                System.out.println("choisissez-en une par son identifiant :");
                int id = 0;
                try {
                    id = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e){
                    System.out.println("Erreur de saisie");
                    System.exit(3);
                }
                if(missions != null){
                    Mission mission = null;
                    for (Mission mission1 : missions){
                        if (mission1.getIdMission()==id){
                            mission = mission1;
                        }
                    }
                    if(mission != null){
                        System.out.println("######");
                        System.out.println(id);
                        System.out.println("Voici la mission que vous avez sélectionné :");
                        System.out.println("Mission's Id : " + mission.getIdMission() + " | Mission's title : " + mission.getTitle());
                        System.out.println(mission.getDescription());
                        System.out.println("Souhaitez vous validez cette mission ?");
                        System.out.println("Si oui, tapez 'oui', sinon, tapez 'non' ");
                        String validation = scanner.nextLine();
                        if (validation.equals("non")){
                            System.out.println("Saisissez le motif pour la non validation de la mission :");
                            String motif;
                            motif = scanner.nextLine();
                            mission.setMotifMissionNonValidee(motif);
                            mission.setStatutMission("Non Validée");
                            DataSource.updateStatusMission(mission);
                            DataSource.setMotifMission(mission);
                        } else if (validation.equals("oui")){
                            mission.setStatutMission("Validée");
                            DataSource.updateStatusMission(mission);
                        } else{
                            System.out.println("Erreur de saisie");
                            System.exit(2);
                        }
                    }else {
                        System.out.println("Erreur, la mission sélectionnée n'existe pas");
                    }
                }
            }
            //Connexion en tant que personne bénévole :
            else if (user.getRole().equals("Benevole")){
                ArrayList<Mission> missions = DataSource.getMissionsByStatus("Validée");
                System.out.println("Voici la liste des missions disponibles : ");
                if(missions != null){
                    for (Mission mission : missions){
                        System.out.println("Mission's Id : " + mission.getIdMission() + " | Mission's title : " + mission.getTitle() + " | Mission's status : " + mission.getStatutMission());
                    }
                }
                else{
                    System.out.println("La liste des missions disponibles est vide.");
                }

                System.out.println("Si vous souhaitez proposer votre aide pour une mission, ");
                System.out.println("choisissez-en une par son identifiant :");
                int id = 0;
                try {
                    id = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e){
                    System.out.println("Erreur de saisie");
                    System.exit(4);
                }
                if(missions != null){
                    Mission mission = null;
                    for (Mission mission1 : missions){
                        if (mission1.getIdMission()==id){
                            mission = mission1;
                        }
                    }
                    if(mission != null){
                        System.out.println("Voici la mission que vous avez sélectionnée :");
                        System.out.println("Mission's Id : " + mission.getIdMission() + " | Mission's title : " + mission.getTitle() + " | Mission's status : " + mission.getStatutMission());
                        mission.setStatutMission("Réalisée");
                        DataSource.updateStatusMission(mission);
                        //System.out.println(mission.getDescription());
                        System.out.println("Merci pour votre soutien envers les personnes vulnérables");
                    }
                }
            }
            else{ //
                System.out.println("Votre rôle est inconnue");
                System.exit(2);
            }
        }
        else{
            System.out.println("Aucun utilisateur enregistré. Connectez vous ou inscrivez vous.");
        }
        scanner.close();
    }
}