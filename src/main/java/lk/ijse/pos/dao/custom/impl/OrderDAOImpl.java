package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.util.CrudUtil;
import lk.ijse.pos.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Connection connection, Order order) throws SQLException {
        String sql = "INSERT INTO orders (order_id, date, cus_id, total) VALUES( ?, ?, ?, ? )";
        return CrudUtil.execute(connection, sql, order.getOrder_id(), order.getDate(), order.getCus_id(), order.getTotal());
    }

    @Override
    public boolean update(Connection connection, Order order) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Order> getAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM orders";
        ArrayList<Order> orderList = new ArrayList<Order>();
        ResultSet rst = CrudUtil.execute(connection, sql);

        while (rst.next()) {
            Order orders = new Order(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3).toLocalDate(),
                    rst.getBigDecimal(4)
            );
            orderList.add(orders);
        }
        return orderList;
    }
    @Override
    public boolean delete(Connection connection, String id) throws SQLException {
        return false;
    }

    @Override
    public Order findBy(Connection connection, String id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        ResultSet rs = CrudUtil.execute(connection, sql, id);

        Order orders = new Order();
        System.out.println(orders);
        if (rs.next()) {
            orders.setOrder_id(rs.getString("order_id"));
            orders.setDate(rs.getDate("date").toLocalDate());
            orders.setCus_id(rs.getString("cus_id"));
            orders.setTotal(rs.getBigDecimal("total"));
        }
        return orders;
    }


    @Override
    public String getLastId(Connection connection) throws SQLException {
        String sql = "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1";
        ResultSet rs = CrudUtil.execute(connection, sql);

        String lastId = "no_ids";
        if (rs.next()) {
            lastId = rs.getString(1);
        }
        return lastId;
    }
}
