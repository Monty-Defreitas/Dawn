package com.leonde.seconddawn.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class Weapons {
    @JsonIgnore
    int weaponId;
    String weaponName;
    int energyRequirements;
    BigDecimal parsecks;
    int weaponDamage;

}
