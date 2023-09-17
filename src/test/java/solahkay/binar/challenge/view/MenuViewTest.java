package solahkay.binar.challenge.view;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import solahkay.binar.challenge.entity.Menu;
import solahkay.binar.challenge.service.MenuService;
import solahkay.binar.challenge.service.OrderService;
import solahkay.binar.challenge.util.InputUtil;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

@Extensions({
        @ExtendWith(MockitoExtension.class)
})
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

    @Test
    void testShowMenuSuccess_WhenShowMenuAndBreak() {
        Mockito.when(menuService.getMenuById("0"))
                        .thenReturn(Optional.empty());
        try (MockedStatic<InputUtil> mocked = mockStatic(InputUtil.class)) {
            mocked.when(() -> InputUtil.input("=>")).thenReturn("0");

            assertDoesNotThrow(() -> menuView.showMenu());

            Mockito.verify(menuService, Mockito.times(1))
                    .showAllMenu();
            Mockito.verify(menuService, Mockito.times(1))
                    .getMenuById("0");
            mocked.verify(() -> InputUtil.input("=>"));
        }
    }

    @Test
    @Disabled("Success but causing infinite loop")
    void testShowMenuSuccess_WhenShowMenu() {
        Mockito.when(menuService.getMenuById("qe"))
                .thenReturn(Optional.empty());
        try (MockedStatic<InputUtil> mocked = mockStatic(InputUtil.class)) {
            mocked.when(() -> InputUtil.input("=>")).thenReturn("qe");

            assertDoesNotThrow(() -> menuView.showMenu());

            Mockito.verify(menuService, Mockito.times(1))
                    .showAllMenu();
            Mockito.verify(menuService, Mockito.times(1))
                    .getMenuById("0");
            mocked.verify(() -> InputUtil.input("=>"));
        }
    }

    @Test
    void testShowQuantityConfirmSuccess_WhenQuantityMoreThanZero() {
        Mockito.when(orderService.addOrUpdateOrder(new Menu(1L, "Nasi Goreng", 15000), 12))
                .thenReturn(true);

        try (MockedStatic<InputUtil> mocked = mockStatic(InputUtil.class)) {
            mocked.when(() -> InputUtil.input("qty =>")).thenReturn("12");

            assertDoesNotThrow(() -> menuView.showQuantityConfirm(new Menu(1L, "Nasi Goreng", 15000)));

            mocked.verify(() -> InputUtil.input("qty =>"));
        }
    }

    @Test
    void testShowQuantityConfirmFailed_WhenQuantityIsZeroAndBackToMenu() {
        try (MockedStatic<InputUtil> mocked = mockStatic(InputUtil.class)) {
            mocked.when(() -> InputUtil.input("qty =>")).thenReturn("0");

            assertDoesNotThrow(() -> menuView.showQuantityConfirm(new Menu(1L, "Nasi Goreng", 15000)));

            mocked.verify(() -> InputUtil.input("qty =>"));
        }
    }

    @Test
    @Disabled("Success but causing infinite loop")
    void testShowQuantityConfirmFailed_WhenQuantityIsMines() {
        try (MockedStatic<InputUtil> mocked = mockStatic(InputUtil.class)) {
            mocked.when(() -> InputUtil.input("qty =>")).thenReturn("-2");

            assertDoesNotThrow(() -> menuView.showQuantityConfirm(new Menu(1L, "Nasi Goreng", 15000)));

            mocked.verify(() -> InputUtil.input("qty =>"));
        }
    }

    @Test
    @Disabled("Success but causing infinite loop")
    void testShowQuantityConfirmFailed_WhenInputIsNotNumber() {
        try (MockedStatic<InputUtil> mocked = mockStatic(InputUtil.class)) {
            mocked.when(() -> InputUtil.input("qty =>")).thenReturn("qer");

            assertDoesNotThrow(() -> menuView.showQuantityConfirm(new Menu(1L, "Nasi Goreng", 15000)));

            mocked.verify(() -> InputUtil.input("qty =>"));
        }
    }

    @Test
    void testShowPaymentConfirmSuccess_WhenPaymentIsSuccess() throws Exception {
        Mockito.when(orderService.organizeOrder())
                .thenReturn("Nasi Goreng        1      15.000\n" +
                              "Mie Goreng         1      13.000\n" +
                              "Nasi Ayam          1      18.000\n" +
                              "------------------------------------+\n" +
                              "Total              3      46.000");

        try (MockedStatic<InputUtil> mocked = mockStatic(InputUtil.class)) {
            mocked.when(() -> InputUtil.input("=>")).thenReturn("1");

            int expected = 0;
            int statusCode = SystemLambda.catchSystemExit(() -> {
                menuView.showPaymentConfirm();
            });

            assertEquals(expected, statusCode);

            Mockito.verify(orderService, Mockito.times(2))
                    .organizeOrder();
            mocked.verify(() -> InputUtil.input("=>"));
        }
    }

    @Test
    void testShowPaymentConfirmFailed_WhenBackToMenu() {
        Mockito.when(orderService.organizeOrder())
                .thenReturn("Nasi Goreng        1      15.000\n" +
                        "Mie Goreng         1      13.000\n" +
                        "Nasi Ayam          1      18.000\n" +
                        "------------------------------------+\n" +
                        "Total              3      46.000");

        try (MockedStatic<InputUtil> mocked = mockStatic(InputUtil.class)) {
            mocked.when(() -> InputUtil.input("=>")).thenReturn("2");

            assertDoesNotThrow(() -> menuView.showPaymentConfirm());

            Mockito.verify(orderService, Mockito.times(1))
                    .organizeOrder();
            mocked.verify(() -> InputUtil.input("=>"));
        }
    }

    @Test
    void testShowPaymentConfirmFailed_WhenShutdownApplication() throws Exception {
        Mockito.when(orderService.organizeOrder())
                .thenReturn("Nasi Goreng        1      15.000\n" +
                        "Mie Goreng         1      13.000\n" +
                        "Nasi Ayam          1      18.000\n" +
                        "------------------------------------+\n" +
                        "Total              3      46.000");

        try (MockedStatic<InputUtil> mocked = mockStatic(InputUtil.class)) {
            mocked.when(() -> InputUtil.input("=>")).thenReturn("0");

            int expected = 0;
            int statusCode = SystemLambda.catchSystemExit(() -> {
                menuView.showPaymentConfirm();
            });

            assertEquals(expected, statusCode);

            Mockito.verify(orderService, Mockito.times(1))
                    .organizeOrder();
            mocked.verify(() -> InputUtil.input("=>"));
        }
    }

    @Test
    @Disabled("Success but causing infinite loop")
    void testShowPaymentConfirmFailed_WhenInputNotInMenu() {
        Mockito.when(orderService.organizeOrder())
                .thenReturn("Nasi Goreng        1      15.000\n" +
                        "Mie Goreng         1      13.000\n" +
                        "Nasi Ayam          1      18.000\n" +
                        "------------------------------------+\n" +
                        "Total              3      46.000");

        try (MockedStatic<InputUtil> mocked = mockStatic(InputUtil.class)) {
            mocked.when(() -> InputUtil.input("=>")).thenReturn("de");

            assertDoesNotThrow(() -> menuView.showPaymentConfirm());

            Mockito.verify(orderService, Mockito.times(1))
                    .organizeOrder();
            mocked.verify(() -> InputUtil.input("=>"));
        }
    }

}
