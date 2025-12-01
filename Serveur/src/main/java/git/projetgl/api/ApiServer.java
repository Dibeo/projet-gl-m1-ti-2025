package git.projetgl.api;

import git.projetgl.api.handlers.*;
import io.javalin.Javalin;
import org.hibernate.SessionFactory;

import java.util.Map;
import java.util.logging.Logger;

public class ApiServer {
    private static final Logger LOGGER = Logger.getLogger(ApiServer.class.getName());
    private final Javalin app;
    private final int port;

    public ApiServer(int port, SessionFactory sessionFactory) {
        this.port = port;
        GetHandler getHandler = new GetHandler();
        PostHandler postHandler = new PostHandler();

        AppUserHandler appUserHandler = new AppUserHandler(sessionFactory);
        //AuthHandler authHandler = new AuthHandler();
        AdvertHandler advertHandler = new AdvertHandler(sessionFactory);
        ApplicationHandler applicationHandler = new ApplicationHandler(sessionFactory);
        MessageHandler messageHandler = new MessageHandler(sessionFactory);
        NotificationHandler notificationHandler = new NotificationHandler(sessionFactory);
        //ReviewHandler reviewHandler = new ReviewHandler(sessionFactory);

        this.app = Javalin.create(config -> {
            config.router.ignoreTrailingSlashes = true;
        });

        app.get("/test", getHandler::handleTest);

        /// --- USER ENDPOINTS ---
        app.get("/users", appUserHandler::getAllUsers);
        app.get("/users/{id}", appUserHandler::getUserById);
        app.post("/users", appUserHandler::createUser);
        app.put("/users/{id}", appUserHandler::updateUser);
        app.delete("/users/{id}", appUserHandler::deleteUser);

        // --- AUTHENTICATION ---
        //app.post("/auth/login", authHandler::login);
        //app.post("/auth/logout", authHandler::logout);

        // --- ADVERTS ---
        app.get("/adverts", advertHandler::getAllAdverts);
        app.get("/adverts/{id}", advertHandler::getAdvertById);
        app.post("/adverts", advertHandler::createAdvert);
        app.put("/adverts/{id}", advertHandler::updateAdvert);
        app.delete("/adverts/{id}", advertHandler::deleteAdvert);
        app.get("/adverts/search", advertHandler::searchAdverts);

        // --- APPLICATIONS / DEMANDES ---
        app.get("/applications", applicationHandler::getAllApplications);
        app.get("/applications/{id}", applicationHandler::getApplicationById);
        app.post("/applications", applicationHandler::createApplication);
        app.post("/applications/{id}/accept", applicationHandler::acceptApplication);
        app.post("/applications/{id}/reject", applicationHandler::rejectApplication);
        app.delete("/applications/{id}", applicationHandler::deleteApplication);

        // --- MESSAGES ---
        app.get("/messages", messageHandler::getAllMessages);
        app.get("/messages/{id}", messageHandler::getMessageById);
        app.post("/messages", messageHandler::sendMessage);
        app.get("/messages/conversation/{userId}", messageHandler::getConversationWithUser);

        // --- NOTIFICATIONS ---
        app.get("/notifications", notificationHandler::getAllNotifications);
        app.get("/notifications/{id}", notificationHandler::getNotificationById);
        app.post("/notifications/{id}/read", notificationHandler::markAsRead);
        app.post("/notifications", notificationHandler::createNotification);

        // --- REVIEWS / REPUTATION ---
        //app.post("/reviews", reviewHandler::createReview);
        //app.get("/reviews/{userId}", reviewHandler::getReviewsForUser);


        app.exception(Exception.class, (e, ctx) -> {
            ctx.status(500);
            ctx.json(Map.of(
                    "error", "internal_error",
                    "message", e.getMessage() != null ? e.getMessage() : "unknown"
            ));
            LOGGER.warning(e.getMessage() != null ? e.getMessage() : "unknown");
        });
    }

    public void start() {
        app.start(port);
        LOGGER.info("Server start on http://localhost:" + port);
    }

    public void stop() {
        app.stop();
    }
}
