package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.entity.Empires;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController implements Login{

    @Override
    public void loginUser(Empires login) {
        authenticate(login);
    }
    private void authenticate(Empires createEmpire) {
    }
}
