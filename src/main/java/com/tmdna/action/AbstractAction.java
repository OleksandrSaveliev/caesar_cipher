package com.tmdna.action;

import com.tmdna.model.ProgramOptions;
import com.tmdna.service.FileService;

public abstract class AbstractAction {
    protected final FileService fileService;

    protected AbstractAction() {
        this.fileService = null;
    }

    protected AbstractAction (FileService fileService) {
        this.fileService = fileService;
    }

    public abstract void execute(ProgramOptions options);
}
