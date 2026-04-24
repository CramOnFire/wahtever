package com.simplerpg.entity;

public abstract class Entity {
    private String name;
    private int health;
    private int maxHealth;
    private final int baseAttack;
    private final int baseDefense;
    private int gold; // For the player, this represents the amount of gold they have. For enemies, this can represent the amount of gold they drop when defeated.

    public Entity(String name, int maxHealth, int baseAttack, int baseDefense, int gold) {
        this.name = name;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.gold = gold;
    }

    // Overloaded constructor for entities that don't have gold (like certain enemies or the player at the start of the game)
    public Entity(String name, int maxHealth, int baseAttack, int baseDefense) {
        this(name, maxHealth, baseAttack, baseDefense, 0);
    }

    // To modify to account for defense and other factors later
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
            // When an enemy dies, we can add logic here to drop loot, give experience, etc.
            // When the player dies, we can add logic here to end the game, show a game over screen, etc.
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(health, maxHealth));
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        if (maxHealth < 1) {
            throw new IllegalArgumentException("maxHealth must be at least 1.");
        }
        this.maxHealth = maxHealth;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void deductGold(int amount) {
        try {
            if (amount > gold) {
                throw new IllegalArgumentException("Not enough gold to deduct.");
            }
            gold -= amount;
        } catch (IllegalArgumentException e) {
            System.out.println("Error deducting gold: " + e.getMessage());
        }
    }
}