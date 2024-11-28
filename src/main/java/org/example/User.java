package org.example;

public abstract class User {
    private int id_User;
    private String fName;
    private String lName;
    private String mailAdress;
    private String password;

    public User(int id_User, String fName, String lName, String mailAdress, String password) {
        this.id_User = id_User;
        this.fName = fName;
        this.lName = lName;
        this.mailAdress = mailAdress;
        this.password = password;
    }

    public User(String fName, String lName, String mailAdress, String password) {
        this(0, fName, lName, mailAdress, password); // Par défaut, id = 0 avant insertion en base
    }

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserFullName(){
        return this.getFName() + " " + this.getLName();
    }

    // Méthode abstraite pour un comportement spécifique à implémenter dans les sous-classes
    public abstract String getRole();
}
