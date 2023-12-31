package solahkay.binar.challenge;

import solahkay.binar.challenge.dataimport.CSVImporter;
import solahkay.binar.challenge.repository.MenuRepository;
import solahkay.binar.challenge.repository.MenuRepositoryImpl;
import solahkay.binar.challenge.repository.OrderRepository;
import solahkay.binar.challenge.repository.OrderRepositoryImpl;
import solahkay.binar.challenge.service.MenuService;
import solahkay.binar.challenge.service.MenuServiceImpl;
import solahkay.binar.challenge.service.OrderService;
import solahkay.binar.challenge.service.OrderServiceImpl;
import solahkay.binar.challenge.view.MenuView;

public class App {

    public static void main(String[] args) {
        MenuRepository menuRepository = new MenuRepositoryImpl();
        OrderRepository orderRepository = new OrderRepositoryImpl();
        MenuService menuService = new MenuServiceImpl(menuRepository);
        OrderService orderService = new OrderServiceImpl(orderRepository);
        MenuView menuView = new MenuView(menuService, orderService);

        CSVImporter.csvImport(menuService, "/menu.csv");

        menuView.showMenu();
    }

}
