drop database if exists schedule;
create database schedule;
use schedule;

DROP TABLE IF EXISTS User;
CREATE TABLE User(


User_ID	nvarchar(36)	DEFAULT(uuid())	not null	comment '',
User_Name	nvarchar(100)	not null	unique	comment '',
User_PW	nvarchar(100)	not null	comment '',
Email	nvarchar(100)	not null	unique	comment '',

CONSTRAINT User_PK PRIMARY KEY (User_ID),

 UNIQUE (User_ID),
 UNIQUE (User_Name),
 UNIQUE (Email)

);

 /******************
Create the Role table
 Created By Jonathan Beck 7/26/2024
***************/

DROP TABLE IF EXISTS Role;
CREATE TABLE Role(

Role_ID	nvarchar(100)	not null	unique	comment '',

CONSTRAINT Role_PK PRIMARY KEY (Role_ID),

 UNIQUE (Role_ID)

);

/******************
Create the Uwer_Role_Line table
 Created By Jonathan Beck 7/26/2024
***************/


DROP TABLE IF EXISTS User_Role_Line;
CREATE TABLE User_Role_Line(

Role_ID	nvarchar(100)	not null	comment '',
User_ID	nvarchar(36)	not null	comment '',

CONSTRAINT Uwer_Role_Line_PK PRIMARY KEY (Role_ID , User_ID),

CONSTRAINT fk_Uwer_Role_Line_Roel0 foreign key (Role_ID) references Role (Role_ID),
CONSTRAINT fk_Uwer_Role_Line_User1 foreign key (User_ID) references User (User_ID)
);

/******************
Create the Person table
 Created By Jonathan Beck 4/9/2025
***************/


DROP TABLE IF EXISTS Person;
CREATE TABLE Person(

Person_ID	nvarchar(36)	DEFAULT "uuid()"	not null	comment '',
First_Name	nvarchar(30)	not null	comment '',
Last_Name	nvarchar(30)	not null	comment '',
Description	nvarchar(100)	not null	comment '',

CONSTRAINT Person_PK PRIMARY KEY (Person_ID)


);

/******************
Create the Event table
 Created By Jonathan Beck 4/9/2025
***************/


DROP TABLE IF EXISTS Event;
CREATE TABLE Event(

Event_ID	nvarchar(36)	DEFAULT "uuid()"	not null	comment '',
User_ID	nvarchar(36)	not null	comment '',
Name	nvarchar(100)	not null	comment '',
Date	DateTime	not null	comment '',
Location	nvarchar(100)	not null	comment '',
description	nvarchar(256)	not null	comment '',
Length	decimal	not null	comment '',
Descision	nvarchar(6)	not null	comment '',
Paid	nvarchar(6)	not null	comment '',

CONSTRAINT Event_PK PRIMARY KEY (Event_ID),
CONSTRAINT fk_Event_User0 foreign key (User_ID) references User (User_ID)
);

/******************
Create the Person_Event_Line table
 Created By Jonathan Beck 4/9/2025
***************/


DROP TABLE IF EXISTS Person_Event_Line;
CREATE TABLE Person_Event_Line(

Person_ID	nvarchar(36)	not null	comment '',
Event_ID	nvarchar(36)	not null	comment '',

CONSTRAINT Person_Event_Line_PK PRIMARY KEY (Person_ID , Event_ID),

CONSTRAINT fk_Person_Event_Line_Person0 foreign key (Person_ID) references Person (Person_ID),
CONSTRAINT fk_Person_Event_Line_Event1 foreign key (Event_ID) references Event (Event_ID)
);


