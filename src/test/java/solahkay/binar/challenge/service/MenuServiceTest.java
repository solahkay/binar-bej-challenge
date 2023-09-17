package solahkay.binar.challenge.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import solahkay.binar.challenge.entity.Menu;
import solahkay.binar.challenge.repository.MenuRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Extensions({
        @ExtendWith(MockitoExtension.class)
})
@TestMethodOrder(value = MethodOrderer.MethodName.class)
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        menuService = new MenuServiceImpl(menuRepository);
    }

    @Test
    void testShowAllMenuSuccess_WhenPrintMenu() {
        Mockito.when(menuRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Menu(1L, "Nasi Goreng", 15000),
                        new Menu(2L, "Mie Goreng", 13000)
                ));

        assertDoesNotThrow(() -> menuService.showAllMenu());

        Mockito.verify(menuRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    void testAddMenuSuccess_WhenAddNewMenu() {
        Mockito.when(menuRepository.findAll())
                .thenReturn(Collections.singletonList(new Menu(1L, "Nasi Goreng", 15000)));
        Mockito.when(menuRepository.insert(Mockito.any(Menu.class)))
                .thenReturn(true);

        assertDoesNotThrow(() -> menuService.addMenu("Nasi + Ayam", 10000));

        Mockito.verify(menuRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(menuRepository, Mockito.times(1))
                .insert(new Menu(2L, "Nasi + Ayam", 10000));
    }

    @Test
    void testAddMenuFailed_WhenMenuIsDuplicateAndReturnFalse() {
        Mockito.when(menuRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Menu(1L, "Nasi Goreng", 15000),
                        new Menu(2L, "Mie Goreng", 13000)
                ));

        assertFalse(menuService.addMenu("Nasi Goreng", 15000));

        Mockito.verify(menuRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    void testAddMenuFailed_WhenMenuNameAndPriceIsNullAndReturnFalse() {
        Mockito.when(menuRepository.findAll())
                .thenReturn(new ArrayList<>());
        Mockito.when(menuRepository.insert(new Menu(1L, null, null)))
                        .thenThrow(NullPointerException.class);

        assertFalse(menuService.addMenu(null, null));

        Mockito.verify(menuRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(menuRepository, Mockito.times(1))
                .insert(new Menu(1L, null, null));
    }

    @Test
    void testGetMenuByIdSuccess_WhenIdIsExist() {
        Mockito.when(menuRepository.getById(2L))
                .thenReturn(Optional.of(
                    new Menu(2L, "Mie Goreng", 13000)
                ));

        Optional<Menu> expected = Optional.of(
            new Menu(2L, "Mie Goreng", 13000)
        );
        Optional<Menu> result = menuService.getMenuById("2");

        assertNotNull(result);
        assertEquals(expected, result);

        Mockito.verify(menuRepository, Mockito.times(1))
                .getById(2L);
    }

    @Test
    void testGetMenuByIdSuccess_WhenIdIsNotNumberAndReturnOptEmpty() {
        Optional<Menu> expected = Optional.empty();
        Optional<Menu> result = menuService.getMenuById("not-number");

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void testGetMenuByIdSuccess_WhenIdIsNullAndReturnOptEmpty() {
        Optional<Menu> expected = Optional.empty();
        Optional<Menu> result = menuService.getMenuById(null);

        assertNotNull(result);
        assertEquals(expected, result);
    }

}
