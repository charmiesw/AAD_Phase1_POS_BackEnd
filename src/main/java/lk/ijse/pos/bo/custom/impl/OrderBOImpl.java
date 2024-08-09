package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER_DAO);
    OrderDAO orderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DAO);
    OrderDetailsDAO orderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS_DAO);
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM_DAO);

    public String getLastId(Connection connection) throws SQLException {
        return orderDAO.getLastId(connection);
    }

    @Override
    public boolean placeOrder(Connection connection, OrderDTO dto) throws SQLException {
        try {
            connection.setAutoCommit(false);
            boolean customerExists = customerDAO.exists(connection, dto.getCus_id());
            if (!customerExists) {
                throw new SQLException("Customer ID does not exist : " + dto.getCus_id());
            }

            boolean isOrderSave = orderDAO.save(connection, new Order(dto.getOrder_id(), dto.getCus_id(), dto.getDate(), dto.getTotal()));
            if (!isOrderSave) {
                throw new SQLException("Failed to save the order..!!");
            }

            for (OrderDetailsDTO details : dto.getOrder_list()) {
                boolean itemExists = itemDAO.exists(connection, details.getItem_code());
                if (!itemExists) {
                    throw new SQLException("Item code does not exist : " + details.getItem_code());
                }

                Item item = itemDAO.findBy(connection, details.getItem_code());
                if (item.getQty() < details.getQty()) {
                    throw new SQLException("Insufficient quantity for item : " + details.getItem_code());
                }

                boolean isOrderDetailSaved = orderDetailsDAO.save(connection, new OrderDetails(details.getOrder_id(), details.getItem_code(), details.getUnit_price(), details.getQty()));
                if (!isOrderDetailSaved) {
                    throw new SQLException("Failed to save order detail for item : " + details.getItem_code());
                }

                boolean isQtyReduced = itemDAO.reduceQty(connection, new Item(details.getItem_code(), details.getQty()));
                if (!isQtyReduced) {
                    throw new SQLException("Failed to reduce quantity for item : " + details.getItem_code());
                }
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }
    }

    private boolean updateItemQty(Connection connection, List<OrderDetailsDTO> order_list) throws SQLException {
        for (OrderDetailsDTO dto : order_list) {
            Item item = new Item(dto.getItem_code(), dto.getQty());
            System.out.println("updateItemQty : "+ item);
            if (!itemDAO.reduceQty(connection, item)) {
                return false;
            }
        }
        return true;
    }

    public boolean saveOrderDetails(Connection connection, List<OrderDetailsDTO> orderList) throws SQLException {
        for (OrderDetailsDTO details : orderList) {
            System.out.println("saveOrderDetails orderList : " + orderList);

            boolean itemExists = itemDAO.exists(connection, details.getItem_code());
            if (!itemExists) {
                throw new SQLException("Item code does not exist : " + details.getItem_code());
            }

            boolean isSaved = orderDetailsDAO.save(connection, new OrderDetails(details.getOrder_id(), details.getItem_code(), details.getUnit_price(), details.getQty()));
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public OrderDTO getOrderById(Connection connection, String id) throws SQLException {
        Order orders = orderDAO.findBy(connection, id);
        System.out.println("getOrderById ::"+orders);
        return new OrderDTO(
                orders.getOrder_id(),
                orders.getDate(),
                orders.getCus_id(),
                orders.getTotal()
        );
    }
}
