package com.tmdna.utils;

import com.tmdna.model.Command;
import com.tmdna.model.ProgramOptions;

import java.nio.file.Path;

public class ProgramOptionsBuilder {

    private Path filePath;
    private Integer key;
    private Command command;

    private ProgramOptionsBuilder() {
    }

    public static ProgramOptionsBuilder create() {
        return new ProgramOptionsBuilder();
    }

    public ProgramOptionsBuilder withCommand(Command command) {
        this.command = command;
        return this;
    }

    public ProgramOptionsBuilder withFilePath(String filePathStr) {
        this.filePath = ProgramOptionsValidator.validateFilePath(filePathStr);
        return this;
    }

    public ProgramOptionsBuilder withKey(String keyStr) {
        this.key = ProgramOptionsValidator.validateKey(keyStr);
        return this;
    }

    public ProgramOptions build() {
        return new ProgramOptions(filePath, key, command);
    }
}
