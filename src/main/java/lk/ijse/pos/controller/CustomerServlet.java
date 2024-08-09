package lk.ijse.pos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lombok.SneakyThrows;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

@WebServlet(name = "customer",urlPatterns = "/customer",loadOnStartup = 3)
public class CustomerServlet extends HttpServlet {
    CustomerBO customerBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.CUSTOMER_BO);
    DataSource connectionPool;

    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/pos_system_aad");
            this.connectionPool = dataSource;
        } catch (NamingException e) {
            throw new ServletException("Cannot find JNDI resource", e);
        }
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getParameterMap().forEach((key, value) -> {
            System.out.println("Parameter : " + key + " = " + String.join(", ", value));
        });

        String function = req.getParameter("function");
        System.out.println("Function parameter : " + function);

        if (function != null) {
            if (function.equals("someValue")) {
                handleGetAllCustomers(req, resp);
            } else if (function.equals("getById")) {
                handleGetCustomerById(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid function parameter..!!");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Function parameter is missing..!!");
        }
    }

    private void handleGetAllCustomers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = connectionPool.getConnection()) {
            ArrayList<CustomerDTO> customerDTOList = customerBO.getAllCustomers(connection);
            Jsonb jsonb = JsonbBuilder.create();
            String json = jsonb.toJson(customerDTOList);
            resp.getWriter().write(json);
        } catch (JsonbException | IOException | SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private void handleGetCustomerById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID parameter is missing..!!");
            return;
        }

        try (Connection connection = connectionPool.getConnection()) {
            CustomerDTO customerDTO = customerBO.getCustomerById(connection, id);
            Jsonb jsonb = JsonbBuilder.create();
            String json = jsonb.toJson(customerDTO);
            resp.getWriter().write(json);
        } catch (JsonbException | IOException | SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = connectionPool.getConnection()) {
            Jsonb jsonb = JsonbBuilder.create();

            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            System.out.println(customerDTO);

            boolean isSaved = customerBO.saveCustomer(connection, customerDTO);
            if (isSaved) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Customer Saved Successfully..!!");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save the customer..!!");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            resp.sendError(HttpServletResponse.SC_CONFLICT, "Duplicate values. Please check again..!!");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request..!!");
        }
    }

    @SneakyThrows
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try (Connection connection = connectionPool.getConnection()){
            boolean isDeleted = customerBO.deleteCustomer(connection,id);
            if (isDeleted){
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Customer Deleted Successfully..!!");
            }else{
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Failed to delete customer..!!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = connectionPool.getConnection()) {
            Jsonb jsonb = JsonbBuilder.create();

            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            System.out.println(customerDTO);

            boolean isUpdated = customerBO.updateCustomer(connection, customerDTO);
            if (isUpdated) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Customer Updated Successfully..!!");

            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update the customer..!!");
            }


        } catch (SQLIntegrityConstraintViolationException e) {
            resp.sendError(HttpServletResponse.SC_CONFLICT, "Duplicate values. Please check again..!!");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request..!!");
        }
    }
}
