delimiter $$

CREATE TABLE `book` (
  `idBook` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) NOT NULL,
  `ProductionYear` varchar(4) DEFAULT 'null',
  `Pages` int(11) DEFAULT NULL,
  `id_author` int(11) NOT NULL,
  PRIMARY KEY (`idBook`,`id_author`),
  UNIQUE KEY `idBook_UNIQUE` (`idBook`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='List of books in the library of company '$$

