package com.leonde.seconddawn.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Missiles {
    @JsonIgnore
    private int missileId;
    private String missileName;
    private int damageOutput;
    private int energyRequirements;
    private int combatSpeed;
}
