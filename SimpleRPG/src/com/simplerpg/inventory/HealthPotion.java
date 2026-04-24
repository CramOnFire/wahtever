package com.simplerpg.inventory;

import com.simplerpg.entity.Player;
import com.simplerpg.combat.CombatContext;

// Heals the player instantly and permanently.
public class HealthPotion extends Potion {
    int healAmount;

    public HealthPotion(String name, int healAmount) {
        super(name);
        this.healAmount = healAmount;
    }
    
    @Override
    public void applyInCombat(Player player, CombatContext context) {
        int missing = player.getMaxHealth() - player.getHealth();
        if (missing <= 0) {
            System.out.println("You are already at full health.");
            return;
        }

        int healed = Math.min(missing, healAmount);
        player.heal(healed);
        System.out.println("You used " + this.getName() + " and healed " + healed + " HP.");
    }

    @Override
    public String getEffectDescription() {
        return "Heal " + healAmount + " HP";
    }

    public int getHealAmount() { return healAmount; }
}