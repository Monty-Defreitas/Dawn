package com.leonde.seconddawn.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class DockOrder {
    @JsonIgnore
    private int dockPk;
    private String message;
    private String orderKey;
    @NotNull
    @Length(max = 30)

    private Empires empireFk;
    @NotNull
    @Length(max = 300)
    @Pattern(regexp = "^[^\s]+[-a-zA-Z\s]+([-a-zA-Z]+)*$")
    private Weapons weaponFk;
    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "^[^\s]+[-a-zA-Z\s]+([-a-zA-Z]+)*$")
    private Shields shieldFk;
    @NotNull
    @Length(max = 30)
    @Pattern(regexp = "^[^\s]+[-a-zA-Z\s]+([-a-zA-Z]+)*$")
    private Hulls hullFk;
    private List<Missiles> missileOptions;
    private BigDecimal parsecks;
}
