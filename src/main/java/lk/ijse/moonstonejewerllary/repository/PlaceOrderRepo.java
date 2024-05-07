package lk.ijse.moonstonejewerllary.repository;

import lk.ijse.moonstonejewerllary.db.DbConnection;
import lk.ijse.moonstonejewerllary.model.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {
    public static boolean orderPlace(PlaceOrder placeOrder) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOderSaved = OrderRepo.saveOrder(placeOrder.getOrder());
            if (isOderSaved) {
                boolean isOrderDetailsSaved = OrderDetailsRepo.saveOrderDetails(placeOrder.getOrderDetails());
                if (isOrderDetailsSaved) {
                    boolean isItemUpdated = ItemRepo.updateItemQty(placeOrder.getOrderDetails());
                    if (isItemUpdated) {
                        boolean isPaymentSaved = PaymentRepo.savePayment(placeOrder.getPayment());
                        if (isPaymentSaved) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        }catch (SQLException e) {
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
