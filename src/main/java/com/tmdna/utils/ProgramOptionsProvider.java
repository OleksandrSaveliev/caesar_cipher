package com.tmdna.utils;

import com.tmdna.exceptions.IllegalArgumentsCountException;
import com.tmdna.model.Command;
import com.tmdna.model.ProgramOptions;
import com.tmdna.ui.Cli;

import java.io.IOException;


public class ProgramOptionsProvider {

    public ProgramOptions getFromArgs(String[] args) {
        Command command = ProgramOptionsValidator.validateCommand(args[0]);
        if (command == Command.ENCRYPT || command == Command.DECRYPT  && args.length != 3) {
            throw new IllegalArgumentsCountException("Invalid number of arguments. Expected 3 arguments: <command> <filePath> <key>");
        }
        if (command == Command.BRUTE_FORCE && args.length != 2) {
            throw new IllegalArgumentsCountException("Invalid number of arguments. Expected 2 arguments: <command> <filePath>");
        }
        String filePath = args[1];
        String key = command == Command.BRUTE_FORCE ? null : args[2];

        return getProgramOptions(command, filePath, key);
    }

    public ProgramOptions getFromCli(Cli cli) {
        Command command;
        String filePath = null;
        String key = null;

        while (true) {
            cli.printBaseMenu();

            try {
                command = cli.readCommand();

                if (command == Command.ENCRYPT || command == Command.DECRYPT) {
                    filePath = cli.readFilePath();
                    key = cli.readKey();
                } else if (command == Command.BRUTE_FORCE) {
                    filePath = cli.readFilePath();
                } else if (command == Command.INVALID) {
                    cli.printInvalidOption();
                    continue;
                }

                return getProgramOptions(command, filePath, key);
            } catch (IOException e) {
                throw new RuntimeException("I/O error reading from console: " + e.getMessage());
            }
        }
    }

    private ProgramOptions getProgramOptions(Command command, String filePath, String key) {
        return switch (command) {
            case Command.ENCRYPT, Command.DECRYPT -> ProgramOptionsBuilder.create()
                    .withCommand(command)
                    .withFilePath(filePath)
                    .withKey(key)
                    .build();
            case Command.BRUTE_FORCE -> ProgramOptionsBuilder.create()
                    .withCommand(command)
                    .withFilePath(filePath)
                    .build();
            case Command.EXIT -> ProgramOptionsBuilder.create()
                    .withCommand(command)
                    .build();
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }
}
