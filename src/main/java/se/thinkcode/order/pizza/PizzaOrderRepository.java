package se.thinkcode.order.pizza;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface PizzaOrderRepository {

    @SqlUpdate("insert into pizza_order (phoneNumber, item) values (:phoneNumber, :item)")
    void saveOrder(@Bind("phoneNumber") String phoneNumber, @Bind("item") String item);

    @SqlQuery("select item from pizza_order where phoneNumber = :phoneNumber")
    String findOrderBy(@Bind("phoneNumber") String phoneNumber);

    void close();
}
