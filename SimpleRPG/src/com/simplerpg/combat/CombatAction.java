package com.simplerpg.combat;

import com.simplerpg.entity.Entity;

public interface CombatAction {
    void execute(Entity firstParty, Entity secondParty);
}
