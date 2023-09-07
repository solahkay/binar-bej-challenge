package solahkay.binar.challenge.entity;

import lombok.Data;

@Data
public class Order {

    private final Menu menu;
    private final Integer total;
    private final Long totalAmount;

}
