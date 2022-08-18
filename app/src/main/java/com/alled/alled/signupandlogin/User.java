package com.alled.alled.signupandlogin;

import java.util.Locale;

public class User {

    public String fullName, age, email,password;

    public User(){

    }

    public User(String fullName,String age, String email, String password){
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.password = password;
    }

}
