package com.example.vasialeleka.mvvm.viewmodel;

import android.databinding.BaseObservable;

import com.example.vasialeleka.mvvm.R;
import com.example.vasialeleka.mvvm.model.User;

public class UserModel extends BaseObservable {
    private String email;
    private  String password;
    private String emailHint;
    private  String passwordHint;
    //private User user;
    public UserModel(User user) {
        this.emailHint = user.getEmailHint();
        this.passwordHint = user.getPasswordHint();

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(R.id.emailID);
    }

    public String getPassword() {
        return password;

    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(R.id.passwordID);
    }

    public String getEmailHint() {
        return emailHint;
    }

    public void setEmailHint(String emailHint) {
        this.emailHint = emailHint;
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }
}
