package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER_DAO);
    @Override
    public boolean saveCustomer(Connection connection, CustomerDTO dto) throws SQLException {
        return customerDAO.save(connection, new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException {
        ArrayList<Customer> customersList = customerDAO.getAll(connection);
        ArrayList<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customersList) {
            CustomerDTO dto = new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getContact()
            );
            customerDTOList.add(dto);
        }
        return customerDTOList;
    }

    @Override
    public CustomerDTO getCustomerById(Connection connection, String id) throws SQLException {
        Customer customer = customerDAO.findBy(connection, id);
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getContact()
        );
    }

    @Override
    public boolean updateCustomer(Connection connection, CustomerDTO dto) throws SQLException {
        return customerDAO.update(connection, new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact()));

    }

    @Override
    public boolean deleteCustomer(Connection connection, String id) throws SQLException {
        return customerDAO.delete(connection, id);


    }
}
