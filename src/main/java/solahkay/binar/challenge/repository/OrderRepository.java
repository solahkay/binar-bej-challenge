package solahkay.binar.challenge.repository;

import solahkay.binar.challenge.entity.Order;

import java.util.Map;
import java.util.Optional;

public interface OrderRepository {

    Map<Long, Order> findAll();

    Optional<Order> getById(Long id);

    boolean add(Long id, Order order);

}
