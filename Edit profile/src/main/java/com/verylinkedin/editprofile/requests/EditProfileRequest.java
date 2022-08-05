package com.verylinkedin.editprofile.requests;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record EditProfileRequest(String userId,
                                 String username,
                                 String name,
                                 DateTimeFormat age,
                                 String profilephoto) {
    }


