package com.verylinkedin.editprofile;

import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class  UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity updateusername(Username username){

       return userRepository.updateusername(username);
    }
    public ResponseEntity updatepassword(Password password){

        return userRepository.updatepassword(password);
    }
    public ResponseEntity updatepp(profilephoto pp){

        return userRepository.updatepp(pp);
    }
    public ResponseEntity updateage(age age){

        return userRepository.updateage(age);
    }
    public ResponseEntity updatename(name name){

        return userRepository.updatename(name);
    }
    public ResponseEntity updatecomp(iscompany iscomp){

        return userRepository.updatecomp(iscomp);
    }
    public ResponseEntity updatefield(field isp){

        return userRepository.updatefield(isp);
    }
}
