package solahkay.binar.challenge.view;

import solahkay.binar.challenge.entity.Menu;
import solahkay.binar.challenge.service.MenuService;
import solahkay.binar.challenge.service.OrderService;
import solahkay.binar.challenge.util.InputUtil;
import solahkay.binar.challenge.util.ParsingUtil;
import solahkay.binar.challenge.util.ReceiptUtil;

public class MenuView {

    private final MenuService menuService;
    private final OrderService orderService;


    public MenuView(MenuService menuService, OrderService orderService) {
        this.menuService = menuService;
        this.orderService = orderService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("==========================");
            System.out.println("Selamat datang di BinarFud");
            System.out.println("==========================");
            System.out.println();
            System.out.println("Silahkan pilih makanan :");
            menuService.showMenu();
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar aplikasi");
            System.out.println();

            String input = InputUtil.input("=>").trim();

            Menu menu = menuService.getMenuById(input);

            if (menu != null) {
                if (input.equals(menu.getId().toString())) {
                    showQuantityConfirm(menu);
                }
            } else {
                if (input.equals("99")) {
                    showPaymentConfirm();
                } else if (input.equals("0")) {
                    break;
                } else {
                    System.out.println("Input tidak ada dalam pilihan, coba lagi!");
                    System.out.println();
                }
            }

        }
    }

    public void showQuantityConfirm(Menu menu) {
        System.out.println("==========================");
        System.out.println("Berapa pesanan anda");
        System.out.println("==========================");

        System.out.print(menu.getItemName());
        // 15 character before quantity, print the rest character left with space
        for (int i = 1; i <= (15 - menu.getItemName().length()); i++) {
            System.out.print(" ");
        }
        System.out.print("| ");
        System.out.println(menu.getPrice());
        System.out.println();

        System.out.println("input 0 untuk kembali");
        String input = InputUtil.input("qty =>").trim();

        boolean isNumber = ParsingUtil.isNumber(input);
        if (isNumber) {
            orderService.addOrUpdateOrder(menu, Integer.parseInt(input));
        } else if (input.equals("0")) {
            // back to menu
        } else {
            System.out.println("Input tidak ada dalam pilihan, coba lagi!");
        }
    }

    public void showPaymentConfirm() {
        System.out.println("==========================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("==========================");
        System.out.println();

        System.out.println(orderService.orderList());

        System.out.println();
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");

        String input = InputUtil.input("=>").trim();

        if (input.equals("1")) {
            showReceipt(orderService.orderList());
        } else if (input.equals("2")) {
            // back to menu
        } else if (input.equals("0")) {
            System.exit(0);
        } else {
            System.out.println("Input tidak ada dalam pilihan, coba lagi!");
            System.out.println();
            showPaymentConfirm();
        }
    }

    public void showReceipt(String orderList) {
        String receipt = ReceiptUtil.createReceipt(orderList);
        System.out.println(receipt);
        ReceiptUtil.writeReceiptToTxtFile(receipt);

        System.exit(0);
    }

}
