package com.tmdna.action;

import com.tmdna.model.ProgramOptions;
import com.tmdna.service.FileService;

public class BruteForceAction extends AbstractAction{
    private final FileService fileService;

    public BruteForceAction(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void execute(ProgramOptions options) {

    }
}
