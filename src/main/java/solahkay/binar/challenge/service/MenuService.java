package solahkay.binar.challenge.service;

import solahkay.binar.challenge.entity.Menu;

import java.util.Optional;

public interface MenuService {

    void showAllMenu();

    boolean addMenu(String itemName, Integer price);

    Optional<Menu> getMenuById(String id);

}
