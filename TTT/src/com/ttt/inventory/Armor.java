package com.ttt.inventory;

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