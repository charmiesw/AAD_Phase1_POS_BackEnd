package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderDetailsBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dao.custom.impl.OrderDAOImpl;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.OrderDetails;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDAO orderDAO = (OrderDAO) new OrderDAOImpl();
    OrderDetailsDAO orderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS_DAO);

    @Override
    public OrderDTO getOrderDetailsById(Connection connection, String id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    LocalDate date = rs.getDate("date").toLocalDate();
                    BigDecimal total = rs.getBigDecimal("total");

                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setOrder_id(rs.getString("order_id"));
                    orderDTO.setCus_id(rs.getString("cus_id"));
                    orderDTO.setDate(date);
                    orderDTO.setTotal(total);

                    List<OrderDetails> orderDetailList = orderDetailsDAO.getAllById(connection, id);
                    List<OrderDetailsDTO> orderDetailDTOList = new ArrayList<>();
                    for (OrderDetails orderDetails : orderDetailList) {
                        orderDetailDTOList.add(new OrderDetailsDTO(
                                orderDetails.getItem_code(),
                                orderDetails.getUnit_price(),
                                orderDetails.getQty()
                        ));
                    }
                    orderDTO.setOrder_list(orderDetailDTOList);
                    return orderDTO;
                } else {
                    return null;
                }
            }
        }
    }
}
