package git.projetgl.config;

public record AppConfig(boolean consoleLogs, String databaseType, int port) {
}