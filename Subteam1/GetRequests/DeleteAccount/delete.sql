create procedure delete_acc (iusername varchar(255) , iuserpassword varchar(255))    
AS $$
begin 
	DELETE FROM users
	WHERE iusername = user_name and iuserpassword = password ;                      
end;
$$ language plpgsql;
