package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDetailsDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;

    }

    public enum DAOTypes{
        CUSTOMER_DAO, ITEM_DAO, ORDER_DAO, ORDER_DETAILS_DAO
    }

    public <T extends SuperDAO> T getDAO (DAOTypes types){
        switch (types){
            case CUSTOMER_DAO:
                return (T) new CustomerDAOImpl();
            case ITEM_DAO:
                return (T) new ItemDAOImpl();
            case ORDER_DAO:
                return (T) new OrderDAOImpl();
            case ORDER_DETAILS_DAO:
                return (T) new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }
}
