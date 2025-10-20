package com.tmdna.ui;

import com.tmdna.model.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cli {
    private static final String FILE_PATH_MSG = "Enter the file path: ";
    private static final String KEY_MSG = "Enter the key: ";
    private static final String INVALID_OPTION_MSG = "Invalid option, try again.";

    final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    public Command readCommand() throws IOException {
        int choice = Integer.parseInt(console.readLine());
        return switch (choice) {
            case 1 -> Command.ENCRYPT;
            case 2 -> Command.DECRYPT;
            case 3 -> Command.BRUTE_FORCE;
            case 4 -> Command.EXIT;
            default -> Command.INVALID;
        };
    }

    public String readKey() throws IOException {
        return ask(KEY_MSG);
    }

    public String readFilePath() throws IOException {
        return ask(FILE_PATH_MSG);
    }

    private String ask(String message) throws IOException {
        System.out.println(message);
        return console.readLine();
    }

    public void printBaseMenu() {
        System.out.println("Select an action:");
        System.out.println("1. Encrypt file.");
        System.out.println("2. Decrypt file.");
        System.out.println("3. Brut-force file.");
        System.out.println("4. Exit.");
    }

    public void printInvalidOption() {
        System.out.println("--------------------------");
        System.out.println(INVALID_OPTION_MSG);
        System.out.println("--------------------------");
    }
}
