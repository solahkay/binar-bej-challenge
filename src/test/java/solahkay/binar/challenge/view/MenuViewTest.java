package solahkay.binar.challenge.view;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import solahkay.binar.challenge.entity.Menu;
import solahkay.binar.challenge.service.MenuService;
import solahkay.binar.challenge.service.OrderService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Extensions({
        @ExtendWith(MockitoExtension.class)
})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MenuViewTest {

    @Mock
    private OrderService orderService;
    @Mock
    private MenuService menuService;
    private MenuView menuView;

    @BeforeEach
    void setUp() {
        menuView = new MenuView(menuService, orderService);
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
    }

    @Test
    @Order(2)
    void testShowMenuSuccess_WhenShowMenu() {
        Mockito.when(menuService.getMenuById("0"))
                        .thenReturn(Optional.empty());

        String input = "0";
        InputStream stream  = new ByteArrayInputStream(input.getBytes());

        System.setIn(stream);
        assertDoesNotThrow(() -> menuView.showMenu());

        Mockito.verify(menuService, Mockito.times(1))
                .showAllMenu();
        Mockito.verify(menuService, Mockito.times(1))
                .getMenuById("0");
    }

    @Test
    @Order(1)
    void testShowQuantityConfirmSuccess_WhenQuantityMoreThanZero() {
        Mockito.when(orderService.addOrUpdateOrder(new Menu(1L, "Nasi Goreng", 15000), 12))
                .thenReturn(true);
        String input = "12";
        InputStream stream  = new ByteArrayInputStream(input.getBytes());

        System.setIn(stream);
        assertDoesNotThrow(() -> menuView.showQuantityConfirm(new Menu(1L, "Nasi Goreng", 15000)));
    }
}
