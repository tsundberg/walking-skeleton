package se.thinkcode;

import java.net.URL;

public class ApplicationRunner {
    public static Main runApplication() throws Exception {
        URL configResource = ApplicationRunner.class.getResource("/test-configuration.yaml");
        String configFile = configResource.getFile();

        String mode = "server";
        Main application = new Main();
        String[] args = {mode, configFile};
        application.run(args);

        return application;
    }
}
