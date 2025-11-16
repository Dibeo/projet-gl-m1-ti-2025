package git.projetgl.database.service;

import git.projetgl.config.DatabaseConfig;

public interface DatabaseInitializer {
    void initialize(DatabaseConfig dbType);
    void shutdown();
}