package com.ttt.inventory.weapon.weapondecorator;
import com.ttt.inventory.Weapon;

public class LegendaryDecorator extends WeaponDecorator {
    public LegendaryDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Legendary " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 1.2;
    }
}