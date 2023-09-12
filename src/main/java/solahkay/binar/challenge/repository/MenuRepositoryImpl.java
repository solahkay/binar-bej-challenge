package solahkay.binar.challenge.repository;

import solahkay.binar.challenge.entity.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

public class MenuRepositoryImpl implements MenuRepository {

    private final List<Menu> menus = new ArrayList<>();

    @Override
    public List<Menu> findAll() {
        return Collections.unmodifiableList(menus);
    }

    @Override
    public Optional<Menu> getById(Long id) {
        Menu menu = null;
        for (Menu value : menus) {
            if (value.getId().equals(id)) {
                menu = value;
            }
        }
        return Optional.ofNullable(menu);
    }

    @Override
    public boolean insert(Menu menu) {
        Objects.requireNonNull(menu, "data can't be null");
        return menus.add(menu);
    }

}
