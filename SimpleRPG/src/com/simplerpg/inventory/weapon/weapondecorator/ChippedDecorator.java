package com.simplerpg.inventory.weapon.weapondecorator;
import com.simplerpg.inventory.Weapon;

public class ChippedDecorator extends WeaponDecorator {
    public ChippedDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Chipped " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 0.8;
        // Damage modifier can be changed later
    }
}