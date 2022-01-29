package com.leonde.seconddawn.dao;

import com.leonde.seconddawn.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CreateShipOrderDao {
    DockOrder saveDockOrder (Weapons weaponFk, Shields shieldFk,
                             Empires empireFk, Hulls hullFk, List<Missiles> missileOptions,
                            BigDecimal parsecks);

    List<Missiles> fetchOptions(List<String> optionIds);
    Optional<Empires> fetchEmpire(String customerId);
    Optional <Shields> fetchShields(String colorId);
    Optional <Weapons> fetchWeapons(String engineId);
    Optional <Hulls> fetchHulls(String hullId);

}
