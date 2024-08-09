package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException;

    ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException;

    CustomerDTO getCustomerById(Connection connection, String id) throws SQLException;

    boolean updateCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException;

    boolean deleteCustomer(Connection connection, String id) throws SQLException;
}
