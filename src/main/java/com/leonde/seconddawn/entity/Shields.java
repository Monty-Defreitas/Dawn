package com.leonde.seconddawn.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Shields {
    int shieldId;
    String shieldType;
    int energyRequirements;
    BigDecimal parsecks;
    int damageMitigation;
}
