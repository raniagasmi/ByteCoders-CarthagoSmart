-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 05 mars 2024 à 17:05
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id_cat` int(11) NOT NULL,
  `nom_cat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `collectdechets`
--

CREATE TABLE `collectdechets` (
  `typeID` int(11) NOT NULL,
  `nomType` varchar(200) NOT NULL,
  `categorie` varchar(200) NOT NULL,
  `PointRamassage` varchar(100) NOT NULL,
  `DateRamassage` varchar(200) NOT NULL,
  `PointRecyclage` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `collectdechets`
--

INSERT INTO `collectdechets` (`typeID`, `nomType`, `categorie`, `PointRamassage`, `DateRamassage`, `PointRecyclage`) VALUES
(10, 'dechet medicaux', 'non recyclable', 'cite el ghazala', '0000-00-00', ''),
(11, 'electronique', 'non recyclable', 'cite el ghazala', '2024-03-04', ''),
(45, 'plastique', 'recyclable', '__', '0000-00-00', 'nour jafer'),
(47, 'dechet medicaux', 'non recyclable', 'cite el ghazala', '0000-00-00', '__'),
(49, 'plastiue', 'recyclable', 'null', '0000-00-00', 'ariana'),
(64, 'dechet medicaux', 'non recyclable', 'cite el ghazala', '2024-02-27', '__'),
(65, 'mmmmmm', 'non recyclable', 'cite el ghazala', '2024-03-27', '__'),
(67, 'PPP', 'NON RECYCLABLE', 'rue rue', '2024-03-07', '__'),
(71, 'mm', 'recyclable', '__', '__', 'mm'),
(72, 'jhb', 'recyclable', '____', '____', 'kjnjk'),
(74, 'nnb', 'recyclable', 'hh', 'nnnn', 'nn'),
(75, 'kk', 'recyclable', '__', '__', '__'),
(76, 'verre', 'RECYCLABLE', '', '', ''),
(78, 'gfg', 'NON RECYCLABLE', 'sokra', '2024-03-05', '--');

-- --------------------------------------------------------

--
-- Structure de la table `consommation`
--

CREATE TABLE `consommation` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `type` varchar(255) NOT NULL,
  `quantite` int(11) NOT NULL,
  `montant` float NOT NULL,
  `userId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `nom_event` varchar(50) NOT NULL,
  `categorie` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL,
  `heure` varchar(50) NOT NULL,
  `lieu` varchar(50) NOT NULL,
  `capacite` int(11) NOT NULL,
  `cout` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `id_facture` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `date_ech` date DEFAULT NULL,
  `montant` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `estPayee` tinyint(1) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `id_paiement` int(11) NOT NULL,
  `id_facture` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `montant` double NOT NULL,
  `mode_paiement` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_res` int(11) NOT NULL,
  `nom_event` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `numero` int(11) NOT NULL,
  `nbr_place` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `cin` int(10) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `numTlfn` int(8) NOT NULL,
  `addEmail` varchar(100) NOT NULL,
  `mdp` varchar(100) NOT NULL,
  `roleUser` enum('ADMIN','RESPONSABLE_DECHETS','RESPONSABLE_ENERGIES','ORGANISATEURS','MEMBRE','TECHNICIEN','UTILISATEURS') NOT NULL,
  `confirmationCode` varchar(500) DEFAULT NULL,
  `urlImage` mediumtext NOT NULL,
  `isVerified` tinyint(1) DEFAULT NULL,
  `Status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `cin`, `nom`, `prenom`, `username`, `numTlfn`, `addEmail`, `mdp`, `roleUser`, `confirmationCode`, `urlImage`, `isVerified`, `Status`) VALUES
(20, 12345678, 'sami', 'belakhdher', 'samsoum', 12345678, 'rania.gasmi@esprit.tn', 'MNmrQeZvxkmBw5qtgzmGN+uN16G5D5UjdBRw0hGjBU8=', 'RESPONSABLE_DECHETS', '8XFryQ', '', 0, 0),
(24, 123, 'newesttest', 'newesttest', 'newesttest', 123, 'newesttest', 'newesttest', 'MEMBRE', NULL, '/image.png', 0, 0),
(25, 0, 'etstetst', 'etstetst', 'etstetst', 12345678, 'etstetst@esprit.tn', 'fc3163057bfd5dfd05a0d5f7f9a2d6ab111dfee5f5e3fbd636cf88d03d71f415', 'MEMBRE', NULL, 'file:/C:/Users/RANIA/Downloads/captcha.PNG', 0, 0),
(26, 123, 'montassar', 'kabsi', 'newUser', 123, 'montassar.kabsi@esprit.tn', 'rania', 'MEMBRE', NULL, '/image.png', NULL, 0),
(27, 123, 'montassar', 'kabsi', 'newUser', 123, 'montassar.kabsi@esprit.tn', 'o26XbI1JDe0+5jTuIBDalA==', 'MEMBRE', NULL, '/image.png', NULL, 0),
(28, 9876543, 'raaaaniaaaaaa', 'gaaaaasmiiiii', 'ranran', 12345678, 'rania.gasmi@esprit.tn', 'MNmrQeZvxkmBw5qtgzmGN+uN16G5D5UjdBRw0hGjBU8=', 'ORGANISATEURS', '8XFryQ', 'file:/C:/Users/RANIA/Downloads/ran.png', NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la table `user1`
--

CREATE TABLE `user1` (
  `id_user` int(11) NOT NULL,
  `num_compte` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `id_facture` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `collectdechets`
--
ALTER TABLE `collectdechets`
  ADD PRIMARY KEY (`typeID`);

--
-- Index pour la table `consommation`
--
ALTER TABLE `consommation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `consommation_ibfk_1` (`userId`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `collectdechets`
--
ALTER TABLE `collectdechets`
  MODIFY `typeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT pour la table `consommation`
--
ALTER TABLE `consommation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
