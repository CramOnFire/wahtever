package com.simplerpg.inventory;

// Define an abstract class for potions, which can be extended to create specific types of potions (e.g., health, mana, strength).
public abstract class Potion implements Item {
    String name;

    public Potion(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
