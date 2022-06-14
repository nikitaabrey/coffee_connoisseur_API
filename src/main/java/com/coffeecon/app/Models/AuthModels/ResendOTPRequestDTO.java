package com.coffeecon.app.Models.AuthModels;

import javax.validation.constraints.Pattern;

public class ResendOTPRequestDTO {

    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Invalid username, username must contain alphanumeric characters only")
    private String username;

    public ResendOTPRequestDTO(String username) {
        this.username = username;
    }

    public ResendOTPRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
