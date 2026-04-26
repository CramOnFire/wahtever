package com.ttt.inventory;

import com.ttt.combat.CombatContext;
import com.ttt.entity.Player;

// Temporarily multiplies future attack damage.
public class StrengthPotion extends Potion {
    private final int attackPercentBonus;  // e.g., 20 means 1.20x multiplier

    public StrengthPotion(String name, int attackPercentBonus) {
        super(name);
        this.attackPercentBonus = attackPercentBonus;
    }

    @Override
    public void applyInCombat(Player player, CombatContext context) {
        if (context.strengthBuffTurnsRemaining > 0) {
            System.out.println("A strength buff is already active and cannot stack.");
            return;
        }

        context.temporaryStrengthMultiplier = attackPercentBonus;
        context.strengthBuffTurnsRemaining = 3;
        System.out.println("You used " + this.getName() + ". Your next 3 attacks are multiplied by " + (1.0 + attackPercentBonus / 100.0) + "x.");
    }

    @Override
    public String getEffectDescription() {
        return "x" + (1.0 + attackPercentBonus / 100.0) + " for 3 attacks";
    }

    public int getAttackPercentBonus() {
        return attackPercentBonus;
    }
}