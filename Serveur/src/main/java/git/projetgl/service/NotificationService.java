package git.projetgl.service;

import git.projetgl.database.model.Notification;
import git.projetgl.database.repository.NotificationRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(SessionFactory sessionFactory) {
        this.repository = new NotificationRepository(sessionFactory);
    }

    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return repository.findById(id);
    }

    public Notification createNotification(Notification notification) {
        return repository.save(notification);
    }

    public void markAsRead(Long id) {
        Notification notification = repository.findById(id);
        if (notification != null) {
            notification.setRead(true);
            repository.save(notification);
        }
    }
}
