package lk.ijse.pos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.bo.custom.OrderDetailsBO;
import lk.ijse.pos.bo.custom.impl.OrderBOImpl;
import lk.ijse.pos.dto.OrderDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "orders" , urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    OrderBO orderBO = (OrderBO) new OrderBOImpl();
    OrderDetailsBO orderDetailsBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER_DETAILS_BO);

    DataSource connectionPool;

    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            this.connectionPool = (DataSource) envContext.lookup("jdbc/pos_system_aad");
        } catch (NamingException e) {
            throw new ServletException("Cannot find JNDI resource", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String function = req.getParameter("function");
        String orderId = req.getParameter("order_id");

        try (Connection connection = connectionPool.getConnection()) {
            if ("getLastId".equals(function)) {
                String lastId = orderBO.getLastId(connection);
                resp.setContentType("text/plain");
                resp.getWriter().write(lastId);

            } else if ("getById".equals(function) && orderId != null) {
                OrderDTO orderDTO = orderDetailsBO.getOrderDetailsById(connection, orderId);
                System.out.println(orderDTO);
                if (orderDTO != null) {
                    Jsonb jsonb = JsonbBuilder.create();
                    String json = jsonb.toJson(orderDTO);
                    resp.setContentType("application/json");
                    resp.getWriter().write(json);
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Order is not found..!!");
                }

            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request parameters..!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = connectionPool.getConnection()) {

            Jsonb jsonb = JsonbBuilder.create();
            OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
            System.out.println(orderDTO);

            boolean isOrder = orderBO.placeOrder(connection, orderDTO);
            if (isOrder) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Order Saved Successfully..!!");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save the order..!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Transaction failed..!!");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
