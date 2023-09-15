package solahkay.binar.challenge.repository;

import solahkay.binar.challenge.entity.Order;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Objects;

public class OrderRepositoryImpl implements OrderRepository {

    private final Map<Long, Order> orders = new HashMap<>();

    @Override
    public Map<Long, Order> findAll() {
        return Collections.unmodifiableMap(orders);
    }

    @Override
    public Optional<Order> getById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public boolean add(Long id, Order order) {
        Objects.requireNonNull(order.getMenu().getItemName(), "data can't be null");
        Order previousOrder = orders.put(id, order);
        return previousOrder == null;
    }

}
