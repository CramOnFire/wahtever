package com.ttt.market.stock;

import java.util.ArrayList;
import java.util.List;

import com.ttt.inventory.Armor;
import com.ttt.inventory.armor.ChainmailArmor;
import com.ttt.inventory.armor.DragonHunterArmor;
import com.ttt.inventory.armor.KnightArmor;
import com.ttt.inventory.armor.LeatherArmor;

public class ArmorStock {

    private final List<ShopItem> items = new ArrayList<>();
    private final boolean[] sold;

    public ArmorStock() {
        items.add(new ShopItem("Leather Armor",       5,   80));
        items.add(new ShopItem("Chainmail Armor",     15,  150));
        items.add(new ShopItem("Knight Armor",        40,  350));
        items.add(new ShopItem("Dragon Hunter Armor", 100, 700));
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