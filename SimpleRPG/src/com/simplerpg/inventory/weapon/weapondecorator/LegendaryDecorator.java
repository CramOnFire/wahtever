package com.simplerpg.inventory.weapon.weapondecorator;
import com.simplerpg.inventory.Weapon;

public class LegendaryDecorator extends WeaponDecorator {
    public LegendaryDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Legendary " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 1.2;
        // Damage modifier can be changed later
    }
}