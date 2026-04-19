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
                    return input;
                } else {
                    System.out.print("Enter number between " + min + " and " + max + ": ");
                }

            } catch (Exception e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
}