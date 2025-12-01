package git.projetgl.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LoggerConfig {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    public static void setup(boolean consoleLogging) {
        try {
            LogManager.getLogManager().reset();

            File logDir = new File("logs");
            if (!logDir.exists() && !logDir.mkdirs()) throw new IOException("Could not create logs directory: " + logDir.getAbsolutePath());

            String logFile = "logs/" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".log";

            FileHandler fileHandler = new FileHandler(logFile, true);
            fileHandler.setFormatter(new SimpleFormatter());

            Logger rootLogger = Logger.getLogger("");
            rootLogger.addHandler(fileHandler);
            rootLogger.setLevel(Level.ALL);

            if (consoleLogging) {
                ConsoleHandler consoleHandler = getConsoleHandler();
                rootLogger.addHandler(consoleHandler);
            }
            Logger.getLogger(LoggerConfig.class.getName()).info("Logger initialized. Console: " + consoleLogging + ", file: " + logFile);

        } catch (IOException e) {
            System.err.println("Error when setting up Logger config.");
        }
    }

    private static ConsoleHandler getConsoleHandler() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                String color = switch (record.getLevel().getName()) {
                    case "SEVERE" -> RED;
                    case "WARNING", "WARN" -> YELLOW;
                    default -> BLUE;
                };
                return color + "[" + record.getLevel() + "] " + record.getMessage() + RESET + "\n";
            }
        });
        consoleHandler.setLevel(Level.ALL);
        return consoleHandler;
    }
}
