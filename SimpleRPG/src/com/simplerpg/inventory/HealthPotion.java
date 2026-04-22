package com.simplerpg.inventory;

// Maybe change to decorator pattern?
// Also create one for strength potions, etc. and maybe add special effects like temporary buffs, etc.
public class HealthPotion extends Potion {
    int healAmount;

    public HealthPotion(String name, int healAmount) {
        super(name);
        this.healAmount = healAmount;
    }
    
    // To be implemented
    @Override
    public void use() {}

    public int getHealAmount() { return healAmount; }

}