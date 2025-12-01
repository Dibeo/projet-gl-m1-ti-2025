package git.projetgl.database.service;

import git.projetgl.config.DatabaseConfig;
import git.projetgl.database.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Logger;

public class PostgresInitializer implements DatabaseInitializer {
    private static final Logger LOGGER = Logger.getLogger(PostgresInitializer.class.getName());
    private SessionFactory sessionFactory;

    @Override
    public void initialize(DatabaseConfig config) {
        if (sessionFactory != null) return;

        LOGGER.info("Initializing PostgreSQL database...");

        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().configure("hibernate-config/hibernate-postgres.cfg.xml");

        if (config.getLink() != null) {
            String url = "jdbc:postgresql://" + config.getLink();
            registryBuilder.applySetting("hibernate.connection.url", url);
        }

        StandardServiceRegistry registry = registryBuilder.build();

        MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(AppUser.class).addAnnotatedClass(Advert.class).addAnnotatedClass(Application.class).addAnnotatedClass(Message.class).addAnnotatedClass(Notification.class);

        Metadata metadata = sources.getMetadataBuilder().build();
        sessionFactory = metadata.buildSessionFactory();

        LOGGER.info("PostgreSQL database fully initialized");
    }


    @Override
    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            LOGGER.info("PostgreSQL session closed");
        }
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
