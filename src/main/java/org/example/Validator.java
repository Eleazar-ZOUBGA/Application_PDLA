package org.example;

public class Validator extends User {

    private String motifMissionNonValidee;

    public Validator(int id, String fName, String lName, String mailAdress, String password) {
        super(id, fName, lName, mailAdress, password, "Validator");
    }
    public Validator(String fName, String lName, String mailAdress, String password) {
        super(fName, lName, mailAdress, password, "Validator");
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
