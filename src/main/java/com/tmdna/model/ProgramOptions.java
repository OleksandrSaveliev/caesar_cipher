package com.tmdna.model;

import java.nio.file.Path;

public class ProgramOptions {
    private final Path filePath;
    private final Integer key;
    private final Command command;

    public ProgramOptions(Path filePath, Integer key, Command command) {
        this.filePath = filePath;
        this.key = key;
        this.command = command;
    }

    public Path getFilePath() {
        return filePath;
    }

    public Integer getKey() {
        return key;
    }

    public Command getCommand() {
        return command;
    }
}
