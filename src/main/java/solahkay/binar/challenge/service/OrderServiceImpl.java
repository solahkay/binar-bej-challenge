package solahkay.binar.challenge.service;

import solahkay.binar.challenge.entity.Menu;
import solahkay.binar.challenge.entity.Order;
import solahkay.binar.challenge.repository.OrderRepository;
import solahkay.binar.challenge.util.OrderUtil;
import solahkay.binar.challenge.util.PrintUtil;

import java.util.Map;
import java.util.Objects;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public String organizeOrder() {
        StringBuilder builder = new StringBuilder();
        Map<Long, Order> allOrder = orderRepository.findAll();

        formatOrders(builder, allOrder);
        formatTotalOrders(builder, allOrder);

        return builder.toString();
    }

    @Override
    public boolean addOrUpdateOrder(Menu menu, Integer totalOrder) {
        Map<Long, Order> allOrder = orderRepository.findAll();
        Integer currentTotalOrder = totalOrder;

        for (Map.Entry<Long, Order> entry : allOrder.entrySet()) {
            // if totalOrder exist then sum previous totalOrder and new totalOrder
            if (Objects.equals(entry.getKey(), menu.getId())) {
                currentTotalOrder += entry.getValue().getTotal();
            }
        }

        Long totalAmount = ((long) menu.getPrice() * currentTotalOrder);
        Order order = new Order(menu, currentTotalOrder, totalAmount);
        return orderRepository.add(menu.getId(), order);
    }

    private void formatTotalOrders(StringBuilder builder, Map<Long, Order> allOrder) {
        builder.append("------------------------------------+\n");

        builder.append("Total");
        // 15 character for total order include space
        String allOrderFormatted = String.format("%15d", OrderUtil.countTotalOrder(
                allOrder));
        builder.append(allOrderFormatted);
        builder.append("      ");

        builder.append(PrintUtil.addDotNumber(
                OrderUtil.countTotalPrice(allOrder)
        ));
    }

    private void formatOrders(StringBuilder builder, Map<Long, Order> allOrder) {
        for (Order order : allOrder.values()) {
            String itemName = order.getMenu().getItemName();
            builder.append(itemName);
            // 13 character before total quantity, add the rest character left with space
            int lengthBeforeTotalQty = 13 - itemName.length();
            for (int i = 1; i <= lengthBeforeTotalQty; i++) {
                builder.append(" ");
            }
            // 7 character for total quantity include space
            String totalFormatted = String.format("%7d", order.getTotal());
            builder.append(totalFormatted);
            builder.append("      ");
            builder.append(PrintUtil.addDotNumber(
                    order.getTotalAmount()));
            builder.append("\n");
        }
    }

}
