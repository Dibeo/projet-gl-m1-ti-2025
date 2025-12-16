package git.projetgl.database.repository;

import git.projetgl.database.model.Advert;
import git.projetgl.database.model.AppUser;
import git.projetgl.database.model.Application;
import git.projetgl.database.model.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ApplicationRepository extends AbstractRepository {

    public ApplicationRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Application> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Application", Application.class).list();
        }
    }

    public Application findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Application.class, id);
        }
    }

    public Application save(Application application) {
        executeVoid(session -> session.merge(application));
        return application;
    }

    public void delete(Long id) {
        executeVoid(session -> {
            Application application = session.find(Application.class, id);
            if (application != null) session.remove(application);
        });
    }

    public void createFromIds(Long userId, Long advertId) {
        executeVoid(session -> {
            AppUser user = session.find(AppUser.class, userId);
            Advert advert = session.find(Advert.class, advertId);

            if (user == null || advert == null) {
                throw new IllegalArgumentException("Utilisateur ou advert introuvable");
            }

            // Création de l'application
            Application application = new Application(advert, user);
            application.setApplicationStatus(Status.WAITING);
            // Persistance
            session.persist(application);

        });
    }

}
