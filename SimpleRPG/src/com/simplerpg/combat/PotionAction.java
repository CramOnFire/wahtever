package com.simplerpg.combat;

import com.simplerpg.entity.Entity;

// This is the only action that can be used both in combat and outside of combat.
public class PotionAction implements CombatAction {
    public void execute(Entity firstParty, Entity secondParty) {
        // Implement potion usage logic here
    }

}
