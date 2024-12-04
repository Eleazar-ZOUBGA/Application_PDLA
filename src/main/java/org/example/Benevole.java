package org.example;

public class Benevole extends User {

    public Benevole(int id, String fName, String lName, String mailAdress, String password) {
        super(id, fName, lName, mailAdress, password, "Benevole");
    }

    public Benevole(String fName, String lName, String mailAdress, String password) {
        super(fName, lName, mailAdress, password, "Benevole");
    }

    public void volontaireMission(Mission mission) {
        if ("Validée".equals(mission.getStatutMission())) {
            System.out.println("Je suis volontaire pour cette mission : " + mission.getTitle());
            mission.setStatutMission("En cours");
        }
        else if ("En Attente".equals(mission.getStatutMission())){
            System.out.println("Vous ne pouvez pas avoir cette mission car elle est en attente de validation");
        }
        else if ("Terminé".equals(mission.getStatutMission())){
            System.out.println("Cette mission n'est plus disponible, merci pour votre disponibilité");
        }
        else if ("En cours".equals(mission.getStatutMission())){
            System.out.println("Vous ne pouvez pas avoir cette mission car elle est déjà prise");
        }
    }

    // Implémenter l'état de la mission terminée.

}
