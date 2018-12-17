package com.example.esthere.gett2.model.entities;

public class Customer {
    protected String name;
    protected String email;
    protected String tel;


    public Customer() {
    }

    public Customer(Customer customer) {
        this.name = customer.name;
        this.email = customer.email;
        this.tel = customer.tel;
    }


    public Customer(String name, String email, String tel) {
        this.name = name;
        this.email = email;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean Validate() {
        if (this.email.equals("")) return false;
        if (this.name.equals("")) return false;
        if (this.tel.equals("")) return false;
        return true;

    }
}

