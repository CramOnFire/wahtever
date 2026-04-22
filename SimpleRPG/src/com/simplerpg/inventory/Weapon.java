package com.simplerpg.inventory;

// A decorator is applied to a weapon to enhance/reduce its damage or add special effects.
public class Weapon extends Item {
    protected double baseDamage;

    public Weapon(String name, double baseDamage) {
        super(name);
        this.baseDamage = baseDamage;
    }

    public double getBaseDamage() {
        return baseDamage;
    }

}
