
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

/******************
Create the Friend_Line table
 Created By Jonathan Beck 5/7/2025
***************/


DROP TABLE IF EXISTS Friend_Line;
CREATE TABLE Friend_Line(

User_One	nvarchar(36)	not null	comment '',
User_Two	nvarchar(36)	not null	comment '',
Status	nvarchar(25)	not null	comment '',
Last_Updated	datetime	DEFAULT now()	not null	comment '',

CONSTRAINT Friend_Line_PK PRIMARY KEY (User_One , User_Two),

CONSTRAINT fk_Friend_Line_User0 foreign key (User_One) references User (User_ID),
CONSTRAINT fk_Friend_Line_User1 foreign key (User_Two) references User (User_ID)
);


/******************
Create the Culvers table
 Created By Jonathan Beck 11/21/2025
***************/


DROP TABLE IF EXISTS Culvers;
CREATE TABLE Culvers(

Culvers_ID	nvarchar(36)	DEFAULT (uuid())  not null	comment '',
User_ID	nvarchar(36)	not null	comment '',
Name	nvarchar(255)	not null	comment '',
WebAddress	nvarchar(255)	not null	comment '',
Is_Active	bool	DEFAULT TRUE	not null	comment '',

CONSTRAINT Culvers_PK PRIMARY KEY (Culvers_ID),

CONSTRAINT fk_Culvers_User0 foreign key (User_ID) references User (User_ID)
);

