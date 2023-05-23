-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 29 nov. 2022 à 13:42
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ap-clefs`
--

CREATE DATABASE IF NOT EXISTS `ap-clefs`;

USE `ap-clefs`;
-- --------------------------------------------------------

--
-- Structure de la table `clef`
--

DROP TABLE IF EXISTS `clef`;
CREATE TABLE IF NOT EXISTS `clef` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nom` varchar(150) NOT NULL,
    `ouvrir` longtext NOT NULL,
    `nomCouleur` varchar(150) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `clef_couleur_FK` (`nomCouleur`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `comptes`
--

DROP TABLE IF EXISTS `comptes`;
CREATE TABLE IF NOT EXISTS `comptes` (
    `nomUtilisateur` varchar(150) NOT NULL,
    `motDePasse` varchar(150) NOT NULL,
    PRIMARY KEY (`nomUtilisateur`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `couleur`
--

DROP TABLE IF EXISTS `couleur`;
CREATE TABLE IF NOT EXISTS `couleur` (
    `nom` varchar(150) NOT NULL,
    PRIMARY KEY (`nom`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `clef`
--
ALTER TABLE `clef`
    ADD CONSTRAINT `clef_couleur_FK` FOREIGN KEY (`nomCouleur`) REFERENCES `couleur` (`nom`);
COMMIT;

INSERT INTO `couleur` (nom) VALUES ('rouge'), ('vert'), ('bleu');

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_key`(
	IN `nom` VARCHAR(150),
	IN `couleur` VARCHAR(150),
	IN `ouvrir` LONGTEXT
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
INSERT INTO `clef`( `nom`, `ouvrir`, `nomCouleur`) VALUES (nom, ouvrir, couleur);
END//
DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
