package com.tmdna.model;

import java.nio.file.Path;

public record ProgramOptions(Path filePath, Integer key, Command command) {
}
