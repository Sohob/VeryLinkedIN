package com.verylinkedin.editprofile;

import entities.*;
import org.springframework.http.ResponseEntity;

//import com.verylinkedin.User;
public interface  UserService {
    ResponseEntity updateusername(Username username);
    ResponseEntity updatepassword(Password password);
    ResponseEntity updatepp(profilephoto pp);
    ResponseEntity updateage(age age);
    ResponseEntity updatename(name name);
    ResponseEntity updatecomp(iscompany iscomp);
    ResponseEntity updatefield(field f);
}
