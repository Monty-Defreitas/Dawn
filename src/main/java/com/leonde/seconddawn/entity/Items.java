package com.leonde.seconddawn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
public class Items {
    // TODO: 1/29/2022 Note: to comeback and delete commented fields for optimization
//    List<Missiles> missilesId;
//    List<Shields> shieldsId;
//    List<Weapons> weaponsId;
@JsonIgnore
int weaponId;
    String weaponName;
    int energyRequirements;
    BigDecimal parsecks;
    int weaponDamage;
    String shieldType;
    int damageMitigation;
}
