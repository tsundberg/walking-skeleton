package se.thinkcode;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.flywaydb.core.Flyway;
import org.skife.jdbi.v2.DBI;
import se.thinkcode.infrastructure.Configuration;
import se.thinkcode.infrastructure.HealthCheck;
import se.thinkcode.order.pizza.OrderFormResource;
import se.thinkcode.order.pizza.PizzaOrderRepository;
import se.thinkcode.order.pizza.ReceiveOrderResource;

import javax.sql.DataSource;

public class Main extends Application<Configuration> {
    private PizzaOrderRepository pizzaOrderRepository;
    private ReceiveOrderResource receiveOrderResource;
    private ManagedDataSource dataSource;

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.healthChecks().register("HealthCheck", new HealthCheck());
        environment.jersey().register(new OrderFormResource());

        DataSourceFactory dataSourceFactory = configuration.getDataSourceFactory();
        String databaseName = "Order database";
        dataSource = dataSourceFactory.build(environment.metrics(), databaseName);

        initiateDatabase(dataSource);

        DBIFactory factory = new DBIFactory();
        DBI jDbi = factory.build(environment, dataSourceFactory, dataSource, databaseName);
        pizzaOrderRepository = jDbi.onDemand(PizzaOrderRepository.class);
        receiveOrderResource = new ReceiveOrderResource(pizzaOrderRepository);
        environment.jersey().register(receiveOrderResource);
    }

    private void initiateDatabase(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }

    @Override
    public String getName() {
        return "Walking skeleton";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new DBIExceptionsBundle());
    }

    public PizzaOrderRepository getPizzaOrderRepository() {
        return pizzaOrderRepository;
    }

    public ReceiveOrderResource getReceiveOrderResource() {
        return receiveOrderResource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
