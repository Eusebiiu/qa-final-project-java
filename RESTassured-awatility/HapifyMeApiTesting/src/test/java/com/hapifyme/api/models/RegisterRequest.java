package com.hapifyme.api.models;

public class RegisterRequest {
    public String username;
    public String email;
    public String password;
    public String first_name;
    public String last_name;

    public RegisterRequest(String username, String email, String password, String first_name, String last_name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}