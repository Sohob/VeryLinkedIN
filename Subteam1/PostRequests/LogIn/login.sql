create function login (iusername varchar(255) , iuserpassword varchar(255),result varchar(255) default '0') 
	returns varchar(255)   
AS $$
begin 
 if exists (select * from users where user_name=iusername 
                  and password=iuserpassword
              )
    then                                     
        result = 'Login successfully';                       
    else 
        result = 'Login Failed';                                                              
    end if;
    
    return result;                        
end;
$$ language plpgsql;