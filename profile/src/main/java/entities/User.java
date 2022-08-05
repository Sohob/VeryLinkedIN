package entities;

import com.verylinkedin.editprofile.FieldOfInterest;

public class  User {
    private Long id;
    private String username;
    private String full_name;
    private int age;
    private String password;
    private String profilePicture;
    private FieldOfInterest fieldOfInterest;
    private boolean isCompany;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFull_name() {
        return full_name;
    }

    public int getAge() {
        return age;
    }


    public String getPassword() {
        return password;
    }


    public String getProfilePicture() {
        return profilePicture;
    }


    public FieldOfInterest getFieldOfInterest() {
        return fieldOfInterest;
    }


    public boolean isCompany() {
        return isCompany;
    }




}