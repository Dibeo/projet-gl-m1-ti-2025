package git.projetgl.config;

import git.projetgl.cli.HelpPrinter;

public class ArgumentParser {
    public static AppConfig parse(String[] args) {
        boolean consoleLogs = false;
        DatabaseConfig databaseConfig = new DatabaseConfig("postgres", null);
        int port = 4040;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.equalsIgnoreCase("--logs") || arg.equalsIgnoreCase("-l")) {
                consoleLogs = true;
            }

            if (arg.equalsIgnoreCase("--database") || arg.equalsIgnoreCase("-db")) {
                if (i + 1 < args.length) {
                    databaseConfig.setDbType(args[++i].toLowerCase());
                }
            }

            if (arg.equalsIgnoreCase("--database-link") || arg.equalsIgnoreCase("-db-link")) {
                if (i + 1 < args.length) databaseConfig.setLink(args[++i].toLowerCase());
            }

            if (arg.equalsIgnoreCase("--port") || arg.equalsIgnoreCase("-p")) {
                if (i + 1 < args.length) {
                    port = Integer.parseInt(args[++i]);
                }
            }

            if (arg.equalsIgnoreCase("-h") || arg.equalsIgnoreCase("--help")) {
                HelpPrinter.printHelp();
                System.exit(0);
            }
        }

        return new AppConfig(consoleLogs, databaseConfig, port);
    }
}