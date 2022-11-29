#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: comptes
#------------------------------------------------------------

CREATE TABLE comptes(
        nomUtilisateur Varchar (150) NOT NULL ,
        motDePasse     Varchar (150) NOT NULL
	,CONSTRAINT comptes_PK PRIMARY KEY (nomUtilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: couleur
#------------------------------------------------------------

CREATE TABLE couleur(
        nom Varchar (150) NOT NULL
	,CONSTRAINT couleur_PK PRIMARY KEY (nom)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: clef
#------------------------------------------------------------

CREATE TABLE clef(
        id     Int  Auto_increment  NOT NULL ,
        ouvrir Longtext NOT NULL ,
        nom    Varchar (150) NOT NULL
	,CONSTRAINT clef_PK PRIMARY KEY (id)

	,CONSTRAINT clef_couleur_FK FOREIGN KEY (nom) REFERENCES couleur(nom)
)ENGINE=InnoDB;

