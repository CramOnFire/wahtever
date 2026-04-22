package com.simplerpg.market.stock;

import com.simplerpg.inventory.Weapon;
import com.simplerpg.inventory.weapon.IronSword;
import com.simplerpg.inventory.weapon.KnightsLongSword;
import com.simplerpg.inventory.weapon.RoyalGreatsword;

import java.util.ArrayList;
import java.util.List;

public class WeaponStock {

    private final List<ShopItem> items = new ArrayList<>();
    private final boolean[] sold;

    public WeaponStock() {
        items.add(new ShopItem("Iron Sword",          15,  100));
        items.add(new ShopItem("Knight's Long Sword", 40,  350));
        items.add(new ShopItem("Royal Greatsword",    100, 900));
        sold = new boolean[items.size()];
    }

    public List<ShopItem> getItems() { return items; }
    public boolean isSold(int idx)   { return sold[idx]; }
    public void    markSold(int idx) { sold[idx] = true; }

    public Weapon create(int idx) {
        switch (idx) {
            case 0:  return new IronSword();
            case 1:  return new KnightsLongSword();
            default: return new RoyalGreatsword();
        }
    }
}
