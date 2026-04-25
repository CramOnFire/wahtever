package com.ttt.market;
 
import com.ttt.area.Area;
import com.ttt.area.TownArea;
import com.ttt.engine.*;
import com.ttt.entity.Player;
 
public class Market implements Area{
 
    private GameEngine engine;
 
    public Market(GameEngine engine) {
        this.engine = engine;
    }
 
    @Override
    public int showMenu() {
        System.out.println("\n=== MARKET ===");
        System.out.printf("Gold: %d%n", engine.getPlayer().getGold());
        System.out.println("1. Blacksmith");
        System.out.println("2. Apothecary");
        System.out.println("3. Exit Market");
        System.out.print("Choose: ");
        return 3;
    }
 
    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                engine.setArea(new Blacksmith(engine));
                break;
 
            case 2:
                engine.setArea(new Apothecary(engine));
                break;
 
            case 3:
                engine.setArea(new TownArea(engine));
                break;
        }
    }
}