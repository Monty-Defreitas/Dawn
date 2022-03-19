package com.leonde.seconddawn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Shields {
    @JsonIgnore
    int shieldId;

    String shieldType;
    int energyRequirements;
    BigDecimal parsecks;
    int damageMitigation;
}
