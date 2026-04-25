package com.ttt.market.stock;

import java.util.ArrayList;
import java.util.List;

import com.ttt.inventory.HealthPotion;
import com.ttt.inventory.Potion;
import com.ttt.inventory.StrengthPotion;
import com.ttt.inventory.ToughPotion;

public class PotionStock {

    private final List<ShopItem> items = new ArrayList<>();

    public PotionStock() {
        items.add(new ShopItem("Lesser Health Potion", 25,  50));
        items.add(new ShopItem("Health Potion",       50,  120));
        items.add(new ShopItem("Greater Health Potion", 100, 220));
        items.add(new ShopItem("Strength Potion", 20, 140));
        items.add(new ShopItem("Tough Potion", 15, 100));
    }

    public List<ShopItem> getItems() { return items; }

    public Potion create(int idx) {
        ShopItem s = items.get(idx);
        if (idx == 3) {
            return new StrengthPotion(s.getName(), (int) s.getStat());
        }
        if (idx == 4) {
            return new ToughPotion(s.getName(), (int) s.getStat());
        }
        return new HealthPotion(s.getName(), (int) s.getStat());
    }
}