package com.simplerpg.inventory;

// No decorators for armor yet, but we can easily add them later if we want to add special effects like fire resistance, etc.
public class Armor implements Item {
    String name;
    int defense;

    public Armor(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getDefense() {
        return defense;
    }

}
