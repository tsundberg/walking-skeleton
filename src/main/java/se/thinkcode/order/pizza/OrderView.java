package se.thinkcode.order.pizza;


import io.dropwizard.views.View;

import java.nio.charset.Charset;

public class OrderView extends View {
    public OrderView() {
        super("orderForm.mustache", Charset.forName("UTF-8"));
    }
}
