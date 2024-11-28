package org.example;

import java.util.Date;
import java.util.ArrayList;

public class Mission {
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String location;
    private String motifMissionNonValidee;
    private String statutMission;

    public Mission(String title, String description, Date startDate, String location, String statutMission) { // Constructeur sans la date de fin
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.location = location;
        this.statutMission = "En Attente";
    }

    public Mission(String title, String description, Date startDate, Date endDate, String location, String statutMission) { // Constructeur avec la date de fin
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.statutMission = "En attente";
    }

    // Getters and setters for each field
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