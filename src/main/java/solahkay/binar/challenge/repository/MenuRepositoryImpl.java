package solahkay.binar.challenge.repository;

import solahkay.binar.challenge.entity.Menu;

import java.util.*;

public class MenuRepositoryImpl implements MenuRepository {

    private final List<Menu> lists = new ArrayList<>();

    @Override
    public List<Menu> findAll() {
        return Collections.unmodifiableList(lists);
    }

    @Override
    public Optional<Menu> getById(Long id) {
        Menu menu = null;
        for (Menu list : lists) {
            if (list.getId().equals(id)) {
                menu = list;
            }
        }
        return Optional.ofNullable(menu);
    }


    @Override
    public boolean insert(Menu menu) {
        return lists.add(menu);
    }

}
