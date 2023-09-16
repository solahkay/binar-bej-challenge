package solahkay.binar.challenge.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import solahkay.binar.challenge.entity.Menu;
import solahkay.binar.challenge.entity.Order;
import solahkay.binar.challenge.repository.OrderRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

@Extensions({
        @ExtendWith(MockitoExtension.class)
})
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    void testOrganizeOrderSuccess_WhenOrderOrganized() {
        Map<Long, Order> orders = new HashMap<>();
        orders.put(
                1L,
                new Order(new Menu(1L, "Nasi Goreng", 15000), 1, 15000L)
        );
        orders.put(
                2L,
                new Order(new Menu(2L, "Mie Goreng", 13000), 1, 13000L)
        );
        orders.put(
                3L,
                new Order(new Menu(3L, "Nasi Ayam", 18000), 1, 18000L)
        );

        Mockito.when(orderRepository.findAll())
                .thenReturn(orders);
        String expected = "Nasi Goreng        1      15.000\n" +
                          "Mie Goreng         1      13.000\n" +
                          "Nasi Ayam          1      18.000\n" +
                          "------------------------------------+\n" +
                          "Total              3      46.000";

        String result = orderService.organizeOrder();

        assertNotNull(result);
        assertEquals(expected, result);

        Mockito.verify(orderRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    void testAddOrUpdateSuccess_WhenAddNewOrder() {
        Map<Long, Order> orders = new HashMap<>();
        orders.put(
                1L,
                new Order(new Menu(1L, "Nasi Goreng", 15000), 1, 15000L)
        );
        orders.put(
                2L,
                new Order(new Menu(2L, "Mie Goreng", 13000), 1, 13000L)
        );
        orders.put(
                3L,
                new Order(new Menu(3L, "Nasi Ayam", 18000), 1, 18000L)
        );

        Mockito.when(orderRepository.findAll())
                .thenReturn(orders);
        Mockito.when(orderRepository.add(
                4L,
                new Order(new Menu(4L, "Es Jeruk", 5000), 1, 5000L)))
                .thenReturn(true);

        boolean result = orderService.addOrUpdateOrder(new Menu(4L, "Es Jeruk", 5000), 1);

        assertTrue(result);
    }

    @Test
    void testAddOrUpdateSuccess_WhenUpdateExistingOrder() {
        Map<Long, Order> orders = new HashMap<>();
        orders.put(
                1L,
                new Order(new Menu(1L, "Nasi Goreng", 15000), 1, 15000L)
        );
        orders.put(
                2L,
                new Order(new Menu(2L, "Mie Goreng", 13000), 1, 13000L)
        );
        orders.put(
                3L,
                new Order(new Menu(3L, "Nasi Ayam", 18000), 1, 18000L)
        );
        orders.put(
                4L,
                new Order(new Menu(4L, "Es Jeruk", 5000), 1, 5000L)
        );

        Mockito.when(orderRepository.findAll())
                .thenReturn(orders);
        Mockito.when(orderRepository.add(
                        4L,
                        new Order(new Menu(4L, "Es Jeruk", 5000), 2, 10000L)))
                .thenReturn(false);

        boolean result = orderService.addOrUpdateOrder(new Menu(4L, "Es Jeruk", 5000), 1);

        assertFalse(result);
    }

    @Test
    void testAddOrUpdateFailed_WhenMenuIsNullAndReturnFalse() {

        Mockito.when(orderRepository.add(1L, new Order(
                        new Menu(1L, null, 15000), 1, 15000L)))
                .thenThrow(NullPointerException.class);

        boolean result = orderService.addOrUpdateOrder(new Menu(1L, null, 15000), 1);

        assertFalse(result);
    }

}
