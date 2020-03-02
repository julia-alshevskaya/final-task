package com.alshevskaya.cleaningcompany.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ActionFactory {
    private static final Logger logger = LogManager.getLogger();

    public static Optional<Command> defineCommand(String commandName) {
        Optional<Command> current = Optional.empty();
        if (commandName == null) {
            return current;
        }
        try {
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = Optional.of(type.getCurrentCommand());
        } catch (EnumConstantNotPresentException | IllegalArgumentException e) {
            logger.log(Level.ERROR, "Can't define command", e);
        }
        return current;
    }
}