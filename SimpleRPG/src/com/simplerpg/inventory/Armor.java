package com.simplerpg.inventory;

// No decorators for armor yet, but we can easily add them later if we want to add special effects like fire resistance, etc.
// Leather armor, chainmail, plate armor, etc. can all be implemented as subclasses of Armor with different defense values and possibly special effects.
public class Armor extends Item {
    protected double defense;

    public Armor(String name, double defense) {
        super(name);
        this.defense = defense;
    }

    public double getDefense() {
        return defense;
    }

}
