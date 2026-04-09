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

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
            // Handle death if needed
        }
    }

    public abstract boolean isAlive();
}
