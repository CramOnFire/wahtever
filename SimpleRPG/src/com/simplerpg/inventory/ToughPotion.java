package com.simplerpg.inventory;

import com.simplerpg.entity.Player;
import com.simplerpg.combat.CombatContext;

// Temporarily increases defense additively.
public class ToughPotion extends Potion {
    private final int defenseBonus;

    public ToughPotion(String name, int defenseBonus) {
        super(name);
        this.defenseBonus = defenseBonus;
    }

    @Override
    public void applyInCombat(Player player, CombatContext context) {
        context.temporaryDefenseBonus += defenseBonus;
        context.defenseBuffTurnsRemaining = 3;
        System.out.println("You used " + this.getName() + ". Your defense is increased by " + defenseBonus + " for 3 turns.");
    }

    @Override
    public String getEffectDescription() {
        return "+" + defenseBonus + " defense (3 turns)";
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }
}
