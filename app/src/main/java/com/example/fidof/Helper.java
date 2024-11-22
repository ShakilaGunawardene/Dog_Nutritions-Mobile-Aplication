package com.example.fidof;

public class Helper {
    String username, email, phoneNumber, password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Helper(String email, String password, String phoneNumber, String username) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public Helper() {
    }
}
