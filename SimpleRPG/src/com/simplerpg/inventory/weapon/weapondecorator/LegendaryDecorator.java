package com.simplerpg.inventory.weapon.weapondecorator;
import com.simplerpg.inventory.Weapon;

public class Legendary extends WeaponDecorator {
    public Legendary(Weapon weapon) {
        super(weapon);
        this.name = "Legendary " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 1.2;
        // Damage modifier can be changed later
    }
}