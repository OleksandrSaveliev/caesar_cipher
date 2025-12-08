package com.tmdna.app;

import com.tmdna.action.*;
import com.tmdna.model.Command;
import com.tmdna.model.ProgramOptions;
import com.tmdna.service.FileService;
import com.tmdna.ui.Cli;
import com.tmdna.utils.ProgramOptionsProvider;

public class ApplicationRunner {

    private ApplicationRunner() {
    }

    public static void run(String[] args) {
        ProgramOptionsProvider provider = new ProgramOptionsProvider();

        ProgramOptions options = (args.length > 0)
                ? provider.getFromArgs(args)
                : provider.getFromCli(new Cli());

        FileService fileService = new FileService();

        Command command = options.command();

        AbstractAction action = switch (command) {
            case ENCRYPT -> new EncryptAction(fileService);
            case DECRYPT -> new DecryptAction(fileService);
            case BRUTE_FORCE -> new BruteForceAction(fileService);
            case EXIT -> new ExitAction();
            default -> throw new IllegalStateException("Unexpected command: " + command);
        };

        action.execute(options);
    }

}
