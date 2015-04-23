package se.thinkcode.order.pizza;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;

@Path("/order")
public class ReceiveOrderResource {
    private PizzaOrderRepository pizzaOrderRepository;

    public ReceiveOrderResource(PizzaOrderRepository pizzaOrderRepository) {
        this.pizzaOrderRepository = pizzaOrderRepository;
    }

    @POST
    @Produces("text/html;charset=UTF-8")
    public ConfirmView receiveAndConfirmPizzaOrder(MultivaluedMap<String, String> inputForm) {
        String phoneNumber = inputForm.get("phoneNumber").get(0);
        String pizzaDescription = inputForm.get("pizzaDescription").get(0);

        PizzaOrder pizzaOrder = new PizzaOrder(phoneNumber, pizzaDescription);
        save(pizzaOrder);
        String orderItem = getPizzaOrder(phoneNumber);

        return new ConfirmView(orderItem);
    }

    private void save(PizzaOrder pizzaOrder) {
        pizzaOrderRepository.saveOrder(pizzaOrder.getPhoneNumber(), pizzaOrder.getPizzaDescription());
    }

    private String getPizzaOrder(String phoneNumber) {
        return pizzaOrderRepository.findOrderBy(phoneNumber);
    }
}
