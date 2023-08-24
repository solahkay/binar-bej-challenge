package solahkay.binar.challenge.entity;

public class Order {

    private Menu menu;
    private Integer total;
    private Long totalAmount;

    public Order(Menu menu, Integer total) {
        this.menu = menu;
        this.total = total;
        this.totalAmount = ((long) menu.getPrice() * total);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "menu=" + menu +
                ", total=" + total +
                ", totalAmount=" + totalAmount +
                '}';
    }

}
