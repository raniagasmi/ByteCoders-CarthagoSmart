CREATE TABLE `consommation` (
                                `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                `date` date NOT NULL,
                                `type` varchar(255) NOT NULL,
                                `quantite` int(11) NOT NULL,
                                `montant` float NOT NULL,
                                `userId` int(11) NOT NULL,
                                CONSTRAINT `consommation_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id_user`)
)