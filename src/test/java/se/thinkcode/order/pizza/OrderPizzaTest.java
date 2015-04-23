package se.thinkcode.order.pizza;

import org.junit.Test;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OrderPizzaTest {
    @Test
	public void order_a_pizza() {
        ReceiveOrderResource orderResource = new ReceiveOrderResource();

        MultivaluedMap<String, String> orderForm = new MultivaluedHashMap<>();
        String phoneNumber = "+46 70 767 33 15";
        orderForm.put("phoneNumber", Collections.singletonList(phoneNumber));
        String pizzaDescription = "A nice pizza with great filling.";
        orderForm.put("pizzaDescription", Collections.singletonList(pizzaDescription));
        ConfirmView confirmView = orderResource.receiveAndConfirmPizzaOrder(orderForm);

        assertThat(confirmView.getOrderItem(), is(pizzaDescription));
    }
}
