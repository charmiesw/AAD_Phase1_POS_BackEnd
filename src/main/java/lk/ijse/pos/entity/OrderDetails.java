package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {
    private String order_id;
    private String item_code;
    private BigDecimal unit_price;
    private int qty;

    public OrderDetails(String item_code, BigDecimal unit_price, int qty) {
        this.item_code = item_code;
        this.unit_price = unit_price;
        this.qty = qty;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }
}
