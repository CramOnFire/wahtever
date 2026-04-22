package com.simplerpg;
import com.simplerpg.engine.GameEngine;

public class Main {
    public static void main(String[] args) throws Exception {
        GameEngine game = new GameEngine();
        game.run();
    }
}

// cd ~/newwok/SimpleRPG/src
// javac -d . com/simplerpg/Main.java com/simplerpg/**/*.java com/simplerpg/*.java
// java com.simplerpg.Main
// for running the game