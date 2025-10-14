package com.tmdna.app;

import com.tmdna.action.AbstractAction;
import com.tmdna.action.BruteForceAction;
import com.tmdna.action.DecryptAction;
import com.tmdna.action.EncryptAction;
import com.tmdna.model.Command;
import com.tmdna.model.ProgramOptions;
import com.tmdna.service.FileService;
import com.tmdna.ui.Cli;
import com.tmdna.utils.ArgsParser;

import java.util.Map;

public class ApplicationRunner {

    public static void run(String[] args) {
        ProgramOptions options;

        if (args.length > 0) {
            options = new ArgsParser(args).getProgramOptions();
        } else {
            options = new Cli().getProgramOptions();
        }

        FileService fileService = new FileService();

        Map<Command, AbstractAction> actions = Map.of(
                Command.ENCRYPT, new EncryptAction(fileService),
                Command.DECRYPT, new DecryptAction(fileService),
                Command.BRUTE_FORCE, new BruteForceAction(fileService)
        );

        Command command = options.command();

        if (command == Command.EXIT) {
            System.out.println("Goodbye!");
            return;
        }

        AbstractAction action = actions.get(command);

        action.execute(options);
    }

}
