package com.simplerpg.market.stock;

import com.simplerpg.inventory.HealthPotion;
import com.simplerpg.inventory.Potion;
import com.simplerpg.inventory.StrengthPotion;
import com.simplerpg.inventory.ToughPotion;

import java.util.ArrayList;
import java.util.List;

public class PotionStock {

    private final List<ShopItem> items = new ArrayList<>();

    public PotionStock() {
        items.add(new ShopItem("Lesser Health Potion", 30,  50));
        items.add(new ShopItem("Health Potion",       75,  120));
        items.add(new ShopItem("Greater Health Potion", 150, 220));
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
