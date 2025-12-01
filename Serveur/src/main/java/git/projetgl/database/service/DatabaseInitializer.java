package git.projetgl.database.service;

import git.projetgl.config.DatabaseConfig;
import org.hibernate.SessionFactory;

public interface DatabaseInitializer {
    void initialize(DatabaseConfig dbType);
    void shutdown();
    SessionFactory getSessionFactory();
}