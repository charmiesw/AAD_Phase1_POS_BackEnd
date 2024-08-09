package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderBO {
    boolean placeOrder(Connection connection, OrderDTO orderDTO) throws SQLException;

    String getLastId(Connection connection) throws SQLException;

    OrderDTO getOrderById(Connection connection, String id) throws SQLException;
}
