package com.leonde.seconddawn.controller;


import com.leonde.seconddawn.entity.Empires;
import com.leonde.seconddawn.service.CreateEmpireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class DefaultCreateController implements CreateEmpireController {

     @Autowired
    CreateEmpireService createEmpireService;

    @Override
    public Empires registerEmpire(Empires createUser) {
        return createEmpireService.createEmpire(createUser);
    }
}

