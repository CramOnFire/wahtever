package com.ttt.market.stock;

import java.util.ArrayList;
import java.util.List;

import com.ttt.inventory.Weapon;
import com.ttt.inventory.weapon.IronSword;
import com.ttt.inventory.weapon.KnightsLongsword;
import com.ttt.inventory.weapon.RoyalGreatsword;

public class WeaponStock {

    private final List<ShopItem> items = new ArrayList<>();
    private final boolean[] sold;

    public WeaponStock() {
        items.add(new ShopItem("Iron Sword",          15,  100));
        items.add(new ShopItem("Knight's Longsword", 40,  250));
        items.add(new ShopItem("Royal Greatsword",    100, 600));
        sold = new boolean[items.size()];
    }

    public List<ShopItem> getItems() { return items; }
    public boolean isSold(int idx)   { return sold[idx]; }
    public void    markSold(int idx) { sold[idx] = true; }

    public Weapon create(int idx) {
        switch (idx) {
            case 0:  return new IronSword();
            case 1:  return new KnightsLongsword();
            default: return new RoyalGreatsword();
        }
    }
}