use schedule;

/******************
Create the sp_check_email_avail
 Created By Jonathan Beck3/3/2024

***************/
DROP PROCEDURE IF EXISTS sp_check_email_avail;
DELIMITER $$
CREATE PROCEDURE sp_check_email_avail(
in Email_param nvarchar(100)
)
begin 
select count(*)
 from user
 where Email=Email_Param;
 END $$
 DELIMITER ;
 
  /******************
Create the retreive by key script for the User table
 Created By Jonathan Beck 7/24/2024
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_pk_User;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_pk_User
(
User_ID_param nvarchar(36)
)
 Begin 
 select 

User.User_ID as 'User_ID',
User.User_Name as 'User_Name',
User.User_PW as 'User_PW',
User.Email as 'Email'
 FROM User
where User_ID=User_ID_param
 ; END $$
 DELIMITER ;
 
  /******************
Create the sp_check_username_avail
 Created By Jonathan Beck3/3/2024

***************/
DROP PROCEDURE IF EXISTS sp_check_username_avail;
DELIMITER $$
CREATE PROCEDURE sp_check_username_avail(
in user_param nvarchar(100)
)
begin 
select COUNT(*)
 from user
 where User_Name=user_param;
 END $$
 DELIMITER ;
 
 
/******************
Create the retrive user id by email
 Created By Jonathan Beck3/3/2024

***************/
DROP PROCEDURE IF EXISTS sp_user_id_by_email;
DELIMITER $$
CREATE PROCEDURE sp_user_id_by_email(
in Email_param nvarchar(100)
)
begin 

 select max(user_Id)
 from user
 where Email=Email_Param;
 END $$
 DELIMITER ;
 
  
  /******************
Create the retrive user id by username
 Created By Jonathan Beck3/3/2024

***************/
DROP PROCEDURE IF EXISTS sp_user_id_by_username;
DELIMITER $$
CREATE PROCEDURE sp_user_id_by_username(
in user_name_param nvarchar(100)
)
begin 

 select user_Id
 from user
 where User_Name=user_name_param;
 END $$
 DELIMITER ;
 
  /******************
Create the insert script for the User table
 Created By Jonathan Beck 7/24/2024
***************/
DROP PROCEDURE IF EXISTS sp_insert_User;
DELIMITER $$
CREATE PROCEDURE sp_insert_User(
in User_Name_param nvarchar(100)
,in User_PW_param nvarchar(100)
,in Email_param nvarchar(100)
)
begin 
INSERT INTO  User
(User_Name,User_PW,Email)
 values 
(User_Name_param
,User_PW_param
,Email_param
)
 ; END $$
 DELIMITER ;
 
  /******************
Create the get_pw script for the user table
***************/

DROP PROCEDURE IF EXISTS sp_get_pw;
DELIMITER $$
CREATE PROCEDURE sp_get_pw(
User_Name_param nvarchar(100)

)
begin 
select User_PW 
from user

where User_Name = User_Name_param

;
 END $$
 DELIMITER ;
 

 
  /******************
Create the retreive by user script for the Uwer_Role_Line table
 Created By Jonathan Beck 7/26/2024
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_User_User_Role_Line;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_User_User_Role_Line
(
User_ID_param nvarchar(36)
)
 Begin 
 select 

User_Role_Line.Role_ID as 'User_Role_Line_Role_ID'
from User_Role_Line
where User_ID=User_ID_param
 ; END $$
 DELIMITER ;
 
  /******************
Create the insert script for the User_Role_Line table
 Created By Jonathan Beck 7/26/2024
***************/
DROP PROCEDURE IF EXISTS sp_insert_User_Role_Line;
DELIMITER $$
CREATE PROCEDURE sp_insert_User_Role_Line(
in Role_ID_param nvarchar(100)
,in User_ID_param nvarchar(36)
)
begin 
INSERT INTO  User_Role_Line
(Role_ID,User_ID)
 values 
(Role_ID_param
,User_ID_param
)
 ; END $$
 DELIMITER ;
 
 
/******************
Create the retreive by all script for the Event table
 Created By Jonathan Beck 4/9/2025
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_all_Event;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_all_Event(
limit_param int ,
 offset_param int 
)
begin 
 SELECT 

Event.Event_ID as 'Event_Event_ID',
Event.Name as 'Event_Name',
Event.Date as 'Event_Date',
Event.Location as 'Event_Location',
Event.description as 'Event_description',
Event.Length as 'Event_Length',
Event.Descision as 'Event_Descision',
Event.Paid as 'Event_Paid'
 FROM Event

ORDER BY Event_ID
limit limit_param
offset offset_param

 ;
 END $$ 
 DELIMITER ;
 
 
/******************
Create the insert script for the Event table
 Created By Jonathan Beck 4/9/2025
***************/
DROP PROCEDURE IF EXISTS sp_insert_Event;
DELIMITER $$
CREATE PROCEDURE sp_insert_Event(
in Name_param nvarchar(100)
,in Date_param DAteTime
,in Location_param nvarchar(100)
,in description_param nvarchar(256)
,in Length_param decimal
,in Descision_param nvarchar(6)
,in Paid_param nvarchar(6)
)
begin 
INSERT INTO  Event
(Name,Date,Location,description,Length,Descision,Paid)
 values 
(Name_param
,Date_param
,Location_param
,description_param
,Length_param
,Descision_param
,Paid_param
)
 ; END $$
 DELIMITER ;


/******************
Create the insert script for the Person table
 Created By Jonathan Beck 4/9/2025
***************/
DROP PROCEDURE IF EXISTS sp_insert_Person;
DELIMITER $$
CREATE PROCEDURE sp_insert_Person(
in First_Name_param nvarchar(30)
,in Last_Name_param nvarchar(30)
,in Description_param nvarchar(100)
)
begin 
INSERT INTO  Person
(First_Name,Last_Name,Description)
 values 
(First_Name_param
,Last_Name_param
,Description_param
)
 ; END $$
 DELIMITER ;
 
 /******************
Create the retreive by all script for the Person table
 Created By Jonathan Beck 4/9/2025
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_all_Person;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_all_Person(

)
begin 
 SELECT 

Person.Person_ID as 'Person_Person_ID',
Person.First_Name as 'Person_First_Name',
Person.Last_Name as 'Person_Last_Name',
Person.Description as 'Person_Description'
 FROM Person

ORDER BY Person_ID


 ;
 END $$ 
 DELIMITER ;
 
/******************
Create the update script for the Person table
 Created By Jonathan Beck 4/9/2025
***************/
DROP PROCEDURE IF EXISTS sp_update_Person;
 DELIMITER $$
CREATE PROCEDURE sp_update_Person
(oldPerson_ID nvarchar(36)
,oldFirst_Name nvarchar(30)
,newFirst_Name nvarchar(30)
,oldLast_Name nvarchar(30)
,newLast_Name nvarchar(30)
,oldDescription nvarchar(100)
,newDescription nvarchar(100)
)
begin 
UPDATE Person
 set First_Name = newFirst_Name
,Last_Name = newLast_Name
,Description = newDescription
WHERE Person_ID= oldPerson_ID
AND First_Name= oldFirst_Name
AND Last_Name= oldLast_Name
AND Description= oldDescription

 ; end $$
 DELIMITER ;
 
 /******************
Create the delete script for the Person table
 Created By Jonathan Beck 4/9/2025
***************/
DROP PROCEDURE IF EXISTS sp_delete_Person;
DELIMITER $$
CREATE PROCEDURE sp_delete_Person
(Person_ID_param nvarchar(36)
)
BEGIN
UPDATE Person
   set is_active=0
WHERE Person_ID=Person_ID_param

 ; END $$
 DELIMITER ;
 
 /******************
Create the retreive by key script for the Person table
 Created By Jonathan Beck 4/9/2025
***************/
DROP PROCEDURE IF EXISTS sp_retreive_by_pk_Person;
DELIMITER $$
CREATE PROCEDURE sp_retreive_by_pk_Person
(
Person_ID_param nvarchar(36)
)
 Begin 
 select 

Person.Person_ID as 'Person_Person_ID',
Person.First_Name as 'Person_First_Name',
Person.Last_Name as 'Person_Last_Name',
Person.Description as 'Person_Description'
 FROM Person
where Person_ID=Person_ID_param
 ; END $$
 DELIMITER ;
 
 /******************
Create the update script for the Event table
 Created By Jonathan Beck 4/10/2025
***************/
DROP PROCEDURE IF EXISTS sp_update_Event;
 DELIMITER $$
CREATE PROCEDURE sp_update_Event
(oldEvent_ID nvarchar(36)
,oldName nvarchar(100)
,newName nvarchar(100)
,oldDate Date
,newDate Date
,oldTime Time
,newTime Time
,oldLocation nvarchar(100)
,newLocation nvarchar(100)
,oldDescription nvarchar(256)
,newDescription nvarchar(256)
,oldLength decimal
,newLength decimal
,oldDecision nvarchar(6)
,newDecision nvarchar(6)
,oldPaid nvarchar(6)
,newPaid nvarchar(6)
)
begin 
UPDATE Event
 set Name = newName
,Date = newDate
,Time = newTime
,Location = newLocation
,Description = newDescription
,Length = newLength
,Decision = newDecision
,Paid = newPaid
WHERE Event_ID= oldEvent_ID
AND Name= oldName
AND Date= oldDate
AND Time= oldTime
AND Location= oldLocation
AND Description= oldDescription
AND Length= oldLength
AND Decision= oldDecision
AND Paid= oldPaid

 ; end $$
 DELIMITER ;
