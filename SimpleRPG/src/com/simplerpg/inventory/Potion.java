package com.simplerpg.inventory;

// Define an abstract class for potions, which can be extended to create specific types of potions (e.g., health, mana, strength).
public abstract class Potion extends Item {

    public Potion(String name) {
        super(name);
    }

    // To be implemented
    public abstract void use();
}
