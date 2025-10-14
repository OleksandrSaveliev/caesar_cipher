package com.tmdna.app;

import com.tmdna.action.AbstractAction;
import com.tmdna.action.BruteForceAction;
import com.tmdna.action.DecryptAction;
import com.tmdna.action.EncryptAction;
import com.tmdna.model.Command;
import com.tmdna.model.ProgramOptions;
import com.tmdna.service.FileService;
import com.tmdna.ui.Cli;
import com.tmdna.utils.ProgramOptionsProvider;

public class ApplicationRunner {

    private ApplicationRunner() {
    }

    public static void run(String[] args) {
        ProgramOptions options = (args.length > 0)
                ? ProgramOptionsProvider.getFromArgs(args)
                : new Cli().getProgramOptions();

        FileService fileService = new FileService();

        Command command = options.command();

        if (command == Command.EXIT) {
            System.out.println("Goodbye!");
            return;
        }

        AbstractAction action = switch (command) {
            case ENCRYPT -> new EncryptAction(fileService);
            case DECRYPT -> new DecryptAction(fileService);
            case BRUTE_FORCE -> new BruteForceAction(fileService);
            default -> throw new IllegalStateException("Unexpected command: " + command);
        };

        action.execute(options);
    }

}
