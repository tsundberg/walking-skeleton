package se.thinkcode.order.pizza;

import org.junit.Test;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderPizzaTest {
    @Test
	public void order_a_pizza() {
        String phoneNumber = "+46 70 767 33 15";
        String pizzaDescription = "A nice pizza with great filling.";
        PizzaOrderRepository pizzaDao = mock(PizzaOrderRepository.class);
        when(pizzaDao.findOrderBy(phoneNumber)).thenReturn(pizzaDescription);
        ReceiveOrderResource orderResource = new ReceiveOrderResource(pizzaDao);

        MultivaluedMap<String, String> orderForm = new MultivaluedHashMap<>();
        orderForm.put("phoneNumber", Collections.singletonList(phoneNumber));
        orderForm.put("pizzaDescription", Collections.singletonList(pizzaDescription));
        ConfirmView confirmView = orderResource.receiveAndConfirmPizzaOrder(orderForm);

        assertThat(confirmView.getOrderItem(), is(pizzaDescription));

        verify(pizzaDao).saveOrder(phoneNumber, pizzaDescription);
        verify(pizzaDao).findOrderBy(phoneNumber);
    }
}
