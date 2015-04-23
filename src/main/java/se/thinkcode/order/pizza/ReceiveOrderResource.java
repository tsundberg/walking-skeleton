package se.thinkcode.order.pizza;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;

@Path("/order")
public class ReceiveOrderResource {

    @POST
    @Produces("text/html;charset=UTF-8")
    public ConfirmView receiveAndConfirmPizzaOrder(MultivaluedMap<String, String> inputForm) {
        String phoneNumber = inputForm.get("phoneNumber").get(0);
        String pizzaDescription = inputForm.get("pizzaDescription").get(0);

        return new ConfirmView(pizzaDescription);
    }
}
