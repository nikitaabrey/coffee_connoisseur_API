package com.coffeecon.app.Models.AuthModels;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class RegisterRequestDTO {

    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Invalid username, username must contain alphanumeric characters only")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\^$*.\\[\\]{}\\(\\)?\\-“!@#%&/,><\\’:;|_~`])\\S{8,99}$", message = "Invalid password format")
    private String password;

    @Email
    private String email;

    public RegisterRequestDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
