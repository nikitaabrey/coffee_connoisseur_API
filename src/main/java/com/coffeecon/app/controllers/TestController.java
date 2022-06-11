package com.coffeecon.app.controllers;

import com.coffeecon.app.Models.*;
import com.coffeecon.app.Response.HttpSuccess;
import com.coffeecon.app.Services.CognitoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;



@RestController
public class TestController {

    @Autowired
    private CognitoUserService userService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<Object> signIn(@Valid @RequestBody(required = true) LoginRequestDTO request) {
        CognitoAuthToken token = userService.authenticate(request);
        return new HttpSuccess.Builder<CognitoAuthToken>(HttpStatus.OK)
                .withMessage("Successful login")
                .withBody(token)
                .build();
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<Object> signIn(@Valid @RequestBody(required = true)  RegisterRequestDTO request) {
        userService.signUp(request);
        return new HttpSuccess.Builder<Void>(HttpStatus.OK)
                .withMessage("Registration successfull, we have sent an OTP to your email").build();
    }

    @RequestMapping(value="/confirm", method = RequestMethod.POST)
    public ResponseEntity<Object> confirm(@Valid @RequestBody(required = true)  ConfirmRegistrationDTO request) {
        userService.confirmSignUp(request);
        return new HttpSuccess.Builder<Void>(HttpStatus.OK)
                .withMessage("Email confirmed").build();
    }

}
