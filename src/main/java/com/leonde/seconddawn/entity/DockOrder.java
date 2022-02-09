package com.leonde.seconddawn.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class DockOrder {
    @JsonIgnore
    private int dockPk;
    private String message;
    private String orderKey;
    private Empires empireFk;
    private Weapons weaponFk;
    private Shields shieldFk;
    private Hulls hullFk;
    private List<Missiles> missileOptions;
    private BigDecimal parsecks;
}
