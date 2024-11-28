package org.example;

public class VulnerableUser extends User {
    private String role;

    public VulnerableUser(String fName, String lName, String mailAdress, String password) {
        super(fName, lName, mailAdress, password);
        this.role = "Vulnerable User";
    }

    @Override
    public String getRole() {
        return this.role;
    }

    public void proposeMission(Mission mission){
        if ("En attente".equals(mission.getStatutMission()) || "Validée".equals(mission.getStatutMission())) {
            System.out.println("J'ai besoin d'aide pour cette mission : " + mission);
        }
        else{
            System.out.println("Cette mission n'est plus disponible, merci pour votre disponibilité");
        }
    }
}
