package com.tmdna.utils;

import com.tmdna.model.Command;
import com.tmdna.model.ProgramOptions;


public class ProgramOptionsProvider {

    private ProgramOptionsProvider() {
    }

    public static ProgramOptions getFromArgs(String[] args) {
        Command command = ProgramOptionsValidator.validateCommand(args[0]);
        String filePath = args[1];
        String key = args[2];

        return getProgramOptions(command, filePath, key);
    }

    public static ProgramOptions getFromParams(Command command, String filePath, String key) {
        return getProgramOptions(command, filePath, key);
    }

    private static ProgramOptions getProgramOptions(Command command, String filePath, String key) {
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
