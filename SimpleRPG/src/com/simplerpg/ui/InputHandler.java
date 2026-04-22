package com.simplerpg.ui;

import java.util.Scanner;

public class InputHandler {

    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public int getValidInt(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());

                if (input >= min && input <= max) {
                    clearScreen();
                    return input;
                } else {
                    System.out.print("Enter number between " + min + " and " + max + ": ");
                }

            } catch (Exception e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // fallback: print blank lines
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
}