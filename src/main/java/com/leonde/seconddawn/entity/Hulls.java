package com.leonde.seconddawn.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Hulls {
     int hullId;
    private String hullName;
    private int hullResilience;
    private int energyRequirements;
    private int combatSpeed;
    BigDecimal parsecks;
}
