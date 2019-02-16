package com.example.vasialeleka.mvvm.model;

public class User {
    private String email;
    private  String password;
    private String emailHint;
    private  String passwordHint;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailHint() {
        return emailHint;
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public User(String emailHint, String passwordHint) {
        this.emailHint = emailHint;
        this.passwordHint = passwordHint;
    }
}
