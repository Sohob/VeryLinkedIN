CREATE PROCEDURE addskills(
   userName varchar(30),
   skill varchar(30))
AS $$
begin 
INSERT INTO Skills (user_Name, skill)
end;

$$ language plpgsql;