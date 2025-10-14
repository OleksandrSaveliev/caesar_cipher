package com.tmdna.action;

import com.tmdna.model.ProgramOptions;

public class ExitAction extends AbstractAction{
    @Override
    public void execute(ProgramOptions options) {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
