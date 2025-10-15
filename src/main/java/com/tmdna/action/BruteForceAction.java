package com.tmdna.action;

import com.tmdna.model.FileSuffix;
import com.tmdna.model.ProgramOptions;
import com.tmdna.service.CaesarCipher;
import com.tmdna.service.FileService;

import java.nio.file.Path;
import java.util.List;

public class BruteForceAction extends AbstractAction{

    public BruteForceAction(FileService fileService) {
        super(fileService);
    }

    @Override
    public void execute(ProgramOptions options) {
        Path filePath = options.filePath();

        CaesarCipher cipher = new CaesarCipher();
        List<String> source = fileService.readText(filePath);
        List<String> result = cipher.decrypt(source);

        Path newFilePath = fileService.addSuffix(filePath, FileSuffix.DECRYPTED);
        fileService.writeText(newFilePath, result);

        System.out.println("File decrypted using brute-force successfully: " + newFilePath);
    }
}
