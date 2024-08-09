package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDetailsBO extends SuperBO {
    OrderDTO getOrderDetailsById(Connection connection, String id) throws SQLException;
}
