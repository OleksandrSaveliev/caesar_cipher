package com.tmdna.action;

import com.tmdna.model.FileSuffix;
import com.tmdna.model.ProgramOptions;
import com.tmdna.service.CaesarCipher;
import com.tmdna.service.FileService;

import java.nio.file.Path;
import java.util.List;

public class DecryptAction extends AbstractAction {

    public DecryptAction(FileService fileService) {
        super(fileService);
    }

    @Override
    public void execute(ProgramOptions options) {
        Path filePath = options.filePath();
        int key = options.key();

        CaesarCipher cipher = new CaesarCipher(key);
        List<String> source = fileService.readText(filePath);
        List<String> result = cipher.decrypt(source);

        Path newFilePath = fileService.addSuffix(filePath, FileSuffix.DECRYPTED);
        fileService.writeText(newFilePath, result);

        System.out.println("File decrypted successfully: " + newFilePath);
    }
}