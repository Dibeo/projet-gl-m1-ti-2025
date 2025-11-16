package git.projetgl;

import git.projetgl.api.ApiServer;
import git.projetgl.config.AppConfig;
import git.projetgl.config.ArgumentParser;
import git.projetgl.initializer.AppInitializer;


public class Main {
    public static void main(String[] args) {
        AppConfig config = ArgumentParser.parse(args);
        new AppInitializer(config).initialize();
        ApiServer server = new ApiServer(config.port());
        server.start();
    }
}