package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM_DAO);
    @Override
    public boolean saveItem(Connection connection, ItemDTO dto) throws SQLException {
        return itemDAO.save(connection, new Item(dto.getCode(), dto.getName(), dto.getQty(), dto.getPrice()));
    }

    @Override
    public boolean updateItem(Connection connection, ItemDTO dto) throws SQLException {
        return itemDAO.update(connection, new Item(dto.getCode(), dto.getName(), dto.getQty(), dto.getPrice()));

    }

    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException {
        ArrayList<Item> itemList = itemDAO.getAll(connection);

        ArrayList<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();

        for(Item item : itemList){
            ItemDTO dto = new ItemDTO(
                    item.getCode(),
                    item.getName(),
                    item.getQty(),
                    item.getPrice()
            );

            itemDTOList.add(dto);
        }
        return itemDTOList;
    }

    @Override
    public ItemDTO getItemByCode(Connection connection, String code) throws SQLException {
        Item item = itemDAO.findBy(connection, code);
        return new ItemDTO(
                item.getCode(),
                item.getName(),
                item.getQty(),
                item.getPrice()
        );
    }

    @Override
    public boolean deleteItem(Connection connection, String code) throws SQLException {
        return itemDAO.delete(connection, code);
    }
}
