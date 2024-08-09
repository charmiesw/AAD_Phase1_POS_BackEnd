package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.CustomerBOImpl;
import lk.ijse.pos.bo.custom.impl.ItemBOImpl;
import lk.ijse.pos.bo.custom.impl.OrderBOImpl;
import lk.ijse.pos.bo.custom.impl.OrderDetailsBOImpl;

public class BOFactory {
    private static  BOFactory boFactory;
    private BOFactory() {
    }

    public static BOFactory getBOFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER_BO, ITEM_BO, ORDER_BO, ORDER_DETAILS_BO
    }

    public <T extends  SuperBO> T getBO(BOTypes types){
        switch (types){
            case CUSTOMER_BO :
                return (T) new CustomerBOImpl();
            case ITEM_BO:
                return (T) new ItemBOImpl();
            case ORDER_BO:
                return (T) new OrderBOImpl();
            case ORDER_DETAILS_BO:
                return (T) new OrderDetailsBOImpl();

            default:
                return null;
        }
    }
}
