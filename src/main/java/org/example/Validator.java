package org.example;

import java.util.ArrayList;

public class Validator extends User {

    private String motifMissionNonValidee;
    private String role;

    public Validator(String fName, String lName, String mailAdress, String password) {
        super(fName, lName, mailAdress, password);
        this.role = "Validator";
    }

    @Override
    public String getRole() {
        return this.role;
    }

    public void validateMission(Mission mission, boolean etatValidee) {
        if("En attente".equals(mission.getStatutMission()) && etatValidee == false){
            mission.setStatutMission("Validée");
            etatValidee = true;
        } else if (etatValidee == false && mission.getStatutMission().equals("")) {
            System.out.println("Veuillez saisir un motif de non validation de la mission");
            mission.setMotifMissionNonValidee(motifMissionNonValidee);
        }
    }

}
