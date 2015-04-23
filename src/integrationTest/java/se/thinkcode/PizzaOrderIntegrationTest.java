package se.thinkcode;


import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.thinkcode.order.pizza.ConfirmView;
import se.thinkcode.order.pizza.ReceiveOrderResource;

import javax.sql.DataSource;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class PizzaOrderIntegrationTest {
    private Main application;

    @Before
    public void setUp() throws Exception {
        application = ApplicationRunner.runApplication();
        cleanDatabase();
    }

    @After
    public void tearDown() {
        shutDownDatabase();
    }

    @Test
    public void order_a_pizza() throws Exception {
        String phoneNumber = "+46 70 767 33 15";
        String pizzaDescription = "A nice pizza with great filling.";

        ReceiveOrderResource orderResource = application.getReceiveOrderResource();
        ConfirmView confirmView = placeOrder(phoneNumber, pizzaDescription, orderResource);
        String actualPizzaOrder = confirmView.getOrderItem();

        assertThat(actualPizzaOrder, is(pizzaDescription));
    }

    private ConfirmView placeOrder(String phone, String descr, ReceiveOrderResource resource) {
        MultivaluedMap<String, String> orderForm = new MultivaluedHashMap<>();
        orderForm.put("phoneNumber", Collections.singletonList(phone));
        orderForm.put("pizzaDescription", Collections.singletonList(descr));
        return resource.receiveAndConfirmPizzaOrder(orderForm);
    }

    private void cleanDatabase() {
        DataSource dataSource = application.getDataSource();
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.clean();
        flyway.migrate();
    }

    private void shutDownDatabase() {
        try {
            String shutDownDatabase = "shutdown=true";
            String protocol = "jdbc:derby";
            String url = protocol + ":;" + shutDownDatabase;
            DriverManager.getConnection(url);
        } catch (SQLException se) {
            if (((se.getErrorCode() != 50000) || (!"XJ015".equals(se.getSQLState())))) {
                fail("Derby did not shut down normally");
            }
        }
    }
}
