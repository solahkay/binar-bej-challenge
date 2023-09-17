package solahkay.binar.challenge.service;

import solahkay.binar.challenge.entity.Menu;
import solahkay.binar.challenge.generator.IncrementIDGenerator;
import solahkay.binar.challenge.repository.MenuRepository;
import solahkay.binar.challenge.util.ParsingUtil;

import java.util.List;
import java.util.Optional;

public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void showAllMenu() {
        List<Menu> model = menuRepository.findAll();
        model.forEach(menu -> {
            Long no = menu.getId();
            String itemName = menu.getItemName();
            Integer price = menu.getPrice();

            // print list menu
            System.out.print(no + ". ");
            System.out.print(itemName);
            // 15 character before | printing the rest character left with space
            int lengthBeforeBar = 15 - itemName.length();
            for (int i = 1; i <= lengthBeforeBar; i++) {
                System.out.print(" ");
            }
            System.out.print("| ");
            System.out.print(price);
            System.out.println();
        });
    }

    @Override
    public boolean addMenu(String itemName, Integer price) {
        List<Menu> menus = menuRepository.findAll();

        boolean isExist = menus.stream()
                .anyMatch(menu -> menu.getItemName().equalsIgnoreCase(itemName));
        if (isExist) {
            return false;
        }

        Long id = IncrementIDGenerator.generate();
        Menu menu = new Menu(id, itemName, price);
        try {
            return menuRepository.insert(menu);
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public Optional<Menu> getMenuById(String id) {
        boolean isNumber = ParsingUtil.isLong(id);
        long parsedId;
        if (isNumber) {
            parsedId = Long.parseLong(id);
        } else {
            return Optional.empty();
        }
        return menuRepository.getById(parsedId);
    }

}
