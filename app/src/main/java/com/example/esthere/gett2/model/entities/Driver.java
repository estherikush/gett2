package com.example.esthere.gett2.model.entities;

public class Driver {
    private String lastName;
    private String firstName;
    private String id;
    private String phone;
    private String email;
    private String creditCard;
    private String password;
    //constructor
    public Driver(String lastName, String firstName, String id, String phone, String email, String creditCard, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.creditCard = creditCard;
        this.password=password;
    }

    public Driver() {
    }

    public Driver(String email, String password) {
        this.email = email;
        this.password=password;
    }

    //getters & setters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getKey() {
        String key = this.email;
        key = key.replace("@","_");
        key = key.replace(".","__");
        return key;
    }
}
