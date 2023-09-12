package solahkay.binar.challenge.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solahkay.binar.challenge.entity.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MenuRepositoryTest {

    private MenuRepository menuRepository;

    @BeforeEach
    void setUp() {
        menuRepository = new MenuRepositoryImpl();
        menuRepository.insert(new Menu(1L, "Nasi Goreng", 15000));
        menuRepository.insert(new Menu(2L, "Mie Goreng", 13000));
        menuRepository.insert(new Menu(3L, "Nasi Ayam", 18000));
        menuRepository.insert(new Menu(4L, "Es Teh Manis", 3000));
    }

    @Test
    void testFindAllSuccess_WhenReturnList() {
        List<Menu> expected = new ArrayList<>();
        expected.add(new Menu(1L, "Nasi Goreng", 15000));
        expected.add(new Menu(2L, "Mie Goreng", 13000));
        expected.add(new Menu(3L, "Nasi Ayam", 18000));
        expected.add(new Menu(4L, "Es Teh Manis", 3000));

        List<Menu> menus = menuRepository.findAll();

        assertNotNull(menuRepository);
        assertEquals(expected, menus);
    }

    @Test
    void testGetByIdSuccess_WhenIdIsExist() {
        Optional<Menu> expected = Optional.of(
                new Menu(1L, "Nasi Goreng", 15000)
        );
        Optional<Menu> menu = menuRepository.getById(1L);

        assertNotNull(menu);
        assertEquals(expected, menu);
    }

    @Test
    void testGetByIdSuccess_WhenIdIsNotExistAndReturnOptEmpty() {
        Optional<Menu> expected = Optional.empty();
        Optional<Menu> menu = menuRepository.getById(12091L);

        assertNotNull(menu);
        assertEquals(expected, menu);
    }

    @Test
    void testGetByIdSuccess_WhenIdIsNullAndReturnOptEmpty() {
        Optional<Menu> expected = Optional.empty();
        Optional<Menu> menu = menuRepository.getById(null);

        assertNotNull(menu);
        assertEquals(expected, menu);
    }

    @Test
    void testInsertSuccess_WhenReturnTrue() {
        boolean insert = menuRepository.insert(
                new Menu(5L, "Es Jeruk", 5000)
        );
        Optional<Menu> expected = Optional.of(
                new Menu(5L, "Es Jeruk", 5000)
        );
        Optional<Menu> menu = menuRepository.getById(5L);

        assertTrue(insert);
        assertEquals(expected, menu);
    }
    @Test
    void testInsertFailed_WhenMenuIsNull() {
        assertThrows(NullPointerException.class, () -> menuRepository.insert(null));
    }
}
