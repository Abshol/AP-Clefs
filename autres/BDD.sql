#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Ressource
#------------------------------------------------------------

CREATE TABLE Ressource(
        id  Int  Auto_increment  NOT NULL ,
        nom Varchar (150) NOT NULL
	,CONSTRAINT Ressource_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Comptes
#------------------------------------------------------------

CREATE TABLE Comptes(
        nomUtilisateur Varchar (150) NOT NULL ,
        motDePasse     Varchar (150) NOT NULL
	,CONSTRAINT Comptes_PK PRIMARY KEY (nomUtilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Couleur
#------------------------------------------------------------

CREATE TABLE Couleur(
        nom Varchar (50) NOT NULL
	,CONSTRAINT Couleur_PK PRIMARY KEY (nom)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Clef
#------------------------------------------------------------

CREATE TABLE Clef (
        id  Int  Auto_increment  NOT NULL ,
        nom Varchar (50) NOT NULL
	,CONSTRAINT Clef_PK PRIMARY KEY (id)

	,CONSTRAINT Clef_Couleur_FK FOREIGN KEY (nom) REFERENCES Couleur(nom)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Ouvre
#------------------------------------------------------------

CREATE TABLE Ouvre(
        id      Int NOT NULL ,
        id_Clef Int NOT NULL ,
        idCles  Int NOT NULL ,
        idSalle Int NOT NULL
	,CONSTRAINT Ouvre_PK PRIMARY KEY (id,id_Clef)

	,CONSTRAINT Ouvre_Ressource_FK FOREIGN KEY (id) REFERENCES Ressource(id)
	,CONSTRAINT Ouvre_Clef0_FK FOREIGN KEY (id_Clef) REFERENCES Clef(id)
)ENGINE=InnoDB;

