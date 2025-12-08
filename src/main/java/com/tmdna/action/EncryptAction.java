package com.tmdna.action;

import com.tmdna.model.FileSuffix;
import com.tmdna.model.ProgramOptions;
import com.tmdna.service.CaesarCipher;
import com.tmdna.service.FileService;

import java.nio.file.Path;
import java.util.List;

public class EncryptAction extends AbstractAction {

    public EncryptAction(FileService fileService) {
        super(fileService);
    }

    @Override
    public void execute(ProgramOptions options) {
        Path filePath = options.filePath();
        int key = options.key();

        CaesarCipher cipher = new CaesarCipher(key);
        List<String> source = fileService.readText(filePath);
        List<String> result = cipher.encrypt(source);

        Path newFilePath = fileService.addSuffix(filePath, FileSuffix.ENCRYPTED);
        fileService.writeText(newFilePath, result);

        System.out.println("File encrypted successfully: " + newFilePath);
    }
}

