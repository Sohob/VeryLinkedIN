CREATE PROCEDURE Report(
  userName varchar(255),
 reportedName varchar(255),
 ireason  varchar(255),
  tm TIMESTAMP) 
AS $$
begin 
INSERT INTO Reports(userName , reportedName,ireason,0,tm)
end;
$$ language plpgsql;

CREATE PROCEDURE ReviewReport(
  userName varchar(255),
 reportedName varchar(255),
  tm TIMESTAMP) 
AS $$
begin 
UPDATE Reports
SET Reviewed=1
where User_Name=userName AND Reported_UserName =reportedName AND issued_date =tm 
end;
$$ language plpgsql;

-- we Need to have an Id for every Report.
