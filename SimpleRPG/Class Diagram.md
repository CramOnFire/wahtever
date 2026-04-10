
| Main |
| ---- |
|      |
|      |

| Game |
| ---- |
|      |
|      |
## Inventory
| abstract class Item      |
| ----------------------- |
| ~ name: String          |
| + Item(String name): Item<br>+ getName(): String |


| Armor extends Item                                                                     |
| ---------------------------------------------------------------------------------------- |
| ~ defense: int                                                                         |
| + Armor(String name, int defense): Armor<br>+ getDefense(): int                         |

| abstract Potion extends Item                                                                  |
| ---------------------------------------------------------------------------------------------- |
|                                                                                              |
| + Potion(String name): Potion<br>+ abstract use(): void                           |

| Weapon extends Item                                                                            |
| ------------------------------------------------------------------------------------------------ |
| ~ baseDamage: int                                                                                |
| + Weapon(String name, int baseDamage): Weapon<br>+ getBaseDamage(): int                         |
## Entity

| abstract Entity                                                                                                                                 |
| ----------------------------------------------------------------------------------------------------------------------------------------------- |
| ~ name: String<br>~ health: int<br>~ maxHealth: int<br>~ baseAttack: int<br>~ baseDefense: int<br>~ gold: int                                   |
| \+ Entity(String name, int maxHealth, int baseAttack, int baseDefense): Entity (defaults gold = 0)<br>\+ Entity(String name, int maxHealth, int baseAttack, int baseDefense, int gold): Entity<br>\+ takeDamage(int damage): void<br>\+ getGold(): gold<br>\+ addGold(int amount): void<br>\+ deductGold(int amount): void|

| Player extends Entity                                                                                                                                                                                 |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| ~ inventory: List\<Item><br>~ equippedWeapon: Weapon<br>~ equippedArmor: Armor                                                                                                                        |
| \+ Player(String name): Player<br>\+ equipWeapon(Weapon weapon): void<br>\+ unequipWeapon(): void<br>\+ equipArmor(Armor armor): void<br>\+ unequipArmor(): void<br>\+ usePotion(Potion potion): void |

| abstract Enemy extends Entity                                                          |
| -------------------------------------------------------------------------------------- |
|                                                                                        |
| \+ Enemy(String name, int maxHealth, int baseAttack, int baseDefense, int gold): Enemy |

| Slime extends Enemy |
| ------------------- |
|                     |
| + Slime(): Slime (drops 2 gold)    |
## Combat


| interface CombatAction                                 |
| ------------------------------------------------------ |
|                                                        |
| ~ execute(Entity firstParty, Entity secondParty): void |

| AttackAction implements CombatAction                    |
| ------------------------------------------------------- |
|                                                         |
| \+ execute(Entity firstParty, Entity secondParty): void |

| BlockAction implements CombatAction                     |
| ------------------------------------------------------- |
|                                                         |
| \+ execute(Entity firstParty, Entity secondParty): void |

| FleeAction implements CombatAction                      |
| ------------------------------------------------------- |
|                                                         |
| \+ execute(Entity firstParty, Entity secondParty): void |