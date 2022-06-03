package com.verylinkedin.editprofile;

import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.util.EnumUtils;

import java.sql.PreparedStatement;


@Repository
public class UserRepositoryImp implements UserRepository {
    private static final String SQL_COUNT_BY_USERNAME= "SELECT COUNT(*) FROM USERS WHERE USERNAME = ?";
    private static final String SQL_password= "SELECT PASSWORD FROM USERS WHERE ID= ?";
    private static final String SQL_UPDATE_USERNAME = "UPDATE USERS SET USERNAME=? WHERE ID=?";
    private static final String SQL_UPDATE_PASSWORD= "UPDATE USERS SET PASSWORD=? WHERE ID=?";
    private static final String SQL_UPDATE_PROFILEPHOTO = "UPDATE USERS SET PROFILEPHOTO=? WHERE ID=?";
    private static final String SQL_UPDATE_AGE= "UPDATE USERS SET AGE=? WHERE ID=?";
    private static final String SQL_UPDATE_NAME= "UPDATE USERS SET FULLNAME=? WHERE ID=?";
    private static final String SQL_UPDATE_COMP= "UPDATE USERS SET ISCOMPANY=? WHERE ID=?";
    private static final String SQL_UPDATE_FIELD= "UPDATE USERS SET FIELDOFINTEREST=? WHERE ID=?";


    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Integer getCountByusername(String username) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_USERNAME, Integer.class,new Object[]{username});
    }
    public String getpassword(Long id) {
        return jdbcTemplate.queryForObject(SQL_password, String.class,new Object[]{id});
    }
    public ResponseEntity updateusername(Username username){
        try{
            boolean okay=true;
            if(getCountByusername(username.getUsername())>0){
               okay=false;
            }
            if(okay){
                jdbcTemplate.update(SQL_UPDATE_USERNAME,username.getUsername(),username.getId());
                return ResponseEntity.ok()
                        .body("USERNAME UPDATED!");
            }
            else{
                return ResponseEntity.badRequest()
                        .body("THIS USERNAME IS ALREADY USED");
            }

        }
        catch(DataAccessException e){
            e.printStackTrace();

        }
        return null;
    }

    public ResponseEntity updatepassword(Password password){
        try{
            boolean okay=true;

            if(!password.getOldpassword().equals(getpassword(password.getId()))){
                return ResponseEntity.badRequest()
                        .body("PASSWORD IS INCORRECT!");
            }

            if(password.getNewpassword().equals(password.getOldpassword())){
                return ResponseEntity.badRequest()
                        .body("NEW PASSWORDS CAN'T BE THE SAME AS OLD PASSWORDS!");
            }
            if(okay){
                jdbcTemplate.update(SQL_UPDATE_PASSWORD,password.getNewpassword(),password.getId());
                return ResponseEntity.ok()
                        .body("PASSWORD UPDATED!");
            }



        }
        catch(DataAccessException e){
            e.printStackTrace();

        }
        return null;
    }

    public ResponseEntity updatepp(profilephoto profilephoto){
        try{
            jdbcTemplate.update(SQL_UPDATE_PROFILEPHOTO,profilephoto.getProfilephoto(),profilephoto.getId());
            return ResponseEntity.ok()
                        .body("PROFILE PHOTO IS UPDATED!");

        }
        catch(DataAccessException e){
            e.printStackTrace();

        }
        return null;
    }

    public ResponseEntity updateage(age age){
        try{
            boolean okay=true;
            if(age.getage()>150){
                okay=false;
            }
            if(okay) {
                jdbcTemplate.update(SQL_UPDATE_AGE, age.getage(), age.getId());
                return ResponseEntity.ok()
                        .body("AGE IS UPDATED!");
            }
            else{
                return ResponseEntity.badRequest()
                        .body("AGE IS INACCURATE!");
            }


        }
        catch(DataAccessException e){
            e.printStackTrace();

        }
        return null;
    }

    public ResponseEntity updatename(name name){
        try{
                jdbcTemplate.update(SQL_UPDATE_NAME, name.getname(), name.getId());
                return ResponseEntity.ok()
                        .body("NAME IS UPDATED!");


        }
        catch(DataAccessException e){
            e.printStackTrace();

        }
        return null;
    }
    public ResponseEntity updatecomp(iscompany iscomp){
        try{
            jdbcTemplate.update(SQL_UPDATE_COMP, iscomp.iscompany(), iscomp.getId());
            return ResponseEntity.ok()
                    .body("COMPANY STATUS IS UPDATED!");


        }
        catch(DataAccessException e){
            e.printStackTrace();

        }
        return null;
    }

    public ResponseEntity updatefield(field f){
        try{
            boolean okay =false;
            for (FieldOfInterest c : FieldOfInterest.values()) {
                if (c.name().equals(f.x)) {
                   okay=true;
                   break;
                }
            }
            if(okay){
                jdbcTemplate.update(SQL_UPDATE_FIELD, f.getx(), f.getId());
                return ResponseEntity.ok()
                        .body("FIELD OF INTEREST IS UPDATED!");
            }else{
                return ResponseEntity.badRequest()
                        .body("INVALID FIELD OF INTEREST!");
            }



        }
        catch(DataAccessException e){
            e.printStackTrace();

        }
        return null;
    }
}
