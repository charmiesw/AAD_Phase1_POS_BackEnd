package lk.ijse.pos.entity;

import jakarta.servlet.annotation.WebServlet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@WebServlet
public class Custom {
    private String order_id;
    private LocalDate date;
    private String cus_id;
    private BigDecimal total;
    private String item_code;
    private BigDecimal unit_price;
    private int qty;
}
