package com.coffeecon.app.Models;

import javax.validation.constraints.Pattern;

public class ConfirmRegistrationDTO {

    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Invalid username, username must contain alphanumeric characters only")
    private String username;

    @Pattern(regexp = "^[0-9]+$", message = "Invalid confirmation code")
    private String confirmationCode;

    public ConfirmRegistrationDTO(String username, String confirmationCode) {
        this.username = username;
        this.confirmationCode = confirmationCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}

