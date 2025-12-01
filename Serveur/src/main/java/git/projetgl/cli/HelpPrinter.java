package git.projetgl.cli;

public class HelpPrinter {
    public static void printHelp() {
        System.out.println("Usage: java -jar app.jar [options]\n" +
                "\t-l, --logs \tActivate in console logs\n" +
                "\t-db, --database \tDatabase type\n" +
                "\t currently supported :\n" +
                "\t\tpostgres\n" +
                "\t\tsqlite\n" +
                "\t-db-link, --database-link \tDatabase link\n" +
                "\t-p, --port \tListening port\n" +
                "\t-h, --help \tShow this massage");
    }
}
