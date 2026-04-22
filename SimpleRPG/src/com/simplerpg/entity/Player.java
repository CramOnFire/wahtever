package com.simplerpg.entity;

import com.simplerpg.inventory.*;
import java.util.List;
import java.util.ArrayList;

public class Player extends Entity {
    List<Item> inventory = new ArrayList<>();
    Weapon equippedWeapon;
    Armor equippedArmor;
    
    public Player(String name) {
        super(name, 100, 10, 0);
        this.equippedWeapon = null;
        this.equippedArmor = null;
    }

    public void equipWeapon(Weapon weapon) {
        try {
            if (inventory.contains(weapon)) {
                this.equippedWeapon = weapon;
                System.out.println("Equipped weapon: " + weapon.getName());
            } else {
                System.out.println("You don't have that weapon in your inventory.");
            }
        } catch (Exception e) {
            System.out.println("Error equipping weapon: " + e.getMessage());
        }
    }

    public void unequipWeapon() {
        if (this.equippedWeapon != null) {
            System.out.println("Unequipped weapon: " + this.equippedWeapon.getName());
            this.equippedWeapon = null;
        } else {
            System.out.println("No weapon is currently equipped.");
        }
    }

    public void equipArmor(Armor armor) {
        try {
            if (inventory.contains(armor)) {
                this.equippedArmor = armor;
                System.out.println("Equipped armor: " + armor.getName());
            } else {
                System.out.println("You don't have that armor in your inventory.");
            }
        } catch (Exception e) {
            System.out.println("Error equipping armor: " + e.getMessage());
        }
    }

    public void unequipArmor() {
        if (this.equippedArmor != null) {
            System.out.println("Unequipped armor: " + this.equippedArmor.getName());
            this.equippedArmor = null;
        } else {
            System.out.println("No armor is currently equipped.");
        }
    }

    // Waiting for potion implementation to be completed before implementing this method, as the use() method of the Potion class is currently abstract and not defined.
    public void usePotion(Potion potion) {
        try {
            if (inventory.contains(potion)) {
                potion.use();
                inventory.remove(potion);
                System.out.println("Used potion: " + potion.getName());
            } else {
                System.out.println("You don't have that potion in your inventory.");
            }
        } catch (Exception e) {
            System.out.println("Error using potion: " + e.getMessage());
        }
    }

    public List<Item> getInventory() { return inventory; }

    public Weapon getEquippedWeapon() { return equippedWeapon; }
    public void setEquippedWeapon(Weapon weapon) { this.equippedWeapon = weapon; }

    public Armor getEquippedArmor() { return equippedArmor; }
    public void setEquippedArmor(Armor armor) { this.equippedArmor = armor; }

    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
    }
}
