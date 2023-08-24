package solahkay.binar.challenge.repository;

import solahkay.binar.challenge.entity.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {

    Map<Long, Order> orders = new HashMap<>();

    @Override
    public Map<Long, Order> findAll() {
        return orders;
    }

    @Override
    public Optional<Order> getById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public boolean add(Long id, Order order) {
        Order previousOrder = orders.put(id, order);
        return previousOrder == null;
    }

}
