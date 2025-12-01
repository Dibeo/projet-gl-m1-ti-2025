package git.projetgl.api.handlers;

import io.javalin.http.Context;
import java.util.Map;
import java.util.logging.Logger;

public class PostHandler {
    private static final Logger LOGGER = Logger.getLogger(PostHandler.class.getName());

    public void handle(Context ctx) {
        String body = ctx.body();
        LOGGER.info("Received POST data: " + body);

        ctx.json(Map.of(
                "status", "success",
                "received", body
        ));
    }
}
