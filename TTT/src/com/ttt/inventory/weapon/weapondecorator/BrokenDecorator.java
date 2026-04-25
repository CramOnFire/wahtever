package com.ttt.inventory.weapon.weapondecorator;
import com.ttt.inventory.Weapon;

public class BrokenDecorator extends WeaponDecorator {
    public BrokenDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Broken " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 0.20;
    }
}