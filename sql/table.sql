DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Benevole;
DROP TABLE IF EXISTS VulnerableUser;
DROP TABLE IF EXISTS Validator;
DROP TABLE IF EXISTS Mission;

CREATE TABLE User (
    id_User INT PRIMARY KEY,
    fName VARCHAR(50),
    lName VARCHAR(50),
    mailAdress VARCHAR(100),
    password VARCHAR(50),
    role VARCHAR(50)
);
CREATE TABLE Benevole (
    id_Benevole INT PRIMARY KEY REFERENCES User (id_User)
);
CREATE TABLE VulnerableUser (
    id_VulnerableUser INT PRIMARY KEY REFERENCES User (id_User)
);
CREATE TABLE Validator (
    id_Validator INT  PRIMARY KEY REFERENCES User (id_User)
);

CREATE TABLE Mission (
    id_Mission INT PRIMARY KEY,
    title VARCHAR(100),
    description VARCHAR(250),
    id_VulnerableUser int references VulnerableUser (id_VulnerableUser),
    id_Validator int references Validator (id_Validator),
    id_Benevole int references Benevole (id_Benevole),
    startDate date,
    endDate date,
    motifMissionNonValidee VARCHAR(250),
    statutMission VARCHAR(50)
);