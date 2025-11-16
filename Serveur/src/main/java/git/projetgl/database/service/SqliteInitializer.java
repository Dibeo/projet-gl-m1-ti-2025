package git.projetgl.database.service;

import git.projetgl.database.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Logger;

public class SqliteInitializer implements DatabaseInitializer {
    private static final Logger LOGGER = Logger.getLogger(SqliteInitializer.class.getName());
    private SessionFactory sessionFactory;

    @Override
    public void initialize(String dbType) {
        if (sessionFactory != null) return;

        LOGGER.info("DB Init, type : sqlite");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate-sqlite.cfg.xml").build();

        MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(AppUser.class).addAnnotatedClass(Advert.class).addAnnotatedClass(Application.class).addAnnotatedClass(Message.class).addAnnotatedClass(Notification.class);

        Metadata metadata = sources.getMetadataBuilder().build();
        sessionFactory = metadata.buildSessionFactory();
        LOGGER.info("SQLite DB fully initialized");
    }

    @Override
    public void shutdown() {
        if (sessionFactory != null) sessionFactory.close();
    }
}