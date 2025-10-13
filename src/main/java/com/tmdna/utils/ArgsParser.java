package com.tmdna.utils;

import com.tmdna.model.Command;
import com.tmdna.exceptions.IllegalArgumentsCountException;
import com.tmdna.model.ProgramOptions;

import java.nio.file.Path;

public class ArgsParser {
    private final String[] args;

    public ArgsParser(String[] args) {
        this.args = args;
        if (args.length > 3 || args.length < 2) {
            throw new IllegalArgumentsCountException("Unexpectable args count: " + args.length + " .Must be 2 or 3");
        }
    }

    public ProgramOptions getProgramOptions() {
        Command command = ProgramOptionsValidator.validateCommand(args[0]);
        Path filePath = ProgramOptionsValidator.validateFilePath(args[1]);
        Integer key = command == Command.BRUTE_FORCE ? null : ProgramOptionsValidator.validateKey(args[2]);
        return new ProgramOptions(filePath, key, command);
    }
}
