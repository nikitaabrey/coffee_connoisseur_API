package com.coffeecon.app.Models.AuthModels;

import javax.validation.constraints.Pattern;

public class LoginRequestDTO {

    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Invalid username, username must contain alphanumeric characters only")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\^$*.\\[\\]{}\\(\\)?\\-“!@#%&/,><\\’:;|_~`])\\S{8,99}$", message = "Invalid password format")
    private String password;

    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
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


}
