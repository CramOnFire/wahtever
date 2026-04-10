package com.simplerpg.inventory;

// A decorator is applied to a weapon to enhance/reduce its damage or add special effects.
public class Weapon extends Item {
    protected int baseDamage;

    public Weapon(String name, int baseDamage) {
        super(name);
        this.baseDamage = baseDamage;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

}
