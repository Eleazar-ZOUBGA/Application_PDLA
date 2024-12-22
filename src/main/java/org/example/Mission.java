package org.example;

import java.util.Date;

public class Mission {
    private int id_Mission;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String location;
    private String motifMissionNonValidee;
    private String statutMission;

    public Mission(String title, String description, Date startDate, String location) { // Constructeur sans la date de fin
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.location = location;
        this.statutMission = "En Attente";
    }

    public Mission(String title, String description, Date startDate, Date endDate, String location) { // Constructeur avec la date de fin
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.statutMission = "En Attente";
    }

    public Mission(int id_Mission, String title, String description, Date startDate, String location) { // Constructeur sans la date de fin
        this.id_Mission = id_Mission;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.location = location;
        this.statutMission = "En Attente";
    }

    public Mission(int id_Mission, String title, String description, Date startDate, Date endDate, String location) { // Constructeur avec la date de fin et le statut
        this.id_Mission = id_Mission;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.statutMission = "En Attente";
    }

    public Mission(int id_Mission, String title, String description, Date startDate, String location, String statut) { // Constructeur sans la date de fin et le statut
        this.id_Mission = id_Mission;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.location = location;
        this.statutMission = statut;
    }

    public Mission(int id_Mission, String title, String description, Date startDate, Date endDate, String location, String statut) { // Constructeur avec la date de fin
        this.id_Mission = id_Mission;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.statutMission = statut;
    }

    // Getters and setters for each field
    public int getIdMission() {
        return id_Mission;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatutMission(){
        return this.statutMission;
    }

    public void setStatutMission(String statutMission){
        this.statutMission = statutMission;
    }

    public void setMotifMissionNonValidee(String motifMissionNonValidee){
        this.motifMissionNonValidee = motifMissionNonValidee;
    }

    public String getMotifMissionNonValidee(){
        return this.motifMissionNonValidee;
    }
}