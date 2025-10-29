package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartTM {
    private String orderId;
    private String itemName;
    private Integer qty;
    private Double price;
    private Double total;
}
