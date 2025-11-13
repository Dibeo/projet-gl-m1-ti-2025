package git.projetgl.api;

import git.projetgl.api.handlers.GetHandler;
import git.projetgl.api.handlers.PostHandler;
import git.projetgl.api.handlers.UserHandler;
import git.projetgl.database.service.UserService;
import io.javalin.Javalin;
import java.util.Map;
import java.util.logging.Logger;

public class ApiServer {
    private static final Logger LOGGER = Logger.getLogger(ApiServer.class.getName());
    private final Javalin app;
    private final int port;

    public ApiServer(int port) {
        this.port = port;
        GetHandler getHandler = new GetHandler();
        PostHandler postHandler = new PostHandler();

        //UserService userService = new UserService();
        //UserHandler userHandler = new UserHandler(userService);

        this.app = Javalin.create(config -> {
            config.router.ignoreTrailingSlashes = true;
        });

        app.get("/test", getHandler::handleTest);
        //app.get("/users", userHandler::getAllUsers);

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
        LOGGER.info("Server start on http://localhost:"+port);
    }

    public void stop() {
        app.stop();
    }
}
