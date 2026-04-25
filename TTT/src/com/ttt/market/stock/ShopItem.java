package com.ttt.market.stock;

public class ShopItem {
    private final String name;
    private final double stat;   // baseDamage for weapons, defense for armors, healAmount for potions
    private final int price;

    public ShopItem(String name, double stat, int price) {
        this.name  = name;
        this.stat  = stat;
        this.price = price;
    }

    public String getName()  { return name; }
    public double getStat()  { return stat; }
    public int    getPrice() { return price; }
}