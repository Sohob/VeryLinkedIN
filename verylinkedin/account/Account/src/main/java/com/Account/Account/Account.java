package com.Account.Account;

import javax.persistence.*;

@Table
@Entity
public class Account {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    private Long id;
    private String username;
    private String full_name;
    private int age;
    private String password;
    private String profilePicture;
    private FieldOfInterest fieldOfInterest;
    private boolean isCompany;

    public Account() {
    }

    public Account(String username, String full_name, int age, String password, String profilePicture, FieldOfInterest fieldOfInterest, boolean isCompany) {
        this.username = username;
        this.full_name = full_name;
        this.age = age;
        this.password = password;
        this.profilePicture = profilePicture;
        this.fieldOfInterest = fieldOfInterest;
        this.isCompany = isCompany;
    }

    public Account(Long id, String username, String full_name, int age, String password, String profilePicture, FieldOfInterest fieldOfInterest, boolean isCompany) {
        this.id = id;
        this.username = username;
        this.full_name = full_name;
        this.age = age;
        this.password = password;
        this.profilePicture = profilePicture;
        this.fieldOfInterest = fieldOfInterest;
        this.isCompany = isCompany;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public FieldOfInterest getFieldOfInterest() {
        return fieldOfInterest;
    }

    public void setFieldOfInterest(FieldOfInterest fieldOfInterest) {
        this.fieldOfInterest = fieldOfInterest;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", full_name='" + full_name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", fieldOfInterest=" + fieldOfInterest +
                ", isCompany=" + isCompany +
                '}';
    }
}
