package com.leonde.seconddawn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDockOrder {

  private int dockPk;
  private String message;
  private String orderKey;
  private String empireFk;
  private String weaponFk;
  private String shieldFk;
  private String hullFk;
  private BigDecimal parsecks;
}
