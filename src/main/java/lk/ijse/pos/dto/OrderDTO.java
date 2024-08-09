package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    String order_id;
    LocalDate date;
    String cus_id;
    BigDecimal discount;
    BigDecimal total;
    List<OrderDetailsDTO> order_list;

    public OrderDTO(String order_id, LocalDate date, String cus_id, BigDecimal total) {
        this.order_id = order_id;
        this.date = date;
        this.cus_id = cus_id;
        this.total = total;
    }

    public OrderDTO(String order_id, LocalDate date, String cus_id) {
        this.order_id = order_id;
        this.date = date;
        this.cus_id = cus_id;
    }
}
