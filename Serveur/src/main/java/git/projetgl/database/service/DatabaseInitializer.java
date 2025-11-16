package git.projetgl.database.service;

public interface DatabaseInitializer {
    void initialize(String dbType);
    void shutdown();
}