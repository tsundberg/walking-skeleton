package se.thinkcode.order.pizza;
import io.dropwizard.views.View;

import java.nio.charset.Charset;

public class ConfirmView extends View {
    private String orderItem;

    public ConfirmView(String orderItem) {
        super("confirmOrder.mustache", Charset.forName("UTF-8"));
        this.orderItem = orderItem;
    }

    public String getOrderItem() {
        return orderItem;
    }
}
