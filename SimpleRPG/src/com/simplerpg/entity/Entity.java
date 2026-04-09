package com.simplerpg.entity;

public abstract class Entity {
    String name;
    int health;
    int maxHealth;
    int baseAttack;
    int baseDefense;

    public Entity(String name, int health, int maxHealth, int baseAttack, int baseDefense) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
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
}
