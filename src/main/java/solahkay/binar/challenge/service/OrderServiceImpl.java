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
    public String orderList() {
        StringBuilder builder = new StringBuilder();
        Map<Long, Order> allOrder = orderRepository.findAll();

        for (Order order : allOrder.values()) {
            String itemName = order.getMenu().getItemName();
            builder.append(itemName);
            // 13 character before quantity, add the rest character left with space
            for (int i = 1; i <= 13 - itemName.length(); i++) {
                builder.append(" ");
            }
            String totalFormatted = String.format("%7d", order.getTotal());
            builder.append(totalFormatted);
            builder.append("      ");
            builder.append(PrintUtil.addDotNumber(
                    order.getTotalAmount()));
            builder.append("\n");
        }
        builder.append("------------------------------------+\n");

        builder.append("Total");
        String allOrderFormatted = String.format("%15d", OrderUtil.countTotalOrder(
                allOrder));
        builder.append(allOrderFormatted);
        builder.append("      ");

        builder.append(PrintUtil.addDotNumber(
                OrderUtil.countTotalPrice(allOrder)
        ));

        return builder.toString();
    }

    @Override
    public boolean addOrUpdateOrder(Menu menu, Integer totalOrder) {
        Map<Long, Order> allOrder = orderRepository.findAll();
        Integer currentTotalOrder = totalOrder;

        for (Map.Entry<Long, Order> entry : allOrder.entrySet()) {
            if (Objects.equals(entry.getKey(), menu.getId())) {
                currentTotalOrder += entry.getValue().getTotal();
            }
        }

        Order order = new Order(menu, currentTotalOrder);
        return orderRepository.add(menu.getId(), order);
    }
}
