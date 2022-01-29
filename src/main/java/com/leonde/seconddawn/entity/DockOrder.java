package com.leonde.seconddawn.entity;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class DockOrder {
    private int dockPk;
    private Weapons weaponFk;
    private Shields shieldFk;
    private Empires empireFk;
    private Hulls hullFk;
    private List<Missiles> missileOptions;
    private BigDecimal parsecks;
}
