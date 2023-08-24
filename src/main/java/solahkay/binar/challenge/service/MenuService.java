package solahkay.binar.challenge.service;

import solahkay.binar.challenge.entity.Menu;

public interface MenuService {

    void showAllMenu();

    boolean addMenu(String itemName, Integer price);

    Menu getMenuById(String id);

}
