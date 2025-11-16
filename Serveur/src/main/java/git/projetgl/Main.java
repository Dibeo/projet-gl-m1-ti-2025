package git.projetgl;

import git.projetgl.api.ApiServer;
import git.projetgl.database.service.GlobalService;
import git.projetgl.utils.LoggerConfig;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        boolean consoleLogging = false;
        String dbType = "postgres";

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.equalsIgnoreCase("--logs") || arg.equalsIgnoreCase("-l")) {
                consoleLogging = true;
            }

            if (arg.equalsIgnoreCase("-db") || arg.equalsIgnoreCase("--database")) {
                if (i + 1 < args.length) {
                    dbType = args[i + 1].toLowerCase();
                    i++;
                } else {
                    System.err.println("Erreur : aucune valeur après " + arg + ", utilisation de la valeur par défaut : " + dbType);
                }
            }
        }

        LoggerConfig.setup(consoleLogging);
        try {
            GlobalService.initializeDatabase(dbType);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }


        ApiServer server = new ApiServer(4040);
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Shutting down application...");
            GlobalService.shutdown();
        }));
    }
}
