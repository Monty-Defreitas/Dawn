package com.leonde.seconddawn.service;

import com.leonde.seconddawn.dao.DefaultEmpireDAO;
import com.leonde.seconddawn.entity.Empires;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultEmpireService implements CreateEmpireService {

@Autowired
DefaultEmpireDAO defaultEmpireDAO;

    @Override
    public Empires createEmpire(Empires empire) {
        return defaultEmpireDAO.createEmpire(empire);
    }
}
