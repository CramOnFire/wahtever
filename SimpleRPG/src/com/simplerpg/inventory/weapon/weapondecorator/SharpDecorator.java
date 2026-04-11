package com.simplerpg.inventory.weapon.weapondecorator;
import com.simplerpg.inventory.Weapon;

public class SharpDecorator extends WeaponDecorator {
    public SharpDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Sharp " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() + 10;
        // Damage modifier can be changed later
    }
}