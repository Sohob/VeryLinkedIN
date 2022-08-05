create procedure signup(_user_name varchar(255), _name varchar(255),  _password varchar(255), _age date)
AS $$
begin 
	insert into users values(_user_name, _name, _password, _age);
	commit;
end;
$$ language plpgsql;
