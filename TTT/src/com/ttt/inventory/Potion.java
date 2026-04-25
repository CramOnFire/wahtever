package com.ttt.inventory;

import com.ttt.combat.CombatContext;
import com.ttt.entity.Player;

// Define an abstract class for potions, which can be extended to create specific types of potions.
public abstract class Potion extends Item {

    public Potion(String name) {
        super(name);
    }

    // Apply the potion's effect in combat context
    public abstract void applyInCombat(Player player, CombatContext context);

    // Describe the potion's effect for UI display
    public abstract String getEffectDescription();
}