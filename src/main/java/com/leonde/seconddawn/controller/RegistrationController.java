package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.dao.UserRegistrationDao;
import com.leonde.seconddawn.entity.UserRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegistrationController implements RegistrationInterface{
    @Autowired
    private UserRegistrationDao userRegistrationDao;
    @Override
    public String registerUser(UserRegistration register) {
        return userRegistrationDao.registerUser(register);
    }
}
