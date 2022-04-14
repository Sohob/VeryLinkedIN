CREATE PROCEDURE BlockAccount(
   iuserName varchar(30),
   iblockedUserName varchar(30))
AS $$
begin 
INSERT INTO BLOCKED_Accounts (User_Name, Blocked_UserName)
VALUES (iuserName, iblockedUserName)
end;

$$ language plpgsql;


