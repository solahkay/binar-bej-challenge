package solahkay.binar.challenge.entity;

public class Menu {

    private Long id;
    private String itemName;
    private Integer price;

    public Menu() {
    }

    public Menu(Long id, String itemName, Integer price) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuList{" +
                "id=" + id +
                ", menu='" + itemName + '\'' +
                ", price=" + price +
                '}';
    }

}
