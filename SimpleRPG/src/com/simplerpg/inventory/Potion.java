package com.simplerpg.inventory;

import com.simplerpg.entity.Player;
import com.simplerpg.combat.CombatContext;

// Define an abstract class for potions, which can be extended to create specific types of potions (e.g., health, mana, strength).
public abstract class Potion extends Item {

    public Potion(String name) {
        super(name);
    }

    // Apply the potion's effect in combat context
    public abstract void applyInCombat(Player player, CombatContext context);

    // Describe the potion's effect for UI display
    public abstract String getEffectDescription();

    // Legacy method for compatibility
    public void use() {
        // Default no-op; use applyInCombat instead.
    }
}
