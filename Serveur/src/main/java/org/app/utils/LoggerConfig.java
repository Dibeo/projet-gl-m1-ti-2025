package org.app.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LoggerConfig {

    /**
     * Initializes the global logger configuration for the project.
     * @param consoleLogging true to enable console output, false otherwise
     */
    public static void setup(boolean consoleLogging) {
        try {
            // Reset default handlers to avoid duplicate logging
            LogManager.getLogManager().reset();

            // Create logs directory if it doesn't exist
            File logDir = new File("logs");
            if (!logDir.exists()) logDir.mkdirs();

            // Generate a timestamped log file name
            String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
            String logFileName = "logs/" + timestamp + ".log";

            // File handler (always active)
            FileHandler fileHandler = new FileHandler(logFileName, true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());

            // Root logger
            Logger rootLogger = Logger.getLogger("");
            rootLogger.addHandler(fileHandler);
            rootLogger.setLevel(Level.ALL);

            // Optional console handler
            if (consoleLogging) {
                ConsoleHandler consoleHandler = new ConsoleHandler();
                consoleHandler.setLevel(Level.INFO);
                consoleHandler.setFormatter(new SimpleFormatter());
                rootLogger.addHandler(consoleHandler);

                Logger.getLogger(LoggerConfig.class.getName())
                        .info("Console output enabled (--logs or -l detected)");
            } else {
                System.out.println("Console output disabled (use --logs or -l to enable)");
            }

            Logger.getLogger(LoggerConfig.class.getName())
                    .info("Global logger initialized. Log file: " + logFileName);

        } catch (IOException e) {
            Logger.getLogger(LoggerConfig.class.getName())
                    .log(Level.SEVERE, "Error initializing logger", e);
        }
    }
}
