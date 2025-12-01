package git.projetgl.database.service;

import git.projetgl.config.DatabaseConfig;
import git.projetgl.database.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.util.logging.Logger;

public class SqliteInitializer implements DatabaseInitializer {
    private static final Logger LOGGER = Logger.getLogger(SqliteInitializer.class.getName());
    private SessionFactory sessionFactory;

    @Override
    public void initialize(DatabaseConfig dbConfig) {
        if (sessionFactory != null) return;
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            boolean dirCreated = dataDir.mkdirs();
            if (dirCreated) {
                LOGGER.info("Data directory created at: " + dataDir.getAbsolutePath());
            } else {
                LOGGER.severe("Failed to create data directory: " + dataDir.getAbsolutePath());
            }
        }

        LOGGER.info("DB Init, type : sqlite");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate-config/hibernate-sqlite.cfg.xml").build();

        MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(AppUser.class).addAnnotatedClass(Advert.class).addAnnotatedClass(Application.class).addAnnotatedClass(Message.class).addAnnotatedClass(Notification.class);

        Metadata metadata = sources.getMetadataBuilder().build();
        sessionFactory = metadata.buildSessionFactory();
        LOGGER.info("SQLite DB fully initialized");
    }

    @Override
    public void shutdown() {
        if (sessionFactory != null) sessionFactory.close();
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}