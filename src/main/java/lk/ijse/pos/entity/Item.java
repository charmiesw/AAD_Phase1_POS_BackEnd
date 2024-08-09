package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private String code;
    private String name;
    private int qty;
    private BigDecimal price;

    public Item(String code, int qty) {
        this.code = code;
        this.qty = qty;

    }
}
