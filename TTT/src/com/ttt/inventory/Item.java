package com.ttt.inventory;

public abstract class Item {
    protected String name;

    protected Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}