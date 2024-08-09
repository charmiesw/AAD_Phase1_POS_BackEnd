package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.util.CrudUtil;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Connection connection, Customer entity) throws SQLException {
        String sql = "INSERT INTO customer (id, name, address, contact) VALUES (?,?,?,?)";
        return CrudUtil.execute(connection, sql, entity.getId(), entity.getName(), entity.getAddress(), entity.getContact());
    }

    @Override
    public boolean update(Connection connection, Customer entity) throws SQLException {
        String sql = "UPDATE customer SET name = ?, address = ?, contact = ? WHERE id = ?";
        return CrudUtil.execute(connection, sql, entity.getName(), entity.getAddress(), entity.getContact(), entity.getId());
    }

    @Override
    public ArrayList<Customer> getAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM customer";
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        ResultSet rst = CrudUtil.execute(connection, sql);
        System.out.println(customerList);
        while(rst.next()){
            Customer customer = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );

            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public boolean delete(Connection connection, String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE id = ?";
        return CrudUtil.execute(connection, sql, id);
    }

    @Override
    public Customer findBy(Connection connection, String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE id = ?";
        Customer customer = new Customer();
        ResultSet rst = CrudUtil.execute(connection, sql, id);

        if (rst.next()){
            customer.setId(rst.getString(1));
            customer.setName(rst.getString(2));
            customer.setAddress(rst.getString(3));
            customer.setContact(rst.getInt(4));
        }
        return customer;
    }


    public boolean exists(Connection connection, String cusId) throws SQLException {
        String query = "SELECT 1 FROM customer WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, cusId);
            try (ResultSet rst = pst.executeQuery()) {
                return rst.next();
            }
        }
    }
}
