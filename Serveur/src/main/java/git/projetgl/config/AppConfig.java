package git.projetgl.config;

public record AppConfig(boolean consoleLogs, DatabaseConfig databaseConfig, int port) {
}