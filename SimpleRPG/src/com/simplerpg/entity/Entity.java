package com.simplerpg.entity;

public abstract class Entity {
    String name;
    int health;
    int maxHealth;
    int baseAttack;
    int baseDefense;
    int gold; // For the player, this represents the amount of gold they have. For enemies, this can represent the amount of gold they drop when defeated.

    public Entity(String name, int maxHealth, int baseAttack, int baseDefense) {
        this.name = name;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.gold = 0; // Default gold value, can be overridden in subclasses.
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

    public void setGold(int gold) {
        this.gold = gold;
    }
}
