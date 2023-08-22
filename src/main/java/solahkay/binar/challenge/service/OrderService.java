package solahkay.binar.challenge.service;

import solahkay.binar.challenge.entity.Menu;

public interface OrderService {

    String orderList();

    boolean addOrUpdateOrder(Menu menu, Integer totalOrder);

}
