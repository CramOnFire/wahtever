package com.simplerpg.entity;
import com.simplerpg.inventory.*;
import java.util.List;
import java.util.ArrayList;

public class Player extends Entity {
    int gold;
    List<Item> inventory = new ArrayList<>();
    Weapon equippedWeapon;
    Armor equippedArmor;
    
    public Player(String name) {
        super(name, 100, 100, 10, 0);
        this.gold = 0;
        this.equippedWeapon = null;
        this.equippedArmor = null;
    }
}
