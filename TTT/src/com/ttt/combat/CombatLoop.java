package com.ttt.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ttt.engine.GameEngine;
import com.ttt.entity.Player;
import com.ttt.entity.enemy.Enemy;
import com.ttt.inventory.InventoryMenu;
import com.ttt.inventory.Item;
import com.ttt.inventory.Potion;

public class CombatLoop {
    
    private final GameEngine engine;
    private final Random random = new Random();
    private final CombatAction attackAction = new AttackAction(CombatBalance.CHARGE_ATTACK_MULTIPLIER, CombatBalance.BLOCK_DAMAGE_MULTIPLIER);
    private final CombatAction blockAction = new BlockAction();
    private final CombatAction chargeAction = new ChargeAction();
    private final CombatAction fleeAction = new FleeAction(CombatBalance.FLEE_SUCCESS_CHANCE_PERCENT);

    public CombatLoop(GameEngine engine) {
        this.engine = engine;
    }

    public enum CombatResult {
        STAY_IN_AREA,
        EXIT_AREA,
        ENEMY_DEFEATED,
        PLAYER_DEAD
    }

    public interface EnemySpawner {
        Enemy spawn();
    }

    public CombatResult start(EnemySpawner spawner) {
        return start(spawner, true);
    }

    public CombatResult start(EnemySpawner spawner, boolean allowMultipleEnemies) {
        Player player = engine.getPlayer();
        CombatContext context = new CombatContext();

        Enemy enemy = spawner.spawn();

        while (player.getHealth() > 0) {
            if (enemy == null || enemy.getHealth() <= 0) {
                if (!allowMultipleEnemies) {
                    context.clearTemporaryBuffs();
                    return CombatResult.ENEMY_DEFEATED;
                }

                int postFightChoice = askPostFightChoice();
                if (postFightChoice == 2) {
                    context.clearTemporaryBuffs();
                    return CombatResult.EXIT_AREA;
                }

                enemy = spawner.spawn();
                continue;
            }

            printCombatHeader(player, enemy, context);

            CombatAction playerChoice = choosePlayerAction(player, context);
            CombatAction enemyChoice = chooseEnemyAction();

            boolean fleeSucceeded = resolveTurn(player, enemy, playerChoice, enemyChoice, context);

            if (fleeSucceeded) {
                context.clearTemporaryBuffs();
                return CombatResult.STAY_IN_AREA;
            }

            if (player.getHealth() <= 0) {
                context.clearTemporaryBuffs();
                return CombatResult.PLAYER_DEAD;
            }

            if (enemy.getHealth() <= 0) {
                System.out.println("You defeated " + enemy.getName() + "!");
                player.addGold(enemy.getGold());
                System.out.println("You gained " + enemy.getGold() + " gold.");
            }
        }

        context.clearTemporaryBuffs();
        return CombatResult.PLAYER_DEAD;
    }

    private void printCombatHeader(Player player, Enemy enemy, CombatContext context) {
        System.out.println("\n=== COMBAT ===");
        System.out.println(player.getName() + " HP: " + player.getHealth() + "/" + player.getMaxHealth());
        if (context.playerState.charged) {
            System.out.println("Player charge: READY");
        }
        if (context.strengthBuffTurnsRemaining > 0) {
            System.out.println("Player strength bonus: x" + (1.0 + context.temporaryStrengthMultiplier / 100.0) + " (" + context.strengthBuffTurnsRemaining + " turns remaining)");
        }
        if (context.defenseBuffTurnsRemaining > 0) {
            System.out.println("Player defense bonus: +" + context.temporaryDefenseBonus + " (" + context.defenseBuffTurnsRemaining + " turns remaining)");
        }

        System.out.println(enemy.getName() + " HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        if (context.enemyState.charged) {
            System.out.println(enemy.getName() + " charge: READY");
        }
        System.out.println();
    }

    private CombatAction choosePlayerAction(Player player, CombatContext context) {
        while (true) {
            System.out.println("Choose your action:");
            System.out.println("1. Attack");
            System.out.println("2. Block");
            System.out.println("3. Charge");
            System.out.println("4. Flee");
            System.out.println("5. Use Potion");
            System.out.println("6. Manage Equipment (free)");
            System.out.print("Choose: ");

            int choice = engine.getInput().getValidInt(1, 6);
            switch (choice) {
                case 1:
                    return attackAction;
                case 2:
                    return blockAction;
                case 3:
                    return chargeAction;
                case 4:
                    return fleeAction;
                case 5:
                    usePotionInCombat(player, context);
                    break;
                default: // case 6 — no turn consumed, loop back
                    new InventoryMenu(engine).show();
                    break;
            }
        }
    }

    private CombatAction chooseEnemyAction() {
        int roll = random.nextInt(3);
        if (roll == 0) {
            return attackAction;
        }
        if (roll == 1) {
            return blockAction;
        }
        return chargeAction;
    }

    private boolean resolveTurn(
        Player player,
        Enemy enemy,
        CombatAction playerChoice,
        CombatAction enemyChoice,
        CombatContext context
    ) {
        System.out.println("Actions locked in...");

        boolean playerBlocking = playerChoice instanceof BlockAction;
        boolean enemyBlocking = enemyChoice instanceof BlockAction;

        // Calculate attack multiplier from temporary strength buff
        double playerAttackMultiplier = 1.0;
        if (context.strengthBuffTurnsRemaining > 0) {
            playerAttackMultiplier = 1.0 + (context.temporaryStrengthMultiplier / 100.0);
        }

        // Use defense bonus only if remaining turns > 0
        int playerDefenseBonus = 0;
        if (context.defenseBuffTurnsRemaining > 0) {
            playerDefenseBonus = context.temporaryDefenseBonus;
        }

        ActionExecutionInput playerInput = new ActionExecutionInput(
            player,
            enemy,
            context.playerState.charged,
            enemyBlocking,
            playerAttackMultiplier,
            0,
            random
        );
        ActionExecutionInput enemyInput = new ActionExecutionInput(
            enemy,
            player,
            context.enemyState.charged,
            playerBlocking,
            1.0,  // enemy has no multiplier buff
            playerDefenseBonus,
            random
        );

        ActionResult playerResult = playerChoice.execute(playerInput);
        ActionResult enemyResult = enemyChoice.execute(enemyInput);

        // Check for flee BEFORE applying any effects or printing messages
        if (playerResult.isFleeSucceeded()) {
            System.out.println(playerResult.getMessage());
            return true;
        }

        // Print action messages
        if (playerResult.getMessage() != null && !playerResult.getMessage().isEmpty()) {
            System.out.println(playerResult.getMessage());
        }
        if (enemyResult.getMessage() != null && !enemyResult.getMessage().isEmpty()) {
            System.out.println(enemyResult.getMessage());
        }

        // Update charge states
        context.playerState.charged = playerResult.isGrantsCharge() || (context.playerState.charged && !playerResult.isConsumesCharge());
        context.enemyState.charged = enemyResult.isGrantsCharge() || (context.enemyState.charged && !enemyResult.isConsumesCharge());

        // Apply damage
        if (playerResult.getDamageToTarget() > 0) {
            enemy.takeDamage(playerResult.getDamageToTarget());
        }

        if (enemyResult.getDamageToTarget() > 0) {
            player.takeDamage(enemyResult.getDamageToTarget());
        }

        // Decrement buff durations
        if (playerResult.isConsumeStrengthBuff() && context.strengthBuffTurnsRemaining > 0) {
            context.strengthBuffTurnsRemaining--;
            if (context.strengthBuffTurnsRemaining == 0) {
                context.temporaryStrengthMultiplier = 0;
                System.out.println("Your strength boost has faded.");
            }
        }

        if (context.defenseBuffTurnsRemaining > 0) {
            context.defenseBuffTurnsRemaining--;
            if (context.defenseBuffTurnsRemaining == 0) {
                context.temporaryDefenseBonus = 0;
                System.out.println("Your defense bonus has faded.");
            }
        }

        return false;
    }

    private void usePotionInCombat(Player player, CombatContext context) {
        List<Potion> potions = new ArrayList<>();
        for (Item item : player.getInventory()) {
            if (item instanceof Potion) {
                potions.add((Potion) item);
            }
        }

        if (potions.isEmpty()) {
            System.out.println("You have no potions.");
            return;
        }

        System.out.println("\n--- POTIONS ---");
        for (int i = 0; i < potions.size(); i++) {
            Potion potion = potions.get(i);
            System.out.println((i + 1) + ". " + potion.getName() + " (" + potion.getEffectDescription() + ")");
        }

        System.out.println((potions.size() + 1) + ". Cancel");
        System.out.print("Choose potion: ");

        int choice = engine.getInput().getValidInt(1, potions.size() + 1);
        if (choice == potions.size() + 1) {
            return;
        }

        Potion potion = potions.get(choice - 1);
        potion.applyInCombat(player, context);
        player.getInventory().remove(potion);
    }

    private int askPostFightChoice() {
        System.out.println("\nThe enemy is gone.");
        System.out.println("1. Seek another enemy");
        System.out.println("2. Exit this area");
        System.out.print("Choose: ");
        return engine.getInput().getValidInt(1, 2);
    }
}