package com.simplerpg.inventory;

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
