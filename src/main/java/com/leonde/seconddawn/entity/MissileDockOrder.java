package com.leonde.seconddawn.entity;


import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class MissileDockOrder {
   List<Missiles> missileIdFk;

    DockOrder dockOrderFk;
}
