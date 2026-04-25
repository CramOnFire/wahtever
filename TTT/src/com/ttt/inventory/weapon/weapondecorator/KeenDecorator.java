package com.ttt.inventory.weapon.weapondecorator;
import com.ttt.inventory.Weapon;

public class KeenDecorator extends WeaponDecorator {
    public KeenDecorator(Weapon weapon) {
        super(weapon);
        this.name = "Keen " + weapon.getName();
        this.baseDamage = weapon.getBaseDamage() * 1.1;
    }
}