package git.projetgl;

import git.projetgl.utils.LoggerConfig;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        boolean consoleLogging = false;
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--logs") || arg.equalsIgnoreCase("-l")) {
                consoleLogging = true;
                break;
            }
        }

        // Initialize global logger
        LoggerConfig.setup(consoleLogging);

        // Example logs
        LOGGER.info("Application started");
        LOGGER.warning("This is a warning message");
        LOGGER.severe("This is a critical error!");

    }
}
