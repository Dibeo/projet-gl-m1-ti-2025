package git.projetgl.api.handlers;

import git.projetgl.database.model.Notification;
import git.projetgl.service.NotificationService;
import io.javalin.http.Context;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

public class NotificationHandler {

    private final NotificationService notificationService;

    public NotificationHandler(SessionFactory sessionFactory) {
        this.notificationService = new NotificationService(sessionFactory);
    }

    public void getAllNotifications(Context ctx) {
        List<Notification> notifications = notificationService.getAllNotifications();
        ctx.json(notifications);
    }

    public void getNotificationById(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        Notification notification = notificationService.getNotificationById(id);
        if (notification != null) {
            ctx.json(notification);
        } else {
            ctx.status(404).json(Map.of("error", "Notification not found"));
        }
    }

    public void markAsRead(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        notificationService.markAsRead(id);
        ctx.status(204);
    }

    public void createNotification(Context ctx) {
        Notification notification = ctx.bodyAsClass(Notification.class);
        Notification created = notificationService.createNotification(notification);
        ctx.status(201).json(created);
    }
}
