package com.tmdna.utils;

import com.tmdna.model.Command;
import com.tmdna.exceptions.IllegalCommandTypeException;
import com.tmdna.exceptions.IllegalFilePathException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProgramOptionsValidator {

    private ProgramOptionsValidator() {
    }

    public static Command validateCommand(String command) {
        try {
            return Command.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new IllegalCommandTypeException(
                    "Unexpected command type: " + command + ". Expected ENCRYPT, DECRYPT or BRUTE_FORCE."
            );
        }
    }

    public static Path validateFilePath(String path) {
        if (path == null)  {
            throw new IllegalFilePathException("NULL as Path allowed only with EXIT command.");
        }
        try {
            Path filePath = Paths.get(path);

            if (!Files.exists(filePath)) {
                throw new IllegalFilePathException("File does not exist: " + filePath);
            }

            return filePath;
        } catch (InvalidPathException e) {
            throw new IllegalFilePathException("Invalid file path format: " + path);
        }
    }

    public static Integer validateKey(String key) {
        if (key == null)  {
            throw new IllegalArgumentException("NULL as Key allowed only with BRUTE_FORCE and EXIT commands.");
        }
        try {
            return Integer.valueOf(key);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Key must be an integer: " + key);
        }
    }
}
