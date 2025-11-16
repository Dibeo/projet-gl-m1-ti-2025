package git.projetgl.initializer;

import git.projetgl.config.AppConfig;
import git.projetgl.database.service.DatabaseInitializer;
import git.projetgl.database.service.PostgresInitializer;
import git.projetgl.database.service.SqliteInitializer;
import git.projetgl.utils.LoggerConfig;

import java.util.logging.Logger;

public class AppInitializer {
    private static final Logger LOGGER = Logger.getLogger(AppInitializer.class.getName());
    private final AppConfig config;

    public AppInitializer(AppConfig config) {
        this.config = config;
    }

    public void initialize() {
        LoggerConfig.setup(config.consoleLogs());

        DatabaseInitializer dbInitializer = selectDbInitializer();
        dbInitializer.initialize(config.databaseType());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Shutting down application...");
            dbInitializer.shutdown();
        }));
    }

    private DatabaseInitializer selectDbInitializer() {
        return switch (config.databaseType()) {
            case "postgres" -> new PostgresInitializer();
            case "sqlite" -> new SqliteInitializer();
            default -> throw new IllegalArgumentException("Unsupported database: " + config.databaseType());
        };
    }
}