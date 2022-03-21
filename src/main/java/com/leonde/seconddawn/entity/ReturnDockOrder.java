package com.leonde.seconddawn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

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
  private List<String> missiles;
  private BigDecimal parsecks;
}
