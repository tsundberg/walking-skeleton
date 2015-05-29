package se.thinkcode.order.pizza;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class OrderFormResource {
    @GET
    @Produces("text/html;charset=UTF-8")
    public OrderView getOrderForm() {
        return new OrderView();
    }
}