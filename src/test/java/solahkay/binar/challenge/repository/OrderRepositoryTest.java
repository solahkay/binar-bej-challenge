package solahkay.binar.challenge.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solahkay.binar.challenge.entity.Menu;
import solahkay.binar.challenge.entity.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepositoryImpl();
        orderRepository.add(
                1L,
                new Order(new Menu(1L, "Nasi Goreng", 15000), 1, 15000L)
        );
        orderRepository.add(
                2L,
                new Order(new Menu(2L, "Mie Goreng", 13000), 1, 13000L)
        );
        orderRepository.add(
                3L,
                new Order(new Menu(3L, "Nasi Ayam", 18000), 1, 18000L)
        );
    }

    @Test
    void testFindAllSuccess_WhenReturnMap() {
        Map<Long, Order> expected = new HashMap<>();
        expected.put(
                1L,
                new Order(new Menu(1L, "Nasi Goreng", 15000), 1, 15000L)
        );
        expected.put(
                2L,
                new Order(new Menu(2L, "Mie Goreng", 13000), 1, 13000L)
        );
        expected.put(
                3L,
                new Order(new Menu(3L, "Nasi Ayam", 18000), 1, 18000L)
        );

        Map<Long, Order> orders = orderRepository.findAll();

        assertNotNull(orders);
        assertEquals(expected, orders);
    }

    @Test
    void testGetByIdSuccess_WhenIdIsExist() {
        Optional<Order> expected = Optional.of(
                new Order(new Menu(1L, "Nasi Goreng", 15000), 1, 15000L)
        );

        Optional<Order> order = orderRepository.getById(1L);

        assertNotNull(order);
        assertEquals(expected, order);
    }

    @Test
    void testGetByIdSuccess_WhenIdIsNotExistAndReturnOptEmpty() {
        Optional<Order> expected = Optional.empty();
        Optional<Order> order = orderRepository.getById(10L);

        assertNotNull(order);
        assertEquals(expected, order);
    }

    @Test
    void testGetIdSuccess_WhenIdIsNullAndReturnOptEmpty() {
        Optional<Order> expected = Optional.empty();
        Optional<Order> order = orderRepository.getById(null);

        assertNotNull(order);
        assertEquals(expected, order);
    }

    @Test
    void testAddSuccess_WhenAddNewOrderAndReturnTrue() {
        boolean result = orderRepository.add(
                5L,
                new Order(new Menu(5L, "Es Jeruk", 5000), 1, 5000L)
                );

        Optional<Order> expected = Optional.of(
                new Order(new Menu(5L, "Es Jeruk", 5000), 1, 5000L)
        );
        Optional<Order> order = orderRepository.getById(5L);

        assertTrue(result);
        assertNotNull(order);
        assertEquals(expected, order);
    }

    @Test
    void testAddSuccess_WhenUpdateOrderAndReturnFalse() {
        orderRepository.add(
                5L,
                new Order(new Menu(5L, "Es Jeruk", 5000), 1, 5000L)
        );
        int total = 1;
        long totalAmount = 5000L;
        boolean result = orderRepository.add(
                5L,
                new Order(new Menu(5L, "Es Jeruk", 5000), total + 3, totalAmount + 5000L)
        );

        Optional<Order> expected = Optional.of(
                new Order(new Menu(5L, "Es Jeruk", 5000), 4, 10000L)
        );
        Optional<Order> order = orderRepository.getById(5L);

        assertFalse(result);
        assertNotNull(order);
        assertEquals(expected, order);
    }

    @Test
    void testAddFailed_WhenOrderIsNull() {
        assertThrows(NullPointerException.class, () -> orderRepository.add(null, null));
    }
}
