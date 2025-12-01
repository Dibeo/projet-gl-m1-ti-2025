package git.projetgl.initializer;

import git.projetgl.config.AppConfig;
import git.projetgl.database.service.DatabaseInitializer;
import git.projetgl.database.service.PostgresInitializer;
import git.projetgl.database.service.SqliteInitializer;
import git.projetgl.utils.LoggerConfig;
import org.hibernate.SessionFactory;

import java.util.logging.Logger;

public record AppInitializer(AppConfig config) {
    private static final Logger LOGGER = Logger.getLogger(AppInitializer.class.getName());

    public SessionFactory initialize() {
        LoggerConfig.setup(config.consoleLogs());

        DatabaseInitializer dbInitializer = selectDbInitializer();
        dbInitializer.initialize(config.databaseConfig());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Shutting down application...");
            dbInitializer.shutdown();
        }));
        return dbInitializer.getSessionFactory();
    }

    private DatabaseInitializer selectDbInitializer() {
        return switch (config.databaseConfig().getDbType()) {
            case "postgres" -> new PostgresInitializer();
            case "sqlite" -> new SqliteInitializer();
            default -> throw new IllegalArgumentException("Unsupported database: " + config.databaseConfig().getDbType());
        };
    }
}