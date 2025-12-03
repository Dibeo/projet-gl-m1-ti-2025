package git.projetgl.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import git.projetgl.database.model.AppUser;
import git.projetgl.database.model.Advert;
import git.projetgl.database.model.Application;
import git.projetgl.database.model.Message;

public class HibernateTestUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Ajouter toutes vos entités
                configuration.addAnnotatedClass(AppUser.class);
                configuration.addAnnotatedClass(Advert.class);
                configuration.addAnnotatedClass(Application.class);
                configuration.addAnnotatedClass(Message.class);

                // Configuration H2 en mémoire
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
                configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
                configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
                configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop"); // create schema for tests
                configuration.setProperty("hibernate.show_sql", "true");

                sessionFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to build SessionFactory for tests", e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
