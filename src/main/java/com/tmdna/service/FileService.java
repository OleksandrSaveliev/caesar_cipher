package com.tmdna.service;

import model.FileSuffix;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class FileService {

    public List<String> readText(Path path) {
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public void writeText(Path path, List<String> text) {
        try {
            Files.write(path, text, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }

    public Path addSuffix(Path original, FileSuffix suffix) {

        String fileName = original.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');

        String baseName = (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);

        for (FileSuffix fs : FileSuffix.values()) {
            String s = fs.toString();
            if (baseName.endsWith(s)) {
                baseName = baseName.substring(0, baseName.length() - s.length());
                break;
            }
        }

        String newName = baseName + suffix + extension;
        return original.resolveSibling(newName);
    }
}

