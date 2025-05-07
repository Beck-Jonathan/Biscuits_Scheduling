use schedule;
DELIMITER $$
CREATE TRIGGER `create_UUID` BEFORE INSERT ON `suggestion` FOR EACH ROW BEGIN
    -- Check if the bank account exists for the user
    SET @user_account_count = (SELECT COUNT(*) FROM user WHERE user_ID = new.user_ID);
    
    -- If no bank account exists, create a new one
    
    IF (@user_account_count = 0) THEN
        INSERT INTO user (User_ID, User_Name, User_PW, Email)
        VALUES (new.user_ID, 'guest', UUID(), UUID());
    END IF;
END
DELIMITER ;