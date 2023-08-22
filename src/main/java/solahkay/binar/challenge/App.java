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

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        MenuRepository menuRepository = new MenuRepositoryImpl();
        OrderRepository orderRepository = new OrderRepositoryImpl();

        OrderService orderService = new OrderServiceImpl(orderRepository);
        MenuService menuService = new MenuServiceImpl(menuRepository);

        MenuView menuView = new MenuView(menuService, orderService);

        CSVImporter.csvImport(menuService, "src/main/resources/menu.csv");

        menuView.showMenu();

    }

}
