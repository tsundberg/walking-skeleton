package se.thinkcode.order.pizza;

public class PizzaOrder {
    private final String phoneNumber;
    private final String pizzaDescription;

    public PizzaOrder(String phoneNumber, String pizzaDescription) {
        this.phoneNumber = phoneNumber;
        this.pizzaDescription = pizzaDescription;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPizzaDescription() {
        return pizzaDescription;
    }
}
