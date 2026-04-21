package com.simplerpg.inventory.weapon.weapondecorator;
import com.simplerpg.inventory.Weapon;

public class BrokenDecorator extends WeaponDecorator {
    public BrokenDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Broken " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 0.20;
        // Damage modifier can be changed later
    }
}