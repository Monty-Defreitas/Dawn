package com.leonde.seconddawn.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Missiles {
    private int missileId;
    private String missileName;
    private int damageOutput;
    private int energyRequirements;
    private int combatSpeed;
}
