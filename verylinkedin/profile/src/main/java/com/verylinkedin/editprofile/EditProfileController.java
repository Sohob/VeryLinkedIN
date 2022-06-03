package com.verylinkedin.editprofile;


import entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController


@RequestMapping("/edit")
public class EditProfileController {

    @Autowired
    UserService userService;

    @PutMapping("/username")
    public ResponseEntity<Map<String, Boolean>> updusername(@RequestBody Username username)
    {
        return userService.updateusername(username);
    }
    @PutMapping("/password")
    public ResponseEntity<Map<String, Boolean>> updpassword(@RequestBody Password password)
    {
        return userService.updatepassword(password);
    }
    @PutMapping("/profilephoto")
    public ResponseEntity<Map<String, Boolean>> updpp(@RequestBody profilephoto pp)
    {
        return userService.updatepp(pp);
    }

    @PutMapping("/age")
    public ResponseEntity<Map<String, Boolean>> updage(@RequestBody age age)
    {
        return userService.updateage(age);
    }
    @PutMapping("/name")
    public ResponseEntity<Map<String, Boolean>> updname(@RequestBody name name)
    {
        return userService.updatename(name);
    }
    @PutMapping("/iscompany")
    public ResponseEntity<Map<String, Boolean>> updcomp(@RequestBody iscompany iscomp)
    {
        return userService.updatecomp(iscomp);
    }

    @PutMapping("/field")
    public ResponseEntity<Map<String, Boolean>> updfeild(@RequestBody field field)
    {
        return userService.updatefield(field);
    }

}
