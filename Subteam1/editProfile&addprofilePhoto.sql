 CREATE PROCEDURE EditProfile(
  iuserName varchar(255),
 iname varchar(255),
  iuserPassword varchar(255) ,
  iage TIMESTAMP ,
  iprofilePhoto bytea) 
AS $$
begin 
UPDATE USERS 
SET name=iname,userPassword=iuserPassword,
age=iage,profilePhoto=iprofilePhoto
WHERE userName=iuserName
end;
$$ language plpgsql;


 CREATE PROCEDURE AddprofilePhoto(
 iuserName varchar(255),
 iprofilePhoto bytea) 
 AS $$
begin 
 UPDATE USERS 
SET profilePhoto=iprofilePhoto
where userName=iuserName
end;
$$ language plpgsql;

