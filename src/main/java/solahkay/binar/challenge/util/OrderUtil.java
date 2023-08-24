package solahkay.binar.challenge.util;

import solahkay.binar.challenge.entity.Order;

import java.util.Map;

public class OrderUtil {

    private OrderUtil() {
    }

    public static Integer countTotalOrder(Map<Long, Order> orders) {
        Integer count = 0;
        for (Order order : orders.values()) {
            count += order.getTotal();
        }
        return count;
    }

    public static Long countTotalPrice(Map<Long, Order> orders) {
        Long price = 0L;
        for (Order order : orders.values()) {
            price += order.getTotalAmount();
        }
        return price;
    }

}
