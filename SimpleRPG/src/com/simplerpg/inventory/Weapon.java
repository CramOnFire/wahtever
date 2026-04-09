package com.simplerpg.inventory;

// A decorator is applied to a weapon to enhance/reduce its damage or add special effects.
public class Weapon implements Item {
    String name;
    int baseDamage;

    public Weapon(String name, int baseDamage) {
        this.name = name;
        this.baseDamage = baseDamage;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

}
