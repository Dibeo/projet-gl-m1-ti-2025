package git.projetgl.config;

public class DatabaseConfig {
    private String dbType;
    private String link;

    public DatabaseConfig(String dbType, String link) {
        this.dbType = dbType;
        this.link = link;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
