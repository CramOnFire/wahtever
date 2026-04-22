package com.simplerpg.market.stock;

import com.simplerpg.inventory.HealthPotion;

import java.util.ArrayList;
import java.util.List;

public class PotionStock {

    private final List<ShopItem> items = new ArrayList<>();

    public PotionStock() {
        items.add(new ShopItem("Small Health Potion", 30,  50));
        items.add(new ShopItem("Health Potion",       75,  120));
        items.add(new ShopItem("Large Health Potion", 150, 220));
    }

    public List<ShopItem> getItems() { return items; }

    public HealthPotion create(int idx) {
        ShopItem s = items.get(idx);
        return new HealthPotion(s.getName(), (int) s.getStat());
    }
}
