package com.simplerpg.market.stock;

import com.simplerpg.inventory.Armor;
import com.simplerpg.inventory.armor.LeatherArmor;
import com.simplerpg.inventory.armor.ChainmailArmor;
import com.simplerpg.inventory.armor.KnightArmor;
import com.simplerpg.inventory.armor.DragonHunterArmor;

import java.util.ArrayList;
import java.util.List;

public class ArmorStock {

    private final List<ShopItem> items = new ArrayList<>();
    private final boolean[] sold;

    public ArmorStock() {
        items.add(new ShopItem("Leather Armor",       5,   80));
        items.add(new ShopItem("Chainmail Armor",     15,  200));
        items.add(new ShopItem("Knight Armor",        40,  450));
        items.add(new ShopItem("Dragon Hunter Armor", 100, 950));
        sold = new boolean[items.size()];
    }

    public List<ShopItem> getItems() { return items; }
    public boolean isSold(int idx)   { return sold[idx]; }
    public void    markSold(int idx) { sold[idx] = true; }

    public Armor create(int idx) {
        switch (idx) {
            case 0:  return new LeatherArmor();
            case 1:  return new ChainmailArmor();
            case 2:  return new KnightArmor();
            default: return new DragonHunterArmor();
        }
    }
}
