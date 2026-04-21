package com.simplerpg.inventory.weapon.weapondecorator;
import com.simplerpg.inventory.Weapon;

public class KeenDecorator extends WeaponDecorator {
    public KeenDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Keen " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 1.1;
        // Damage modifier can be changed later
    }
}