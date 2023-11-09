CREATE TABLE `ranking` (
	`idPlayer` int(50),
    `playerName` varchar(50),
    `nrOfGamesWon` int(4),
	PRIMARY KEY (`idPlayer`)
) ENGINE=InnoDB;
	INSERT INTO `ranking`(idPlayer, playerName, nrOfGamesWon) VALUES (1, "Lotte", 1004);
	INSERT INTO `ranking`(idPlayer, playerName, nrOfGamesWon) VALUES (2, "Mike", 1);
	INSERT INTO `ranking`(idPlayer, playerName, nrOfGamesWon) VALUES (3, "Frank", 1);