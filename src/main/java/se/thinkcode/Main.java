package se.thinkcode;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import se.thinkcode.infrastructure.Configuration;
import se.thinkcode.infrastructure.HealthCheck;
import se.thinkcode.order.pizza.OrderFormResource;
import se.thinkcode.order.pizza.ReceiveOrderResource;

public class Main extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.healthChecks().register("HealthCheck", new HealthCheck());
        environment.jersey().register(new OrderFormResource());

        ReceiveOrderResource receiveOrderResource = new ReceiveOrderResource();
        environment.jersey().register(receiveOrderResource);
    }

    @Override
    public String getName() {
        return "Walking skeleton";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle());
    }
}
