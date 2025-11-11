package git.projetgl.api.handlers;

import io.javalin.http.Context;
import java.util.Map;
import java.util.logging.Logger;

public class GetHandler {
    private static final Logger LOGGER = Logger.getLogger(GetHandler.class.getName());

    public void handleTest(Context ctx) {
        LOGGER.info("Test from :" + ctx.ip());
        ctx.json(Map.of(
                "message", "Test from server.",
                "status", "ok"
        ));
    }
}
