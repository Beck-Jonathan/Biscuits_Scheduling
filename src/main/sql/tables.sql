
--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `Event_ID` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT (uuid()),
  `User_ID` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Date` datetime NOT NULL,
  `Location` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Length` decimal(10,2) NOT NULL,
  `Decision` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Paid` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`Event_ID`),
  KEY `fk_Event_User0` (`User_ID`),
  CONSTRAINT `fk_Event_User0` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `Person_ID` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'uuid()',
  `First_Name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Last_Name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`Person_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





DROP TABLE IF EXISTS `person_event_line`;

CREATE TABLE `person_event_line` (
  `Person_ID` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Event_ID` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`Person_ID`,`Event_ID`),
  KEY `fk_Person_Event_Line_Event1` (`Event_ID`),
  CONSTRAINT `fk_Person_Event_Line_Event1` FOREIGN KEY (`Event_ID`) REFERENCES `event` (`Event_ID`),
  CONSTRAINT `fk_Person_Event_Line_Person0` FOREIGN KEY (`Person_ID`) REFERENCES `person` (`Person_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `Role_ID` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`Role_ID`),
  UNIQUE KEY `Role_ID` (`Role_ID`),
  UNIQUE KEY `Role_ID_2` (`Role_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- Table structure for table `suggestion`
--

DROP TABLE IF EXISTS `suggestion`;

CREATE TABLE `suggestion` (
  `Suggestion_ID` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT (uuid()),
  `User_ID` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Application_Name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`Suggestion_ID`),
  KEY `fk_Suggestion_User0` (`User_ID`),
  CONSTRAINT `fk_Suggestion_User0` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `User_ID` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT (uuid()),
  `User_Name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `User_PW` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `User_Name` (`User_Name`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `User_ID` (`User_ID`),
  UNIQUE KEY `User_Name_2` (`User_Name`),
  UNIQUE KEY `Email_2` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




--
-- Table structure for table `user_role_line`
--

DROP TABLE IF EXISTS `user_role_line`;

CREATE TABLE `user_role_line` (
  `Role_ID` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `User_ID` varchar(36) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`Role_ID`,`User_ID`),
  KEY `fk_Uwer_Role_Line_User1` (`User_ID`),
  CONSTRAINT `fk_Uwer_Role_Line_Roel0` FOREIGN KEY (`Role_ID`) REFERENCES `role` (`Role_ID`),
  CONSTRAINT `fk_Uwer_Role_Line_User1` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

