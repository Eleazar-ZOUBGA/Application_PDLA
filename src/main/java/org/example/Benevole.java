package org.example;

public class Benevole extends User {
    private String role;

    public Benevole(String fName, String lName, String mailAdress, String password) {
        super(fName, lName, mailAdress, password);
        this.role = "Benevole";
    }

    @Override
    public String getRole() {
        return this.role;
    }

    public String getUserFullName(){
        return this.getFName() + " " + this.getLName();
    }

    public void volontaireMission(Mission mission) {
        if ("En attente".equals(mission.getStatutMission()) || "Validée".equals(mission.getStatutMission())) {
            System.out.println("Je suis volontaire pour cette mission : " + mission.getTitle());
            mission.setStatutMission("En cours");
        }
        else{
            System.out.println("Vous ne pouvez pas avoir cette mission car elle est déjà prise");
        }
    }

    // Implémenter l'état de la mission terminée.

}
