package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    boolean reduceQty(Connection connection, Item item) throws SQLException;

    boolean exists(Connection connection, String itemCode) throws SQLException;
}
