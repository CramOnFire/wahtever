package com.ttt.inventory.weapon.weapondecorator;
import com.ttt.inventory.Weapon;

public class SharpDecorator extends WeaponDecorator {
    public SharpDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Sharp " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 1.05;
    }
}