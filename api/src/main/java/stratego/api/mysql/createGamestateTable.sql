CREATE TABLE `gamestate` (
	`id` int(50),
    `vak` int(3),
    `xCoordinaat` int(3),
    `yCoordinaat` int(3),
    `stuk` varchar(50),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;
	INSERT INTO `gamestate`(id, vak, xCoordinaat, yCoordinaat, stuk) VALUES (1, 1, 1, 1, "verkenner");
	INSERT INTO `gamestate`(id, vak, xCoordinaat, yCoordinaat, stuk) VALUES (2, 2, 2, 1, "maarschalk");

