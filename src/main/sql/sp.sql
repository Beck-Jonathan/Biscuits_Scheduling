

--
-- Dumping routines for database 'schedule'
--

DELIMITER ;;
CREATE PROCEDURE `cross_id_by_email`(
user_email_param nvarchar(100)
)
begin

call budget.sp_user_id_by_email(user_email_param);
end ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `sp_check_email_avail`(
in Email_param nvarchar(100)
)
begin 
select count(*)
 from user
 where Email=Email_Param;
 END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `sp_check_username_avail`(
in user_param nvarchar(100)
)
begin 
select COUNT(*)
 from user
 where User_Name=user_param;
 END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `sp_count_by_all_Suggestion`(
search_param nvarchar(100)
,
User_ID_param nvarchar(36),

Application_Name_param nvarchar(100)
)
begin 
 SELECT 
count(*)

 FROM Suggestion

WHERE
(
case when 
User_ID_param ='' then 1=1
else Suggestion.User_ID=User_ID_param
end
)
and
(
case when 
Application_Name_param ='' then 1=1
else Suggestion.Application_Name=Application_Name_param
end
)
and
case 
when search_param="" then 0=0
else Suggestion.Suggestion_ID LIKE CONCAT('%',search_param,'%') OR Suggestion.User_ID LIKE CONCAT('%',search_param,'%') OR Suggestion.Application_Name LIKE CONCAT('%',search_param,'%') OR Suggestion.content LIKE CONCAT('%',search_param,'%')
end
 ;
 END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_delete_Event`(Event_ID_param nvarchar(36)
)
BEGIN
delete from Event

WHERE Event_ID=Event_ID_param

 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_delete_Person`(Person_ID_param nvarchar(36)
)
BEGIN
UPDATE Person
   set is_active=0
WHERE Person_ID=Person_ID_param

 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_delete_Suggestion`(Suggestion_ID_param nvarchar(36)
)
BEGIN
delete from Suggestion

WHERE Suggestion_ID=Suggestion_ID_param

 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_get_pw`(
User_Name_param nvarchar(100)

)
begin 
select User_PW 
from user

where User_Name = User_Name_param

;
 END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_insert_Event`(
in Name_param nvarchar(100)
,in user_id_param nvarchar(36)
,in Date_param DAteTime
,in Location_param nvarchar(100)
,in description_param nvarchar(256)
,in Length_param decimal
,in Descision_param nvarchar(10)
,in Paid_param nvarchar(10)
)
begin 
INSERT INTO  Event
(Name,user_id,Date,Location,description,Length,Decision,Paid)
 values 
(Name_param
, user_id_param
,Date_param
,Location_param
,description_param
,Length_param
,Descision_param
,Paid_param
)
 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_insert_Person`(
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
 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_insert_Suggestion`(
in User_ID_param nvarchar(36)
,in Application_Name_param nvarchar(100)
,in content_param nvarchar(1000)
)
begin 
INSERT INTO  Suggestion
(User_ID,Application_Name,content)
 values 
(User_ID_param
,Application_Name_param
,content_param
)
 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_insert_User`(
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
 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_insert_User_Role_Line`(
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
 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_retreive_by_all_Event`(
user_id_param nvarchar(36),
month_param int,
day_param int,
year_param int,
search_param nvarchar(100)
)
begin 
 SELECT 

Event.Event_ID as 'Event_Event_ID',
event.user_id as 'Event_User_ID',
Event.Name as 'Event_Name',
Event.Date as 'Event_Date',
Event.Location as 'Event_Location',
Event.Description as 'Event_Description',
Event.Length as 'Event_Length',
Event.Decision as 'Event_Decision',
Event.Paid as 'Event_Paid'
 FROM Event
 where user_id=user_id_param
 and 

case
	when day_param!=0 THEN event.date >= STR_TO_DATE(concat(month_param,'-',day_param,'-',year_param), "%m-%d-%Y") and event.date < STR_TO_DATE(concat(month_param,'-',day_param+1,'-',year_param), "%m-%d-%Y")  
    when day_param=0 and month_param!=0 THEN  event.date >= STR_TO_DATE(concat(month_param,'-',day_param,'-',year_param), "%m-%d-%Y") and event.date < STR_TO_DATE(concat(month_param+1,'-',day_param,'-',year_param), "%m-%d-%Y")
    when day_param=0 and month_param=0 and year_param!=0 THEN event.date >= STR_TO_DATE(concat(month_param,'-',day_param,'-',year_param), "%m-%d-%Y") and event.date < STR_TO_DATE(concat(month_param,'-',day_param,'-',year_param+1), "%m-%d-%Y")
	when day_param=0 and month_param=0 and year_param=0 THEN 0=0  

end
and
case
	when search_param ="" then 0=0
    when search_param !="" then Event.Name like concat('%',search_param,'%') or Event.Location like concat('%',search_param,'%') or Event.Description like concat('%',search_param,'%')
end        

ORDER BY Event.Date


 ;
 END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_retreive_by_all_Person`(

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
 END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_retreive_by_all_Suggestion`(
limit_param int ,
 offset_param int ,
search_param nvarchar(100),
User_ID_param nvarchar(36),
Application_Name_param nvarchar(100)
)
begin 
 SELECT 

Suggestion.Suggestion_ID as 'Suggestion_Suggestion_ID',
Suggestion.User_ID as 'Suggestion_User_ID',
Suggestion.Application_Name as 'Suggestion_Application_Name',
Suggestion.content as 'Suggestion_content',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email'
 FROM Suggestion
join User on Suggestion.User_ID = User.User_ID
WHERE
(
case when 
User_ID_param ='' then 1=1
else Suggestion.User_ID=User_ID_param
end
)
and
case when search_param!='' then 
	Suggestion.Suggestion_ID LIKE CONCAT('%',search_param,'%') 
    OR Suggestion.User_ID LIKE CONCAT('%',search_param,'%')
    OR Suggestion.Application_Name LIKE CONCAT('%',search_param,'%') 
    OR Suggestion.content LIKE CONCAT('%',search_param,'%')
else 1=1

end 
and
(
case when 
Application_Name_param ='' then 1=1
else Suggestion.Application_Name=Application_Name_param
end
)

ORDER BY Suggestion_ID
limit limit_param
offset offset_param

 ;
 END ;;
DELIMITER ;
DELIMITER ;;
 CREATE PROCEDURE `sp_retreive_by_pk_Event`(
Event_ID_param nvarchar(36)
)
Begin 
 select 

Event.Event_ID as 'Event_Event_ID',
Event.User_ID as 'Event_User_ID',
Event.Name as 'Event_Name',
Event.Date as 'Event_Date_Time',
Event.Location as 'Event_Location',
Event.Description as 'Event_Description',
Event.Length as 'Event_Length',
Event.Decision as 'Event_Decision',
Event.Paid as 'Event_Paid'
 FROM Event
where Event_ID=Event_ID_param
 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_retreive_by_pk_Person`(
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
 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_retreive_by_pk_Suggestion`(
Suggestion_ID_param nvarchar(36)
)
Begin 
 select 

Suggestion.Suggestion_ID as 'Suggestion_Suggestion_ID',
Suggestion.User_ID as 'Suggestion_User_ID',
Suggestion.Application_Name as 'Suggestion_Application_Name',
Suggestion.content as 'Suggestion_content',
User.User_ID as 'User_User_ID',
User.User_Name as 'User_User_Name',
User.User_PW as 'User_User_PW',
User.Email as 'User_Email'
 FROM Suggestion
join User on Suggestion.User_ID = User.User_ID
where Suggestion_ID=Suggestion_ID_param
 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_retreive_by_pk_User`(
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
 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_retreive_by_User_User_Role_Line`(
User_ID_param nvarchar(36)
)
Begin 
 select 

User_Role_Line.Role_ID as 'User_Role_Line_Role_ID'
from User_Role_Line
where User_ID=User_ID_param
 ; END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_update_Event`(oldEvent_ID nvarchar(36)
,oldName nvarchar(100)
,newName nvarchar(100)
,oldDate_Time DATETIME 
,newDate_Time DATETIME 
,oldLocation nvarchar(100)
,newLocation nvarchar(100)
,oldDescription nvarchar(256)
,newDescription nvarchar(256)
,oldLength decimal
,newLength decimal
,oldDecision nvarchar(10)
,newDecision nvarchar(10)
,oldPaid nvarchar(10)
,newPaid nvarchar(10)
)
begin 
UPDATE Event
 set Name = newName
,Date = newDate_Time
,Location = newLocation
,Description = newDescription
,Length = newLength
,Decision = newDecision
,Paid = newPaid
WHERE Event_ID= oldEvent_ID
AND Name= oldName
AND Date= oldDate_Time 
AND Location= oldLocation
AND Description= oldDescription
AND Length= oldLength
AND Decision= oldDecision
AND Paid= oldPaid


 ; end ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_update_Person`(oldPerson_ID nvarchar(36)
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

 ; end ;;
DELIMITER ;


DELIMITER ;;
 CREATE PROCEDURE `sp_user_id_by_email`(
in Email_param nvarchar(100)
)
begin 

 select max(user_Id)
 from user
 where Email=Email_Param;
 END ;;
DELIMITER ;

DELIMITER ;;
 CREATE PROCEDURE `sp_user_id_by_username`(
in user_name_param nvarchar(100)
)
begin 

 select user_Id
 from user
 where User_Name=user_name_param;
 END ;;
DELIMITER ;



