package com.simplerpg.inventory.weapon.weapondecorator;
import com.simplerpg.inventory.Weapon;

public class DullDecorator extends WeaponDecorator {
    public DullDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Dull " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 0.9;
        // Damage modifier can be changed later
    }
}