package git.projetgl.database.repository;

import git.projetgl.database.model.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class NotificationRepository extends AbstractRepository {

    public NotificationRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Notification> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Notification", Notification.class).list();
        }
    }

    public Notification findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Notification.class, id);
        }
    }

    public Notification save(Notification notification) {
        executeVoid(session -> session.merge(notification));
        return notification;
    }

    public void delete(Long id) {
        executeVoid(session -> {
            Notification notification = session.find(Notification.class, id);
            if (notification != null) session.remove(notification);
        });
    }
}
