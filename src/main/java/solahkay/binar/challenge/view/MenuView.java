package solahkay.binar.challenge.view;

import solahkay.binar.challenge.entity.Menu;
import solahkay.binar.challenge.service.MenuService;
import solahkay.binar.challenge.service.OrderService;
import solahkay.binar.challenge.util.InputUtil;
import solahkay.binar.challenge.util.ParsingUtil;
import solahkay.binar.challenge.util.ReceiptUtil;

import java.util.Optional;

public class MenuView {

    private final MenuService menuService;
    private final OrderService orderService;

    public MenuView(MenuService menuService, OrderService orderService) {
        this.menuService = menuService;
        this.orderService = orderService;
    }

    public void showMenu() {
        while (true) {
            printWrapper();
            System.out.println("Selamat datang di BinarFud");
            printWrapper();
            System.out.println();
            System.out.println("Silahkan pilih makanan :");
            menuService.showAllMenu();
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar aplikasi");
            System.out.println();

            String input = InputUtil.input("=>").trim();
            if (menuInputValidation(input)) break;
        }
    }

    public void showQuantityConfirm(Menu menu) {
        printWrapper();
        System.out.println("Berapa pesanan anda");
        printWrapper();

        System.out.print(menu.getItemName());
        // 15 character before quantity, print the rest character left with space
        int lengthBeforeQty = 15 - menu.getItemName().length();
        for (int i = 1; i <= lengthBeforeQty; i++) {
            System.out.print(" ");
        }
        System.out.print("| ");
        System.out.println(menu.getPrice());
        System.out.println();
        System.out.println("input 0 untuk kembali");

        String input = InputUtil.input("qty =>").trim();
        quantityInputValidation(menu, input);
    }

    public void showPaymentConfirm() {
        printWrapper();
        System.out.println("Konfirmasi & Pembayaran");
        printWrapper();
        System.out.println();

        System.out.println(orderService.organizeOrder());

        System.out.println();
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");

        String input = InputUtil.input("=>").trim();
        paymentInputValidation(input);
    }

    public void showReceipt(String orderList) {
        String receipt = ReceiptUtil.createReceipt(orderList);
        System.out.println(receipt);

        ReceiptUtil.writeReceiptToTxtFile(receipt);

        System.exit(0);
    }

    private void printWrapper() {
        System.out.println("==========================");
    }

    private void printNoOptions() {
        System.out.println("Input tidak ada dalam pilihan, coba lagi!");
    }

    private boolean menuInputValidation(String input) {
        Optional<Menu> menuOptional = menuService.getMenuById(input);
        menuOptional.ifPresent(menu -> {
            if (input.equals(menu.getId().toString())) {
                showQuantityConfirm(menu);
            }
        });
        if (!(menuOptional.isPresent())) {
            if (input.equals("99")) {
                showPaymentConfirm();
            } else if (input.equals("0")) {
                return true;
            } else {
                printNoOptions();
                System.out.println();
            }
        }
        return false;
    }

    private void quantityInputValidation(Menu menu, String input) {
        boolean isNumber = ParsingUtil.isInteger(input);
        if (isNumber) {
            int quantity = Integer.parseInt(input);
            if (quantity > 0) {
                orderService.addOrUpdateOrder(menu, quantity);
            } else if (quantity == 0) {
                // back to menu
            } else {
                System.out.println("Minimal 1 jumlah pesanan!");
                System.out.println();
                showQuantityConfirm(menu);
            }
        } else {
            System.out.println("Input melebihi batas atau input tidak valid!");
            System.out.println();
            showQuantityConfirm(menu);
        }
    }

    private void paymentInputValidation(String input) {
        switch (input) {
            case "1":
                showReceipt(orderService.organizeOrder());
                break;
            case "2":
                // back to main menu
                break;
            case "0":
                System.exit(0);
                break;
            default:
                printNoOptions();
                System.out.println();
                showPaymentConfirm();
                break;
        }
    }

}
