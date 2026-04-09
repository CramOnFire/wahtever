package com.simplerpg.inventory;

import com.simplerpg.entity.Entity;

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

    public abstract void use(Entity entity); // Each potion will have its own implementation of the use method, which will define what happens when the potion is consumed.
}
