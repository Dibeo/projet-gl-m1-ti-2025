package git.projetgl.database.service;

import git.projetgl.database.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Logger;

public class GlobalService {
    private static final Logger LOGGER = Logger.getLogger(GlobalService.class.getName());
    private static SessionFactory sessionFactory;

    public static void initializeDatabase(String dbType) {
        if (sessionFactory != null) return;

        try {
            LOGGER.info("DB Init, type : " + dbType);

            String cfgFile;
            if ("postgres".equalsIgnoreCase(dbType)) {
                cfgFile = "hibernate-postgres.cfg.xml";
            } else if ("sqlite".equalsIgnoreCase(dbType)) {
                cfgFile = "hibernate-sqlite.cfg.xml";
            } else {
                LOGGER.severe("Unsupported db type : " + dbType);
                throw new IllegalArgumentException("Unsupported db type : " + dbType);
            }

            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure(cfgFile)
                    .build();

            MetadataSources sources = new MetadataSources(registry)
                    .addAnnotatedClass(AppUser.class)
                    .addAnnotatedClass(Advert.class)
                    .addAnnotatedClass(Application.class)
                    .addAnnotatedClass(Message.class)
                    .addAnnotatedClass(Notification.class);

            Metadata metadata = sources.getMetadataBuilder().build();

            try {
                sessionFactory = metadata.buildSessionFactory();
                LOGGER.info("DB fully initialized");
            } catch (Exception ex) {
                LOGGER.severe("Database schema generation failed: " + ex.getMessage());
                throw new RuntimeException("Database schema generation failed", ex);
            }

        } catch (Exception e) {
            LOGGER.severe("Error while initializing database: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database initialization failed", e);
        }
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("Database not initialized. Call initializeDatabase() first.");
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            LOGGER.info("Session closed.");
        }
    }
}
