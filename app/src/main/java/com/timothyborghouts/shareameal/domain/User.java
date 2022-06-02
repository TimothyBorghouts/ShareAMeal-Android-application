package com.timothyborghouts.shareameal.domain;

public class User {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private boolean isActive;
    private String emailAdress;
    private String password;
    private String phoneNumber;

    public User(String firstName, String lastName, String street, String city, boolean isActive, String emailAdress, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.isActive = isActive;
        this.emailAdress = emailAdress;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }


    public boolean getIsActive() {
        return isActive;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
