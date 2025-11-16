package git.projetgl.database.service;

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
    public void initialize(String dbType) {
        if (sessionFactory != null) return;

        LOGGER.info("Initializing PostgreSQL database...");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate-postgres.cfg.xml").build();

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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
